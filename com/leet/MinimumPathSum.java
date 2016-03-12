package com.leet;

//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
//
//Note: You can only move either down or right at any point in time.

public class MinimumPathSum {

	public MinimumPathSum() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[][] grid = {{1}};
		
		System.out.println("Min Sum = " + minPathSum(grid));
	}
	

    public int minPathSum(int[][] grid) {
    	if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    	int i,j;
        int m = grid.length;
        int n = grid[0].length;
        int[][] narrSum = new int[m][n];
        narrSum[0][0] = grid[0][0];
        
        for (i=1; i<m; i++) narrSum[i][0] = narrSum[i-1][0] + grid[i][0];
        for (i=1; i<n; i++) narrSum[0][i] = narrSum[0][i-1] + grid[0][i];
        
        for (i=1; i<m; i++) {
        	for (j=1; j<n; j++) {
        		narrSum[i][j] = Math.min(narrSum[i-1][j], narrSum[i][j-1]) + grid[i][j];
        	}
        }
        
        return narrSum[m-1][n-1];
    }
	
}
