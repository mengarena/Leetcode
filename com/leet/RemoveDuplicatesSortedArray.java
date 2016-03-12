package com.leet;

//Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
//
//Do not allocate extra space for another array, you must do this in place with constant memory.
//
//For example,
//Given input array nums = [1,1,2],
//
//Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
//It doesn't matter what you leave beyond the new length.

public class RemoveDuplicatesSortedArray {

	public RemoveDuplicatesSortedArray() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {1, 1, 1, 2, 2, 2, 2, 3};
		
		System.out.println("New Len = " + removeDuplicates(nums));
	}
	
    public int removeDuplicates(int[] nums) {
    	if (nums == null) return 0;
    	if (nums.length == 0) return 0;
    	if (nums.length == 1) return nums[0];
    	
    	int n = nums.length;
    	int nNewLen = 1;
    	
    	for (int i=1; i<n; i++) {
    		if (nums[i] != nums[nNewLen-1]) {
    			nums[nNewLen] = nums[i];
    			nNewLen = nNewLen + 1;
    		}
    	}
    
    	return nNewLen;
    }
	
	
}
