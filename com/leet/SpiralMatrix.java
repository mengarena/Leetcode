package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
//For example,
//Given the following matrix:
//
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//You should return [1,2,3,6,9,8,7,4,5].

public class SpiralMatrix {

	public SpiralMatrix() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};

		//int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};
		int[][] matrix = {{1,2},{3,4}};
		List<Integer> lstNumbers = spiralOrder(matrix);
		
		for (Integer nVal:lstNumbers) System.out.print(nVal + ",");
	}
	
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> lstNumbers = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return lstNumbers;
        int nRowCnt = matrix.length;
        int nColCnt = matrix[0].length;
        int nRowIdx = 0;
        int nColIdx = -1;
        int nDirection = 0;
        int i;
        
        while (nColCnt > 0 && nRowCnt > 0) {
        	nDirection = (nDirection + 1) %  4;
        	
        	if (nDirection == 1) {  //Row not change, col: left to right (i.e. small --> large)
        		for (i=nColIdx+1; i<=nColIdx+nColCnt; i++) {
        			lstNumbers.add(matrix[nRowIdx][i]);
        		}
        		
        		nColIdx = nColIdx+nColCnt;
        		nRowCnt--;
        	} else if (nDirection == 2) {  //Col not change, row:  top to bottom (i.e. small --> large)
        		for (i=nRowIdx+1; i<=nRowIdx+nRowCnt; i++) {
        			lstNumbers.add(matrix[i][nColIdx]);
        		}
        		nRowIdx = nRowIdx+nRowCnt;
        		nColCnt--;
        	} else if (nDirection == 3) {  //Row not change, col: right to left (i.e. large --> small
        		for (i=nColIdx-1; i>=nColIdx-nColCnt; i--) {
        			lstNumbers.add(matrix[nRowIdx][i]);
        		}
        		nColIdx = nColIdx-nColCnt;
        		nRowCnt--;
        	} else {  //Col not change, row: bottom to top (i.e. large --> small)
        		for (i=nRowIdx-1; i>=nRowIdx-nRowCnt; i--) {
        			lstNumbers.add(matrix[i][nColIdx]);
        		}
        		nRowIdx = nRowIdx-nRowCnt;
        		nColCnt--;
        	}
        }
        	
        return lstNumbers;
    }	
}
