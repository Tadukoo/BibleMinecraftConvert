package com.gmail.realtadukoo.BMC;

import com.gmail.realtadukoo.BMC.Parallel.GenerateBookParallel;

public class BMCMain{
	
	public static void main(String[] args){
		long before, after, elapsedBible, elapsedPsalms, elapsedParBible;
		
		/*
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
		*/
		
		// Test Parallel on Entire Bible
		before = System.currentTimeMillis();
		
		GenerateBookParallel.generateWholeBible(4);
		
		after = System.currentTimeMillis();
		
		elapsedParBible = after - before;
		
		int parBibleSec = (int) (elapsedParBible/1000);
		int parBibleMin = parBibleSec/60;
		parBibleSec = parBibleSec%60;
		long parBibleMS = elapsedParBible%1000;
		
		System.out.println("Parallel Bible Time Taken: " + parBibleMin + " mins " + parBibleSec + " s " + parBibleMS + " ms");
	}
}
