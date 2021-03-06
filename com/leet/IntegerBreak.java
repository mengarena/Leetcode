package com.leet;

//Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. 
//Return the maximum product you can get.
//
//For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
//
//Note: you may assume that n is not less than 2.
//
//Hint:
//
//There is a simple O(n) solution to this problem.
//You may check the breaking results of n ranging from 7 to 10 to discover the regularities.

public class IntegerBreak {

	public IntegerBreak() {
		// TODO Auto-generated constructor stub
	}
	
	//ACC
    public int integerBreak(int n) {
        int prod = 1;
        
        if (n <= 3) return 1*(n-1);
        
        while (n > 4) {
            prod = prod*3;
            n = n - 3;
        }
        
        prod = prod * n;
        
        return prod;
    }
	

	//ACC
	//Strategy: Largest product comes when factors are all 3, so try to get as many 3 as possible
    public int integerBreakA(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        
        if (n % 3 == 1) {
        	return 4 * getSum(n-4);
        } else if (n % 3 == 2) {
        	return 2 * getSum(n-2);
        } else {
        	return getSum(n);
        }
    }
    
    public int getSum(int n) {
    	if (n == 0) return 1;
    	int sum = 1;
    	
    	while (n > 0) {
    		sum *= 3;
    		n = n-3;
    	}
    	
    	return sum;
    }

}
