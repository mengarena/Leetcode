package com.leet;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//Note:
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

public class BestTimeBuySellStockIII {

	public BestTimeBuySellStockIII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
	    //int[] prices = {6,1,3,2,4,7};   //7
		//int[] prices = {4,7,1,2};  //4
	    //int[] prices = {3,3,5,0,0,3,1,4};  //6
	    //int[] prices = {2,1,4,5,2,9,7}; //11
	    //int[] prices = {1,2,4,2,5,7,2,4,9,0}; //13
		int[] prices = {8,6,4,3,3,2,3,5,8,3,8,2,6};  //11
	    //int[] prices = new int[10000];
	    //for (int i=0; i<9000; i++) prices[i] = 10000-i;
	    //for (int i=9000; i<10000; i++) prices[i] = 0;
		
	    
	    System.out.println(maxProfit(prices));
	}
	
	//Attention:  buy on day i, but sell not necessarily on day i+1, it could be on day i+x
	
	//** This solution is based on the video: https://www.youtube.com/watch?v=oDhu5uGq_ic&feature=youtu.be
	//
	//The original DP function:  T[i][j] --- i is the index of transaction, j is the index of day for the price
	//
	//T[i][j] = max(T[i][j-1], prices[j]-prices[m] + T[i-1][m])
	//
	//In this equation; T[i][j] selects between two options (which are in the max function)
	//The first option means on jth day, do nothing, so the profit will be the profit of last day, which is T[i][j-1]
	//The second option is try to sell with prices[j], say this stock was buy on day m, 
	//so the total profit will be the profit earned on jth day (prices[j]-prices[m]) plus the profit earned by mth day
	//
	//*in any one day, could sell and then buy
	//
	//prices[j]-prices[m] + T[i-1][m] could be rewrote as prices[j] + (T[i-1][m]-prices[m])
	//
	//For ith transaction, if use DP to calculate from day 1 to day n-1, 
	//we will find that (T[i-1][m]-prices[m]) will be the maximal of the past (i.e. before we come to day j)
	//So don't need to calculate the maximal value again and again. only when current difference is larger, 
	//then we update it (i.e. the nMaxDiff in the code)
    public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2) return 0;
		int n = prices.length;
		int[][] T = new int[2 + 1][n]; //Profit
		int nMaxDiff = 0;

		for (int i = 1; i <= 2; i++) {
			nMaxDiff = -(prices[0]);
			for (int j = 1; j < n; j++) {
				T[i][j] = Math.max(T[i][j - 1], prices[j] + nMaxDiff);
				nMaxDiff = Math.max(nMaxDiff, T[i - 1][j] - prices[j]);
			}
		}

		return T[2][n - 1];
	}
		
}
