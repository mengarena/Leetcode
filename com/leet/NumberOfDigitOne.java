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
	Go through the digit positions by using position multiplier m with values 1, 10, 100, 1000, etc.

	For each position, split the decimal representation into two parts, 
    for example split n=3141592 into a=31415 and b=92 when we're at m=100 for analyzing the hundreds-digit. 
    And then we know that the hundreds-digit of n is 1 for prefixes "" to "3141", i.e., 3142 times. 
    Each of those times is a streak, though. 
    Because it's the hundreds-digit, each streak is 100 long. So (a / 10 + 1) * 100 times, the hundreds-digit is 1.

	Consider the thousands-digit, i.e., when m=1000. Then a=3141 and b=592. 
	The thousands-digit is 1 for prefixes "" to "314", so 315 times. 
	And each time is a streak of 1000 numbers. However, since the thousands-digit is a 1, 
	the very last streak isn't 1000 numbers but only 593 numbers, for the suffixes "000" to "592". So (a / 10 * 1000) + (b + 1) times, 
	the thousands-digit is 1.

	The case distincton between the current digit/position being 0, 1 and >=2 can easily be done in one expression. 
	With (a + 8) / 10 you get the number of full streaks, and a % 10 == 1 tells you whether to add a partial streak.
    */

	public int countDigitOne(int n) {
		int ones = 0;
		for (long m = 1; m <= n; m *= 10)
			ones += (n/m + 8) / 10 * m + (n/m % 10 == 1 ? n%m + 1 : 0);
		return ones;
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
