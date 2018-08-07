// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

//subclass of QuickSort which operates in a non-recursive (therefore, iterative) manner.
//In place of recursive calls which are pushed onto the stack, an explicit stack is used here to keep track of index values that need to be partitioned
//By using an explicit stack, less memory is used since only integer values are being stored instead of all of the state data associated with pushing the entire
//method call onto the stack.
//This implementation is attempted as extra credit.
public class IterativeQuickSort extends QuickSort{
	
	//This subclass version of "sort" finds the pivot closest to the mean value of the range
	//Note that on the original call to "sort", the start and end indices are exactly those - the starting and ending indices of the arrayList
	public void sort(int startIndex, int endIndex){
		Stack<Integer> indexStack = new Stack<Integer>();
		
		//to start, push initial index start/end values onto the stack
		indexStack.push(startIndex);
		indexStack.push(endIndex);
		
		while(!indexStack.empty()){
			
			endIndex = indexStack.pop();
			startIndex = indexStack.pop();
			
			if(startIndex < endIndex){//(endIndex - startIndex >= 2){
				int pivotIndex = ThreadLocalRandom.current().nextInt(startIndex, endIndex + 1); //use random pivots for this version of quicksort
				int postPartitionPivotIndex = partition(startIndex, endIndex, pivotIndex);
				
				//push "left hand side" indices onto the stack first
				indexStack.push(startIndex);
				indexStack.push((postPartitionPivotIndex - 1));

				//then push "right hand side" indices onto the stack
				indexStack.push((postPartitionPivotIndex + 1));
				indexStack.push(endIndex);
			}
		}
	} //end sort
	

}