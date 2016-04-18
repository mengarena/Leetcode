package com.leet;

//Divide two integers without using multiplication, division and mod operator.
//
//If it is overflow, return MAX_INT.

public class DivideTwoIntegers {

	public DivideTwoIntegers() {
		// TODO Auto-generated constructor stub
	}


//	if (nNeg == 1) {
//		nResult = (int) (0x0100000000L - nResult);
//	}
//	
//	if (nResult > Integer.MAX_VALUE || nResult < Integer.MIN_VALUE) return Integer.MAX_VALUE;
//
	
	public void run() {
		int dividend = -2147483648;
		int divisor = -1;
		
		System.out.println(divide(dividend, divisor));
	}
	
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        long nDividend = (long) dividend;
        long nDivisor = (long) divisor;
        int nNeg = 0;
        
        if (nDividend < 0) {
        	nDividend = 0x0100000000L - (nDividend ^ 0xFFFFFFFF00000000L);   //i.e. complement, which is invert original (bit by bit) + 1
        	nNeg++;
        }
        
        if (nDivisor < 0) {
        	nDivisor = 0x0100000000L - (nDivisor ^ 0xFFFFFFFF00000000L); 
        	nNeg++;
        }
        
    	long nResult = divideHelper(nDividend, nDivisor);

    	//The following 5 lines are important in order
    	//Integer.MAX_VALUE = 2147483647
    	//Integer.MIN_VALUE = -2147483648
    	//nResult might be 2147483648, the process needs to be careful
    	//If the final result should be negative (i.e. final result should be -2147483648), after the following 3 lines, it is valid; 
    	//otherwise (i.e. the final result should be positive), the next 5th line will conclude it to be overflow
    	if (nNeg == 1) {  
    		nResult = (int) (0x0100000000L - nResult);    //Negative is presented with its complement, for nResult = 2147483648, will convert to -2147483648 in int.
    	}
    	
    	//Overflow
    	if (nResult > Integer.MAX_VALUE || nResult < Integer.MIN_VALUE) return Integer.MAX_VALUE;
    	
    	return (int)nResult;
    }

    
    private long divideHelper(long dividend, long divisor) {
    	long nBase = 1;
    	long nDivisor = divisor;
    	
    	if (dividend < divisor) return 0;
    	
    	//By multiplying by 2 to approach dividend
    	while (nDivisor < dividend) {
    		nDivisor = nDivisor << 1;
    		nBase = nBase << 1;
    	}
    	
    	if (nDivisor == dividend) return nBase;
    	
    	//nDivisor > dividend
    	nBase = nBase >> 1;
    	nDivisor = nDivisor >> 1;	
    	nBase = nBase + divideHelper(dividend - nDivisor, divisor);
    	
    	return nBase;
    }
}
