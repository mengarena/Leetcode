package com.leet;

//Range Sum Query - Immutable

//Given an integer array nums, find the sum of the elements between indices i and j (i ¡Ü j), inclusive.
//
//Example:
//Given nums = [-2, 0, 3, -5, 2, -1]
//
//sumRange(0, 2) -> 1
//sumRange(2, 5) -> -1
//sumRange(0, 5) -> -3
//Note:
//You may assume that the array does not change.
//There are many calls to sumRange function.

public class NumArray {

	private int[] narrSum = null;
	
    public NumArray(int[] nums) {
        if (nums == null || nums.length ==0) return;
       
        narrSum = new int[nums.length];
        narrSum[0] = nums[0];
        
        for (int i=1; i<nums.length; i++) narrSum[i] = narrSum[i-1] + nums[i];
    }

    public int sumRange(int i, int j) {        
        if (narrSum == null) return 0;
                
        if (i < 0 || j >= narrSum.length || i > j) return 0;
        
        if (i == 0) return narrSum[j];
        
        return narrSum[j] - narrSum[i-1];
    }

 // Your NumArray object will be instantiated and called as such:
 // NumArray numArray = new NumArray(nums);
 // numArray.sumRange(0, 1);
 // numArray.sumRange(1, 2);
    
}
