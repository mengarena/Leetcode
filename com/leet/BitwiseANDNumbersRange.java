package com.leet;

//Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
//
//For example, given the range [5, 7], you should return 4.

public class BitwiseANDNumbersRange {

	public BitwiseANDNumbersRange() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int m = 5;
		int n = 7;
	
		System.out.println("Range Bitwised And = " + rangeBitwiseAnd(m,n));
	}
	
	
    public int rangeBitwiseAnd(int m, int n) {
        int nResult = 0;
        
        if (m == 0 || n == 0) return 0;
        if (m == n) return m;
        
        int nBitCntM = (int) (Math.log(m)/Math.log(2));  //The actual #bit - 1; e.g. m = 7, nBitCntM = 2;
        int nBitCntN = (int) (Math.log(n)/Math.log(2));
        
        if (nBitCntM < nBitCntN) {  //Must experience something like 7 (111) & 8 (1000) = 0, one "0" occurs, the final result will be 0)
        	return 0;
        } 
        
        //Here below, process the case when nBitCntM = nBitCntN
        
        if (nBitCntM == 0) return m & n;  //m, n only contains one digit (i.e. 0 or 1)
        
        int nBase = (int) Math.pow(2, nBitCntM)-1;
        
        int nNewM = m & nBase;   //Process the value after the highest bit (i.e. for example, for 0x1101, separate it into 0x1000,  (nNewM) 0x101 
        int nNewN = n & nBase;
        
        nResult = (nBase + 1) + rangeBitwiseAnd(nNewM, nNewN);   //nBase+1 is the highest bit. m and n have the same highest bit
        
        return nResult;
    }
	
}
