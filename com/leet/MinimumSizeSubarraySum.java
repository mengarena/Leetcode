package com.leet;

import java.util.Arrays;

//Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ¡Ý s. 
//If there isn't one, return 0 instead.
//
//For example, given the array [2,3,1,2,4,3] and s = 7,
//the subarray [4,3] has the minimal length under the problem constraint.
//
//click to show more practice.
//
//More practice:
//If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

//Facebook
public class MinimumSizeSubarraySum {

	public MinimumSizeSubarraySum() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {4, Integer.MAX_VALUE, 5, 2}; //{2,3,1,2,4,3};
		int s = Integer.MAX_VALUE-2;
		
		System.out.println("Len = " + minSubArrayLen(s, nums));
	}
	
	
	//Pay attention: the subarray must be continuous
    public int minSubArrayLen(int s, int[] nums) {
    	if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int nSum = 0;
        int nStartPos = 0;
        int nEndPos = 0;
        int nMinLen = n+1;
        boolean bOverflow = false;
        
        while (nEndPos <= n-1) {
        	if (nSum < s) {
        		if (nSum > Integer.MAX_VALUE-nums[nEndPos]) {
        			bOverflow = true;
        		} else {
        			nSum = nSum + nums[nEndPos];
        		}
        		
        		nEndPos++;
        	}
        	
        	while (nSum >= s || bOverflow == true) {
        		nMinLen = Math.min(nMinLen, nEndPos-nStartPos);
        		if (nMinLen == 1) break;
        		if (bOverflow == true) {
        			nSum = nSum - nums[nStartPos];
        			if (nSum <= Integer.MAX_VALUE-nums[nEndPos-1]) {
        				nSum = nSum + nums[nEndPos-1];
        				bOverflow = false;
        			}
        		} else {
        			nSum = nSum - nums[nStartPos];
        		}
        		
        		
        		nStartPos++;
        	}
        	
        	if (nMinLen == 1) break;
        	
        }
        
        if (nMinLen == n+1) {
        	nMinLen = 0;
        }
        
        return nMinLen;
    }	
	
/*    
	//Solution for non-continuous subarray
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int i;
        int nSum = 0;
        int nLen = 0;
        
        Arrays.sort(nums);
        
        for (i=n-1; i>=0; i--) {
        	nLen++;
        	
        	if (nSum > Integer.MAX_VALUE-nums[i]) {
        		nSum = s;
        		break;
        	}
        	
        	nSum = nSum + nums[i];

        	if (nSum >= s) break;
        	
        }
        
        if (nSum < s) return 0;
        
        return nLen;
    }
*/
    
}
