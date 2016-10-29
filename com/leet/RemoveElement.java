package com.leet;

//Given an array and a value, remove all instances of that value in place and return the new length.
//
//The order of elements can be changed. It doesn't matter what you leave beyond the new length.

//Easy
public class RemoveElement {

	public RemoveElement() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int nums[] = {2};
		
		System.out.println("New Length = " + removeElement(nums, 2));
	}
	
    public int removeElement(int[] nums, int val) {
        int nNewLen = 0;
        int n = nums.length;
        
        if (n == 0) return 0;
        
        for (int i=0; i<n; i++) {
        	if (nums[i] != val) {
        		nNewLen = nNewLen + 1;
        		nums[nNewLen-1] = nums[i];
        	}
        }
        
        return nNewLen;
    }
	
}
