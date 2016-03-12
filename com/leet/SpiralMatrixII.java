package com.leet;

//Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
//
//For example,
//Given n = 3,
//
//You should return the following matrix:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//]
		
public class SpiralMatrixII {

	public SpiralMatrixII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int n = 1;
		
		int[][] narrRet = generateMatrix(n);
		
		for (int i=0; i<narrRet.length; i++) {
			for (int j=0; j<narrRet.length; j++) {
				System.out.print(narrRet[i][j] + ",");
			}
			System.out.println();
		}
	}
	

    public int[][] generateMatrix(int n) {
        int[][] narrRet = new int[n][n];
        if (n == 0) return narrRet;
        int i;
        int k = n - 1;
        int nCnt = 0;
        int nX, nY;
        int nNum;
        
        for (i=0; i<n; i++) narrRet[0][i] = i+1;

        nX = 0;
        nY = n-1;
        nNum = n;
        
        while (k >= 1) {
        	nCnt = nCnt + 1;
        	
        	if ((nCnt % 4) == 1) {   // x +
        		for (i=1; i<=k; i++) narrRet[nX+i][nY] = ++nNum;
        		nX = nX + k;
        	} else if ((nCnt % 4) == 3) { // x -
        		for (i=1; i<=k; i++) narrRet[nX-i][nY] = ++nNum;
        		nX = nX - k;
        	}
        		
        	nCnt = nCnt + 1;
        	
        	if ((nCnt % 4) == 2) {   // y -
        		for (i=1; i<=k; i++) narrRet[nX][nY-i] = ++nNum;
        		nY = nY - k;
        	} else if ((nCnt % 4) == 0) { // y +
        		for (i=1; i<=k; i++) narrRet[nX][nY+i] = ++nNum;
        		nY = nY + k;
        	}
        	
        	k = k - 1;
        }
        
        return narrRet;
    }
	
}
