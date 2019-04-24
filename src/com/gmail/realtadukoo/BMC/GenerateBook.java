package com.gmail.realtadukoo.BMC;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.gmail.realtadukoo.BMC.File.BookFiles;
import com.gmail.realtadukoo.BMC.File.VerseFiles;

public class GenerateBook{
	
	public static void generateWholeBible(){
		for(int i = 1; i <= 66; i++){
			generateWholeBook(EnumBible.fromInt(i));
		}
	}
	
	public static void generateWholeBook(EnumBible book){
		for(int i = 1; i <= EnumBibleChps.fromString(book.book()).getChps().length; i++){
			generateWholeChapter(book, i);
		}
		System.out.println(book.book() + " Done");
	}
	
	public static void generateWholeChapter(EnumBible book, int chp){
		int bookNum = 1, pageNum = 1;
		if(chp != 1){
			String bookAndPageEnd = "";
			try{
				bookAndPageEnd = BookFiles.loadMinecraftBook(book).getProperty("Ch" + (chp-1));
			}catch(IOException e){
				e.printStackTrace();
			}
			bookAndPageEnd = bookAndPageEnd.replace("B", "");
			String[] bookPageSplit = bookAndPageEnd.split("P");
			bookNum = Integer.parseInt(bookPageSplit[0]);
			pageNum = Integer.parseInt(bookPageSplit[1]);
			if(pageNum == 50){
				bookNum++;
				pageNum = 1;
			}else{
				pageNum++;
			}
		}
		String page = "Chapter " + chp + "\n";
		
		ArrayList<String> verses = new ArrayList<String>();
		for(int i = 1; i <= EnumBibleChps.fromString(book.book()).getNum(chp); i++){
			Properties bookFile = VerseFiles.getBook(book);
			verses.add(VerseFiles.getVerse(bookFile, chp, i));
		}
		for(int i = 0; i < verses.size(); i++){
			if(page.length() + verses.get(i).length() + 9 <= 256){
				if(i == 0){
					page += verses.get(i);
				}else{
					page += " &l" + (i + 1) + "&r" + verses.get(i);
				}
			}else{
				if(pageNum == 50){
					BookFiles.addToMinecraftBook(book, bookNum, pageNum, page);
					bookNum++;
					pageNum = 1;
					page = "Chapter " + chp + " Cont.\n" + verses.get(i);
				}else{
					String[] verseSplit = verses.get(i).split(" ");
					if(page.length() + verseSplit[0].length() + 9 > 256){
						BookFiles.addToMinecraftBook(book, bookNum, pageNum, page);
						page = "&l" + (i + 1) + "&r" + verses.get(i);
						if(pageNum == 50){
							bookNum++;
							pageNum = 1;
							page = "Chapter " + chp + " Cont.\n" + page;
						}else{
							pageNum++;
						}
					}else{
						page += " &l" + (i + 1) + "&r" + verseSplit[0];
						for(int j = 1; j < verseSplit.length; j++){
							if(page.length() + verseSplit[j].length() + 1 > 256){
								BookFiles.addToMinecraftBook(book, bookNum, pageNum, 
										page);
								page = verseSplit[j];
								if(pageNum == 50){
									bookNum++;
									pageNum = 1;
									page = "Chapter " + chp + " Cont.\n" + page;
								}else{
									pageNum++;
								}
							}else{
								page += " " + verseSplit[j];
							}
						}
					}
				}
			}
		}
		BookFiles.addToMinecraftBook(book, bookNum, pageNum, page);
		BookFiles.addToMinecraftBook(book, chp, bookNum, pageNum);
		//System.out.println(book.book() + " Chapter " + chp + " Done");
	}
}