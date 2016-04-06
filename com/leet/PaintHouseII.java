package com.leet;

//There are a row of n houses, each house can be painted with one of the k colors. 
//The cost of painting each house with a certain color is different. 
//You have to paint all the houses such that no two adjacent houses have the same color.
//
//The cost of painting each house with a certain color is represented by a n x k cost matrix. 
//For example, 
//costs[0][0] is the cost of painting house 0 with color 0; 
//costs[1][2] is the cost of painting house 1 with color 2, and so on... 
//Find the minimum cost to paint all houses.
//
//Note:
//All costs are positive integers.
//
//Follow up:
//Could you solve it in O(nk) runtime?
		
//Facebook
public class PaintHouseII {

	public PaintHouseII() {
		// TODO Auto-generated constructor stub
	}

	
	//Acc: 58%
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) return 0;
        int n = costs.length;
        int k = costs[0].length;
        int i,j;
        
        for (i=1; i<n; i++) {
        	for (j=0; j<k; j++) {
        		costs[i][j] = costs[i][j] + getMin(costs[i-1], k, j);
        	}
        }
        
        int nMin = costs[n-1][0];
        
        for (i=1; i<k; i++) {
        	nMin = Math.min(nMin, costs[n-1][i]);
        }
        
        return nMin;
    }
    
    
    private int getMin(int[] costs, int k, int nExcludedIdx) {
    	int nMin = Integer.MAX_VALUE;
    	
    	for (int i = 0; i<k; i++) {
    	    if (i != nExcludedIdx) {
    	    	nMin = Math.min(nMin, costs[i]);
    	    }
    	}
    	
    	return nMin;
    }

}
