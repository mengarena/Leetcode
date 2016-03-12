package com.leet;

import java.util.Arrays;

//Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), 
//prove that at least one duplicate number must exist. 
//Assume that there is only one duplicate number, find the duplicate one.
//
//Note:
//You must not modify the array (assume the array is read only).
//You must use only constant, O(1) extra space.
//Your runtime complexity should be less than O(n2).
//There is only one duplicate number in the array, but it could be repeated more than once.

public class FindDuplicateNumber {

	public FindDuplicateNumber() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {};
		
		System.out.println("The duplicate number is: " + findDuplicate(nums));
	}
	
	
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int i;
        int nDuplicateNum = 0;
        
        Arrays.sort(nums);
        for (i=1; i<n; i++) {
        	if (nums[i] == nums[i-1]) {
        		nDuplicateNum = nums[i];
        		break;
        	}
        }
        
        return nDuplicateNum;
    }	
}
