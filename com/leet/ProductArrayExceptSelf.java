package com.leet;

//Given an array of n integers where n > 1, nums, 
//return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
//
//Solve it without division and in O(n).
//
//For example, given [1,2,3,4], return [24,12,8,6].
//
//Follow up:
//Could you solve it with constant space complexity? 
//(Note: The output array does not count as extra space for the purpose of space complexity analysis.)

//Facebook, Linkedin
public class ProductArrayExceptSelf {

	public ProductArrayExceptSelf() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		int[] nums = {1};
		
		int[] output = productExceptSelf(nums);
		
		if (output != null) {
			for (int i=0; i<output.length; i++) System.out.print(output[i] + ",");
		}
	}
	
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) return null;
        int n = nums.length;
        
        int output[] = new int[n];
        
        if (n == 1) {
        	output[0] = 0;
        	return output;
        }
        
        int nPre[] = new int[n];
        int nPost[] = new int[n];
        int i;
        
        nPre[0] = 1;
        nPre[1] = nums[0];
        nPost[n-1] = 1;
        nPost[n-2] = nums[n-1];
        
        for (i=2; i<n; i++) nPre[i] = nPre[i-1]*nums[i-1];  //For every position, nPre[i] is the product of the elements before nums[i]
        for (i=n-3; i>=0; i--) nPost[i] = nPost[i+1]*nums[i+1];  //For every position, nPost[i] is the product of the elements after nums[i]
        
        for (i=0; i<n; i++) output[i] = nPre[i]*nPost[i];
        
        
        return output;
    }
	
}
