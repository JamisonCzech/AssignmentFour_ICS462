/*
  * ICS 462 - Assignment 4
 * This class implements the FIFO page replacement algorithms.
 * */

public class FIFOAlgorithm {

	/**
	 * This method takes in a page-reference array and applies FIFO page reference
	 * algorithm to determine the number of page faults.
	 * @param reference
	 * @param numFrames
	 */
	public void FIFOPaging(String[] reference, int numFrames){

		int index = 0;
		int numFaults = 0;	               	        
		int numPages = reference.length;
		String memory[] = new String[numFrames];
		for(int j = 0; j < numFrames; j++){
			memory[j] = "";
		}

		// Do FIFO paging
		for(int i = 0; i < numPages; i++)
		{
			int exists = -1;

			// Check all the frames to see if the page is already in memory
			for(int j = 0; j < numFrames; j++)
			{
				if(memory[j].equals(reference[i]))
				{
					exists = j;
					break;
				} 				
			}

			// Loads the pages into memory
			if(exists == -1)
			{
				memory[index] = reference[i];
				numFaults++;
				index++;
				if(index == numFrames)
					index = 0;
			}
		}

		System.out.println("LRU had " + numFaults + " faults.");
	}

}
