package com.leet;

//Given a sorted array of integers, find the starting and ending position of a given target value.
//
//Your algorithm's runtime complexity must be in the order of O(log n).
//
//If the target is not found in the array, return [-1, -1].
//
//For example,
//Given [5, 7, 7, 8, 8, 10] and target value 8,
//return [3, 4].

public class SearchRange {

	public SearchRange() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {5,7,7,8,8,8,8,10};
		int[] nums = {1};

		int target = 1;
		
		int[] narrRange = searchRange(nums, target);
		System.out.println("[" + narrRange[0] + "," + narrRange[1] + "]");
	}
	
    public int[] searchRange(int[] nums, int target) {
        int[] narrRange = new int[2];
        int nLeftPos = -1;
        int nRightPos = -1;
        
        if (nums == null || nums.length == 0) return narrRange;
        int n = nums.length;
        int i,j;
        int nMiddle;

        //Find the left pos
        i = 0; j = n - 1;
        while (i <= j) {
        	nMiddle = (i+j)/2;
        	
        	if (nums[nMiddle] >= target) {
        		j = nMiddle - 1;
        	} else if (nums[nMiddle] < target) {  //When stopped, i will be the first target
        		i = nMiddle + 1;
        	}
        }
        
        if (i <= n-1 && nums[i] == target) nLeftPos = i;   //i <= n-1 to prevent i runs out of the right border
        
        //Find the right pos
        i = 0; j = n - 1;
        while (i <= j) {
        	nMiddle = (i+j)/2;
        	
        	if (nums[nMiddle] > target) {  //When stopped, i will be the last target
        		j = nMiddle - 1;
        	} else if (nums[nMiddle] <= target) {
        		i = nMiddle + 1;
        	}
        }
        
        if (j >= 0 && nums[j] == target) nRightPos = j;  //j >=0 to prevent j runs out of the left border
        
        narrRange[0] = nLeftPos;
        narrRange[1] = nRightPos;
        
        return narrRange;
    }
	
}
