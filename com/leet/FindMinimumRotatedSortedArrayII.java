package com.leet;

//Follow up for "Find Minimum in Rotated Sorted Array":
//What if duplicates are allowed?
//
//Would this affect the run-time complexity? How and why?
//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//Find the minimum element.
//
//The array may contain duplicates.

public class FindMinimumRotatedSortedArrayII {

	public FindMinimumRotatedSortedArrayII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {4,5,6,7,0,1,2};
		System.out.println("The Min = " + findMin(nums));
	}
	
    public int findMin(int[] nums) {
        int i;
        int nMin = nums[0];
        
        for (i=0; i<nums.length-1; i++) {
        	if (nums[i+1] < nums[i]) {
        		nMin = nums[i+1];
        		break;
        	}
        }
        
        return nMin;
    }
	
}
