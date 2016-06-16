package com.leet;

import java.util.Arrays;

//You are given coins of different denominations and a total amount of money amount. 
//Write a function to compute the fewest number of coins that you need to make up that amount. 
//If that amount of money cannot be made up by any combination of the coins, return -1.
//
//Example 1:
//coins = [1, 2, 5], amount = 11
//return 3 (11 = 5 + 5 + 1)
//
//Example 2:
//coins = [2], amount = 3
//return -1
//
//Note:
//You may assume that you have an infinite number of each kind of coin.

public class CoinChange {

	public CoinChange() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] coins = {346,29,395,188,155,109};
		int amount = 4;
		
		System.out.println("Coin Change = " + coinChange(coins, amount));
	}
	

	//ACC
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins == null || coins.length == 0) return -1;
        int n = coins.length;
        int i,j;
        int[] dp = new int[amount+1];
        
        dp[0] = 0;
        for (i=1; i<=amount; i++) dp[i] = Integer.MAX_VALUE;
        
        for (i=1; i<=amount; i++) {
            for (j=0; j<n; j++) {
                if (i-coins[j] >= 0 && dp[i-coins[j]] < Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i-coins[j]]);
                }
            }
        }

        if (dp[amount] == Integer.MAX_VALUE) return -1;
        
        return dp[amount];
    }
    
	
    //ACC
	public int coinChangeA(int[] coins, int amount) {
        if (amount == 0) return 0;
		if (coins == null || coins.length == 0) return -1;

        int n = coins.length;
        Arrays.sort(coins);
        int i,j;
        if (amount < coins[0]) return -1;
        
        int nDP[] = new int[amount+1];  //Min #coin of for amount i
        for (i=0; i<=amount; i++) nDP[i] = Integer.MAX_VALUE;
        nDP[0] = 0; 
        
        for (i=0; i<=amount; i++) {
        	for (j=0; j<n; j++) {
        		if (i <= amount - coins[j]) {
        			if (nDP[i] <= Integer.MAX_VALUE-1) nDP[i+coins[j]] = Math.min(nDP[i+coins[j]], nDP[i]+1);
        		}
        	}
        }
               
        if (nDP[amount] == Integer.MAX_VALUE) {
        	return -1;
        } else {
        	return nDP[amount];
        }
	}
	
	
/* Works, but exceed time limit	
	public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        int n = coins.length;
        Arrays.sort(coins);
        
        int nMinCountTmp = coinChangeHelper(coins, amount, n-1);
        
        return nMinCountTmp;
	}
	
	
    private int coinChangeHelper(int[] coins, int amount, int nStartIdx) {
    	int i;
    	int nRemained = 0;
    	int nCount;
    	int nTmpMinCount;
    	
    	if (amount == 0) return 0;
    	if (nStartIdx < 0) return -1;
    
    	nCount = amount/coins[nStartIdx];
    	
    	nTmpMinCount = -1;
    	
    	for (i=nCount; i>=0; i--) {   //Number of coins[nStartIdx]
    		nRemained = amount - coins[nStartIdx]*i;
    		int nCountTmp = coinChangeHelper(coins, nRemained, nStartIdx-1);
    		if (nCountTmp != -1) {
    			if (nTmpMinCount == -1) {
    				nTmpMinCount = i+nCountTmp;
    			} else {
    				nTmpMinCount = Math.min(nTmpMinCount, i+nCountTmp);
    			}
    		} else {
    			if (i == 0) {
    				if (nTmpMinCount == -1) return -1;
    			} else {
    				continue;
    			}
    		}
    	}
    	    	
    	return nTmpMinCount;
    }
*/
    
    
}
