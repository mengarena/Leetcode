package com.leet;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

//Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
//
//A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//For example,
//X X X X
//X O O X
//X X O X
//X O X X
//After running your function, the board should be:
//
//X X X X
//X X X X
//X X X X
//X O X X

public class SurroundedRegions {

	public SurroundedRegions() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		char[][] board = {{'X','X', 'X', 'X'},{'X','O', 'O', 'X'},{'X','X', 'O', 'X'},{'X','O', 'X', 'X'}};
		
		solve(board);
		
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[0].length; j++) {
				System.out.print(board[i][j] + ",");
			}
			
			System.out.println();
		}
	}
	
	
	//Strategy:
	//If 'O' is not on the board, and all the board are 'X', then all the 'O' could be flipped
	//So only need to check from the four boards, all the 'O' connects to the 'O' on board should be flipped
	//
	//Here there is performance issue: In searchRegion, if using recursion, it will cause stack over flow.
    public void solve(char[][] board) {
        if (board == null || board.length < 3 || board[0].length < 3) return;
        int nRowCnt = board.length;
        int nColCnt = board[0].length;
        int i,j;
                
        for (i=0; i<nColCnt; i++) {
        	if (board[0][i] == 'O') searchRegion(board, nRowCnt, nColCnt, 0, i);
        }
        
        for (i=0; i<nColCnt; i++) {
        	if (board[nRowCnt-1][i] == 'O') searchRegion(board,  nRowCnt, nColCnt, nRowCnt-1, i);
        }
        
        for (i=0; i<nRowCnt; i++) {
        	if (board[i][0] == 'O') searchRegion(board,  nRowCnt, nColCnt, i, 0);
        }
        
        for (i=0; i<nRowCnt; i++) {
        	if (board[i][nColCnt-1] == 'O') searchRegion(board,  nRowCnt, nColCnt, i, nColCnt-1);
        }
        
        for (i=0; i<nRowCnt; i++) {
        	for (j=0; j<nColCnt; j++) {
        		if (board[i][j] == 'O') board[i][j] = 'X';
        		if (board[i][j] == '#') board[i][j] = 'O';
        	}
        }
       
    }	
    
    //DFS    
    private void searchRegion(char[][] board, int nRowCnt, int nColCnt, int nStartRow,  int nStartCol) {
        int i,j;
        Stack<Point> stkChange = new Stack<Point>();
                
        stkChange.push(new Point(nStartRow, nStartCol));
    	
    	while (!stkChange.isEmpty()) {
    		        		
    		Point ptTmp = stkChange.pop();
			i = ptTmp.x;
			j = ptTmp.y;
			
			board[i][j] = '#';
			
			if (i+1 < nRowCnt && board[i+1][j] == 'O') stkChange.push(new Point(i+1, j));
			
			if (j+1 < nColCnt && board[i][j+1] == 'O') stkChange.push(new Point(i, j+1));
				
			if (i-1 >= 0 && board[i-1][j] == 'O') stkChange.push(new Point(i-1, j));
		
			if (j-1 >= 0 && board[i][j-1] == 'O') stkChange.push(new Point(i, j-1));
    	}

    }


/*  Time out 
    private void searchRegion(char[][] board, int nRowCnt, int nColCnt, int nStartRow,  int nStartCol) {
        int i,j;
        List<Point> lstChange = new ArrayList<Point>();
                
        if (board[nStartRow][nStartCol] == 'O') {

        	lstChange.add(new Point(nStartRow, nStartCol));
        	
        	while (lstChange.size() > 0) {
        		int n = lstChange.size();
        		        		
        		for (int k=0; k<n; k++) {
        			Point ptTmp = lstChange.remove(0);
        			i = ptTmp.x;
        			j = ptTmp.y;
        			
        			board[i][j] = '#';
        			
        			if (i+1 < nRowCnt && board[i+1][j] == 'O') lstChange.add(new Point(i+1, j));
        			
        			
        			if (j+1 < nColCnt && board[i][j+1] == 'O') lstChange.add(new Point(i, j+1));
        			
        			
        			if (i-1 >= 0 && board[i-1][j] == 'O') lstChange.add(new Point(i-1, j));
        			
        			
        			if (j-1 >= 0 && board[i][j-1] == 'O') lstChange.add(new Point(i, j-1));
        			
        		}
        		
        	}
        	        	
        }
    }
 
 * /    
 */
    
}
