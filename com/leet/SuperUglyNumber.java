package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
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

    
	//ACC:  98%
    public int nthSuperUglyNumber(int n, int[] primes) {
    	int nPrimesCnt = primes.length;
    	int i;
    	int[] narrNumbers = new int[n];
    	int[] narrPos = new int[nPrimesCnt];   //for primes[i], next time, which narrNumbers[] to be multiplied
    	int[] narrHeads = new int[nPrimesCnt];  //Like Ugly Number II, this array remembers the head elements of the rows corresponding to each prime
    	int nCurPointingPos;
    	int idx = 0;
    	int next = 1;   //Next Ugly number
    	
    	Arrays.fill(narrHeads, 1);
    	
    	while (idx < n) {
    		narrNumbers[idx] = next;
    		
    		next = Integer.MAX_VALUE;
    		
    		for (i=0; i<nPrimesCnt; i++) {
    			if (narrHeads[i] == narrNumbers[idx] ) {  //Current head is selected as the smallest. Here different from Ugly Number II solution, 
    				                                      //here only when current head is the previous smallest, then update this row
    				nCurPointingPos = narrPos[i];
    				narrHeads[i] = narrNumbers[nCurPointingPos]*primes[i];  //narrNumbers[nCurPointingPos] could be considered the next smallest to be used to multiply 
    				                                                        //(like nCurNum in Ugly Number II)
    				narrPos[i]++;
    			}
    			
    			next = Math.min(next, narrHeads[i]);   //Select the smallest head of the rows corresponding to each prime as the next Ugly number, 
    			                                       //just like the solution in Ugly Number II
    		}
            
            idx++;
    	}
    	
    	return narrNumbers[n-1];
    }
	
	
	
	
	//ACC: 78%
    public int nthSuperUglyNumberC(int n, int[] primes) {
    	int nPrimesCnt = primes.length;
    	int i;
    	int[] narrNumbers = new int[n];
    	int[] narrPos = new int[nPrimesCnt];   //for primes[i], next time, which narrNumbers[] to be multiplied
    	int nMin;
    	int nVal;
    	int nCurPointingPos;
    	int idx = 1;
    	
    	narrNumbers[0] = 1;
    	
    	while (idx < n) {
    		nMin = Integer.MAX_VALUE;
    		
    		for (i=0; i<nPrimesCnt; i++) {
    			nCurPointingPos = narrPos[i];
    			nVal = narrNumbers[nCurPointingPos]*primes[i];
    			if (nMin > nVal) {
    				nMin = nVal;
    			}
    		}

        	narrNumbers[idx] = nMin;

            for (i=0; i<nPrimesCnt; i++) {
            	if (narrNumbers[narrPos[i]] * primes[i] <= nMin) narrPos[i]++;
            }
            
            idx++;
    	}
    	
    	return narrNumbers[n-1];
    }

	
	
	//ACC:  7%
    public int nthSuperUglyNumberB(int n, int[] primes) {
    	int nPrimesCnt = primes.length;
    	int i;
    	List<Integer> lstNumbers = new ArrayList<Integer>();
    	lstNumbers.add(1);
    	int[] carrPos = new int[nPrimesCnt];
    	int nMin;
    	int nMinPos;
    	int nVal;
    	int nCurPointingPos;
    	int nPrevVal = 0;
    	
    	while (lstNumbers.size() < n) {
    		nMin = Integer.MAX_VALUE;
    		nMinPos = 0;
    		for (i=0; i<nPrimesCnt; i++) {
    			nCurPointingPos = carrPos[i];
    			nVal = lstNumbers.get(nCurPointingPos)*primes[i];
    			if (nMin > nVal) {
    				nMin = nVal;
    				nMinPos = i;
    			}
    		}

            if (nMin > nPrevVal) {
                lstNumbers.add(nMin);
                nPrevVal = nMin;
            }

            carrPos[nMinPos]++;
    	}
    	
    	return lstNumbers.get(n-1);
    }
	
    
    
    
    
	
    
	//ACC:  but slow
    public int nthSuperUglyNumberA(int n, int[] primes) {
    	int nPrimesCnt = primes.length;
    	int i;
    	int nInsertPos;
    	List<Integer> lstNumbers = new ArrayList<Integer>();
    	List<Integer> lstPos = new ArrayList<Integer>();   //count of each primes has been used. Or for each prime, which element in lstNumbers to multiple based on.
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
    		} else {   //Exist
    			return -1;
    		}
    	}
    	
    	return i;
    }
   
    
}
