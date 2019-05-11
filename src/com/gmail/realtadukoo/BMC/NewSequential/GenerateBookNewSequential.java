package com.gmail.realtadukoo.BMC.NewSequential;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.gmail.realtadukoo.BMC.EnumBible;
import com.gmail.realtadukoo.BMC.EnumBibleChps;
import com.gmail.realtadukoo.BMC.File.BookFiles;
import com.gmail.realtadukoo.BMC.File.VerseFiles;

public class GenerateBookNewSequential{
	
	public static void generateWholeBible(){
		for(int i = 1; i <= 66; i++){
			generateWholeBook(EnumBible.fromInt(i));
		}
	}
	
	public static void generateWholeBook(EnumBible book){
		Properties bookFile = new Properties();
		int bookNum = 1, pageNum = 1;
		for(int i = 1; i <= EnumBibleChps.fromString(book.book()).getChps().length; i++){
			ArrayList<String> pages = generateWholeChapter(book, i);
			for(String page: pages){
				bookFile.put("Book" + bookNum + "Page" + pageNum, page);
				pageNum++;
				if(pageNum > 50){
					bookNum++;
					pageNum = 1;
				}
			}
		}
		try{
			BookFiles.saveMinecraftBook(bookFile, book, false, 0);
		}catch(IOException e){
			e.printStackTrace();
		}
		//System.out.println(book.book() + " Done");
	}
	
	public static ArrayList<String> generateWholeChapter(EnumBible book, int chp){
		ArrayList<String> pages = new ArrayList<String>();
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
				String[] verseSplit = verses.get(i).split(" ");
				if(page.length() + verseSplit[0].length() + 9 > 256){
					pages.add(page);
					page = "&l" + (i + 1) + "&r" + verses.get(i);
				}else{
					page += " &l" + (i + 1) + "&r" + verseSplit[0];
					for(int j = 1; j < verseSplit.length; j++){
						if(page.length() + verseSplit[j].length() + 1 > 256){
							pages.add(page);
							//BookFiles.addToMinecraftBook(book, bookNum, pageNum, page);
							page = verseSplit[j];
						}else{
							page += " " + verseSplit[j];
						}
					}
				}
			}
		}
		pages.add(page);
		return pages;
		//System.out.println(book.book() + " Chapter " + chp + " Done");
	}
}