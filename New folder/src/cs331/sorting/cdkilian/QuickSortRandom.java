// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

import java.util.concurrent.ThreadLocalRandom;

//subclass of QuickSort whose only job is to provide a new method for sorting which uses a different pivot value from that used in the parent class
public class QuickSortRandom extends QuickSort{
	
	//This subclass version of "sort" uses a pivot selected at random from the available range of indices
	public void sort(int startIndex, int endIndex){
		if(startIndex < endIndex){
			int pivotIndex = ThreadLocalRandom.current().nextInt(startIndex, endIndex + 1); //get random pivot value somewhere between (and including) the two index values
			int postPartitionPivotIndex = partition(startIndex, endIndex, pivotIndex);
			sort(startIndex, (postPartitionPivotIndex - 1));
			sort((postPartitionPivotIndex + 1), endIndex);
		}
	} //end sort

}