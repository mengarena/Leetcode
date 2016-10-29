package com.leet;

//Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
//
//For example:
//Given n = 13,
//Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
//
//Hint:
//
//Beware of overflow.


//Hard
public class NumberOfDigitOne {

	public NumberOfDigitOne() {
		// TODO Auto-generated constructor stub
	}

	/*
	 *  Number: x2x1x0
	 *  
	 *  Rule for count of "1" on each digit:  
	 *    x2 == 1  ==> x1x0 + 1
	 *    x2 <> 1  ==> pow(10, n-1)
	 *    
	 *    x1 > 1 ==> (x2+1)*pow(10, n-2)
	 *    x1 == 1 ==> x2*pow(10, n-2) + (x0 + 1)
	 *    x1 == 0 ==> x2*pow(10, n-2)
	 *    
	 *    x0 >= 1 ==> (x2x1 + 1) * pow(10,n-3)
	 *    x0 == 0 ==> x2x1*pow(10, n-3)
	 */
	 public static int countDigitOne(int n) {
        int nTotalDigitOneCnt = 0;
        int nQuotient = 0;
        int nRemainder = 0;
        int nRng = 0;
        int nTotalDigitCnt = 0;
        int nCurDigit = 0;
        
        nTotalDigitCnt = 1;
        nQuotient = n / 10;
        
        // Cal number of digits
        while (nQuotient > 0) {
        	nTotalDigitCnt = nTotalDigitCnt + 1;
        	
        	nQuotient = nQuotient / 10;
        }
        	        
        nRemainder = n % 10;
        nQuotient = n / 10;
        
        // Process occurrence of "1" at last digit
        if (nRemainder >= 1) {
        	nTotalDigitOneCnt = nQuotient + 1;
        } else {
        	nTotalDigitOneCnt = nQuotient;
        }
        
        if (nTotalDigitCnt >= 2) {
            // Process occurrence of "1" at first digit
        	nRng = (int)Math.pow(10, nTotalDigitCnt-1);
        	nRemainder = n % nRng;
        	nQuotient = n / nRng;
        	
        	if (nQuotient == 1) {
        		nTotalDigitOneCnt = nTotalDigitOneCnt + nRemainder + 1;
        	} else {
        		nTotalDigitOneCnt = nTotalDigitOneCnt + nRng;
        	}
        	
        	// Process occurrence of "1" at the digits in-between
        	for (int i=2; i<=nTotalDigitCnt-1; i++) {
        		nRng = (int) Math.pow(10, i-1);
        		nRemainder = n % nRng;
        		nQuotient = n / (nRng*10);
        		
        		nCurDigit = (n / nRng) % 10;
        		
        		if (nCurDigit > 1) {
        			nTotalDigitOneCnt = nTotalDigitOneCnt + (nQuotient + 1)*nRng;
        		} else if (nCurDigit == 1) {
        			nTotalDigitOneCnt = nTotalDigitOneCnt + nQuotient*nRng + (nRemainder + 1);
        		} else {
        			nTotalDigitOneCnt = nTotalDigitOneCnt + nQuotient*nRng;
        		}
        		
        	}
        	
        }
        
        return nTotalDigitOneCnt;
	 }	
}
