package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given two sparse matrices A and B, return the result of AB.
//
//You may assume that A's column number is equal to B's row number.
//
//Example:
//
//A = [
//  [ 1, 0, 0],
//  [-1, 0, 3]
//]
//
//B = [
//  [ 7, 0, 0 ],
//  [ 0, 0, 0 ],
//  [ 0, 0, 1 ]
//]
//
//
//     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
//AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                  | 0 0 1 |

public class SparseMatrixMultiplication {

	public SparseMatrixMultiplication() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		int[][] A = {{1,0,0}, {-1,0,3}};
		int[][] B = {{7,0,0}, {0,0,0}, {0,0,1}};
		int[][] Ret = multiply(A, B);
		
		System.out.println();
	}

	//AC: 38%
	public int[][] multiply(int[][] A, int[][] B) {
		if (A == null || A.length == 0 || A[0].length == 0 || B == null || B.length == 0 || B[0].length == 0) return null;
	    int nRowA = A.length;
	    int nColARowB = A[0].length;
	    int nColB = B[0].length;
	    int i, j;
	    int[][] narrarrRet = new int[nRowA][nColB];
	    List<Integer> lstValidRow = new ArrayList<Integer>();
	    List<Integer> lstValidCol = new ArrayList<Integer>();
	    boolean bValid = false;
	    int nSum = 0;
	    
	    //First, find the all-0 rows in A and all-0 cols in B, these rows and cols will generate 0 in the result, so we don't need to process them later
	    for (i=0; i<nRowA; i++) {
	    	bValid = false;
	    	
	    	for (j=0; j<nColARowB; j++) {
	    	    if (A[i][j] != 0) {
	    	    	bValid = true;
	    	    	break;
	    	    }
	    	}
	    	
	    	if (bValid) lstValidRow.add(i);
	    }
	    
	    for (i=0; i<nColB; i++) {
	    	bValid = false;
	    	
	    	for (j=0; j<nColARowB; j++) {
	    		if (B[j][i] != 0) {
	    			bValid = true;
	    			break;
	    		}
	    	}
	    	
	    	if (bValid) lstValidCol.add(i);
	    }
    	
	    if (lstValidRow.size() == 0 || lstValidCol.size() == 0) return narrarrRet;
	    
	    //Process valid rows and cols in A and B
	    for (Integer nARowIdx:lstValidRow) {
	        
	    	for (Integer nBColIdx:lstValidCol) {
	    		nSum = 0;
	    		
	    		for (i=0; i<nColARowB; i++) {
	    		    if (A[nARowIdx][i] != 0 && B[i][nBColIdx] != 0) nSum = nSum + A[nARowIdx][i]*B[i][nBColIdx];
	    		}
	    		
	    		narrarrRet[nARowIdx][nBColIdx] = nSum;
	    	}
	    }
	    
	    return narrarrRet;
	}	
}
