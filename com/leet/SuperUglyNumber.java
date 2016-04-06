package com.leet;

import java.util.ArrayList;
import java.util.List;

//Write a program to find the nth super ugly number.
//
//Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. 
//For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32] is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4.
//
//Note:
//(1) 1 is a super ugly number for any given primes.
//(2) The given numbers in primes are in ascending order.
//(3) 0 < k ¡Ü 100, 0 < n ¡Ü 106, 0 < primes[i] < 1000.

//Google
public class SuperUglyNumber {

	public SuperUglyNumber() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 15;
		int[] primes = {3,5,7,11,19,23,29,41,43,47};   //15-->35
//		int[] primes = {2,7,13,19};   //15-->35
//		int[] primes = {2,5,7};   //15-->35
		
		System.out.println("nthSuperUglyNumber = " + nthSuperUglyNumber(n, primes));
	}

	
    public int nthSuperUglyNumber(int n, int[] primes) {
    	int nPrimesCnt = primes.length;
    	int i;
    	int nInsertPos;
    	List<Integer> lstNumbers = new ArrayList<Integer>();
    	List<Integer> lstPos = new ArrayList<Integer>();
    	int nMin;
    	int nMinPos;
    	int nVal;
    	int nCurPointingPos;
    	
    	for (i=0; i<nPrimesCnt; i++) lstPos.add(0);
    	
    	lstNumbers.add(1);
    	
    	while (lstNumbers.size() < n) {
    		nMin = Integer.MAX_VALUE;
    		nMinPos = 0;
    		for (i=0; i<nPrimesCnt; i++) {
    			nCurPointingPos = lstPos.get(i);
    			nVal = lstNumbers.get(nCurPointingPos)*primes[i];
    			if (nMin > nVal) {
    				nMin = nVal;
    				nMinPos = i;
    			}
    		}
    		
			nInsertPos = getInsertPos(lstNumbers, nMin);
			if (nInsertPos > 0) lstNumbers.add(nInsertPos, nMin);

    		lstPos.set(nMinPos, lstPos.get(nMinPos)+1);
    	}
    	
    	return lstNumbers.get(n-1);
    }
	
/*   
	//Works, not efficient enough
    public int nthSuperUglyNumber(int n, int[] primes) {
        int nPrimesCnt = primes.length;
    	int i,j;
    	int nCnt = 1;
    	List<Integer> lstNumber = new ArrayList<Integer>();
    	int nNewNumber = 0;
    	int nInsertPos = 0;
    	int nCurLen = 0;
    	
    	if (n == 1) return 1;
    	
    	lstNumber.add(1);
    	
    	for (i=0; i<nPrimesCnt; i++) {
    		lstNumber.add(primes[i]);
    	}
    	
    	while (nCnt < n) {    		
    		nCurLen = lstNumber.size();
    		
			for (i=0; i<nPrimesCnt; i++) {
				for (j=0; j<nCurLen; j++) {
					nNewNumber = lstNumber.get(j)*primes[i];
					nInsertPos = getInsertPos(lstNumber, nNewNumber);
					if (nInsertPos > 0) lstNumber.add(nInsertPos, nNewNumber);
				}
				
			}

			nCurLen = lstNumber.size();
			for (i=n; i<nCurLen; i++) lstNumber.remove(n);
			
    		nCnt++;
    		lstNumber.remove(0);
    	}
        
        return lstNumber.get(0);
    }

  */ 
    
    public int getInsertPos(List<Integer> lstNumber, int nNewNumber) {
    	if (lstNumber.size() == 0) return 0;
    	int i=0, j=lstNumber.size()-1;
    	int nMid;
    	
    	while (i<=j) {
    		nMid = i+(j-i)/2;
    		
    		if (lstNumber.get(nMid) > nNewNumber) {
    			j = nMid - 1;
    		} else if (lstNumber.get(nMid) < nNewNumber) {
    			i = nMid + 1;
    		} else {
    			return -1;
    		}
    	}
    	
    	return i;
    }
   
    
}
