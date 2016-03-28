package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given an array of n integers nums and a target, 
//find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
//For example, given nums = [-2, 0, 1, 3], and target = 2.
//
//Return 2. Because there are two triplets which sums are less than 2:
//
//[-2, 0, 1]
//[-2, 0, 3]
//		
//Follow up:
//Could you solve it in O(n2) runtime?

//Google
public class ThreeSumSmaller {

	public ThreeSumSmaller() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}

	//Accepted:  75%
	//Attention: This question does not require us to output the triplets, it only requires us to count the number of triplets
	//We could find all 3-element sets, whose sum is < target, because all 3 different elements could have i < j < k (index) (although we don't find out)
	//That's why we could use sort
	//
	//End if the array is sorted, and if nums[start] + nums[end] < target, then all elements between start~end could be summed with the nums[start] to meet the requirement
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        int n = nums.length;
        int i, start, end;
        int nCount = 0;
        
        Arrays.sort(nums);
        
        for (i=0; i<n-2; i++) {
            start = i+1;
            end = n-1;
            
            while (start < end) {
                if (nums[i] + nums[start] + nums[end] < target) {
                    nCount += end-start;   //For the end position, all elements between start+1 (including) and end will meet the requirement < target
                    start++;
                } else {
                    end--;
                }
            }
        }
        
        return nCount;
    }
}
