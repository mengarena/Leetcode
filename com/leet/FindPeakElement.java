package com.leet;

//A peak element is an element that is greater than its neighbors.
//
//Given an input array where num[i] ¡Ù num[i+1], find a peak element and return its index.
//
//The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//You may imagine that num[-1] = num[n] = -¡Þ.
//
//For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
//
//click to show spoilers.
//
//Note:
//Your solution should be in logarithmic complexity.

public class FindPeakElement {

	public FindPeakElement() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1,2};
		
		System.out.println("Peak Index: " + findPeakElement(nums));
	}
	

    public int findPeakElement(int[] nums) {
    	if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int i=0, j=n-1;
        int nMiddle;
        
        if (n == 1) return 0;
        
        while (i <= j) {
        	nMiddle = (i+j)/2;
        	
        	if (nMiddle == 0) {
        		if (nums[nMiddle] > nums[nMiddle+1]) {
        			return nMiddle;
        		} else {
        			i = nMiddle + 1;
        		}
        	} else if (nMiddle == n-1) {
        		if (nums[nMiddle] > nums[nMiddle-1]) {
        			return nMiddle;
        		} else {
        			j = nMiddle - 1;
        		}
        	} else {
        		if (nums[nMiddle] > nums[nMiddle-1] && nums[nMiddle] > nums[nMiddle+1]) {
        			return nMiddle;
        		} else if (nums[nMiddle] < nums[nMiddle-1]) {
        			j = nMiddle - 1;
        		} else if (nums[nMiddle] < nums[nMiddle+1]) {
        			i = nMiddle + 1;
        		}
        	}
        }
        
        return -1;
    }
	
}
