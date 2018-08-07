// Christopher Kilian
// CS 331 Project #1

package cs331.sorting.cdkilian;

//subclass of QuickSort whose only job is to provide a new method for sorting which uses a different pivot value from that used in the parent class
public class QuickSortMean extends QuickSort{
        int lowRange = -1000;
        int highRange = 1000;
		
		
		
    
	//This subclass version of "sort" finds the pivot closest to the mean value of the range
	public void sort(int startIndex, int endIndex){
		if(startIndex < endIndex){
			int pivotIndex = findPivot(startIndex, endIndex);
			int postPartitionPivotIndex = partition(startIndex, endIndex, pivotIndex);
			sort(startIndex, (postPartitionPivotIndex - 1));
			sort((postPartitionPivotIndex + 1), endIndex);
		}
	} //end sort
	
        //find the value closest to the expected mean - For the range -1000 to 1000, the expected mean
        //on the first iteration is 0. On further iterations, the expected mean will be 
	public int findPivot(int startIndex, int endIndex){
		int pivot = startIndex;
		int targetMean;
		int initialVal = (int)arrList.get(startIndex); //have to cast to Integer since the arrList is a generic type
        
        if(startIndex == 0 && endIndex == (arrList.size() - 1)){
            targetMean = 0;
        }
		else if(initialVal >= 0){
            targetMean = highRange/2;
        }
		else{
			
		}
            
            
            
		return 0;
	}

}