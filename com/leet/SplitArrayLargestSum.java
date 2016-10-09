package com.leet;

//Given an array which consists of non-negative integers and an integer m, 
//you can split the array into m non-empty continuous subarrays. 
//Write an algorithm to minimize the largest sum among these m subarrays.
//
//Note:
//Given m satisfies the following constraint: 1 ¡Ü m ¡Ü length(nums) ¡Ü 14,000.
//
//Examples:
//
//Input:
//nums = [7,2,5,10,8]
//m = 2
//
//Output:
//18
//
//Explanation:
//There are four ways to split nums into two subarrays.
//The best way is to split it into [7,2,5] and [10,8],
//where the largest sum among the two subarrays is only 18.
//
//
//Baidu
public class SplitArrayLargestSum {

	public SplitArrayLargestSum() {
		// TODO Auto-generated constructor stub
	}

	//ACC:  10ms
	//
	//Strategy:
	//The minimal largest sum should be between the max (single) element (in case each subarray has only one element) and the sum of the array (in case m = 1)
	//
	//Use binary search, select a threshold for subarray, to see whether it is possible to split into m subarray
	//If for a threshold, there are more than m subarray, we should increase the threshold
	//Otherwise, we need to decrease the threshold
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length < m) return 0;
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int i;
        long sum = 0;
        
        for (i=0; i<n; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        
        if (m == 1) return (int) sum;
        
        long low = max;
        long up = sum;
        long mid;    //The threshold for subarray
        
        //Choose different threshold and try to split the original array (from left) into subarrays
        while (low < up) {
            mid = low + (up - low)/2;
            if (isValid(nums, m, mid)) {
                up = mid;
            } else {
                low = mid + 1;
            } 
        }
        
        return (int)up;
    }
    
    //Check wheather it is possible to divide the original array into m subarrays based on the threshold for each subarray
    private boolean isValid(int[] nums, int m, long threshold) {
        int count = 1;   //Number of subarray whose sum does not exceed threshold
        long sum = 0;
        
        for (int num:nums) {
            sum += num;
            
            if (sum > threshold) {  //Means should be one subarray before this num, next subarray starts from this num
                sum = num;
                count++;
                
                if (count > m) {   //Already exceed m subarrays, means this way of splitting arrays does not work, need to increase the threshold
                    return false;
                }
            }
        }
        
        //We might have less than m subarrays, the threshold might work, but might could decrease
        return true;
    }

}
