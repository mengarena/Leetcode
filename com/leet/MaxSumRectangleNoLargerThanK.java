package com.leet;

import java.util.TreeSet;

//Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.
//
//Example:
//	
//Given matrix = [
//  [1,  0, 1],
//  [0, -2, 3]
//]
//		
//k = 2
//
//The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
//
//Note:
//	
//The rectangle inside the matrix must have an area > 0.
//What if the number of rows is much larger than the number of columns?

//Hard
//Google
public class MaxSumRectangleNoLargerThanK {

	public MaxSumRectangleNoLargerThanK() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[][] matrix = {{2,2,-1}};
		int k = 0;
		
		int maxSum = maxSumSubmatrix(matrix, k);
		System.out.println(maxSum);
	}
	
	//ACC: 50%
	//Complexity: min(m,n)^2 * max(m,n) * log(max(m,n))
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int mi = Math.min(matrix.length, matrix[0].length);
        int mx = Math.max(matrix.length, matrix[0].length);
        int i, j, t;
        int maxSum = Integer.MIN_VALUE;
        boolean colBig = matrix[0].length >= matrix.length ? true:false;
        
        for (i=0; i<mi; i++) {    //min(m,n)
            int[] array = new int[mx];
            
            for (j=i; j>=0; j--) {   //min(m,n)
                int val = 0;
                TreeSet<Integer> ts = new TreeSet<>();   //It is binary tree
                ts.add(0);
                
                for (t=0; t<mx; t++) {   //max(m,n)
                    array[t] = array[t] + (colBig? matrix[j][t]:matrix[t][j]);
                    
                    val = val + array[t];
                    
                    Integer diff = ts.ceiling(val - k);    //log(max(m,n))
                    if (diff != null) maxSum = Math.max(maxSum, val - diff);
                    
                    ts.add(val);
                }
            }
        }
        
        return maxSum;
    }

    
    
    /*
    public int maxSumSubmatrixA(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int mi = Math.min(matrix.length, matrix[0].length);
        int mx = Math.max(matrix.length, matrix[0].length);
        int i, j, t;
        int maxSum = Integer.MIN_VALUE;
        boolean colBig = matrix[0].length >= matrix.length ? true:false;
        int minGap = Integer.MAX_VALUE;
        int tmpGap = 0;
                
        for (i=0; i<mi; i++) {
            int[] array = new int[mx];
            
            for (j=i; j>=0; j--) {
                int val = 0;

                for (t=0; t<mx; t++) {
                    array[t] = array[t] + (colBig? matrix[j][t]:matrix[t][j]);
                    
                    val = val + array[t];
                    
                    tmpGap = k - val;
                    
                    if (tmpGap >= 0 && tmpGap < minGap) {
                        minGap = tmpGap;
                        maxSum = val;
                    }
                }
            }
        }
        
        return maxSum;
    }
    */
    
}

