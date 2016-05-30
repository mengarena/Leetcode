package com.leet;

import java.util.HashMap;
import java.util.Map;

//Reverse bits of a given 32 bits unsigned integer.
//
//For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
//return 964176192 (represented in binary as 00111001011110000010100101000000).
//
//Follow up:
//If this function is called many times, how would you optimize it?
//
//Related problem: Reverse Integer

//Apple, Airbnb
public class ReverseBits {

	public ReverseBits() {
		// TODO Auto-generated constructor stub
	}

	public void run() {

	}
	
	
	//ACC
    public int reverseBits(int n) {
        int cnt = 0;
        int ret = 0;
        int bit = 0;
        
        while (n != 0) {  //Can't be (n > 0), for example if n = 2147483648 (10000000000000000000000000000000), n < 0
            bit = n & 1;
            ret = (ret << 1) | bit;
            n = n >>> 1;
            cnt++;
        }
        
        while (cnt < 32) {
            ret = ret << 1;
            cnt++;
        }
        
        return ret;
    }
	
	
	
	//StraightForward Version
    // you need treat n as an unsigned value
    public int reverseBitsA(int n) {
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
    
    
    //Optimized version
    //ACC:  Divided 32-bit integer into 4 bytes, and reverse each byte and then assemble to form a reversed integer
    //Use cache to save effort
    private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();   //Original byte, Reversed byte(integer)
    
    public int reverseBitsB(int n) {
        int ret = 0;
        int i;
        byte[] bytes = new byte[4];
        
        for (i=0; i<4; i++) {
        	bytes[i] = (byte) ((n >>> 8*i) & 0xFF);
        }
        
        for (i=0; i<4; i++) {
        	ret = (ret << 8) + reverse(bytes[i]);
        }
        
        return ret;	
    }
	
    private int reverse(byte b) {
    	if (cache.containsKey(b)) return cache.get(b);
    	
    	int value = 0;
    	
    	for (int i=0; i<8; i++) {
    		value = (value << 1) + ((b >>> i) & 1);
    	}
    	
    	cache.put(b, value);
    	
    	return value;
    }
}
