package com.leet;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. 
//You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
//However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

public class BestTimeBuySellStockII {

	public BestTimeBuySellStockII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] prices = {};
		
		System.out.println("Max Profit = " + maxProfit(prices));
	}
	
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int nDelta;
        int nProfit = 0;
        
        if (n == 0) return 0;
        
        for (int i=1; i<n; i++) {
        	nDelta = prices[i] - prices[i-1];
        	if (nDelta > 0) {
        		nProfit = nProfit + nDelta;
        	}
        }
        
        return nProfit;
    }
	
	
}
