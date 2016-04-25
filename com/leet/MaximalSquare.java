package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area.
//
//For example, given the following matrix:
//
//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
//
//Return 4.

//Facebook, Airbnb
public class MaximalSquare {

	public MaximalSquare() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		char[][] matrix= {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
		char[][] matrix= {{'1', '1', '0', '0', '0'}, {'1', '1', '1', '1', '1'}, {'0', '0', '1', '1', '1'}, {'0', '0', '1', '1', '1'}};

		System.out.println("Maximal Squre = " + maximalSquare(matrix));
	}
	
	//From current valid square, to detect whether next-bigger square based on the current square is valid, 
	//only need to check the elements which wrap current square at the one-element right and bottom of current square
    public int maximalSquare(char[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
    	int nRow = matrix.length;
    	int nCol = matrix[0].length;
    	int i,j;
    	int nSquareLen = 0;
    	int nMaxSquareLen = 0;
    	i = 0;
    	j = 0;
    	
    	//Loop through row 0, row 1.... (i.e. base position)
    	while (i + nSquareLen  < nRow ) {
    		j = 0;
    		
    		//Check from column 0, column 1....
    		while (j + nSquareLen < nCol) {
    			if (matrix[i][j] == '1') {
    				nSquareLen = 1;
    				
    				int nBaseMinRow = i;
    				int nBaseMaxRow = i;
    				int nBaseMinCol = j;
    				int nBaseMaxCol = j;
    				
    				while (nBaseMaxRow+1 < nRow && nBaseMaxCol+1 < nCol) {
    					boolean bValid = true;
    					for (int t = nBaseMinRow; t <=nBaseMaxRow; t++) {  //Check the possible additional column
    						if (matrix[t][nBaseMaxCol+1] == '0') {
    							bValid = false;
    							break;
    						}
    					}
    					
    					if (bValid == false) break;
    					
    					for (int t = nBaseMinCol; t <= nBaseMaxCol; t++) {   //Check the possible additional row
    						if (matrix[nBaseMaxRow+1][t] == '0') {
    							bValid = false;
    							break;
    						}
    					}
    					
    					if (bValid == false) break;
    					
    					if (matrix[nBaseMaxRow+1][nBaseMaxCol+1] == '0') break;
    					
    					nSquareLen++;
    					nBaseMaxRow++;
    					nBaseMaxCol++;
    				}
    				
    				nMaxSquareLen = Math.max(nMaxSquareLen, nSquareLen);
    				
    			}  //if (matrix[i][j] == '1') {
    			
    			j++;
    		}  //while j 
    		
    		i++;
    	} // while i
    	
        
    	return nMaxSquareLen*nMaxSquareLen;
    }
	
}
