// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

import java.util.*;
import cs331.sorting.Sort;

//Insertion Sort class which operates on a linked list of generic items rather than on an array or a specific datatype.
public class InsertionSort<T extends Comparable<T>> implements Sort<T>{
	LinkedList<T> orgList;
	
	public void init(List<T> list){
		orgList = new LinkedList<T>(list);
	}
	
	public List<T> getSortedList(){
		LinkedList<T> sorted = sort();
		return sorted;
	}
	
	
	//sorting method which implements an insertion sort on the provided linked list
	//Sorts the provided list by removing elements from it one at a time, and inserting them into their appropriate places in a new sorted list.
	//Because each element is removed from the unsorted list before being added to the sorted list, no extra memory is used to save the sorted list.
	private LinkedList<T> sort(){
		LinkedList<T> sortedList = new LinkedList<T>();
		
		while(orgList.peekFirst() != null){ //does original list still hold values?
			T currentNode = orgList.remove(); //get head of original list and remove it from the original list
			ListIterator<T> theIterator = sortedList.listIterator(0); //start iterator at head of new list being built - reinitialize for each new addition to beginning of list
			while(theIterator.hasNext()){
				if(theIterator.next().compareTo(currentNode) >= 0){
					theIterator.previous(); //move iterator back since it's now moved 1 position past where it needs to insert the currentNode
					break;
				}
			}
			
			theIterator.add(currentNode); //once the above loop completes, the iterator is in the correct position to insert the unsorted element into the sorted list
		}
		
		return sortedList;
	} //end sort
	
} //end class InsertionSort

