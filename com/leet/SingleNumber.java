package com.leet;

//Given an array of integers, every element appears twice except for one. Find that single one.
//
//Note:
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
		
public class SingleNumber {

	public SingleNumber() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {0, 5};
		
		int nRet = singleNumber(nums);
		
		System.out.println("Single Number = " + nRet);
	}
	
    public int singleNumber(int[] nums) {
        int nRet = 0;
        
        for (int i=0; i<nums.length; i++) {
        	nRet = nRet ^ nums[i];
        }
        
        return nRet;
    }	
	
}
