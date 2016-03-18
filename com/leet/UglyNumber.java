package com.leet;

//Write a program to check whether a given number is an ugly number.
//
//Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
//For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
//
//Note that 1 is typically treated as an ugly number.

public class UglyNumber {

	public UglyNumber() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int nNum = -2147483648;
		boolean bRet = isUgly(nNum);
		
		if (bRet) {
			System.out.println("Number: " + nNum + " is Ugly Number!");
		} else {
			System.out.println("Number: " + nNum + " is NOT Ugly Number!");			
		}
	}
	
	
    public boolean isUgly(int num) {
        boolean bUgly = false;
        int narrPrime[] = {2, 3, 5};
        int i;
        int nPrime;
        int nPreviousQuotient = 0;
        int nQuotient = num;
        int nRemainder = 0;
       
        if (num <= 0) return false;
        if (num == 1) return true;
        		
        for (i=0; i<narrPrime.length; i++) {
        	nPrime = narrPrime[i];

        	nRemainder = nQuotient % nPrime;
        	nPreviousQuotient = nQuotient;
        	nQuotient = nQuotient / nPrime;
        	
        	while (nRemainder == 0 && (int) Math.abs(nQuotient) >= nPrime) {
            	nRemainder = nQuotient % nPrime;  
            	nPreviousQuotient = nQuotient;
        		nQuotient = nQuotient / nPrime;
        	}
        	
        	if (nRemainder == 0) {
        		bUgly = true;
        		break;
        	} else {  // nRemainder >= 1
            	if (i < narrPrime.length -1) {
            		nQuotient = nPreviousQuotient;
            		continue;
            	} else {
            		bUgly = false;
            		break;
            	}
        		
        	}
        }
        
        return bUgly;
    }

}
