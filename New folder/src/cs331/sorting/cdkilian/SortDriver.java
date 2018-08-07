package cs331.sorting.cdkilian;

// Christopher Kilian
// CS 331 Project #1

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import cs331.sorting.*;

public class SortDriver {
	
	static final int MAX_POW = 17   ; //2^12 = 4096, 2^15 = 32768
	static final int START_POW = 4; //2^4 = 16
	
	public static void main(String[] args) throws FileNotFoundException{
		new SortDriver();
	} //end main
	
	//constructor is where main work of program actually takes place - sets up scanners, reads files, and processes commands via the class methods
	private SortDriver() {
		
		PrintStream ps = null;
		try{
			ps = new PrintStream("runtimes.csv"); //make CSV file for reading into Excel
		}catch(FileNotFoundException x){
			System.out.println("A file could not be found!");
			exit();
		}
		long startTime;
		long endTime;
		long elapsedTime;
		long totalTime;
		
		List<Integer> test;
		
		for(int i = START_POW; i <= MAX_POW; i++){
			int n  = (int) Math.pow(2, i);
			test = randomList(n);
			//test.add(20);
			//test.add(32);
			//test.add(20);
			//test.add(32);

			totalTime = 0;
			ps.print(n);
			List<Integer> mergeResult = new LinkedList<Integer>();
			mergeResult.add(1);
			List<Integer> quickSorted = new LinkedList<Integer>();
			quickSorted.add(1);
			System.out.println("For n = " + n);
			
			if(i <= 5){
				System.out.println("Unsorted list: ");
				printList(test);
			}
			
			
			//Test Insertion Sort
			List<Integer> newTest = new LinkedList<Integer>();
			newTest.add(1);
			for(int j = 0; j < 20; j++){
                                Sort theAlgorithm = new InsertionSort();
				startTime = System.nanoTime();
                                theAlgorithm.init(test);
				newTest = theAlgorithm.getSortedList();
				endTime = System.nanoTime();
				elapsedTime = endTime - startTime;
			
				totalTime += elapsedTime;
                                System.out.println("List is sorted: " + verify(newTest));
                                System.out.println("List size is: " + newTest.size());
			}
			totalTime = totalTime/20;
                        long timeInMicros = TimeUnit.MICROSECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
			ps.println("," + timeInMicros);
			totalTime = 0;
			
			
                        if(i <= 5){
				System.out.println("Sorted list: ");
				printList(newTest);
			}
                        
			/*
			//Test Merge Sort
			for(int j = 0; j < 20; j++){
				//Sort merger = new MergeSort();
                                Sort mergeIns = new MergeInsertionSort();
				startTime = System.nanoTime();
                                mergeIns.init(test);
				//mergeIns.init(test);
				mergeResult = mergeIns.getSortedList();
				endTime = System.nanoTime();
				elapsedTime = endTime - startTime;
			
				totalTime += elapsedTime;
                                System.out.println("List is sorted: " + verify(mergeResult));
                                System.out.println("List size is: " + mergeResult.size());
			}
                        totalTime = totalTime/20;
                        long timeInMicros = TimeUnit.MICROSECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
			ps.println("," + timeInMicros);
			System.out.println("Average Merge Time Was: " + timeInMicros);
			totalTime = 0;
                        */
			
			
			
			/*
			//Test Quick Sort
			for(int j = 0; j < 20; j++){
                                Sort quick = new IterativeQuickSort();
                                //Sort quick = new QuickSort();
                                //Sort quick = new QuickSortRandom();
				startTime = System.nanoTime();
                                quick.init(test);
				quickSorted = quick.getSortedList();
				endTime = System.nanoTime();
				elapsedTime = endTime - startTime;
			
				long timeInMicros = TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                                if(i >= 21){
                                    System.out.println("test " + (j+1) + " was " + timeInMicros);
                                }
                                System.out.println("List is sorted: " + verify(quickSorted));
                                System.out.println("List size is: " + quickSorted.size());
                                totalTime += elapsedTime;
			}
                        totalTime = totalTime/20;
                        long timeInMicros = TimeUnit.MICROSECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
			
			if(i <= 5){
				System.out.println("Sorted list: ");
				printList(quickSorted);
			}
			
			ps.println("," + timeInMicros);
			System.out.println("Average Quick Time Was: " + timeInMicros);
			*/
		}
		
		ps.close();
	} //end SortDriver constructor
	
	
	public List<Integer> randomList(int n){
		List<Integer> theList = new LinkedList<Integer>();
		
		for(int i = 0; i < n; i++){
			theList.add(ThreadLocalRandom.current().nextInt(-1000, 1000 + 1));
		}
		
		return theList;
	}
	
	public void printList(List<Integer> list){
		for(Integer k : list){
			System.out.print(k + ", ");
		}
		System.out.println();
	}
	
