package com.leet;

//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//You are given a target value to search. If found in the array return its index, otherwise return -1.
//
//You may assume no duplicate exists in the array.

public class SearchRotatedSortedArray {

	public SearchRotatedSortedArray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {5,7,8,0,3,4};
//		int[] nums = {5,7};
//		int[] nums = {7,8,1,2,3,4,5,6};
		int[] nums = {6};

		int target = 6;
		
		System.out.println("Index of Target: " + search(nums, target));
	}
	
	
    public int search(int[] nums, int target) {
        int i,j;
        int nMiddle;
        int n;
        
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) {
        	if (nums[0] == target) {
        		return 0;
        	} else {
        		return -1;
        	}
        }
        
        n = nums.length;
        
        //Find the pivot
        i = 1; j = n-1;
        while (nums[i] > nums[j] || i < j) {
        	nMiddle = (i+j)/2;
        	
        	if (nums[nMiddle] > nums[0]) {
        		i = nMiddle + 1;
        	} else {
        		j = nMiddle - 1;
        	}
        }
        
        if (i < n -1 && nums[i] > nums[i+1]) {
        	nMiddle = i + 1;
        } else {
        	nMiddle = i;
        }
                
        if (target >= nums[nMiddle] && target <= nums[n-1]) {
        	i = nMiddle;
        	j = n - 1;
        } else {
        	i = 0;
        	j = nMiddle-1;
        }
        
        while (i<=j) {
        	nMiddle = (i+j)/2;
        	
        	if (nums[nMiddle] == target) {
        		return nMiddle;
        	} else if (nums[nMiddle] < target) {
        		i = nMiddle + 1;
        	} else {
        		j = nMiddle - 1;
        	}
        			
        }
        
        
        return -1;
    }
	
}
