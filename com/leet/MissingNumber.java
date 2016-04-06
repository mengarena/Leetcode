package com.leet;

//Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
//
//For example,
//Given nums = [0, 1, 3] return 2.
//
//Note:
//Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
	
//Bloomberg
public class MissingNumber {

	public MissingNumber() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1, 2, 3};
		
		System.out.println("The missing number is: " + missingNumber(nums));
	}
	
	//Sum of 0~n = (n+1)*n/2
    public int missingNumber(int[] nums) {
    	int n = nums.length;
    	int nMissing = 0;
    	
    	//Sum (0~n) - Sum(nums)
    	for (int i=0; i<n; i++) {
    		nMissing = nMissing  + i - nums[i];   //This way could avoid overflow
    	}
    	
    	nMissing = nMissing + n;
    			
    	return nMissing;
    }
	
}
