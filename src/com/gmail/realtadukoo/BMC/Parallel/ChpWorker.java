package com.gmail.realtadukoo.BMC.Parallel;

import java.util.ArrayList;

import com.gmail.realtadukoo.BMC.GenerateBook;

public class ChpWorker implements Runnable{	
	protected MyQueue<ChpWorkInfo> todo, done;
	//private Thread thread;
	
	public ChpWorker(MyQueue<ChpWorkInfo> todo, MyQueue<ChpWorkInfo> done) {
		this.todo = todo;
		this.done = done;
	}

	@Override
	public void run() {
		//thread = Thread.currentThread();
		
		try {
			// Get the thread's work order from the todo queue
			ChpWorkInfo work = todo.dequeue();
			// Do some work
			work.setPages(GenerateBook.generateWholeChapter(work.getBook(), work.getChp()));
			// Put the generated chapter into the done queue
			done.enqueue(work);
		} catch(InterruptedException e) {
			// stuff?
			e.printStackTrace();
		}
		
	}

}
