package com.leet;

import java.util.ArrayList;
import java.util.List;

//Reverse digits of an integer.
//
//Example1: x = 123, return 321
//Example2: x = -123, return -321
//
//click to show spoilers.
//
//Have you thought about this?
//Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
//
//If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
//
//Did you notice that the reversed integer might overflow? 
//Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
//
//For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
//
//Update (2014-11-10):
//Test cases had been added to test the overflow behavior.

//Bloomberg
public class ReverseInteger {

	public ReverseInteger() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int nOrg = 0; //-2147483648;
		int nReversed = reverse(nOrg);
		
		System.out.println("Original = " + nOrg + ", Reversed = " + nReversed);
		
		//System.out.println("==mode =" + (-2147 % 10) + "===" + (-2147/10));
		
	}

    public int reverse(int x) {
        int nReversed = 0;
        int nNeg = 1;
        
        if (x < 0) {
        	nNeg = -1;
        }
        
    	int nRemainder = x % 10;
    	int nQuotient = x / 10;
    	
    	nReversed = nRemainder;
    	
    	while (nQuotient*nNeg >= 10) {
    		nRemainder = nQuotient % 10;
    		nQuotient = nQuotient / 10;
    		
    		if (nNeg == 1) {
    			if ((Integer.MAX_VALUE - nRemainder)*1.0/10 < nReversed) return 0;   //i.e. nReversed*10 + nRemaineder > Integer.MAX_VALUE
    		} else {
    			if ((Integer.MIN_VALUE - nRemainder)*1.0/10 > nReversed) return 0;   //i.e. nReversed*10 + nRemaineder < Integer.MIN_VALUE
    		}
    		
    		nReversed = nReversed*10 + nRemainder;
    	}

    	if (nQuotient*nNeg > 0) {   		
    		if (nNeg == 1) {
    			if ((Integer.MAX_VALUE - nQuotient)*1.0/10 < nReversed) return 0;
    		} else {
    			if ((Integer.MIN_VALUE - nQuotient)*1.0/10 > nReversed) return 0;
    		}

    		nReversed = nReversed*10 + nQuotient;
    	}
    	    	    	
    	return nReversed;
    }	
    
    
    
//    public int reverse(int x) {
//        int nReversed = 0;
//        int nNew = 0;
//        boolean blnNeg = false;
//        if (x > 0) {
//        	nNew = x;
//        	blnNeg = false;
//        } else {
//        	nNew = (-1)*x;
//        	blnNeg = true;
//        }
//        
//    	int nRemainder = nNew % 10;
//    	int nQuotient = nNew / 10;
//    	
//    	nReversed = nReversed*10 + nRemainder;
//    	
//    	while (nQuotient >= 10) {
//    		nRemainder = nQuotient % 10;
//    		nQuotient = nQuotient / 10;
//
//    		if (blnNeg == false) {
//    			if (nReversed > (Integer.MAX_VALUE - nRemainder)/10) return 0;
//    		} else {
//    			if (nReversed > (Integer.MIN_VALUE*(-1) - nRemainder)/10) return 0;
//    		}
//    		
//    		nReversed = nReversed*10 + nRemainder;
//    	}
//
//    	if (nQuotient > 0) {
//    		if (blnNeg == false) {
//    			if (nReversed > (Integer.MAX_VALUE - nQuotient)/10) return 0;
//    		} else {
//    			if (nReversed > (Integer.MIN_VALUE*(-1) - nQuotient)/10) return 0;
//    		}
//    		
//    		nReversed = nReversed*10 + nQuotient;
//    	}
//    	    	
//    	if (x < 0) {   		
//    		nReversed = nReversed * (-1);
//    	}
//    	
//    	return nReversed;
//    }	   
}

