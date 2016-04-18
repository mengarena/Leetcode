package com.leet;

//You are climbing a stair case. It takes n steps to reach to the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

public class ClimbingStairs {

	public ClimbingStairs() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		System.out.println("----" + climbStairs(44));
//		System.out.println("----" + getDistinct(5, 0));

	}
	
    public int climbStairs(int n) {
        int nSet = 0;
        int nTwoCnt = 0;
        int nOneCnt = 0;
        
        //Continuously introduce more "2 steps"
        while (nTwoCnt*2 <= n) {
        	nOneCnt = n - nTwoCnt*2;
        	
        	nSet = nSet + (int)getDistinct(nOneCnt, nTwoCnt);
        	
        	nTwoCnt = nTwoCnt + 1;
        }
        
        return nSet;
    }
	

    //The distinct permutation is (nOneCnt+nTwoCnt)!/(nOneCnt! * nTwoCnt!)
    public double getDistinct(int nOneCnt, int nTwoCnt) {
    	double nSet = 1;
    	    
    	for (int i=1; i<=nTwoCnt; i++) {
    		nSet = nSet*(i+nOneCnt)*1.0/i;    //=Factorial
    	}
    	
    	return nSet;
    }
    
//    public int getDistinct(int nOneCnt, int nTwoCnt) {
//    	int nSet = 0;
//    	
//    	nSet = factorial(nOneCnt+nTwoCnt) / (factorial(nOneCnt)*factorial(nTwoCnt));
//    	
//    	return nSet;
//    }
    
    
    public int factorial(int n) {
    	int nRet = 1;
    	
    	while (n > 0) {
    		nRet = nRet * n;
    		n = n - 1;
    	}
    	
    	return nRet;
    }
}
