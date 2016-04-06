package com.leet;

//Implement int sqrt(int x).
//
//Compute and return the square root of x.
	
//Facebook, Bloomberg
public class SqrtX {

	public SqrtX() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		System.out.println(mySqrt(Integer.MAX_VALUE));
	}
	
    public int mySqrt(int x) {
        if (x < 0) return (int) Double.NaN;
        int i = 0;
        int j = x/2+1;
        int nMid;
        
        while (i <= j) {
        	nMid = (i+j)/2;
        	if (nMid == 0) {
	        	if (nMid*nMid > x) {
	        		j = nMid-1;
	        	} else if (nMid*nMid < x) {
	        		i = nMid+1;
	        	} else {
	        		return nMid;
	        	}
        	} else {
	        	if (nMid > x/nMid) {
	        		j = nMid-1;
	        	} else if (nMid < x/nMid) {
	        		i = nMid+1;
	        	} else {
	        		return nMid;
	        	}
        	}
        }
        
        return j;
    }
        
/*	OK, but not so efficient
    public int mySqrt(int x) {
        int nRoot = 0;
        if (x < 0) return (int) Double.NaN;
        if (x == 0) return 0;
        double i = 1;        
        
        while ((int)i <= x) {
        	if (i > x*1.0/i) {
        		nRoot = (int) (i-1);
        		break;
        	} else if (i == x*1.0/i) {
        		nRoot = (int) i;
        		break;
        	}
        	
        	i = i+1;
        }
        
        return nRoot;
    }
*/
	
}