        public boolean verify(List<Integer> theList){
		boolean flag = true;
		ArrayList<Integer> arrList = new ArrayList<Integer>(theList);
                
                
		for(int i = 0; i < (arrList.size() - 1); i++){
			if(arrList.get(i) > arrList.get(i+1)){
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	
	/*
	public boolean verify(double [] theArray){
		boolean flag = true;
		
		for(int i = 0; i < (theArray.length - 1); i++){
			if(theArray[i] > theArray[i+1]){
				flag = false;
				break;
			}
		}
		
		return flag;
	}
	*/
	//exit method displays proper program usage before exiting program
	public static void exit(){
		System.out.println("Usage: java SortDriver <name of sorting method> <power of 10 to run>");
		System.exit(0);
	} //end exit method

} //end class




/*
	//constructor is where main work of program actually takes place - sets up scanners, reads files, and processes commands via the class methods
	private SortDriver() {
		
		PrintStream ps = null;
		try{
			ps = new PrintStream("runtimes.csv"); //make CSV file for reading into Excel
		}catch(FileNotFoundException x){
			System.out.println("A file could not be found!");
			exit();
		}
		long startTime;
		long endTime;
		long elapsedTime;
		long totalTime;
		Sort theAlgorithm = new InsertionSort();
		//ArrInsertionSorter sortArr = new ArrInsertionSorter();
		Sort merger = new MergeSort();
		//ArrMergeSorter mergeArr = new ArrMergeSorter();
		Sort quick = new QuickSort();
		MyQuickSorter quickArr = new MyQuickSorter();
		
		List<Integer> test;
		
		for(int i = START_POW; i <= MAX_POW; i++){
			int n  = (int) Math.pow(2, i);
			test = randomList(n);
			Integer[] testArr = test.toArray(new Integer[0]);
			
			if(i == 4){
				System.out.println("UnSorted List: ");
				for(Integer k : test){
					System.out.print(k + ", ");
				}
				System.out.println();
			}
			
			
			
			
			
			
			//testing List version of Insertion sort
			totalTime = 0;
			ps.print(n);
			//List<Integer> mergeResult = new LinkedList<Integer>();
			//mergeResult.add(1);
			List<Integer> quickSorted = new LinkedList<Integer>();
			quickSorted.add(1);
			for(int j = 0; j < 10; j++){
				//theAlgorithm.init(test);
				//merger.init(test);
				quick.init(test);
				startTime = System.nanoTime();
				//List<Integer> newTest = theAlgorithm.getSortedList();
				//mergeResult = merger.getSortedList();
				quickSorted = quick.getSortedList();
				endTime = System.nanoTime();
				elapsedTime = endTime - startTime;
			
				long timeInMicros = TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
				totalTime += timeInMicros;
			}
			//ps.println("," + totalTime/10);
		
			
			if(i == 4){
				System.out.println("Sorted List: ");
				for(Integer k : quickSorted){
					System.out.print(k + ", ");
				}
				System.out.println();
			}
			
			
			
			ps.println("," + totalTime/10);
			System.out.println("For n = " + n);
			System.out.println("Time for List was: " + totalTime/10);
			
			
			//testing array version of insertionSort
			totalTime = 0;
			for(int j = 0; j < 10; j++){
				testArr = test.toArray(new Integer[0]);
				startTime = System.nanoTime();
				//sortArr.sort(testArr);
				//mergeArr.sort(testArr);
				quickArr.sort(testArr);
				endTime = System.nanoTime();
				elapsedTime = endTime - startTime;
			
				long timeInMicros = TimeUnit.MICROSECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
				totalTime += timeInMicros;
			}
			
			ps.println("," + totalTime/10);
			System.out.println("Time for Array was: " + totalTime/10);
			
		}
		
		
		
		
		test.add(4);
		test.add(9);
		test.add(8);
		test.add(1);
		test.add(3);
		test.add(2);
		test.add(7);
		
		System.out.println("The List: ");
		for(int i=0; i < test.size(); i++){
			System.out.print(test.get(i) + ", ");
		}
		System.out.println("\n");
		theAlgorithm.init(test);
		List<Integer> sorted = theAlgorithm.getSortedList();
		
		System.out.println("The Sorted List: ");
		for(int i=0; i < sorted.size(); i++){
			System.out.print(sorted.get(i) + ", ");
		}
		System.out.println("\n");
		
		List<Integer> bigList = randomList(10);
		
		System.out.println("The BigList: ");
		for(int i=0; i < bigList.size(); i++){
			System.out.print(bigList.get(i) + ", ");
		}
		System.out.println("\n");
		theAlgorithm.init(bigList);
		List<Integer> bigSorted = theAlgorithm.getSortedList();
		
		System.out.println("The BigSorted List: ");
		for(int i=0; i < bigSorted.size(); i++){
			System.out.print(bigSorted.get(i) + ", ");
		}
		
		ps.close();
	} //end SortDriver constructor


*/