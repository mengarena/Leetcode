package com.leet;


//Write an efficient algorithm that searches for a value in an m x n matrix. 
//This matrix has the following properties:
//
//Integers in each row are sorted from left to right.
//The first integer of each row is greater than the last integer of the previous row.
//For example,
//
//Consider the following matrix:
//
//[
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//Given target = 3, return true.
		
public class Search2DMatrix {

	public Search2DMatrix() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[][] matrix = {{1,3,5,7}, {10,11,16,20}, {23,30,34,50}};
		int target = 12;
		
		System.out.println("Is Target in Matrix: " + searchMatrix(matrix, target));
	}
	
	
    public boolean searchMatrix(int[][] matrix, int target) {        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length;
        for (int i=0; i<m; i++) {
        	if (searchRow(matrix[i], target)) return true;
        }
        
        return false;
    }
    
    
    public boolean searchRow(int[] narrRow, int target) {
    	int n = narrRow.length;
    	if (target < narrRow[0] || target > narrRow[n-1]) return false;
    	int i=0, j=n-1;
    	int nMiddle;
    	
    	while (i<=j) {
    		nMiddle = (i+j)/2;
    		
    		if (narrRow[nMiddle] == target) {
    			return true;
    		} else if (narrRow[nMiddle] > target) {
    			j = nMiddle-1;
    		} else {
    			i = nMiddle + 1;
    		}
    		
    	}
    	
    	return false;
    }
	
}
