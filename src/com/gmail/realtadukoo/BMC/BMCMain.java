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
		
		int bibleSec = (int) (elapsedBible/1000);
		int bibleMin = bibleSec/60;
		bibleSec = bibleSec%60;
		long bibleMS = elapsedBible%1000;
		
		int psalmsSec = (int) (elapsedPsalms/1000);
		int psalmsMin = psalmsSec/60;
		psalmsSec = psalmsSec%60;
		long psalmsMS = elapsedPsalms%1000;
		
		System.out.println("Entire Bible Time taken: " + bibleMin + " mins " + bibleSec + " s " + bibleMS + " ms");
		System.out.println("Psalms Time Taken: " + psalmsMin + " mins " + psalmsSec + " s " + psalmsMS + " ms");
	}
}
