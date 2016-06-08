package com.leet;

//Best Time to Buy and Sell Stock with Cooldown

//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. 
//You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//
//Example:
//
//prices = [1, 2, 3, 0, 2]
//maxProfit = 3
//transactions = [buy, sell, cooldown, buy, sell]


//Google
public class BuySellStockWithCooldown {

	public BuySellStockWithCooldown() {
		// TODO Auto-generated constructor stub
	}

//	http://bookshadow.com/weblog/2015/11/24/leetcode-best-time-to-buy-and-sell-stock-with-cooldown/#0-tsina-1-7114-397232819ff9a47a7b7e80a40613cfe1
//	
//	sells[i]  Max profit on day i without holding stock
//	buys[i]   Max profit on day i with holding stock
//
//	Initialization:£º
//	sells[0] = 0
//	sells[1] = max(0, prices[1] - prices[0])
//	buys[0] = -prices[0]
//	buys[1] = max(-prices[0], -prices[1])
//	
//	State transaction:£º
//	sells[i] = max(sells[i - 1], buys[i - 1] + prices[i])
//	buys[i] = max(buys[i - 1], sells[i - 2] - prices[i])
// 
//  The maximal profit is sells[-1]	  //i.e. Last one
	
	//ACC
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        int[] sells = new int[n];   //Max profit by day i with a sell on day i
        int[] buys = new int[n];    //Max profit by day i with a buy on day i
        
        sells[0] = 0; 
        sells[1] = Math.max(0, prices[1] - prices[0]);
        buys[0] = -prices[0];   
        buys[1] = Math.max(-prices[0], -prices[1]);
        
        for (int i=2; i<n; i++) {
            sells[i] = Math.max(sells[i-1], buys[i-1] + prices[i]);  //do nothing  OR   do a sell based on what has bought by day i-1
            buys[i] = Math.max(buys[i-1], sells[i-2] - prices[i]);   //do nothing  OR   do a buy based on the profit from sells[i-2] (i.e. there is a cooldown day between)
        }

        return sells[n-1];
    }	
	
    
    
	public void run() {
		int[] prices = {1, 2, 3, 0, 2};
		
		System.out.println("Max Profit: " + maxProfit(prices));
	}
	
	
	
	
	public int maxProfitA(int[] prices) {
		int n = prices.length;
		if (n <= 1) return 0;
		int[] narrDpProfit = new int[n];
		
		narrDpProfit[0] = 0;
		narrDpProfit[1] = Math.max(0, prices[1]-prices[0]);
		
		for (int i=2; i<n; i++) {
			narrDpProfit[i] = Math.max(narrDpProfit[i-1], narrDpProfit[i-2] + Math.max(prices[i]-prices[i-1], 0));
		}
		
		return narrDpProfit[n-1];
	}
	
	
	
	
	
	
	
	
	
	
	
	
/*	
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        if (n == 0) return 0;
        
        int buy[] = new int[n];
        int sell[] = new int[n];
        
        buy[0] = -1*prices[0];
        
        if (n > 1) {
        	buy[1] = Math.max(-prices[0], -prices[1]);
        	sell[1] = Math.max(0, prices[1]-prices[0]);
        }
        
        for (int i=2; i<n; i++) {
        	buy[i] = Math.max(sell[i-2] - prices[i], buy[i-1]);
        	sell[i] = Math.max(buy[i-1] + prices[i], sell[i-1]);
        }
        
        
        return sell[n-1];
    }
*/    
    
}
