// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

import java.io.*;
import java.util.*;
import cs331.sorting.Sort;

//Subclass of the base "MergeSort", this version uses the same methods as its parent, but overrides the two methods which describe the base case.
//By doing so, this subclass can call Insertion Sort on the base case instead of allowing the base case to reduce to a list of size 1, taking advantage
//of Insertion Sort's greater efficiency for small list sizes.
public class MergeInsertionSort<T extends Comparable<T>> extends MergeSort<T>{
	
	//experimental data revealed that Insertion Sort generally works faster than Merge Sort for lists of length 32 or smaller
	public int getBaseSize(){
		return 32;
	}//end getBaseSize
	
	//MergeInsertion works by using insertion sort instead of merge sort for the base case, rather than getting down to a base case of size 1 and merging
	//since insertion sort has been demonstrated to work faster on small list sizes. Here the insertion sort algorithm is instantiated and used to sort the small
	//sized list, which is returned to MergeSort as the base case.
	public LinkedList<T> getBaseCase(LinkedList<T> listToSort){
		Sort insertion = new InsertionSort();
		insertion.init(listToSort);
		LinkedList<T> sorted = new LinkedList<T>(insertion.getSortedList());//insertion.getSortedList();
		
		return sorted;
	}//end getBaseCase
}


