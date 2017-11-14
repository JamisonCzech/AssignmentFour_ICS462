/*
 * ICS 462 - Assignment 4
 * This assignment implements the FIFO and LRU page replacement algorithms.
 * It generates a random page-reference string with range from 0 to 9 and Applies the 
 * random page-reference string to each algorithm, and records the number of page faults 
 * incurred by each algorithm. Implement the replacement algorithms.
 *
 */

import java.util.Random;

public class Main {

	public static void main(String[] args) {

		String[] referencePage;
		String referenceString;

		// Input for page reference string
		referenceString = "0,7,0,1,2,0,8,9,0,3,0,4,5,6,7,0,8,9,1,2";		

		// Generate random page reference string
		referenceString = GetRandomPageReference();

		// Convert the string into an array
		System.out.println(referenceString);
		referenceString = referenceString.replaceAll(",", "");
		referencePage = new String[referenceString.length()];
		for(int i = 0; i < referenceString.length(); i++)
		{ 	
			referencePage[i] = referenceString.substring(i, i+1);
		}

		// Run FIFO and LRU algorithm for page frames ranging from 1 - 7
		for(int i= 1; i<=7; i++){
			System.out.println();
			System.out.println("For " + i + " page frames");

			FIFOAlgorithm fifo = new FIFOAlgorithm();
			fifo.FIFOPaging(referencePage,  i);

			LRUAlgorithm lru = new LRUAlgorithm();
			lru.LRUPaging(referencePage,  i);
		}
	}

	/**
	 * Generates a random page reference with numbers of range 0-9
	 * @return
	 */
	public static String GetRandomPageReference(){
		int size = 20;
		String pageRef = new String();
		Random r = new Random();

		for(int i =0; i < size; i++){
			int num = getRandomNumberInRange(0, 9);
			pageRef = pageRef.concat(String.valueOf(num) + ",");
		}
		pageRef = pageRef.substring(0, pageRef.length() - 1);
		return pageRef;
	}

	/**
	 *
	 * @param min int
	 * @param max int
	 * @return a random int from min to max range
	 * generates a random integer from 0
	 * (inclusive) to bound (exclusive).
	 */

	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
