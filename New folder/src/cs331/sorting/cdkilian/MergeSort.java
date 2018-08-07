// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

import java.io.*;
import java.util.*;
import cs331.sorting.Sort;

//Mergesort using linked lists ---> Notice that this implementation uses only O(logn) auxiliary space since it does not need to create any extra storage space
//for the list as it's being sorted. Every time a list element is moved from one list to another during the sorting process, it is removed from the list it came from,
//ensuring that the storage space used for the totality of elements is never bigger than that needed to store the input list in the first place.
//This implementation of Merge Sort operates on linked lists rather than arrays, and allows for generic types rather than requiring a specific data type.
public class MergeSort<T extends Comparable<T>> implements Sort<T>{
	LinkedList<T> orgList;
	
	public void init(List<T> list){
		orgList = new LinkedList<T>(list);
	}
	
	public List<T> getSortedList(){
		LinkedList<T> sorted = sort(orgList);
		return sorted;
	}
	
	//recursive method which calls successively smaller sub-sections of the list and then merges them
	public LinkedList<T> sort(LinkedList<T> listToSort){
		int size = listToSort.size();
		LinkedList<T> mergedList = null;
		if(size > getBaseSize()){
			int midIndex = size/2;
			LinkedList<T> firstHalf = new LinkedList<T>();
			LinkedList<T> secondHalf = new LinkedList<T>();
			
			//split elements of list into two separate lists, each with half (or close to half if odd number) of the elements from the original passed list
			for(int i = 0; i < midIndex; i++){
				firstHalf.add(listToSort.remove());
			}
			for(int i = midIndex; i < size; i++){
				secondHalf.add(listToSort.remove());
			}
			
			firstHalf = sort(firstHalf);
			secondHalf = sort(secondHalf);
			mergedList = merge(firstHalf, secondHalf);
			
		}else{
			mergedList = getBaseCase(listToSort); //in base case, simply return the original value passed (for lists of size 1)
		}
		
		return mergedList;
	} //end sort
	
	//method to be overridden in subclass which will allow easy changing of base case size
	public int getBaseSize(){
		return 1;
	}//end getBaseSize
	
	//method to be overridden in subclass which will allow easy changing of the list being returned in the base case (here it simply returns the passed list unmolested)
	public LinkedList<T> getBaseCase(LinkedList<T> listToSort){
		return listToSort;
	}//end getBaseCase
	
	
	//Merge function takes two sorted linked lists and merges them into a single sorted linked list. No extra memory space is used, as each time an element is added to a new list,
	//it is removed from its original list, ensuring the memory used is limited to that required for the original unsorted list passed to the class in the "init" method
	public LinkedList<T> merge(LinkedList<T> firstHalf, LinkedList<T> secondHalf){
		LinkedList<T> merged = new LinkedList<T>();
		
		while((firstHalf.peekFirst() != null) && (secondHalf.peekFirst() != null)){
			if(firstHalf.peekFirst().compareTo(secondHalf.peekFirst()) < 0){
				merged.add(firstHalf.remove());
			}else if(firstHalf.peekFirst().compareTo(secondHalf.peekFirst()) > 0){
				merged.add(secondHalf.remove());
			}else{ //elements are equal
				merged.add(firstHalf.remove());
				merged.add(secondHalf.remove());
			}
		}
		
		while(firstHalf.peekFirst() != null){ //get remaining elements from firstHalf
			merged.add(firstHalf.remove());
		}
		
		while(secondHalf.peekFirst() != null){ //get remaining elements from secondHalf
			merged.add(secondHalf.remove());
		}
		
		return merged;
	} //end merge
	
} // end class MergeSort
