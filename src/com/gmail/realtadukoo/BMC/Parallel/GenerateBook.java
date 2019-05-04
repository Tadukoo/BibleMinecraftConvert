package com.gmail.realtadukoo.BMC.Parallel;

import com.gmail.realtadukoo.BMC.EnumBible;
import com.gmail.realtadukoo.BMC.EnumBibleChps;

public class GenerateBook{
	
	public static void generateWholeBible(){
		MyQueue<ChpWorkInfo> todo, done;
		todo = new MyQueue<ChpWorkInfo>(150);
		
		for(int i = 1; i <= 66; i++){
			// Generate an entire book of the Bible
			
			// TODO: Start Worker threads
			
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
			
			// TODO: End Worker threads
		}
	}
}
