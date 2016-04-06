package com.leet;

//You are given an n x n 2D matrix representing an image.
//
//Rotate the image by 90 degrees (clockwise).
//
//Follow up:
//Could you do this in-place?

//Microsoft, Amazon
public class RotateImage {

	public RotateImage() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
		int[][] matrix = {{4}};
		
		rotate(matrix);
		
		for (int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix.length; j++) {
				System.out.print(matrix[i][j] + ",");
			}
			System.out.println();
		}
	}
	
	
	//Rule:  (after rotate) matrix[j][n-i-1] = (before rotate) matrix[i][j]
	// ==> (after rotate) matrix[i][j] = (before rotate) matrix[n-1-j][i]
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int xNew, yNew;
        int xOld, yOld;
        int nTmp;
        int i, j;
        
        for (i=0; i<n/2; i++) {
        	for (j=i; j<=n-2-i*2+i; j++) {  //Each iteration, only rotate 4 elements
		        nTmp = matrix[i][j];
		        xNew = i;
		        yNew = j;
		        xOld = n-1-yNew;
		        yOld = xNew;
		        
		        while (xOld != i || yOld != j) {
		        	matrix[xNew][yNew] = matrix[xOld][yOld];
		        	xNew = xOld; yNew = yOld;
		        	
		            xOld = n-1-yNew;
		            yOld = xNew;
		        }
		        
		        if (xOld == i && yOld == j) matrix[xNew][yNew] = nTmp;
        	}
        }
        
    }
	
}
