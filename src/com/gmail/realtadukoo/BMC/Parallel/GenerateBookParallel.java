package com.gmail.realtadukoo.BMC.Parallel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import com.gmail.realtadukoo.BMC.EnumBible;
import com.gmail.realtadukoo.BMC.EnumBibleChps;
import com.gmail.realtadukoo.BMC.File.BookFiles;

public class GenerateBookParallel{
	
	// This here for use for benchmarking the largest book, Psalms
	public static void generatePsalms(int numThreads){
		MyQueue<ChpWorkInfo> todo, done;
		todo = new MyQueue<ChpWorkInfo>(150);
		done = new MyQueue<ChpWorkInfo>(150);
		
		// Start Worker threads
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < numThreads; i++){
			ChpWorker worker = new ChpWorker(todo, done);
			threads.add(new Thread(worker));
			threads.get(i).start();
		}
		
		generateWholeBook(EnumBible.PSALMS, todo, done, numThreads);
		
		// Send terminate infos
		for(int i = 0; i < numThreads; i++){
			try{
				todo.enqueue(new ChpWorkInfo(null, -1));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		// End Worker threads
		for(int i = 0; i < numThreads; i++){
			try{
				threads.get(i).join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void generateWholeBible(int numThreads){
		MyQueue<ChpWorkInfo> todo, done;
		todo = new MyQueue<ChpWorkInfo>(150);
		done = new MyQueue<ChpWorkInfo>(150);
		
		// Start Worker threads
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i = 0; i < numThreads; i++){
			ChpWorker worker = new ChpWorker(todo, done);
			threads.add(new Thread(worker));
			threads.get(i).start();
		}
		
		// Generate All Books
		for(int i = 1; i <= 66; i++){
			EnumBible book = EnumBible.fromInt(i);
			generateWholeBook(book, todo, done, numThreads);
		}
		
		// Send terminate infos
		for(int i = 0; i < numThreads; i++){
			try{
				todo.enqueue(new ChpWorkInfo(null, -1));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		// End Worker threads
		for(int i = 0; i < numThreads; i++){
			try{
				threads.get(i).join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void generateWholeBook(EnumBible book, MyQueue<ChpWorkInfo> todo, MyQueue<ChpWorkInfo> done, int numThreads){
		// Generate an entire book of the Bible

		// Send out work
		int chps = EnumBibleChps.fromString(book.book()).getChps().length;
		for(int j = 1; j <= chps; j++){
			//System.out.println("Making Work for " + book.book() + " Chp " + j);
			ChpWorkInfo work = new ChpWorkInfo(book, j);
			try{
				todo.enqueue(work);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
		// Receive work
		Properties bookFile = new Properties();
		int bookNum = 1, pageNum = 1;
		int chpDone = 0;
		ArrayList<ChpWorkInfo> works = new ArrayList<ChpWorkInfo>();
		for(int j = 1; j <= chps; j++){
			try{
				//System.out.println("Trying to Receive Stuff...");
				ChpWorkInfo info = done.dequeue();
				//System.out.println("Received " + info.getBook().book() + " Chp " + info.getChp());
				if(info.getChp() == chpDone + 1){
					for(String page: info.getPages()){
						bookFile.put("Book" + bookNum + "Page" + pageNum, page);
						pageNum++;
						if(pageNum > 50){
							bookNum++;
							pageNum = 1;
						}
					}
					//System.out.println("Added " + info.getBook().book() + " Chp " + info.getChp());
					chpDone++;
					while(works.size() > 0 && works.get(0).getChp() == chpDone + 1){
						info = works.remove(0);
						for(String page: info.getPages()){
							bookFile.put("Book" + bookNum + "Page" + pageNum, page);
							pageNum++;
							if(pageNum > 50){
								bookNum++;
								pageNum = 1;
							}
						}
						//System.out.println("Added " + info.getBook().book() + " Chp " + info.getChp());
						chpDone++;
					}
				}else{
					boolean infoInArray = false;
					for(int k = 0; k < works.size(); k++){
						if(works.get(k).getChp() > info.getChp()){
							works.add(k, info);
							infoInArray = true;
							break;
						}
					}
					if(!infoInArray){
						works.add(info);
					}
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		try{
			BookFiles.saveMinecraftBook(bookFile, book, true, numThreads);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
