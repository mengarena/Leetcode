package com.leet;

import java.util.ArrayList;
import java.util.List;

//Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
//
//For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
//the contiguous subarray [4,−1,2,1] has the largest sum = 6.
//
//click to show more practice.
//
//More practice:
//If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

public class MaximumSubarray {

	public MaximumSubarray() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//		int[] nums = {1, 2, -1, -2, 2, 1, -2, 1, 4, -5, 4};
//		int[] nums = {-9, -2, 1, 8, 7, -6, 4, 9, -9, -5, 0, 5, -2, 5, 9, 7};
		int[] nums = {-5, 8, -5, 1, 1, -3, 5, 5, -3, -3, 6, 4, -7, -4, -8, 0, -1, -6};
		
		System.out.println("Max Sub Array = " + maxSubArray(nums));
	}
	

    public int maxSubArray(int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
    	if (nums.length == 1) return nums[0];
    	int n = nums.length;
    	
    	int narrDP[]= new int[n];  //Sum of sub-sequence ends at ith number
    	int i;
    	narrDP[0] = nums[0];
    	int nCurMax = nums[0];
    	
    	for (i=1; i<n; i++) {
    		narrDP[i] = narrDP[i-1] <= 0 ? nums[i]:nums[i]+narrDP[i-1];
    		nCurMax = Math.max(narrDP[i], nCurMax);
    	}
    	
    	return nCurMax;

    }
    
}
