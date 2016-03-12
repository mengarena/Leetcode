package com.leet;


//Follow up for "Search in Rotated Sorted Array":
//What if duplicates are allowed?
//
//Would this affect the run-time complexity? How and why?
//
//Write a function to determine if a given target is in the array.


public class SearchRotatedSortedArrayII {

	public SearchRotatedSortedArrayII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {7,8,1,2,2,3,4,4,5,6};
//		int[] nums = {7,1};
//		int[] nums = {7,6,7};
//		int[] nums = {7};
//		int[] nums = {7,7};
//		int[] nums = {1,1,7};
//		int[] nums = {1,3};		
//		int[] nums = {1,1,1,3,1};
		int[] nums = {1,2,2,2,0,1,1};
		
		int target = 1;
		
		System.out.println(search(nums, target));
	}
	
	
    public boolean search(int[] nums, int target) {
        int i,j;
        int nMiddle;
        int n;
        
        if (nums == null || nums.length == 0) return false;
        if (nums.length == 1) {
        	if (nums[0] == target) {
        		return true;
        	} else {
        		return false;
        	}
        }
        
        n = nums.length;
        
        i = 0; j = n-1;
        while (i <= j) {
        	nMiddle = (i+j)/2;
        	
        	if (nums[nMiddle] == target) return true;
        	
        	if (nums[nMiddle] < nums[j]) {    //right part sorted
        		if (target >= nums[nMiddle] && target <= nums[j]) {
        			i = nMiddle + 1;
        		} else {
        			j = nMiddle - 1;
        		}
        	} else if (nums[nMiddle] > nums[j]) {   //left part sorted
        		if (target >= nums[i] && target <= nums[nMiddle]) {
        			j = nMiddle - 1;
        		} else {
        			i = nMiddle + 1;
        		}
        	} else {
        		j = j - 1;
        	}
        }
        
        return false;
    }
	
}
