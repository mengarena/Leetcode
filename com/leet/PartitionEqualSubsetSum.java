package com.leet;

//Given a non-empty array containing only positive integers, 
//find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
//
//Note:
//Both the array size and each of the array element will not exceed 100.
//
//Example 1:
//
//Input: [1, 5, 11, 5]
//
//Output: true
//
//Explanation: The array can be partitioned as [1, 5, 5] and [11].
//
//Example 2:
//
//Input: [1, 2, 3, 5]
//
//Output: false
//
//Explanation: The array cannot be partitioned into equal sum subsets.
//
//
//eBay
public class PartitionEqualSubsetSum {

	public PartitionEqualSubsetSum() {
		// TODO Auto-generated constructor stub
	}

	//ACC:  16 ms
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length <= 1) return false;
        int i;
        int sum = 0;
        int max = 0;
        
        for (int num:nums) sum += num;
        
        if (sum % 2 != 0) return false;
        
        sum = sum/2;
        
        boolean[] dp = new boolean[sum+1];    //Index is the sum, i.e. dp[100] means whether could get a sum of 100 from the array
        
        dp[0] = true;
        for (int num:nums) {
            for (i=0; i<=max; i++) {
                if (dp[i] && ((i + num) <= sum)) {
                    dp[i+num] = true;
                    max = Math.max(max, i+num);
                    
                    if (max == sum) return true;
                }
            }
        }
        
        return dp[sum];
    }
}
