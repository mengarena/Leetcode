package com.leet;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Given an array of size n, find the majority element. The majority element is the element that appears more than [ n/2 ] (low bound) times.
//
//You may assume that the array is non-empty and the majority element always exist in the array.

public class MajorityElement {

	public MajorityElement() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
    public int majorityElement(int[] nums) {
        int n = nums.length/2;
        int nCnt;
        
        Map<Integer, Integer> numMap = new HashMap<Integer, Integer>();
        
        for (int i=0; i<nums.length; i++) {
        	if (numMap.containsKey(nums[i])) {
        		nCnt = numMap.get(nums[i]);
        		nCnt = nCnt + 1;
        		
        		if (nCnt > n) return nums[i];
        		numMap.put(nums[i], nCnt);
        	} else {
        		numMap.put(nums[i], 1);
        	}
        }
        
		return 0;
        
    }	
}
