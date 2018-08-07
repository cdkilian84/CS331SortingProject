// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

import java.lang.*;
import java.io.*;
import java.util.*;
import cs331.sorting.Sort;

//The base QuickSort class implements a recursive quicksort using the first element of every partition as the pivot
//The method accepts generic lists and operates on them as an ArrayList, returning the sorted list.
public class QuickSort<T extends Comparable<T>> implements Sort<T>{
	ArrayList<T> arrList;
	
	public void init(List<T> list){
		arrList = new ArrayList<T>(list);
	}
	
	public List<T> getSortedList(){
		sort(0, (arrList.size() - 1));
		return arrList;
	}
	
	//Method to quicksort an arraylist
	//pivot used in this case is always the first element of the working range
	public void sort(int startIndex, int endIndex){
		if(startIndex < endIndex){
			int pivotIndex = startIndex;
			int postPartitionPivotIndex = partition(startIndex, endIndex, pivotIndex);
			sort(startIndex, (postPartitionPivotIndex - 1));
			sort((postPartitionPivotIndex + 1), endIndex);
		}
	} //end sort
	
	
	//partition handles the actual organization of data, moving elements in the array around based on whether they are less than or equal to the pivot value or not.
	//returns the index value of the pivot once it is finished
	public int partition(int startIndex, int endIndex, int pivotIndex){
		int workingIndex = startIndex; //set workingIndex to startIndex; workingIndex represents the boundary between small and large elements
		T pivotVal = arrList.get(pivotIndex);
		
		//Move pivot value to end of area being partitioned --> done so that in the below loop, the pivot is never compared against itself
		//which would corrupt the operation of the workingIndex by incrementing it too far.
		swap(pivotIndex, endIndex);
		pivotIndex = endIndex;
		
		for(int i = startIndex; i < endIndex; i++){
			//if element at i is <= pivot, swap it with element at workingIndex and advance workingIndex 
			if(arrList.get(i).compareTo(pivotVal) <= 0){
				swap(i, workingIndex);
				workingIndex++;
			}
		}
		
		//at this point, leftmost element > pivot is at workingIndex; swap pivot with this element
		swap(workingIndex, pivotIndex);
		pivotIndex = workingIndex;
		
		return pivotIndex;
	} //end partition
	
	
	//simple method to swap two elements in an arraylist
	public void swap(int swapFrom, int swapTo){
		if(swapFrom != swapTo){ //no need to swap if "from" and "to" are same location
			T temp = arrList.get(swapTo);
			arrList.set(swapTo, arrList.get(swapFrom));
			arrList.set(swapFrom, temp);
		}
	} //end swap
	
} //end class QuickSort
