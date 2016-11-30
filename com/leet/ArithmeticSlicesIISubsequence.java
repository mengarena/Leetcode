package com.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
//
//For example, these are arithmetic sequences:
//
//1, 3, 5, 7, 9
//7, 7, 7, 7
//3, -1, -5, -9
//The following sequence is not arithmetic.
//
//1, 1, 2, 5, 7
//
//A zero-indexed array A consisting of N numbers is given. 
//A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 °‹ P0 < P1 < ... < Pk < N.
//
//A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. 
//In particular, this means that k °› 2.
//
//The function should return the number of arithmetic subsequence slices in the array A.
//
//The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 °‹ N °‹ 1000. 
//The output is guaranteed to be less than 231-1.
//
//
//Example:
//
//Input: [2, 4, 6, 8, 10]
//
//Output: 7
//
//Explanation:
//All arithmetic subsequence slices are:
//[2,4,6]
//[4,6,8]
//[6,8,10]
//[2,4,6,8]
//[4,6,8,10]
//[2,4,6,8,10]
//[2,6,10]

//Baidu
//Hard
public class ArithmeticSlicesIISubsequence {

	public ArithmeticSlicesIISubsequence() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] A = {2,4,6,8,10};
		
		int cnt = numberOfArithmeticSlices(A);
		
		System.out.println(cnt);
		
	}
	
	
	//ACC
	//A[] might contain duplicate elements, so, for each i, the same value for diff might occur more than once
	//Each slice has certain length;
	//If a slice ends at an element, with same difference between consecutive elements, there could be various length for the slice
	//Then for example, at position j, for diff (between two consecutive elements), if the number of total slices is x
	//Then we add a new element A[i] at the end
	//we have x + 1, because we could slide the slice one step further based on j's situation, that's why it = x + 1 
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) return 0;
        int res = 0;
        Map<Integer, Integer>[] map = new Map[A.length];   //<Difference, number of slices> at element i (the index for this Map array) 
		
        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>(i);
        	
            for (int j = 0; j < i; j++) {
                long diff = (long)A[i] - A[j];
                if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;
        		
                //Run the program to understand
                int d = (int)diff;
                int c1 = map[i].getOrDefault(d, 0);   //might contain duplicate elements, so same d might occur more than once for the same i (i.a. different j might have same value)
                int c2 = map[j].getOrDefault(d, 0);
                res += c2;   //c2 could be thought as #2-element slices, then by adding A[i], it is the number of 3-element slices (i.e. arithmetic) (i.e. by A[i] and this d, will have c2 slices
                map[i].put(d, c1 + c2 + 1);  //It actually remembers:  how many elements we have before i to form the difference d slice
            }
        }
		
        return res;
    }
	
	
	//Not complete, suffer from one problem: step size = 2, get a list of numbers; and when step size = 4, also get a list of numbers
	//Then some combination (slices) will be duplicately counted
    public int numberOfArithmeticSlicesAA(int[] A) {
        if (A == null || A.length < 3) return 0;
        int n = A.length;
        int i,j;
        
        Set<Integer> st = new HashSet<Integer>();
        int minStep, maxStep;
        int nElmCnt;
        int nElm;
        int ans = 0;
        Set<Integer> stValidStepSize = new HashSet<>();
        
        Arrays.sort(A);
        for (int num:A) st.add(num);
        
        for (i=0; i<n-2; i++) {   //Position of first element
            minStep = 1;
            maxStep = (A[n-1]-A[i])/2;
            
            stValidStepSize.clear();
            
            for (j=minStep; j<=maxStep; j++) {  //Step size from first element
                nElmCnt = 1;
                nElm = A[i];
                
                while (nElm + j <= A[n-1]) {
                    if (st.contains(nElm+j)) {
                        nElmCnt++;
                        nElm = nElm + j;
                    } else {
                        break;
                    }
                }
                
                ans += getCombinationCnt(nElmCnt);
                if (nElmCnt >= 3) stValidStepSize.add(j);
            }
        }
        
        return ans;
    }
    
    private int getCombinationCnt(int nElmCnt) {
        if (nElmCnt <= 2) return 0;
        int ans = 0;
        int nSliceLen = 0;
        
        for (int i=3; i<=nElmCnt; i++) {   //Size of an arithmetic subsequence slice
            for (int step=1; step<=(nElmCnt-1)/2; step++) {
                nSliceLen = (i-1)*step+1;
                
                if (nSliceLen <= nElmCnt) ans++;
            }
        }
        
        return ans;
    }

}
