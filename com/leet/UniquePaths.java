package com.leet;

//A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
//
//The robot can only move either down or right at any point in time. 
//The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
//
//How many possible unique paths are there?
//
//Above is a 3 x 7 grid. How many possible unique paths are there?
//
//Note: m and n will be at most 100.

public class UniquePaths {

	public UniquePaths() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int m = 2;
		int n = 2;
		
		System.out.println("#Path = " + uniquePaths(m, n));
	}
	
    public int uniquePaths(int m, int n) {
        
        int narrPath[][] = new int[m][n];
        int i, j;
        
        for (i=0; i<m; i++) narrPath[i][0] = 1;
        for (i=0; i<n; i++) narrPath[0][i] = 1;

        for (i=1; i<m; i++) {
        	for (j=1; j<n; j++) {
        		narrPath[i][j] = narrPath[i-1][j] + narrPath[i][j-1];
        	}
        }
        
        return narrPath[m-1][n-1];
    }
    
    
	/*
    public int uniquePaths(int m, int n) {
        int nPathCnt = 0;
                
        if (m > 1 && n > 1) {
        	nPathCnt = uniquePaths(m, n-1) + uniquePaths(m-1, n);
        } else if (m == 1 || n == 1) {
        	nPathCnt = 1;
        }
        
        return nPathCnt;
    }
    */
}
