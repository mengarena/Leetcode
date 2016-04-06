package com.leet;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

//Given an array nums and a target value k, find the maximum length of a subarray that sums to k. 
//If there isn't one, return 0 instead.
//
//Example 1:
//Given nums = [1, -1, 5, -2, 3], k = 3,
//return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
//
//Example 2:
//Given nums = [-2, -1, 2, 1], k = 1,
//return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
//
//Follow Up:
//Can you do it in O(n) time?

//Palantir
public class MaximumSizeSubarraySumEqualsK {

	public MaximumSizeSubarraySumEqualsK() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[] nums = {1, -1, 5, -2, 3};
		int[] nums = {-2, -1, 2, 1};
		
		System.out.println(maxSubArrayLen(nums, 3));
	}

	//AC
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int nSize = 0;
        int i;
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        
        hm.put(nums[0], 0);
        int nSum = nums[0];
        
        if (nSum == k) nSize = 1;
        
        for (i=1; i<n; i++) {
        	nSum = nSum + nums[i];
        	
        	if (nSum == k) {
        		nSize = i+1;   //Don't need to Math.max compare, by this position, i+1 will be the longest
        	} else {
        		if (hm.containsKey(nSum - k)) {
        			nSize = Math.max(nSize, i-hm.get(nSum-k));
        		}
        	}
        	
            if (!hm.containsKey(nSum)) hm.put(nSum, i);
            //The reason to check whether hm already contains nSum or not, is that:
            //If the array contains 0 or -2, 2 like sub arrays
            //For example, 3,4,7,0,5,-2,2,6
            //Sum by 7 and by 0 is the same; sum by 5 and 2 is the same
            //To get the longest subarray for the result, 
            //For the same value sum, if hm already contains, don't update it, in this way, for the same value, we get the lowest index, which will help to produce longest subarrau
        }
        
        return nSize;
    }	
    
}
