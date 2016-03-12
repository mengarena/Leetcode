package com.leet;

//According to the Wikipedia's article: 
//"The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
//
//Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
//Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
//
//Any live cell with fewer than two live neighbors dies, as if caused by under-population.
//Any live cell with two or three live neighbors lives on to the next generation.
//Any live cell with more than three live neighbors dies, as if by over-population..
//Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
//Write a function to compute the next state (after one update) of the board given its current state.
//
//Follow up: 
//Could you solve it in-place? 
//Remember that the board needs to be updated at the same time: 
//You cannot update some cells first and then use their updated values to update other cells.
//In this question, we represent the board using a 2D array. 
//In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
//How would you address these problems?

public class GameLife {

	public GameLife() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[][] board = {{1,1}, {1,0}};
		gameOfLife(board);
		
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				System.out.print(board[i][j] + ",");
			}
			
			System.out.println();
		}
		
	}
	

	//Count #neighbors of each cell, if live cell, #neighbors+9; else -9
    public void gameOfLife(int[][] board) {
    	if (board == null || board.length == 0 || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;
        int i, j;
        int nNeighLiveCnt = 0;
        
        for (i=0; i<m; i++) {
        	for (j=0; j<n; j++) {
        		nNeighLiveCnt = 0;
        		
        		if (i-1 >= 0) {
        			if (j-1 >= 0) {
        				if (board[i-1][j-1] == 1 || board[i-1][j-1] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        			}
        			
        			if (board[i-1][j] == 1 || board[i-1][j] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        			
        			if (j+1 <= n-1) {
        				if (board[i-1][j+1] == 1 || board[i-1][j+1] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        			}
        		}
        
        		if (j-1 >= 0) {
        			if (board[i][j-1] == 1 || board[i][j-1] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        		}
        		if (j+1 <= n-1) {
        			if (board[i][j+1] == 1 || board[i][j+1] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        		}
        		
        		if (i+1 <= m-1) {
        			if (j-1 >= 0) {
        				if (board[i+1][j-1] == 1 || board[i+1][j-1] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        			}
        			
        			if (board[i+1][j] == 1 || board[i+1][j] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        			
        			if (j+1 <= n-1) {
        				if (board[i+1][j+1] == 1 || board[i+1][j+1] >= 9) nNeighLiveCnt = nNeighLiveCnt + 1;
        			}
        		}
        		
        		if (board[i][j] == 1)  {
        			nNeighLiveCnt = nNeighLiveCnt + 9;
        		} else {
        			nNeighLiveCnt = nNeighLiveCnt - 9;
        		}
        		
        		board[i][j] = nNeighLiveCnt;
        	}
        }
        
        for (i=0; i<m; i++) {
        	for (j=0; j<n; j++) {
        		if (board[i][j] < 0) {  //Dead cell
        			if (board[i][j] + 9 == 3) {
        				board[i][j] = 1;  //Dead cell with exactly three live neighbors     			
        			} else {
        				board[i][j] = 0;
        			}
        		} else if (board[i][j] == 9) {   //One live cell, no neighbor
        			board[i][j] = 0;
        		} else {  //Live cell
        			if ((board[i][j] % 9) < 2) {  //Any live cell with fewer than two live neighbors dies, as if caused by under-population.
        				board[i][j] = 0; 
        			} else if ((board[i][j] % 9) > 3) {  //Any live cell with more than three live neighbors dies, as if by over-population..
        				board[i][j] = 0; 
        			} else {  //Any live cell with two or three live neighbors lives on to the next generation.
        				board[i][j] = 1;
        			}
        		}
        		
        	}
        }
        
    }
    
}
