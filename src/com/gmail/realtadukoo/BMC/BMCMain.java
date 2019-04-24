package com.gmail.realtadukoo.BMC;

public class BMCMain{
	
	public static void main(String[] args){
		long before, after, elapsed;
		
		before = System.currentTimeMillis();
		
		//GenerateBook.generateWholeBible();
		GenerateBook.generateWholeBook(EnumBible.PSALMS);
		
		after = System.currentTimeMillis();
		
		elapsed = after - before;
		
		System.out.println("Time taken: " + elapsed + "ms");
	}
}
