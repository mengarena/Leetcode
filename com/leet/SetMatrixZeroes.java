package com.leet;

//Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
//
//click to show follow up.
//
//Follow up:
//Did you use extra space?
//A straight forward solution using O(mn) space is probably a bad idea.
//A simple improvement uses O(m + n) space, but still not the best solution.
//Could you devise a constant space solution?

//Microsoft
public class SetMatrixZeroes {

	public SetMatrixZeroes() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[][] matrix = {};
		
		setZeroes(matrix);
		
		for (int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix[0].length; j++) {
				System.out.print(matrix[i][j] + ",");
			}
			System.out.println();
		}
	}
	
	//Remember whether first row, column needs to be set to 0. 
	//And then utilize the element in first row/column to remember the overall row, colum which needs to be set to zero
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int i, j;
        boolean bFirstRow = false, bFirstColumn = false;   
        
        //Check first column, to see whether it needs to be set to all 0
        for (i=0; i<m; i++) {
        	if (matrix[i][0] == 0) {
        		bFirstColumn = true;
        		break;
        	}
        }
        
        //Check first row, to see whether it needs to be set to all 0
        for (j=0; j<n; j++) {
        	if (matrix[0][j] == 0) {
        		bFirstRow = true;
        		break;
        	}
        }
        
        //Check all other cells, and mark correspondingly in first column, first row
        for (i=1; i<m; i++) {
        	for (j=1; j<n; j++) {
        		if (matrix[i][j] == 0) {
        			matrix[0][j] = 0;
        			matrix[i][0] = 0;
        		}
        		
        	}
        }
        
        //Set cells to 0
        for (i=1; i<m; i++) {
        	for (j=1; j<n; j++) {
        		if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        	}
        }
        
        //Process first column
        if (bFirstColumn) {
        	for (i=0; i<m; i++) matrix[i][0] = 0;
        }
        
        //Process first row
        if (bFirstRow) {
        	for (j=0; j<n; j++) matrix[0][j] = 0;
        }
    }
	
}
