package com.leet;

//Reverse bits of a given 32 bits unsigned integer.
//
//For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
//return 964176192 (represented in binary as 00111001011110000010100101000000).
//
//Follow up:
//If this function is called many times, how would you optimize it?
//
//Related problem: Reverse Integer

public class ReverseBits {

	public ReverseBits() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		
	}
	
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
    	int nRet = 0;
    	int nCount = 0;
    	
    	if (n == 0) return 0;
    	
    	nRet = n % 2;
    	n = n >>> 1;   //">>>" For unsigned int
    	nCount = 1;
    	
        while (n != 0) {
        	nRet = (nRet << 1) + n % 2;
        	n = n >>> 1;
        	nCount = nCount + 1;
        }
        
        while (nCount < 32) {
        	nRet = nRet << 1;
        	nCount = nCount + 1; 
        }
        
        return nRet;
    }
	
}
