package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. 
//You are asked to burst all the balloons. 
//If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
//Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
//
//Find the maximum coins you can collect by bursting the balloons wisely.
//
//Note: 
//(1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
//(2) 0 ¡Ü n ¡Ü 500, 0 ¡Ü nums[i] ¡Ü 100
//
//Example:
//
//Given [3, 1, 5, 8]
//
//Return 167
//
//    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167


//Google, Snapchat
//Hard
public class BurstBalloons {

	public BurstBalloons() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {3};
//		int[] nums = {9,76,64,21,97,60};   //1086136
//		int[] nums = {9,76,64,21,97,60,5};   //1088290
		
		System.out.println("#Coins = " + maxCoins(nums));
	}
	
	//Rule:  Each time, bust the minimal-value balloon in the middle (i.e. except the head and tail), 
	//process the head and tail in the end
    public int maxCoins(int[] nums) {        
        if (nums.length == 0) return 0;
        
        int[] numsNew = new int[nums.length+2];
        
    	int n = 0;
    	
    	for (int i:nums) {
    		if (i > 0)  numsNew[++n] = i;   //Remove the 0s
    	}
    	
    	numsNew[0] = 1;
    	numsNew[n+1] = 1;
    	n = n + 2;

    	int dp[][] = new int[n][n];   //dp[][] records the result through bursting the balloon between left/right

    	for (int len = 2; len <= n-1; len++) {  //Distance between left and right elements (distance = right - left)
    		
    		for (int left = 0; left < n - len; left++) {  //Position of left element
    			int right = left + len;   //Position of right position
    			
    			for (int k = left+1; k < right; k++) {   
    				//try every Element between left and right, to get maxmimal dp[left][right],
				// suppose this is the LAST balloon we burst
				// (i.e. by this step, the bursting between left--k, and k--right
				// (i.e. dp[left][k] and dp[k][right] are already calculated.
    				//dp[][] records the result through bursting the balloon between left/right,
    				//i.e. in case "a b c", it records the situation when b is bursted, 
				//     it does not record the result after bursting b,
    				//i.e. it does not contain the coins for "a c"
    				dp[left][right] = Math.max(dp[left][right], 
					   numsNew[left]*numsNew[k]*numsNew[right] + dp[left][k] + dp[k][right]);
    			}
    		}
    	}
    	    	
    	return dp[0][n-1];
    }	
	
    
    
    
    
    public static int getMaxCoin(List<Integer> lstnums) {
    		
    	int n = lstnums.size();
    	int dp[][] = new int[n][n];
    	
    	for (int len = 2; len <= n-1; len++) {  //Distance between left and right elements
    		
    		for (int left = 0; left < n - len; left++) {  //Position of left element
    			int right = left + len;   //Position of right position
    			
    			for (int k = left+1; k < right; k++) {   //try every Element between left and right, to get maxmimal dp[left][right]
    				dp[left][right] = Math.max(dp[left][right], lstnums.get(left)*lstnums.get(k)*lstnums.get(right) + dp[left][k] + dp[k][right]);
    			}
    		}
    	}
    	
    	
    	return dp[0][n-1];
    	
    }
        
    
    
    
    
    
    public static int kkk_getMaxCoin(List<Integer> lstnums) {
    	int nCoins = 0;
    	int nMinIdx;
    	int nMin;
    	
    	if (lstnums.size() == 1) return lstnums.get(0);
    	if (lstnums.size() == 2) return lstnums.get(0)*lstnums.get(1) + Math.max(lstnums.get(0), lstnums.get(1));
    	if (lstnums.size() == 3) {
    		nCoins = lstnums.get(0)*lstnums.get(1)*lstnums.get(2);
    		lstnums.remove(1);
    		return nCoins + getMaxCoin(lstnums);
    	}
    	
    	if (lstnums.size() == 4) {
        	nMin = lstnums.get(0);
        	nMinIdx = 0;

        	for (int i=1; i<lstnums.size(); i++) {
        		if (nMin > lstnums.get(i)) {
        			nMin = lstnums.get(i);
        			nMinIdx = i;
        		}
        	}
        	
        	if (nMinIdx == 1 || nMinIdx == 2) {
        		nCoins = lstnums.get(nMinIdx-1) * lstnums.get(nMinIdx) * lstnums.get(nMinIdx+1);
        		lstnums.remove(nMinIdx);
        		nCoins = nCoins + getMaxCoin(lstnums);
        	} else if (nMinIdx == 0) {
        		nCoins = lstnums.get(1) * lstnums.get(2) * lstnums.get(3);
        		lstnums.remove(2);
        		nCoins = nCoins + getMaxCoin(lstnums);
        	} else {        		
        		nCoins = lstnums.get(0) * lstnums.get(1) * lstnums.get(2);
        		lstnums.remove(1);
        		nCoins = nCoins + getMaxCoin(lstnums);
        	}
        	
        	return nCoins;
    	}
    	
    	nMin = lstnums.get(1);
    	nMinIdx = 1;
    	
    	//Find the min value in the middle (except the head and tail) and its index
    	for (int i=2; i<lstnums.size()-1; i++) {
    		if (nMin > lstnums.get(i)) {
    			nMin = lstnums.get(i);
    			nMinIdx = i;
    		}
    	}
    	
    	if (nMinIdx == 1 && lstnums.get(0) < nMin) 
    	
		nCoins = lstnums.get(nMinIdx-1) * lstnums.get(nMinIdx) * lstnums.get(nMinIdx+1);
    	
    	lstnums.remove(nMinIdx);
    	
    	nCoins = nCoins + getMaxCoin(lstnums);
    	
    	return nCoins;
    }   
    
    
    
    public static int Org_getMaxCoin(List<Integer> lstnums) {
    	int nCoins = 0;
    	int nMinIdx;
    	int nMin;
    	
    	if (lstnums.size() == 1) return lstnums.get(0);
    	if (lstnums.size() == 2) return lstnums.get(0)*lstnums.get(1) + Math.max(lstnums.get(0), lstnums.get(1));
    	
    	nMin = lstnums.get(1);
    	nMinIdx = 1;
    	
    	//Find the min value in the middle (except the head and tail) and its index
    	for (int i=2; i<lstnums.size()-1; i++) {
    		if (nMin > lstnums.get(i)) {
    			nMin = lstnums.get(i);
    			nMinIdx = i;
    		}
    	}
    	
        //	if (nMinIdx == 0) {
        //		nCoins = lstnums.get(0)* lstnums.get(1);
        //	} else if (nMinIdx == lstnums.size()-1) {
        //		nCoins = lstnums.get(nMinIdx-1) * lstnums.get(nMinIdx);
        //	} else {
        		nCoins = lstnums.get(nMinIdx-1) * lstnums.get(nMinIdx) * lstnums.get(nMinIdx+1);
        //	}
    	
    	lstnums.remove(nMinIdx);
    	
    	nCoins = nCoins + getMaxCoin(lstnums);
    	
    	return nCoins;
    }
    
}
