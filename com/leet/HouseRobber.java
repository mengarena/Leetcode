package com.leet;

//You are a professional robber planning to rob houses along a street. 
//Each house has a certain amount of money stashed, 
//the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and 
//it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//Given a list of non-negative integers representing the amount of money of each house, 
//determine the maximum amount of money you can rob tonight without alerting the police.

public class HouseRobber {

	public HouseRobber() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {};
		
		System.out.println("Robbed Amount = " + rob(nums));
	}
	
    public int rob(int[] nums) {
    	if (nums == null) return 0;
    	if (nums.length == 0) return 0;
    	int n = nums.length;
    	
    	int narrDP[] = new int[n];    //Robbed amount at ith house
    	
    	narrDP[0] = nums[0];
    	if (n > 1) {
    		narrDP[1] = Math.max(nums[0], nums[1]);
    		
    		for (int i=2; i<n; i++) {
    			narrDP[i] = Math.max(narrDP[i-1], narrDP[i-2] + nums[i]);
    		}
    	}
    	
    	return narrDP[n-1];
    }
	
	
/*	Works, but Time limit exceeded
    public int rob(int[] nums) {
        int nAmount = 0;
        
        if (nums.length == 0) return 0;
        
        nAmount = myRob(nums, -2, 0);
        
        return nAmount;
    }
	
    public int myRob(int[] nums, int nPrevIdx, int nCurIdx) {     	
    	if (nums.length <= nPrevIdx + 1) {
    		return 0;
    	} else if (nums.length < nCurIdx+1) {
    		return 0;
    	} else if (nums.length == nCurIdx+1) {
    		if (nCurIdx - nPrevIdx == 1) {
    			return 0;
    		} else {
    			return nums[nCurIdx];
    		}
    	}
    	
    	if (nCurIdx - nPrevIdx == 1) {
    		return myRob(nums, nPrevIdx, nCurIdx+1);
    	} else {
    		int nTmp1 = nums[nCurIdx] + myRob(nums, nCurIdx, nCurIdx+1);
    		int nTmp2 = myRob(nums, nCurIdx-1, nCurIdx);
    		
    		return Math.max(nTmp1, nTmp2);
    	}
    	
    }
*/    
}
