package com.leet;

//Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) 
//and lower right corner (row2, col2).
//
//Range Sum Query 2D
//The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.
//
//Example:
//Given matrix = [
//  [3, 0, 1, 4, 2],
//  [5, 6, 3, 2, 1],
//  [1, 2, 0, 1, 5],
//  [4, 1, 0, 1, 7],
//  [1, 0, 3, 0, 5]
//]
//
//sumRegion(2, 1, 4, 3) -> 8
//update(3, 2, 2)
//sumRegion(2, 1, 4, 3) -> 10
//
//Note:
//The matrix is only modifiable by the update function.
//You may assume the number of calls to update and sumRegion function is distributed evenly.
//You may assume that row1 ¡Ü row2 and col1 ¡Ü col2.


public class RangeSumQuery2DMutable {

	public RangeSumQuery2DMutable() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:  75%
	//Calculate the column sum, for each cell, calculate the sum at this column to from first cell at this column to this cell
	private int[][] colSum = null;
	private int[][] matrix = null;
	
    public void NumMatrix(int[][] matrix) {    //Originally, should be constructor public NumMatrix(int[][] matrix) 
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        int i, j;
        
        this.matrix = matrix;
        colSum = new int[m+1][n];
        
        for (i=1; i<m+1; i++) {
        	for (j=0; j<n; j++) {
        		colSum[i][j] = colSum[i-1][j] + matrix[i-1][j];
        	}
        }
    }

    public void update(int row, int col, int val) {
    	int diff = val - matrix[row][col];
    	
        for (int i = row; i<matrix.length; i++) {
        	colSum[i+1][col] = colSum[i+1][col] + diff;
        }
        
        matrix[row][col] = val;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        
        for (int i=col1; i<=col2; i++) {
        	sum = sum + colSum[row2+1][i] - colSum[row1][i];
        }
        
        return sum;
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);