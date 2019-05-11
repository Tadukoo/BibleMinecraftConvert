package com.gmail.realtadukoo.BMC.Parallel;

import com.gmail.realtadukoo.BMC.NewSequential.GenerateBookNewSequential;

public class ChpWorker implements Runnable{	
	protected MyQueue<ChpWorkInfo> todo, done;
	//private Thread thread;
	
	public ChpWorker(MyQueue<ChpWorkInfo> todo, MyQueue<ChpWorkInfo> done) {
		this.todo = todo;
		this.done = done;
	}

	@Override
	public void run() {
		boolean cont = true;
		while(cont){
			try{
				// Get the thread's work order from the todo queue
				ChpWorkInfo work = todo.dequeue();
				if(work.getChp() == -1){
					cont = false;
				}else{
					// Do some work
					work.setPages(GenerateBookNewSequential.generateWholeChapter(work.getBook(), work.getChp()));
					// Put the generated chapter into the done queue
					done.enqueue(work);
				}
			}catch(InterruptedException e){
				// stuff?
				e.printStackTrace();
			}
		}
	}
}
