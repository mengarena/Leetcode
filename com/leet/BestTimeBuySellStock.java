package com.leet;

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
//design an algorithm to find the maximum profit.

public class BestTimeBuySellStock {

	public BestTimeBuySellStock() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int prices[] = {2,1,2,0,1};
		
		System.out.println("Max Profit = " + maxProfit(prices));
	}

	
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int nMin = prices[0];
        int nMaxProfit = 0;
        int i;
        
        for (i=1; i<n; i++) {
        	nMaxProfit = Math.max(nMaxProfit, prices[i]-nMin);
        	
        	nMin = Math.min(nMin, prices[i]);
        }
        
        
        return nMaxProfit;
    }
	
}
