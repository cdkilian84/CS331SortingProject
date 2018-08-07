// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

//subclass of QuickSort whose only job is to provide a new method for sorting which uses a different pivot value from that used in the parent class
//This implementation is attempted as extra credit. It is designed to attempt to select the "expected mean value" in any given partition
//as the pivot value, or whatever value is closest to that expected mean.
public class QuickSortMean extends QuickSort{
        int LOWRANGE = -1000;
        int HIGHRANGE = 1000;
	
    
	//This subclass version of "sort" finds the pivot closest to the mean value of the range
	public void sort(int startIndex, int endIndex){
            meanSort(startIndex, endIndex, 0, 1000); //initial call to meanSort expects "0" as pivotValue and set increment to the size of each "half" of the expected range
	} //end sort
	
        //expectedMeanVal is determined by the given range of expected values --> In this case, the expected values are from -1000 to +1000
        //so its initial call value is "0". The Increment is halved with every recursive call as it moves down the call stack/tree, and so
        //should start as the difference between the initial expected mean and the high/low range (in this case, 1000).
        public void meanSort(int startIndex, int endIndex, int expectedMeanVal, int increment){
            if(startIndex < endIndex){
                        int lowExpectedMean;
                        int highExpectedMean;
			int pivotIndex = findPivot(startIndex, endIndex, expectedMeanVal);
			int postPartitionPivotIndex = partition(startIndex, endIndex, pivotIndex);
                        
                        if(increment > 1){//increment bottoms out at 1
                            increment = increment/2;
                        }
                        if(expectedMeanVal == 0){ //accounts for initial recursive calls when expected mean is 0
                            lowExpectedMean = LOWRANGE/2;
                            highExpectedMean = HIGHRANGE/2;
                        }else{
                            lowExpectedMean = expectedMeanVal - increment;
                            highExpectedMean = expectedMeanVal + increment;
                        }
                        meanSort(startIndex, (postPartitionPivotIndex - 1), lowExpectedMean, increment); //should be "low" side of expected mean
			meanSort((postPartitionPivotIndex + 1), endIndex, highExpectedMean, increment); //should be "high" side of expected mean
            }
        }

        //find the value closest to the passed expected mean in the range start to end, and return the index value for that element
	public int findPivot(int startIndex, int endIndex, int expectedMeanVal){
                int pivot = startIndex; //pivot initialized to first element in sub-array
		int theVal = (int)arrList.get(startIndex); //have to cast to int since the arrList is a generic type
                //if the value at startIndex is equal to the expected mean, skip the loop and simply return that index
                if(theVal != expectedMeanVal){
                    //find the value in the range closest to the expected mean value
                    //Start at startIndex+1 since there's no reason to compare the initial value to itself
                    for(int i = startIndex+1; i <= endIndex; i++){
                        if(Math.abs(theVal - expectedMeanVal) > Math.abs((int)arrList.get(i) - expectedMeanVal)){
                            theVal = (int)arrList.get(i);
                            pivot = i;
                            if(theVal == expectedMeanVal){
                                break; //stop looking for a better pivot value if the value exactly equals the expected mean
                            }
                        }
                    }
                }
		return pivot;
	} //end findPivot

}