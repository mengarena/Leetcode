package com.leet;

//Write a program to solve a Sudoku puzzle by filling the empty cells.
//
//Empty cells are indicated by the character '.'.
//
//You may assume that there will be only one unique solution.
//
//
//A sudoku puzzle...
//
//
//...and its solution numbers marked in red.

//Snapchat, Uber
public class SudokuSolver {

	public SudokuSolver() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:  23%
    public void solveSudoku(char[][] board) {
    	int[][] status = new int[9][9];   //Three status:  0:  non-decided;  1: set with try  2: Pre-set (fixed)
    	int i,j;
    	
    	for (i=0; i<9; i++) {
    		for (j=0; j<9; j++) {
    			status[i][j] = board[i][j] > '0'? 2:0;
    		}
    	}
    	
    	solver(board, status, 0, 0);
    }

    
    private boolean solver(char[][] board, int[][] status, int x, int y) {
    	int i, j, k;
    	int count = 0;
    	
    	if (x >= 9) {
    	    for (i=0; i<9; i++) {
    	    	for (j=0; j<9; j++) {
    	    		count += status[i][j] == 0? 0:1;
    	    	}
    	    }
    	    
    	    return count == 81;
    	}
    	
    	if (status[x][y] >= 1) { //This cell has been set (try-set or pre-set)
    		int nextX = x;
    		int nextY = y+1;
    		
    		if (nextY == 9) {  //Exceeded most right, switch to next row
    			nextX = x+1;
    			nextY = 0;
    		}
    		
    		//Proceed to next cell
    		return solver(board, status, nextX, nextY);
    		
    	} else { //This cell has not been set
    		
    		//Check the row, column and the surrounding 3x3 grid of (x, y) to check what numbers have been used, 
    		//what could be used to set in (x, y)
    		
    		boolean used[] = new boolean[9];
    		
    		//Check row
    		for (i=0; i<9; i++) {
    			if (board[x][i] >= '1') {
    				used[board[x][i]-'1'] = true;
    			}
    		}
    		
    		//Check col
    		for (i=0; i<9; i++) {
    			if (board[i][y] >= '1') {
    				used[board[i][y]-'1'] = true;
    			}
    		}
    		
    		//Check the around 3x3 grid
    		for (i=x-x%3; i<x-x%3+3; i++) {
    			for (j=y-y%3; j<y-y%3+3; j++) {
    				if (board[i][j] >= '1') {
    					used[board[i][j]-'1'] = true;
    				}
    			}
    		}
    		
    		for (k=0; k<9; k++) {
    			if (used[k] == false) {
    				board[x][y] = (char)('0'+ k + 1);
    				status[x][y] = 1;  //try set
    				
    	    		int nextX = x;
    	    		int nextY = y+1;
    	    		
    	    		if (nextY == 9) {
    	    			nextX = x+1;
    	    			nextY = 0;
    	    		}
    	    		
    	    		//Works, directly return
    	    		if (solver(board, status, nextX, nextY)) {
    	    			return true;
    	    		}

    	    		//Reset board and status after (x, y) (including (x, y)) and then loop to try next value for this (x, y) cell
    	    		resetBoard(board, status, x, y);
    			}
    		}
    	}
    	
    	return false;
    }
    
    
    private void resetBoard(char[][] board, int[][] status, int x, int y) {
    	int i, j;
    	
		for (i=0; i<9; i++) {
			for (j=0; j<9; j++) {
				if (i > x || (i == x && j >= y)) {
					if (status[i][j] == 1) {
						status[i][j] = 0;
						board[i][j] = '0';
					}
				}
			}
		}
    }
    
}
