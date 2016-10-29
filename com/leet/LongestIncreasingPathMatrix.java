package com.leet;

//Given an integer matrix, find the length of the longest increasing path.
//
//From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).
//
//Example 1:
//
//nums = [
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//]
//Return 4
//The longest increasing path is [1, 2, 6, 9].
//
//Example 2:
//
//nums = [
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//]
//Return 4
//The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.


import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Google
//Hard
public class LongestIncreasingPathMatrix {

	public LongestIncreasingPathMatrix() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
		int[][] matrix = {{3,4,5},{3,2,6},{2,2,1}};
		
	//	System.out.println("--  " + (7>>1));
		System.out.println("Longest Increasing Path = " + longestIncreasingPath(matrix));
	}
	
	
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int nRow = matrix.length;
        int nCol = matrix[0].length;
        
        int[][] narrarrPathLen = new int[nRow][nCol];   //Len of path starting from each element
        int i,j;
        int nMaxLen = 0;
        
        for (i=0; i<nRow; i++) {
        	for (j=0; j<nCol; j++) {
        		nMaxLen = Math.max(nMaxLen,  DfsPath(i,j, nRow, nCol, matrix, narrarrPathLen));
        	}
        }
                
        return nMaxLen;
    }

    
    public int DfsPath(int i, int j, int nRow, int nCol, int[][] matrix, int[][] narrarrPathLen) {
    	if (narrarrPathLen[i][j] != 0) return narrarrPathLen[i][j];   //Has been processed
    	
    	//Check its neighbors
    	if (j-1 >= 0 && matrix[i][j-1] > matrix[i][j]) {
    		narrarrPathLen[i][j] = DfsPath(i,j-1, nRow, nCol, matrix, narrarrPathLen);
    	}
    	
    	if (j+1 < nCol && matrix[i][j+1] > matrix[i][j]) {
    		narrarrPathLen[i][j] = Math.max(narrarrPathLen[i][j], DfsPath(i,j+1, nRow, nCol, matrix, narrarrPathLen));
    	}
    	
    	if (i-1 >= 0 && matrix[i-1][j] > matrix[i][j]) {
    		narrarrPathLen[i][j] = Math.max(narrarrPathLen[i][j], DfsPath(i-1,j, nRow, nCol, matrix, narrarrPathLen));
    	}
    	
    	if (i+1 < nRow && matrix[i+1][j] > matrix[i][j]) {
    		narrarrPathLen[i][j] = Math.max(narrarrPathLen[i][j], DfsPath(i+1,j, nRow, nCol, matrix, narrarrPathLen));
    	}
    	
    	narrarrPathLen[i][j]++;
    	
    	return narrarrPathLen[i][j];
    }
    
    
/*    Works, not efficient enough
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        
        int nRow = matrix.length;
        int nCol = matrix[0].length;
        
        int nLeft, nRight, nTop, nBottom;
        
        int nPathLen = 0;
        int i,j;
        Queue<Point> matrixPath = new LinkedList<Point>();
        
        //Find all the candidate starting point
        //A valid starting point: surrounding element must >= this element, and not all surrounding element = this element
        for (i=0; i<nRow; i++) {
        	for (j=0; j<nCol; j++) {
        		if (j-1 >= 0) {
        			nLeft = matrix[i][j-1];
        		} else {
        			nLeft = matrix[i][j];
        		}
        		
        		if (j+1 < nCol) {
        			nRight = matrix[i][j+1];
        		} else {
        			nRight = matrix[i][j];
        		}
        		
        		if (i-1 >= 0) {
        			nTop = matrix[i-1][j];
        		} else {
        			nTop = matrix[i][j];
        		}
        		
        		if (i+1 < nRow) {
        			nBottom = matrix[i+1][j];
        		} else {
        			nBottom = matrix[i][j];
        		}
        		
        		if ((nLeft >= matrix[i][j] && nRight >= matrix[i][j] && nTop >= matrix[i][j] && nBottom >= matrix[i][j]) && 
        				(!(nLeft == matrix[i][j] && nRight == matrix[i][j] && nTop == matrix[i][j] && nBottom == matrix[i][j]))) {
        			Point pt = new Point(i,j);
        			matrixPath.offer(pt);
        		}
        	}
        }
        
        if (matrixPath.size() == 0) return 0;
        
        int nCurLevelElementCnt = 0;
        Point ptTmp = null;
        int x, y;
        
        nPathLen = 1;
        
        //Find the path from the starting point, level by level
        while (!matrixPath.isEmpty()) {
        	nCurLevelElementCnt = matrixPath.size();
        	
        	for (i=0; i<nCurLevelElementCnt; i++) {
        		ptTmp = matrixPath.poll();
        		x = (int) ptTmp.getX();
        		y = (int) ptTmp.getY();
        		
        		if (x-1 >= 0 && matrix[x-1][y] > matrix[x][y]) {
        			matrixPath.offer(new Point(x-1,y));
        		}
        		if (x+1 < nRow && matrix[x+1][y] > matrix[x][y]) {
        			matrixPath.offer(new Point(x+1,y));
        		}
        		if (y-1 >= 0 && matrix[x][y-1] > matrix[x][y]) {
        			matrixPath.offer(new Point(x,y-1));
        		}
        		if (y+1 < nCol && matrix[x][y+1] > matrix[x][y]) {
        			matrixPath.offer(new Point(x,y+1));
        		}
        	}
        	
        	if (!matrixPath.isEmpty()) nPathLen++;
        }
        
        return nPathLen;
    }
*/
    
}
