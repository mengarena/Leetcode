package com.leet;

//Given an array of integers with possible duplicates, randomly output the index of a given target number. 
//You can assume that the given target number must exist in the array.
//
//Note:
//The array size can be very large. Solution that uses too much extra space will not pass the judge.
//
//Example:
//
//int[] nums = new int[] {1,2,3,3,3};
//Solution solution = new Solution(nums);
//
//// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
//solution.pick(3);
//
//// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
//solution.pick(1);


//Facebook
public class RandomPickIndex {

	public RandomPickIndex() {
		// TODO Auto-generated constructor stub
	}

	//ACC
    private int[] nums = null;
    private java.util.Random rm = null;
    
    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        rm = new java.util.Random();
    }
    
    //https://en.wikipedia.org/wiki/Reservoir_sampling
    public int pick(int target) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int i;
        int count = 0;
        int retIdx = -1;
        
        for (i=0; i<n; i++) {
            if (nums[i] == target) {
                count++;
                
                if (rm.nextInt(count) == 0) {   //Reservoir sampling. = 0, mean probability 1/i and select this one.
                    retIdx = i;
                }
            }
        }
       
        return retIdx;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */


