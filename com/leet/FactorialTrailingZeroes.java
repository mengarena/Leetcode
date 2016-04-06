package com.leet;

//Given an integer n, return the number of trailing zeroes in n!.
//
//Note: Your solution should be in logarithmic time complexity.

//Bloomberg
public class FactorialTrailingZeroes {

	public FactorialTrailingZeroes() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int n=25;
		
		System.out.println("#Trailing Zeroes = " + trailingZeroes(n));
	}
	
	//AC
    public int trailingZeroes(int n) {
    	int nTrailingZeroCnt = 0;
    	int nRemained = n;

    	if (n < 5) return 0;
    	
    	while (nRemained > 0) {
    		nTrailingZeroCnt = nTrailingZeroCnt + nRemained/5;
    		nRemained = nRemained/5;
    	}
    	
    	return nTrailingZeroCnt;
    }

    //AC
    public int trailingZeroesA(int n) {
        long nCount = 0;
        long nN = (long) n;
        long nBase = 5;
        
        while (nBase <= nN) {
            nCount = nCount + nN/nBase;
            nBase = nBase * 5;
        }
        
        return (int) nCount;
    }


    
/* Works, but not efficient	
    public int trailingZeroes(int n) {
    	int nTrailingZeroCnt = 0;
    	if (n < 5) return 0;
    	
    	for (int i=5; i<=n; i=i+5) {
    		nTrailingZeroCnt = nTrailingZeroCnt + getZero(i);
    	}
    	
    	return nTrailingZeroCnt;
    }
	
    public int getZero(int n) {
    	int nZeroCnt = 0;
    	
    	if (n % 5 != 0) return 0;
    	
    	int nRemained = n;
    	
    	while (nRemained % 10 == 0) {
    		nZeroCnt = nZeroCnt + 1;
    		nRemained = nRemained/10;
    	}
    	
    	while (nRemained % 5 == 0) {
    		nZeroCnt = nZeroCnt + 1;
    		nRemained = nRemained/5;
    	}
    	
    	return nZeroCnt;
    }
*/    
    
}
