package com.leet;

import java.util.Arrays;

//Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
//
//Example:
//
//nums = [1, 2, 3]
//target = 4
//
//The possible combination ways are:
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//
//Note that different sequences are counted as different combinations.
//
//Therefore the output is 7.
//
//Follow up:
//What if negative numbers are allowed in the given array?
//How does it change the problem?
//What limitation we need to add to the question to allow negative numbers?


//Google, Snapchat
public class CombinationSumIV {

	public CombinationSumIV() {
		// TODO Auto-generated constructor stub
	}

	//ACC
	//For follow up question, if -1, 1 like pair exist, the number of combination could be infinite
	//Possible limitation to add: length of the combination sequence to be restricted
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        Arrays.sort(nums);
        int[] dp = new int[target+1];

        for (int i=1; i<=target; i++) {
            for (int j=0; j<n; j++) {
                if (nums[j] > i) {
                    break;
                } else if (nums[j] == i) {
                    dp[i] += 1;
                } else {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        
        return dp[target];
    }

}
