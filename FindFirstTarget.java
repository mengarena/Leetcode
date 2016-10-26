package com.leet;

public class FindFirstTarget {

	public FindFirstTarget() {
		// TODO Auto-generated constructor stub
	}

    public void run() {
//    	int[] arr = {1,2,5,5,5,6,7,8};
//    	int[] arr = {1,2,5,5,6,7,8};
//    	int[] arr = {1,2,5,6,7,8};
//    	int[] arr = {5,5,6,7,8};
//    	int[] arr = {5,6,7,8,9,10};
//    	int[] arr = {1,2,3,6,7,8};
//    	int[] arr = {1,2,3,4,5};
//    	int[] arr = {1,2,3,4,5,5};
//    	int[] arr = {5,5,5,5,5,5,5};
//    	int[] arr = {5,5,5,5,5,5};
//    	int[] arr = {5};
//    	int[] arr = {6};
//    	int[] arr = {3};
//    	int[] arr = {3,3};
//    	int[] arr = {6,6};
    	int[] arr = {3,3,3};
//    	int[] arr = {6,6,6};
    	
    	int target = 5;
    	
    	int idx = findFirst(arr, target);
    	
    	System.out.println("Target Idx = " + idx);
    }

    //Find the index of the first target in arr, if not exist, return -1
    public int findFirst(int[] arr, int target) {
    	if (arr == null || arr.length == 0) return -1;
    	int n = arr.length;
    	int i = 0, j = n-1;
    	int mid = 0;
    	
    	while (i < j) {
    		mid = i + (j-i)/2;
    		
    		if (arr[mid] > target) {
    			j = mid-1;
    		} else if (arr[mid] < target) {
    			i = mid+1;
    		} else {
    			j = mid;
    		}
    	}
    	
    	if (arr[i] == target) return i;
    	
    	return -1;
    }
    
}
