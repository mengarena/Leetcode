package com.leet;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete at most k transactions.
//
//Note:
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

public class BestTimeBuySellStockIV {

	public BestTimeBuySellStockIV() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
	//Solution A: combined version of Solution B
	//When combined together, is slight quicker
    public int maxProfitGood(int k, int[] prices) {
		if (prices == null || prices.length < 2) return 0;
		int n = prices.length;
		
		//Although we could do as many transaction as we want, 
		//but buy stock and sell on the same day (could be many times) does not earn money.
		//To earn money, we must sell at high price than the buying price (meaningful transaction)
		//If we have n days, at most we have (meaningful) n/2 rising! (i.e. day i's prices > day i-1's price 
		//(on one day only complete one operation)
		//So when k >= n/2, it becomes [Best Time Buy and Sell Stock II]
		if (k >= n/2) {
			int nMaxProfit = 0;
			int nDiff = 0;
			for (int i=1; i<n; i++) {
				nDiff = prices[i]-prices[i-1];
				if (nDiff > 0) nMaxProfit += nDiff;
			}
			return nMaxProfit;
		}
		
		int KK = Math.min(k, prices.length-1);
		
		int[][] T = new int[KK + 1][n];
		int nMaxDiff = 0;

		for (int i = 1; i <= KK; i++) {
			nMaxDiff = -(prices[0]);
			for (int j = 1; j < n; j++) {
				T[i][j] = Math.max(T[i][j - 1], prices[j] + nMaxDiff);
				nMaxDiff = Math.max(nMaxDiff, T[i - 1][j] - prices[j]);
			}
		}

		return T[KK][n - 1];
    }
	
	
    //Solution B:
	//The problem is divided into two case:
	//1) k >= n/2, which makes the problem become Best Time Buy and Sell Stock II    
	//2) k < n/2, then we use maxProfitHelper
    public int maxProfit(int k, int[] prices) {
		if (prices == null || prices.length < 2) return 0;
		int n = prices.length;
		
		if (k >= n/2) {   //A valid transaction needs at least 2 days, so k >= n/2 means we could do all possible useful transactions
			int nMaxProfit = 0;
			int nDiff = 0;
			for (int i=1; i<n; i++) {
				nDiff = prices[i]-prices[i-1];
				if (nDiff > 0) nMaxProfit += nDiff;
			}
			return nMaxProfit;
		} else {
			return maxProfitHelper(k, prices);
		}				
    }
    
	//** This solution is based on the video: https://www.youtube.com/watch?v=oDhu5uGq_ic&feature=youtu.be
	//
	//The original DP function:  T[i][j] --- i is the index of transation, j is the index of day for the price
	//
	//T[i][j] = max(T[i][j-1], prices[j]-prices[m] + T[i-1][m])
	//
	//In this equation; T[i][j] selects between two options (which are in the max function)
	//The first option (T[i][j-1]) means on jth day, do nothing, so the profit will be the profit of last day, which is T[i][j-1]
	//The second option (prices[j]-prices[m] + T[i-1][m]) is try to sell with prices[j], say this stock was buy on day m, 
	//so the total profit will be the profit earned on jth day (prices[j]-prices[m]) plus the profit earned by mth day
	//*in any one day, could sell and then buy
	//
	//prices[j]-prices[m] + T[i-1][m] could be rewrote as prices[j] + (T[i-1][m]-prices[m])
	//
	//For ith transation, if use DP to calculate from day 1 to day n-1, 
	//we will find that (T[i-1][m]-prices[m]) will be the maximal of the past (i.e. before we come to day j)
	//So don't need to calculate the maximal value again and again. only when current different is larger, 
	//then we update it (i.e. the nMaxDiff in the code) to optimize the processing (Refer to the Youtube video)
    private int maxProfitHelper(int k, int[] prices) {
		int n = prices.length;		
		int[][] T = new int[k + 1][n];
		int nMaxDiff = 0;

		for (int i = 1; i <= k; i++) {
			nMaxDiff = -(prices[0]);
			for (int j = 1; j < n; j++) {
				T[i][j] = Math.max(T[i][j - 1], prices[j] + nMaxDiff);
				nMaxDiff = Math.max(nMaxDiff, T[i - 1][j] - prices[j]);
			}
		}

		return T[k][n - 1];
    }
}
