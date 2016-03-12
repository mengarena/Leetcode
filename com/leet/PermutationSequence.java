package com.leet;

import java.util.Arrays;

//The set [1,2,3,бн,n] contains a total of n! unique permutations.
//
//By listing and labeling all of the permutations in order,
//We get the following sequence (ie, for n = 3):
//
//1. "123"
//2. "132"
//3. "213"
//4. "231"
//5. "312"
//6. "321"
//
//Given n and k, return the kth permutation sequence.
//
//Note: Given n will be between 1 and 9 inclusive.

public class PermutationSequence {

	public PermutationSequence() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		int n = 5;
		int k = 11;
		
		System.out.println(getPermutation(n, k));
	}

	
	/* Try to recall the complex caogao!!!
	 * From left to right, to decide each digit in the final result
	 * 
	 * Originally, the digits are in ascending order
	 * To get the permutation, the number of digits affected by k: should be at the tail, and be decided by the factorial corresponding to k
	 * For example, if k = 9, then 4 digits will change their order from the original order
	 * 
	 * For example, k = 9, n = 5;
	 * 1) Calculate the factorial result is 6.   9 is two times (round upwards) of 6, so the first number affected should be the 2nd largest number from n-3 ~ n   (here n is the position)
	 * 2) Set the 2nd largest number in the tail to the position n-3
	 * 3) Sorted the remained tail to make them in ascending order
	 * 4) Now number of affected numbers becomes 3
	 * 5) And the order of the remained number should change to 3rd (= 9 % 6) permutation from its ascending order
	 * 6) and goes on, until remained order <= 1
	 */
    public String getPermutation(int n, int k) {
    	if (k < 1) return "";
        StringBuilder sRet = new StringBuilder();
        int i;
        int narr[] = new int[n+1];
        for (i=1; i<=n; i++) narr[i] = i;
        
        boolean bRet = permute(narr, k, n);
        
        if (bRet == false) return "";
        
        for (i=1; i<=n; i++) sRet.append(narr[i]);
        
        return sRet.toString();
    }
	
	
    private boolean permute(int[] narr, int k, int n) {
    	int nRemained = 0;
    	int i = 1, j = 1;
    	int nRank;
    	boolean bFirst = false;
    	
    	if (k <= 1) return true;
    	
    	//Decide the factorial and the number of numbers affected (in the tail)
    	while (i<=k) i=i*(j++);

        if (j > 1) {
        	j = j-1;
        	i = i/j;
        }
    	        
    	nRemained = k % i;
    	nRank = k / i;
    	
    	if (nRemained == 0) {    		
    		if (nRank == 1) { //If k is one time of i, the (n-3)th does not need to change, so only need to process from (n-2)th position
    			bFirst = true;
    			j = j - 1;
    			nRank = j;    //The number at position j should be the jth largest number in the tail
    		}
    	} else {
    		nRank = nRank + 1;
    		if (nRank == 1) {
    			nRank = nRemained;
    			j = j-1;
    		}
    	}
    	   	
    	if (j > n) return false;
    	
    	//Set the first affected number in the tail (from left to right)
    	Arrays.sort(narr, n-j+1, n+1);
    	int nTmp = narr[n-j+1];
    	narr[n-j+1] = narr[n-j+1 + nRank-1];
    	narr[n-j+1 + nRank-1] = nTmp;
    	
    	//Sorted the remained tail to ascending order
    	Arrays.sort(narr, n-j+2, n+1);
    	
    	if (nRemained == 0) {  //The remained rank (i.e. here below i/j or i) will be the full rank at the position (i.e. if last third position, should be 6; if last second position, should be 2...)
    		if (bFirst) {
    			if (j > 0) {
    				return permute(narr, i/j, n);
    			} else {
    				return true;
    			}
    		} else {
    			return permute(narr, i, n);
    		}
    	} else {
    		return permute(narr, nRemained, n);
    	}
    }
    
	
}
