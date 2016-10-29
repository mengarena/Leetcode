package com.leet;

import java.util.Stack;

//Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all(only) ones and return its area.
//
//For example, given the following matrix:

//1 0 1 0 0
//1 0 1 1 1
//1 1 1 1 1
//1 0 0 1 0
	
//Return 6.

//Facebook
//Hard
public class MaximalRectangle {

	public MaximalRectangle() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		char[][] matrix = {
//				{'0', '1', '1', '0', '0', '1'},
//				{'0', '1', '1', '1', '0', '1'},
//				{'1', '1', '0', '0', '0', '1'},
//				{'1', '1', '1', '0', '0', '1'},
//				{'0', '0', '1', '1', '0', '1'},
//				{'0', '1', '1', '0', '0', '1'}
//		};
		
		//char[][] matrix = {{'1'}};
		char[][] matrix = {
				{'1', '0', '1', '0'}, 
				{'1', '1', '1', '1'},
				{'1', '0', '1', '1'},
				{'1', '0', '1', '1'}
		};
		
		System.out.println(maximalRectangle(matrix));
	}
	
	
	//Accepted:
	//Use strategy used in LargestRectangleHistogram
	//Process row by row
	//At each row, calculate the heights corresponding to columns
	//When meet "1", increase height for that column;
	//When meet "0", height is reset to 0 for that column
	//At each row, get an array of heights, and then use the strategy in LargestRectangleHistogram to calculate the max area
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int nRow = matrix.length;
        int nCol = matrix[0].length;
        int i, j;
        int nMaxArea = 0;
        int nTmpArea = 0;
        int[] narrHeights = new int[nCol+1];  //Last one is always 0
        int nRightPos = 0;
        int nLeftPos = 0;
        int nHeight = 0;
        
        for (i=0; i<nRow; i++) {
            
            for (j=0; j<nCol; j++) {
                if (matrix[i][j] == '1') {
                    narrHeights[j]++;
                } else {
                    narrHeights[j] = 0;
                }
            }
            
            Stack<Integer> stkSmallerHeight = new Stack<Integer>();   //Store Index
            
            for (j=0; j<=nCol; j++) {   //last narrHeights[j] is always 0, according to the definition of narrHeights[] 
                nRightPos = j;
                
                while (!stkSmallerHeight.isEmpty() && narrHeights[j] < narrHeights[stkSmallerHeight.peek()]) {
                    nHeight = narrHeights[stkSmallerHeight.pop()];
                    
                    if (stkSmallerHeight.isEmpty()) {
                        nLeftPos = -1;
                    } else {
                        nLeftPos = stkSmallerHeight.peek();
                    }
                    
                    nTmpArea = nHeight * (nRightPos - nLeftPos - 1);
                    nMaxArea = Math.max(nMaxArea, nTmpArea);
                    
                }
                
                stkSmallerHeight.push(j);
            }
            
            
        }
        
        return nMaxArea;	
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
	//Works, but exceeded time limit
    public int maximalRectangleA(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int nRow = matrix.length;
        int nCol = matrix[0].length;
        int i, j;
        int nMaxArea = 0;
        int nTmpArea = 0;
        
        int[][] matrixSearch = new int[nRow][nCol];
        
        //From each cell, search right and down
        for (i=0; i<nRow; i++) {
        	for (j=0; j<nCol; j++) {
        		if (matrix[i][j] == '0') continue;
        		
        		//Up and left has been searched, don't need to search starting from this cell
//        		if (i-1 >= 0 && j-1 >= 0) {
//        			if (matrixSearch[i-1][j] == 1 && matrixSearch[i][j-1] == 1) continue;
//        		} else if (i == 0 && j-1 >= 0) {
//        			if (matrixSearch[i][j-1] == 1) continue; 
//        		} else if (i-1 >= 0 && j == 0) {
//        			if (matrixSearch[i-1][j] == 1) continue;
//        		}
        		       		
//        		if (i-1 >= 0) {
//        			if (matrix[i-1][j] == '1') continue;
//        		}
//        		
//        		if (j-1 >= 0) {
//        			if (matrix[i][j-1] == '1') continue;
//        		}
        		
        		matrixSearch[i][j] = 1;
        		
        		nTmpArea = searchRectangle(matrix, i, j, i, j, matrixSearch, nMaxArea);
        		nMaxArea = Math.max(nMaxArea, nTmpArea);
        	}
        }
        
        return nMaxArea;
    }
    
    
    //From a valid point or rectangle, only search downwards and right wards
    private int searchRectangle(char[][] matrix, int nStartRow, int nStartCol, int nEndRow, int nEndCol, int[][] matrixSearch, int nCurMaxArea) {
        int nRow = matrix.length;
        int nCol = matrix[0].length;
        int i;
        boolean bValid = true;
        int nMaxArea1 = (nEndRow - nStartRow + 1) * (nEndCol - nStartCol + 1);
        int nMaxArea2 = nMaxArea1;
        
        if (nEndRow == nRow -1 && nEndCol == nCol -1) return nMaxArea1;
        
        if ((nRow-1-nStartRow+1) * (nCol-1-nStartCol+1) <= nCurMaxArea) return nCurMaxArea;
                
        if (nEndCol+1 < nCol) {
        	bValid = true;
        	for (i=nStartRow; i<=nEndRow; i++) {
        		if (matrix[i][nEndCol+1] == '0') {
        			bValid = false;
        			break;
        		}
        	}
        	
        	if (bValid == true) {
        		
        		for (i=nStartRow; i<=nEndRow; i++) {
        			matrixSearch[i][nEndCol+1] = 1;
        		}
        		
        		nMaxArea1 = nMaxArea1 + nEndRow - nStartRow + 1;
        		
        		nMaxArea1 = Math.max(nMaxArea1, searchRectangle(matrix, nStartRow, nStartCol, nEndRow, nEndCol+1, matrixSearch, nMaxArea1));
        	}
        }
        
        if (nEndRow + 1 < nRow) {
        	bValid = true;
        	for (i=nStartCol; i<=nEndCol; i++) {
        		if (matrix[nEndRow+1][i] == '0') {
        			bValid = false;
        			break;
        		}
        	}
        	
        	if (bValid == true) {
            	for (i=nStartCol; i<=nEndCol; i++) {
            		matrixSearch[nEndRow+1][i] = 1;
            	}
            	
        		nMaxArea2 = nMaxArea2 + nEndCol - nStartCol + 1;
        		
        		nMaxArea2 = Math.max(nMaxArea2, searchRectangle(matrix, nStartRow, nStartCol, nEndRow+1, nEndCol, matrixSearch, nMaxArea2));
        	}
        }
        
        return Math.max(nMaxArea1, nMaxArea2);
    }
}
