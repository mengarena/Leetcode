package com.leet;

//Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
//
//For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

public class PerfectSquares {

	public PerfectSquares() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 13;
		
		System.out.println(numSquares(n));
	}
	

    public int numSquares(int n) {
        int nMaxRoot = (int) Math.sqrt(n);
        int i;
        boolean bFeasible = false;
        if (n == nMaxRoot*nMaxRoot) {
        	return 1;
        } else {
        	for (i=2; i<=n-1; i++) {   //number of perfect square numbers
        		bFeasible = getCombination(n, i);
        		if (bFeasible) {
        			return i;
        		}
        	}
        }
        
        return n;
    }
	
    
    public boolean getCombination(int n, int nSquareCnt) {
    	boolean bFeasible = false;
    	int nMaxRoot = (int) Math.sqrt(n);
    	int i;
    	
    	if (n == nMaxRoot*nMaxRoot) return true;
    	if (n != nMaxRoot*nMaxRoot && nSquareCnt == 1) return false;
    	
    	for (i=nMaxRoot; i>=1; i--) {
    		bFeasible = getCombination(n-i*i, nSquareCnt-1);
    		if (bFeasible) break;
    	}
    	
    	return bFeasible;
    }
    
}
