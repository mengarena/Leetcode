package com.leet;

//Given an 2D board, count how many different battleships are in it. 
//The battleships are represented with 'X's, empty slots are represented with '.'s. 
//You may assume the following rules:
//
//You receive a valid board, made of only battleships or empty slots.
//Battleships can only be placed horizontally or vertically. 
//In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
//At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
//
//Example:
//X..X
//...X
//...X
//
//In the above board there are 2 battleships.
//
//Invalid Example:
//...X
//XXXX
//...X
//
//This is not a valid board - as battleships will always have a cell separating between them.
//
//Your algorithm should not modify the value of the board.

//Microsoft
public class BattleshipsInABoard {

	public BattleshipsInABoard() {
		// TODO Auto-generated constructor stub
	}

	//ACC
	//Strategy: from each 'X' cell, search horizontally (towards right) and veritically (towards bottom) to check battleship
	//Use visited matrix (i.e. bat here) to mark the battleship
    public int countBattleships(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return 0;
        int m = board.length;
        int n = board[0].length;
        int i, j;
        int count = 0;
        boolean[][] bat = new boolean[m][n];   //visited matrix
        boolean bRet = false;
        
        for (i=0; i<m; i++) {
            for (j=0; j<n; j++) {
                if (board[i][j] == 'X' && bat[i][j] == false) {
                    bRet = getBattleShip(board, bat, m, n, i, j);
                    if (bRet == false) return 0;
                    count++;
                }
            }
        }
        
        return count;
    }
    
    private boolean getBattleShip(char[][] board, boolean[][] bat, int m, int n, int i, int j) {
        int k;
        boolean bHor = false;
        
        //Check horizontally
        for (k=j+1; k<n; k++) {
            if (board[i][k] != 'X') break;
            
            //Only need to check the row below it, don't need to check the row above it
            if (i+1 < m && board[i+1][k] == 'X') return false;
            
            bat[i][k] = true;
            bHor = true;
        }
        
        if (bHor == true) {
            if (i+1 < m && board[i+1][j] == 'X') return false;
        } else {
        	//Check vertically
            for (k=i+1; k<m; k++) {
                if (board[k][j] != 'X') break;
                
                //Only need to check the column right to it, don't need to check the column left to it
                if (j+1 < n && board[k][j+1] == 'X') return false;
                
                bat[k][j] = true;
            }
        }
        
        return true;
    }

}
