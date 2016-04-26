package com.leet;

import java.util.ArrayList;
import java.util.List;

//The n-queens puzzle is the problem of placing n queens on an n¡Án chessboard such that no two queens attack each other.
//
//
//Given an integer n, return all distinct solutions to the n-queens puzzle.
//
//Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' 
//both indicate a queen and an empty space respectively.
//
//For example,
//There exist two distinct solutions to the 4-queens puzzle:
//
//[
// [".Q..",  // Solution 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // Solution 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]

public class NQueens {

	public NQueens() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 4;
		
		List<List<String>> lstlstConfig = solveNQueens(n);
		
		for (List<String> lstConfig:lstlstConfig) {
			System.out.println(lstConfig.toString());
		}
	}
	
	//Accepted
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> lstlstConfigs = new ArrayList<List<String>>();
        if (n == 1) {
            List<String> lstConfig = new ArrayList<String>();
            lstConfig.add("Q");
            lstlstConfigs.add(lstConfig);
            return lstlstConfigs;
        }
        
        int[][] board = new int[n][n];  //0: not set, possible to set;  nRowIdx+1: set (i.e. will be "Q");  -(nRowIdx+1): Invalid, should not set
        
        solveNQueensHelper(board, n, 0, lstlstConfigs);
        
        return lstlstConfigs;
        
    }
    
    private void solveNQueensHelper(int[][] board, int n, int nCurRowIdx, List<List<String>> lstlstConfigs) {
        if (nCurRowIdx == n) {
            setConfig(board, n, lstlstConfigs);
            return;
        }
        
        boolean bpossible = false;
        for (int i=0; i<n; i++) {
            if (board[nCurRowIdx][i] == 0) {
                bpossible = true;
                break;
            }
        }
        
        if (bpossible == false) return;
        
        for (int i=0; i<n; i++) {   //Column
            if (board[nCurRowIdx][i] == 0) {
                board[nCurRowIdx][i] = nCurRowIdx+1;   //Set a "Q"
                
                setInvalid(board, n, nCurRowIdx, i);   //Invalid the corresponding column, diagonal
                
                solveNQueensHelper(board, n, nCurRowIdx+1, lstlstConfigs);
                
                recoverInvalid(board, n, nCurRowIdx, i);  //Recover the corresponding column, diagonal before trying next position in current row
                
                board[nCurRowIdx][i] = 0;
            }
        }
        
    }
    
    private void setConfig(int[][] board, int n, List<List<String>> lstlstConfigs) {
        List<String> lstRow = new ArrayList<String>();
        
        for(int i=0; i<n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<n; j++) {
                if (board[i][j] == i+1) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            lstRow.add(sb.toString());
        }
        lstlstConfigs.add(lstRow);
    }
    
    
    private void setInvalid(int[][] board, int n, int nRowIdx, int nColIdx) {
        int i,ii,jj;
        
        //Process column
        for (i=nRowIdx+1; i<n; i++) {
            if (board[i][nColIdx] == 0) board[i][nColIdx] = -(nRowIdx+1);   //Column
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        //Process diagnoal
        while (ii < n-1 && jj > 0) {
            ii++;
            jj--;
            if (board[ii][jj] == 0) board[ii][jj] = -(nRowIdx+1);   
            //Invalid set to -(nRowIdx+1), then we could not by which row, here is set to invalid
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        //Process diagnoal
        while (ii < n-1 && jj < n-1) {
            ii++;
            jj++;
            if (board[ii][jj] == 0) board[ii][jj] = -(nRowIdx+1);
        }
    }
    
    //Recover the cell which is set invalid by current row or rows below current row
    private void recoverInvalid(int[][] board, int n, int nRowIdx, int nColIdx) {
        int i,ii,jj;
        
        //Process column
        for (i=nRowIdx+1; i<n; i++) {
            if (board[i][nColIdx] >= (nRowIdx+1) || board[i][nColIdx] <= -(nRowIdx+1)) board[i][nColIdx] = 0;   //Column, board[i][nColIdx] >= (nRowIdx+1) => 'Q'
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        //Process diagnoal
        while (ii < n-1 && jj > 0) {
            ii++;
            jj--;
            if (board[ii][jj] >= (nRowIdx+1) || board[ii][jj] <= -(nRowIdx+1)) board[ii][jj] = 0;
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        //Process diagnoal
        while (ii < n-1 && jj < n-1) {
            ii++;
            jj++;
            if (board[ii][jj] >= (nRowIdx+1) || board[ii][jj] <= -(nRowIdx+1)) board[ii][jj] = 0;
        }
    }

}
