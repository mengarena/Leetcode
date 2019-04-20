package com.leet;

//A sequence of number is called arithmetic if 
//it consists of at least three elements and if the difference between any two consecutive elements is the same.
//
//For example, these are arithmetic sequence:
//
//1, 3, 5, 7, 9
//7, 7, 7, 7
//3, -1, -5, -9
//
//The following sequence is not arithmetic.
//
//1, 1, 2, 5, 7
//
//A zero-indexed array A consisting of N numbers is given. 
//A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
//
//A slice (P, Q) of array A is called arithmetic if the sequence:
//A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.
//
//The function should return the number of arithmetic slices in the array A.
//
//
//Example:
//
//A = [1, 2, 3, 4]
//
//return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.



//Aetion
public class ArithmeticSlices {

	public ArithmeticSlices() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:
	//Strategy:  calcualte the consecutive difference
	//Each arithmetic should be at least two consecutive same difference
	//Each sequence, for example, there are n (n >= 2) same difference, #arithmetic = (n-1+1)*(n-1)/2=n*(n-1)/2
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;
        int n = A.length;
        
        int[] diff = new int[n-1];
        int i;
        int count = 0;
        int tmpCount = 0;
        int tmpDiff = 0;
        
        for (i=1; i<n; i++) diff[i-1] = A[i]-A[i-1];
        
        tmpDiff = diff[0];
        tmpCount = 1;
        
        for (i=1; i<n-1; i++) {
            if (diff[i] == tmpDiff) {
                tmpCount++;
            } else {
                
                if (tmpCount >= 2) count += (1+tmpCount-1)*(tmpCount-1)/2;
                tmpDiff = diff[i];
                tmpCount = 1;
            }
        }
        
        if (tmpCount >= 2) count += (1+tmpCount-1)*(tmpCount-1)/2;
        
        return count;
    }

}
