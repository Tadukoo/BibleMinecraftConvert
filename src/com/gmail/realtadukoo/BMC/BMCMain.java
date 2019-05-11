package com.gmail.realtadukoo.BMC;

import java.io.File;

import com.gmail.realtadukoo.BMC.NewSequential.GenerateBookNewSequential;

public class BMCMain{
	
	public static void main(String[] args){
		long before, after, elapsedBible, elapsedPsalms, elapsedNewSeqBible, elapsedNewSeqPsalms;
		
		// Create file directories
		String basePath = "resource/Bible/Minecraft/";
		File folder = new File(basePath);
		folder.mkdirs();
		folder = new File(basePath + "old/");
		folder.mkdirs();
		
		// Benchmark the entire Bible
		before = System.currentTimeMillis();
		
		GenerateBook.generateWholeBible();
		
		after = System.currentTimeMillis();
		
		elapsedBible = after - before;
		
		int bibleSec = (int) (elapsedBible/1000);
		int bibleMin = bibleSec/60;
		bibleSec = bibleSec%60;
		long bibleMS = elapsedBible%1000;
		
		System.out.println("Entire Bible Time taken: " + bibleMin + " mins " + bibleSec + " s " + bibleMS + " ms");
		
		// Delete Psalms from new sequential to prepare for New Sequential on Psalms
		File psalms = new File("resource/Bible/Minecraft/old/Psalms.properties");
		psalms.delete();
		
		// Benchmark just Psalms
		before = System.currentTimeMillis();
		
		GenerateBook.generateWholeBook(EnumBible.PSALMS);
		
		after = System.currentTimeMillis();
		
		elapsedPsalms = after - before;
		
		int psalmsSec = (int) (elapsedPsalms/1000);
		int psalmsMin = psalmsSec/60;
		psalmsSec = psalmsSec%60;
		long psalmsMS = elapsedPsalms%1000;
		
		System.out.println("Psalms Time Taken: " + psalmsMin + " mins " + psalmsSec + " s " + psalmsMS + " ms");
		
		// Test New Sequential on Entire Bible
		before = System.currentTimeMillis();
		
		GenerateBookNewSequential.generateWholeBible();
		
		after = System.currentTimeMillis();
		
		elapsedNewSeqBible = after - before;
		
		int newSeqBibleSec = (int) (elapsedNewSeqBible/1000);
		int newSeqBibleMin = newSeqBibleSec/60;
		newSeqBibleSec = newSeqBibleSec%60;
		long newSeqBibleMS = elapsedNewSeqBible%1000;
		
		System.out.println("New Sequential Bible Time Taken: " + newSeqBibleMin + " mins " + newSeqBibleSec + " s " + 
				newSeqBibleMS + " ms");
		
		// Delete Psalms from new sequential to prepare for New Sequential on Psalms
		File psalmsNewSeq = new File("resource/Bible/Minecraft/Psalms.properties");
		psalmsNewSeq.delete();
		
		// Test New Sequential on Psalms
		before = System.currentTimeMillis();
		
		GenerateBookNewSequential.generateWholeBook(EnumBible.PSALMS);
		
		after = System.currentTimeMillis();
		
		elapsedNewSeqPsalms = after - before;
		
		int newSeqPsalmsSec = (int) (elapsedNewSeqPsalms/1000);
		int newSeqPsalmsMin = newSeqPsalmsSec/60;
		newSeqPsalmsSec = newSeqPsalmsSec%60;
		long newSeqPsalmsMS = elapsedNewSeqPsalms%1000;
		
		System.out.println("New Sequential Psalms Time Taken: " + newSeqPsalmsMin + " mins " + newSeqPsalmsSec + " s " + 
				newSeqPsalmsMS + " ms");
	}
}
