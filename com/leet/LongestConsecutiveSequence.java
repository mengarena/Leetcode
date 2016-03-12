package com.leet;

import java.util.Arrays;

//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
//For example,
//Given [100, 4, 200, 1, 3, 2],
//The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
//
//Your algorithm should run in O(n) complexity.

public class LongestConsecutiveSequence {

	public LongestConsecutiveSequence() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[] nums = {1, 2, 100, 4, 200, 1, 3, 2};
		int[] nums = {3,2,5,6,7,9,10};
		
		System.out.println("Longest Consecutive sequence Len = " + longestConsecutive(nums));
	}
	
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i;
        int nLen = 1;
        int nMaxLen = 1;
        
        Arrays.sort(nums);
        
        for (i=1; i<n; i++) {
        	if (nums[i] - nums[i-1] == 1) {
        		nLen++;
        	} else if (nums[i] - nums[i-1] > 1) {
        		nMaxLen = Math.max(nMaxLen, nLen);
        		nLen = 1;
        	}
        }
        
        nMaxLen = Math.max(nMaxLen, nLen);
        
        return nMaxLen;        
    }
	
}
