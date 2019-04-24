package com.gmail.realtadukoo.BMC;

public class BMCMain{
	
	public static void main(String[] args){
		long before, after, elapsedBible, elapsedPsalms;
		
		// Benchmark the entire Bible
		before = System.currentTimeMillis();
		
		GenerateBook.generateWholeBible();
		
		after = System.currentTimeMillis();
		
		elapsedBible = after - before;
		
		// Benchmark just Psalms
		before = System.currentTimeMillis();
		
		GenerateBook.generateWholeBook(EnumBible.PSALMS);
		
		after = System.currentTimeMillis();
		
		elapsedPsalms = after - before;
		
		System.out.println("Entire Bible Time taken: " + elapsedBible + "ms");
		System.out.println("Psalms Time Taken: " + elapsedPsalms + "ms");
	}
}
