/*
494. Target Sum 

You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5

Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.

Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
*/

//Medium
//Google, Facebook

public class Solution {
	
	//Faster:  DP
    //
    //  The recursive solution is very slow, because its runtime is exponential
    //
    //  The original problem statement is equivalent to:
    //  Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
    //
    //  Let P be the positive subset and N be the negative subset
    //  For example:
    //    Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
    //  Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
    //
    //  Then let's see how this can be converted to a subset sum problem:
    //
    //                     sum(P) - sum(N) = target
    //   sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
    //                          2 * sum(P) = target + sum(nums)
    //
    //  So the original problem has been converted to a subset sum problem as follows:
    //  Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
    //
    //  Note that the above formula has proved that target + sum(nums) must be even
    //  We can use that fact to quickly identify inputs that do not have a solution 
	
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0;
        
        for (int num:nums) sum += num;
        
        return (sum < S || (sum + S) % 2 == 1)? 0:subSet(nums, (sum + S) >>> 1);
    }
    
    private int subSet(int[] nums, int target) {
        int[] dp = new int[target+1];
        
        dp[0] = 1;  //zero elements add up to be target = 0
        
        for (int num:nums) {
        
            for (int n=target; n>=num; n--) {
                dp[n] += dp[n-num];
            }
        }
        
        return dp[target];
    }	
	
	
	
	//DFS
    public int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) return 0;

        return findTargetSumWaysHelper(nums, 0, S);
    }
    
    private int findTargetSumWaysHelper(int[] nums, int startIdx, int target) {
        if (startIdx == nums.length-1) {
            if (nums[startIdx] == target || nums[startIdx] == -target) {
                if (target == 0) return 2;   //If 0, two posibilities: +0, -0 
                return 1;
            } else {
                return 0;
            }
        }
        
        int count = 0;
        
        count = findTargetSumWaysHelper(nums, startIdx+1, target - nums[startIdx]);
        
        count += findTargetSumWaysHelper(nums, startIdx+1, target + nums[startIdx]);

        return count;
    }
    
}
