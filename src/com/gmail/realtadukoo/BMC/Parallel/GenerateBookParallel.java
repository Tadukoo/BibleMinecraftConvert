package com.gmail.realtadukoo.BMC.Parallel;

import java.util.ArrayList;

import com.gmail.realtadukoo.BMC.EnumBible;
import com.gmail.realtadukoo.BMC.EnumBibleChps;
import com.gmail.realtadukoo.BMC.File.BookFiles;

public class GenerateBookParallel{
	
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
		
		for(int i = 1; i <= 66; i++){
			// Generate an entire book of the Bible
			
			// Send out work
			EnumBible book = EnumBible.fromInt(i);
			int chps = EnumBibleChps.fromString(book.book()).getChps().length;
			for(int j = 1; j <= chps; j++){
				ChpWorkInfo work = new ChpWorkInfo(book, j);
				try{
					todo.enqueue(work);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			
			// Receive work
			int chpDone = 0;
			ArrayList<ChpWorkInfo> works = new ArrayList<ChpWorkInfo>();
			for(int j = 1; j <= chps; j++){
				try{
					ChpWorkInfo info = done.dequeue();
					if(info.getChp() == chpDone + 1){
						for(String page: info.getPages()){
							BookFiles.addToMinecraftBook(book, i, info.getChp(), page);
						}
						chpDone++;
						while(works.get(0).getChp() == chpDone + 1){
							info = works.remove(0);
							for(String page: info.getPages()){
								BookFiles.addToMinecraftBook(book, i, info.getChp(), page);
							}
							chpDone++;
						}
					}else{
						boolean infoInArray = false;
						for(int k = 0; k < works.size(); k++){
							if(works.get(k).getChp() > info.getChp()){
								works.add(k, info);
								infoInArray = true;
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
}
