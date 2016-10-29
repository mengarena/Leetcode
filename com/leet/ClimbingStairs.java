package com.leet;

//You are climbing a stair case. It takes n steps to reach to the top.
//
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

//Apple, Adobe
//Easy
public class ClimbingStairs {

	public ClimbingStairs() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		System.out.println("----" + climbStairs(44));
//		System.out.println("----" + getDistinct(5, 0));

	}
	
	//ACC
	//One case: first step: 1 stair;  another case: first step: 2 stairs
    public int climbStairs(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] arrSteps = new int[n+1];
        
        arrSteps[1] = 1;
        arrSteps[2] = 2;
        
        for (int i=3; i<=n; i++) {
            arrSteps[i] = arrSteps[i-1] + arrSteps[i-2];  //arrSteps[i-1]:  First step is 1 stair;
                                                          //arrSteps[i-2]:  First step is 2 stairs
        }
        
        return arrSteps[n];
    }
	
    
	//ACC
    public int climbStairsA(int n) {
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
    //In the following calculation, nOneCnt! part is already eliminated
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
