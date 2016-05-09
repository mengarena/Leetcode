package com.leet;

//Write an efficient algorithm that searches for a value in an m x n matrix. 
//This matrix has the following properties:
//
//Integers in each row are sorted in ascending from left to right.
//Integers in each column are sorted in ascending from top to bottom.
//For example,
//
//Consider the following matrix:
//
//[
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
//Given target = 5, return true.
//
//Given target = 20, return false.

//Google, Amazon, Apple
public class Search2DMatrixII {

	public Search2DMatrixII() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		int[][] matrix = {{1,4,7,11,15}, {2,5,8,12,19}, {3,6,9,16,22}, {10,13,14,17,24}, {18,21,23,26,30}};
//		int[][] matrix = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25}};
		
		int target = 29;
		
		System.out.println(searchMatrix(matrix, target));
	}
	

	//ACC:  59%
	//Strategy:  Start from top-right corner; then goes towards or bottom
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int row = 0, col = n-1;
        
        while (row <= m-1 && col >= 0) {
        	if (matrix[row][col] == target) {
        		return true;
        	} else if (matrix[row][col] > target) {
        		col = col - 1;
        	} else {
        		row = row + 1;
        	}
        }
        
        return false;
    }
	
	
	//ACC: 24%
    public boolean searchMatrixA(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = 0, j = 0;
        int t,s;
        
        if (matrix[0][0] > target || matrix[m-1][n-1] < target) return false;
        
        while (i< m || j < n) {
        	if (matrix[i][j] == target) {
        		return true;
        	} else if (matrix[i][j] < target) {
        		if (i < m-1) i = i+1;
        		if (j < n-1) j = j+1;
        		
        	} else {   //matrix[i][j] > target
        		
        		for (s=j; s<=n-1; s++) {  //Column
        			if (searchRowColumn(matrix, false, s, target, 0, i-1) == true) return true;
        		}
        		
        		for (s=i; s<=m-1; s++) {  //Row
        			if (searchRowColumn(matrix, true, s, target, 0, j-1) == true) return true;
        		}
        		
        		return false;
        	}
        	
        }
        
        return true;  //false also OK, actually will not come here, just need to return a value
    }
	
    
    
    public boolean searchRowColumn(int[][] matrix, boolean bRow, int nFixedIdx, int target, int nStart, int nEnd) {
    	if (bRow) {
    		if (target < matrix[nFixedIdx][nStart] || target > matrix[nFixedIdx][nEnd]) return false;
    	} else {
    		if (target < matrix[nStart][nFixedIdx] || target > matrix[nEnd][nFixedIdx]) return false;
    	}
    	
    	int i=nStart, j=nEnd;
    	int nMiddle;
    	
    	while (i<=j) {
    		nMiddle = (i+j)/2;
    		
    		if (bRow) {
	    		if (matrix[nFixedIdx][nMiddle] == target) {
	    			return true;
	    		} else if (matrix[nFixedIdx][nMiddle] > target) {
	    			j = nMiddle-1;
	    		} else {
	    			i = nMiddle + 1;
	    		}
    		} else {
	    		if (matrix[nMiddle][nFixedIdx] == target) {
	    			return true;
	    		} else if (matrix[nMiddle][nFixedIdx] > target) {
	    			j = nMiddle-1;
	    		} else {
	    			i = nMiddle + 1;
	    		}    			
    		}
    	}
    	
    	return false;
    }
    
}
