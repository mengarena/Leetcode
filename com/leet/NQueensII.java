package com.leet;

import java.util.ArrayList;
import java.util.List;

//Follow up for N-Queens problem.
//
//Now, instead outputting board configurations, return the total number of distinct solutions.


public class NQueensII {

	public NQueensII() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		int n = 8;
		
		System.out.println(totalNQueens(n));		
	}

    public int totalNQueens(int n) {
        if (n <= 1) return n;
        
        int[][] board = new int[n][n];  //0: not set, possible to set;  nRowIdx+1: set (i.e. will be "Q");  -(nRowIdx+1): Invalid, should not set
        
        int nCount = solveNQueensHelper(board, n, 0);
        
        return nCount;
    }
    
    
    private int solveNQueensHelper(int[][] board, int n, int nCurRowIdx) {
        if (nCurRowIdx == n) return 1;
        
        int nCount = 0;
        boolean bpossible = false;
        for (int i=0; i<n; i++) {
            if (board[nCurRowIdx][i] == 0) {
                bpossible = true;
                break;
            }
        }
        
        if (bpossible == false) return 0;
        
        for (int i=0; i<n; i++) {
            if (board[nCurRowIdx][i] == 0) {
                board[nCurRowIdx][i] = nCurRowIdx+1;   //Set a "Q"
                
                setInvalid(board, n, nCurRowIdx, i);   //Invalid the corresponding column, diagonal
                
                nCount = nCount + solveNQueensHelper(board, n, nCurRowIdx+1);
                
                recoverInvalid(board, n, nCurRowIdx, i);  //Recover the corresponding column, diagonal before trying next position in current row
            }
        }
        
        return nCount;
    }
    
    
    private void setInvalid(int[][] board, int n, int nRowIdx, int nColIdx) {
        int i,ii,jj;
        for (i=nRowIdx+1; i<n; i++) {
            if (board[i][nColIdx] == 0)
            board[i][nColIdx] = -(nRowIdx+1);   //Column
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        while (ii < n-1 && jj > 0) {
            ii++;
            jj--;
            if (board[ii][jj] == 0) board[ii][jj] = -(nRowIdx+1);   
            //Invalid set to -(nRowIdx+1), then we could not by which row, here is set to invalid
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        while (ii < n-1 && jj < n-1) {
            ii++;
            jj++;
            if (board[ii][jj] == 0) board[ii][jj] = -(nRowIdx+1);
        }
    }
    
    //Recover the cell which is set invalid by current row or rows below current row
    private void recoverInvalid(int[][] board, int n, int nRowIdx, int nColIdx) {
        int i,ii,jj;
        
        board[nRowIdx][nColIdx] = 0;
        
        for (i=nRowIdx+1; i<n; i++) {
            if (board[i][nColIdx] >= (nRowIdx+1) || board[i][nColIdx] <= -(nRowIdx+1)) board[i][nColIdx] = 0;   //Column
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        while (ii < n-1 && jj > 0) {
            ii++;
            jj--;
            if (board[ii][jj] >= (nRowIdx+1) || board[ii][jj] <= -(nRowIdx+1)) board[ii][jj] = 0;
        }
        
        ii = nRowIdx;
        jj = nColIdx;
        
        while (ii < n-1 && jj < n-1) {
            ii++;
            jj++;
            if (board[ii][jj] >= (nRowIdx+1) || board[ii][jj] <= -(nRowIdx+1)) board[ii][jj] = 0;
        }
    }

}
