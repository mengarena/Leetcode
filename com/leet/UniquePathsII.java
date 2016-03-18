package com.leet;

//Follow up for "Unique Paths":
//
//Now consider if some obstacles are added to the grids. How many unique paths would there be?
//
//An obstacle and empty space is marked as 1 and 0 respectively in the grid.
//
//For example,
//There is one obstacle in the middle of a 3x3 grid as illustrated below.
//
//[
//  [0,0,0],
//  [0,1,0],
//  [0,0,0]
//]
//The total number of unique paths is 2.
//
//Note: m and n will be at most 100.

public class UniquePathsII {

	public UniquePathsII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[][] obstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
		
		System.out.println("#Unique Paths = " + uniquePathsWithObstacles(obstacleGrid));
	}
	
	
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) return 0;
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
        int narrPath[][] = new int[m][n];
        int i, j;
        
        for (i=0; i<m; i++) {
        	if (i == 0) {
        		if (obstacleGrid[i][0] == 1) {
        			narrPath[i][0] = 0;
        		} else {
        			narrPath[i][0] = 1;
        		}
        	} else {
        		if (obstacleGrid[i][0] == 1) {
        			narrPath[i][0] = 0;
        		} else {
        			if (narrPath[i-1][0] == 0) {
        				narrPath[i][0] = 0;
        			} else {
        				narrPath[i][0] = 1;
        			}
        		}
        	}
        }
        
        
        for (i=1; i<n; i++) {
        	if (obstacleGrid[0][i] == 1) {
        		narrPath[0][i] = 0;
        	} else {
        		if (narrPath[0][i-1] == 0) {
        			narrPath[0][i] = 0;
        		} else {
        			narrPath[0][i] = 1;
        		}
        	}
        }

        
        for (i=1; i<m; i++) {
        	for (j=1; j<n; j++) {
        		if (obstacleGrid[i][j] == 1) {
        			narrPath[i][j] = 0;
        		} else {
        			narrPath[i][j] = narrPath[i-1][j] + narrPath[i][j-1];
        		}
        	}
        }
        
        return narrPath[m-1][n-1];
        
    }	
}
