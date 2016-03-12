package com.leet;

//Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
//
//Range Sum Query 2D - Immutable
//
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
//sumRegion(1, 1, 2, 2) -> 11
//sumRegion(1, 2, 2, 4) -> 12
//
//Note:
//You may assume that the matrix does not change.
//There are many calls to sumRegion function.
//You may assume that row1 ¡Ü row2 and col1 ¡Ü col2.

public class NumMatrix {

	private int[][] narrarrMatrix = null;
	
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int nRowCnt = matrix.length;
        int nColCnt = matrix[0].length;
        int nSum = 0;
        
        narrarrMatrix = new int[nRowCnt+1][nColCnt+1];
        
        for (int i=0; i<nRowCnt; i++) {
        	nSum = 0;
        	for (int j=0; j<nColCnt; j++) {
        		nSum += matrix[i][j];
        		narrarrMatrix[i+1][j+1] = nSum + narrarrMatrix[i][j+1];
        	}
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
    	if (narrarrMatrix == null) return 0;
        return narrarrMatrix[row2+1][col2+1] - narrarrMatrix[row1][col2+1] - narrarrMatrix[row2+1][col1] + narrarrMatrix[row1][col1];  
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
