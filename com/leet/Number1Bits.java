package com.leet;

//Write a function that takes an unsigned integer and returns the number of ¡¯1' bits it has (also known as the Hamming weight).
//
//For example, the 32-bit integer ¡¯11' has binary representation 00000000000000000000000000001011, so the function should return 3.

//Apple, Microsoft
//Easy
public class Number1Bits {

	public Number1Bits() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 11; //2147483648;
		
		System.out.println(n + " has #1 : " + hammingWeight(n));
	}
	
	//ACC
    public int hammingWeight(int n) {
        int nOneCnt = 0;
        
        while (n != 0) {        
        	if ((n & 1) == 1) {    //priority of "&" is lower than "=="
        		nOneCnt = nOneCnt + 1;
        	}
        	
        	n = n >>> 1;   //Unsigned right shift operator
        }
        
        return nOneCnt;
    }	
    
    //ACC
    public int hammingWeightA(int n) {
        int count = 0;
        
        for (int i=1; i<=32; i++) {
            if ((n & 1) == 1) count++;
            n = (n >>> 1);
        }
        
        return count;
    }
}
