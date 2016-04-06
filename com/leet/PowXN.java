package com.leet;

//Implement pow(x, n).

//Facebook, Linkedin, Bloomberg
public class PowXN {

	public PowXN() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//double x = -Double.MAX_VALUE;
		double x = 0;
		int n = -1;
		
		System.out.println(myPow(x,n));
	}
	
	
	//This is efficient, this solution is testing different skills from the solution written below
	public double myPow(double x, int n) {
		if (n < 0) {
			return 1.0/myPowHelper(x, -n);   //When x = 0, n = -1,  myPowHelper(x, -n) = 0.0;  BUT 1.0/0.0 will not cause run time error, it will return Infinity.
		} else {
			return myPowHelper(x, n);
		}
	}
	
	public double myPowHelper(double x, int n) {
		if (n == 0) return 1;
		
		double halfRet = myPowHelper(x, n/2);
		
		if (n % 2 == 0) {
			return halfRet*halfRet;
		} else {
			return halfRet*halfRet*x;
		}
	}

	
/*	This is trying to implement a raw pow function
 * 
	//Pay attention:  Double.MIN_VALUE is NOT negative, which is the smallest positive value
	//Therefore the smallest negative value should be -Double.MAX_VALUE 
	//Works, not very efficient
    public double myPow(double x, int n) {
        double fResult = 0;
        boolean bNeg = false;
        if (n == 0) return 1;
        
        if (x == 0 && n > 0) return 0;
        if (x == 0 && n < 0) return Double.POSITIVE_INFINITY;
                
        if (x < 0 && Math.abs(n % 2) == 1) bNeg = true;
        int nCnt = Math.abs(n);
        int i;
        
        if (x == 1) return x;
        if (x == -1) {
        	if (bNeg == true) {
        		return -1;
        	} else {
        		return 1;
        	}
        }
        
        if (n < 0) {
        	fResult = 1/x;
        	for (i=2; i<=nCnt; i++) {
        		fResult = fResult/x;
        	}
        	
        } else {
        	fResult = x;
        	for (i=2; i<=nCnt; i++) {
        		if (x > 0) {
        			if (fResult > Double.MAX_VALUE/x) {
        				if (bNeg == true) {
        					return Double.NEGATIVE_INFINITY;
        				} else {
        					return Double.POSITIVE_INFINITY;
        				}
        			} else if (fResult < Double.MIN_VALUE/x) {
        				return 0;
        			}
        			
        		} else {   //x < 0
        			if (fResult > 0) {  //next fResult*x < 0
        				if (fResult > -Double.MAX_VALUE/x) {
        					if (bNeg == true) {
        						return Double.NEGATIVE_INFINITY;
        					} else {
        						return Double.POSITIVE_INFINITY;
        					}
        				} else if (fResult < -Double.MIN_VALUE/x) {
        					return 0;
        				}
        				
        			} else {  //next fResult*x > 0
        				if (fResult < Double.MAX_VALUE/x) {
        					if (bNeg == true) {
        						return Double.NEGATIVE_INFINITY;
        					} else {
        						return Double.POSITIVE_INFINITY;
        					}
        				} else if (fResult > Double.MIN_VALUE/x) {
        					return 0;
        				}
        				
        			}
        			
        		}
        		
    			fResult = fResult*x;

        	}
        }
        
        return fResult;
    }
    
 */   
	
}
