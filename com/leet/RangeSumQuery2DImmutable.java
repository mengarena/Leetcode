package com.leet;

public class RangeSumQuery2DImmutable {

	public RangeSumQuery2DImmutable() {
		// TODO Auto-generated constructor stub
	}


	private int[][] narrarrMatrix = null;
	
    public void NumMatrix(int[][] matrix) {    //Originally, should be constructor public NumMatrix(int[][] matrix) 
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


