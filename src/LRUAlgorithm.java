/*
 * ICS 462 - Assignment 4
 * This assignment implements the LRU page replacement algorithms.
 */

import java.util.*;

public class LRUAlgorithm {
 
	/**
	 * This method used LRU page reference algorithm to load page-reference array and
	 * determine the number of page faults. It uses a queue to save the history of the
	 * memory frame.
	 * @param reference
	 * @param numFrames
	 */
	public void LRUPaging(String[] reference, int numFrames){

		int index = 0;
		int numFaults = 0;	               	        
		int numPages = reference.length;
		ArrayList<String> memory = new ArrayList<String>(numFrames);
		Queue<String> history = new LinkedList<String>();
		
		// Initialize memory list
		for(int j = 0; j < numFrames; j++){
			memory.add("");
		}
		
		// Do LRU paging
		for(int i = 0; i < numPages; i++)
		{
			int exists = -1;

			// Check all the frames to see if the page is already in memory
			for(int j = 0; j < numFrames; j++)
			{
				if(memory.get(j).equals(reference[i]))
				{
					exists = j;								
					// Re-add it to push it on top of the queue
					history.remove(reference[i]);
					history.add(reference[i]);
					break;
				} 				
			}

			// Bring the pages into memory
			if(exists == -1)
			{	
				// When memory list is full, get the head of the queue, which is
				// the least used page and replace it with the new page
				if(!history.isEmpty() && memory.get(index) != ""){
					String lru = (String) history.remove();
					index = memory.indexOf(lru);
				}
				
				// Replace the least recently used page with the new page
				memory.set(index, reference[i]);
				numFaults++;
				index++;
				history.add(reference[i]);
				if(index == numFrames)
					index = 0;
			}
		}
	
		System.out.println("FIFO had " + numFaults + " faults.");
	}

}
