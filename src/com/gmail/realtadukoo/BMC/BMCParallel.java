package com.gmail.realtadukoo.BMC;

import java.io.File;

import com.gmail.realtadukoo.BMC.Parallel.GenerateBookParallel;

public class BMCParallel{
	
	public static void main(String[] args){
		int minThreads = 1;
		int maxThreads = 10;
		long before, after, elapsedParBible, elapsedParPsalms;
		
		for(int i = minThreads; i <= maxThreads; i++){
			System.out.println("Testing parallel with " + i + " threads...");
			
			File folder = new File("resource/Bible/Minecraft/parallel/" + i + "/");
			folder.mkdirs();
			
			// Test Parallel on Entire Bible
			before = System.currentTimeMillis();
			
			GenerateBookParallel.generateWholeBible(i);
			
			after = System.currentTimeMillis();
			
			elapsedParBible = after - before;
			
			int parBibleSec = (int) (elapsedParBible/1000);
			int parBibleMin = parBibleSec/60;
			parBibleSec = parBibleSec%60;
			long parBibleMS = elapsedParBible%1000;
			
			System.out.println("Parallel (" + i + ") Bible Time Taken: " + parBibleMin + " mins " + parBibleSec + " s " + 
					parBibleMS + " ms");
			
			// Delete Psalms from parallel to prepare for New Sequential on Psalms
			File psalmsPar = new File("resource/Bible/Minecraft/parallel/" + i + "/Psalms.properties");
			psalmsPar.delete();
			
			// Test Parallel on Psalms
			before = System.currentTimeMillis();
			
			GenerateBookParallel.generatePsalms(i);
			
			after = System.currentTimeMillis();
			
			elapsedParPsalms = after - before;
			
			int parPsalmsSec = (int) (elapsedParPsalms/1000);
			int parPsalmsMin = parPsalmsSec/60;
			parPsalmsSec = parPsalmsSec%60;
			long parPsalmsMS = elapsedParPsalms%1000;
			
			System.out.println("Parallel (" + i + ") Psalms Time Taken: " + parPsalmsMin + " mins " + parPsalmsSec + " s " + 
					parPsalmsMS + " ms");
		}
	}
}
