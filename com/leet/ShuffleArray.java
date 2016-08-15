package com.leet;

//Shuffle a set of numbers without duplicates.
//
//Example:
//
//// Init an array with set 1, 2, and 3.
//int[] nums = {1,2,3};
//Solution solution = new Solution(nums);
//
//// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
//solution.shuffle();
//
//// Resets the array back to its original configuration [1,2,3].
//solution.reset();
//
//// Returns the random shuffling of array [1,2,3].
//solution.shuffle();


public class ShuffleArray {

	public ShuffleArray() {
		// TODO Auto-generated constructor stub
	}

    private int[] nums = null;
    private int len = 0;
    java.util.Random rd = new java.util.Random();
    
    public ShuffleArray(int[] nums) {   //In the original question, it is Solution
        this.nums = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (nums == null) return nums;
        int tmp = 0;
        
        int[] newNums = nums.clone();
       
        for (int i=1; i<newNums.length; i++) {
            int selectPos = rd.nextInt(i+1);
            tmp = newNums[i];
            newNums[i] = newNums[selectPos];
            newNums[selectPos] = tmp;
        }
        
        return newNums;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
