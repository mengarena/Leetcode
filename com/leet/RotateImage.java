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
	
	
	//ACC: Rule:  cell [i][j]  will get value of cell [n-1-j][i]  (All pairs follow this rule)
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int n = matrix.length;
        if (n == 1) return;
        int i, j;
        int tmp = 0;
        
        //Pay attention to the two for loops conditions
        for (i=0; i<n/2; i++) {
            for (j=0; j<(n+1)/2; j++) {
                tmp = matrix[i][j];
                
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
                matrix[j][n-i-1] = tmp;
            }
        }
        
    }
	
	
    //ACC
	//Rule:  (after rotate) matrix[j][n-i-1] = (before rotate) matrix[i][j]
	// ==> (after rotate) matrix[i][j] = (before rotate) matrix[n-1-j][i]
    public void rotateA(int[][] matrix) {
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
