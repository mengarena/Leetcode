package com.leet;

//The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. 
//The dungeon consists of M x N rooms laid out in a 2D grid. 
//Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.
//
//The knight has an initial health point represented by a positive integer. 
//If at any point his health point drops to 0 or below, he dies immediately.
//
//Some of the rooms are guarded by demons, 
//so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).
//
//In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
//
//
//Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.
//
//For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
//
//-2 (K)	-3	3
//-5	-10	1
//10	30	-5 (P)
//
//Notes:
//
//The knight's health has no upper bound.
//Any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.


public class DungeonGame {

	public DungeonGame() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[][] dungeon = {{-2,-3, 3}, {-5, -10, 1}, {10, 30, -5}};
		
	    int nInit = calculateMinimumHP(dungeon);
	    
	    System.out.println(nInit);
	}
	
	
	//ACC: 52%  (Basically, same as the solution below, but simplified a little)
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length;
        int n = dungeon[0].length;
        int i, j;
        int[][] dpInit = new int[m][n];

        dpInit[m-1][n-1] = dungeon[m-1][n-1] >= 0? 1:1-dungeon[m-1][n-1];
        
        for (i=m-2; i>=0; i--) {
            dpInit[i][n-1] = dungeon[i][n-1] >= dpInit[i+1][n-1]? 1:dpInit[i+1][n-1] - dungeon[i][n-1];
        }
        
        for (i=n-2; i>=0; i--) {
            dpInit[m-1][i] = dungeon[m-1][i] >= dpInit[m-1][i+1]? 1:dpInit[m-1][i+1] - dungeon[m-1][i];
        }
        
        for (i=m-2; i>=0; i--) {
            for (j=n-2; j>=0; j--) {
                dpInit[i][j] = Math.max(1, Math.min(dpInit[i+1][j], dpInit[i][j+1]) - dungeon[i][j]);
            }
        }
        
        return dpInit[0][0];
    }
	
	
	
	
	//ACC:  10%
	//
	//DP: but from the last cell to the first cell
	//At each cell, need to make sure:
	//Initial value should be at least 1, and also make sure with the initial value and the value in this cell, 
	//should meet the requirement for the initial value for the next (i.e. right, bottom) cell
    public int calculateMinimumHPA(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) return 0;
        int m = dungeon.length;
        int n = dungeon[0].length;
        int dp[][] = new int[m][n];   //The initial value when the knight enters the room [i,j], each initial value must >= 1, otherwise the knight dies
        int i,j;
        
        dp[m-1][n-1] = dungeon[m-1][n-1] >= 0? 1:1-dungeon[m-1][n-1];
        
        //The most right column
        for (i=m-2; i>=0; i--) {
        	dp[i][n-1] = dungeon[i][n-1] >= dp[i+1][n-1]? 1:dp[i+1][n-1]-dungeon[i][n-1];
        }
        
        //The most bottom row
        for (j=n-2; j>=0; j--) {
        	dp[m-1][j] = dungeon[m-1][j] >= dp[m-1][j+1]? 1:dp[m-1][j+1]-dungeon[m-1][j];
        }
        
        for (i=m-2; i>=0; i--) {
        	for (j=n-2; j>=0; j--) {
        		int fromRight = dungeon[i][j] >= dp[i][j+1]? 1:dp[i][j+1]-dungeon[i][j];
        		
        		if (fromRight == 1) {  //If it is 1, don't need to check fromBottom, because the minimal allowed value is 1 and there is a valid path goes through right
        		    dp[i][j] = 1;
        		    continue;
        		}
        		
        		int fromBottom = dungeon[i][j] >= dp[i+1][j]? 1:dp[i+1][j]-dungeon[i][j];
        		dp[i][j] = Math.min(fromRight, fromBottom);
        	}
        }
        		
        return dp[0][0];
    }

}
