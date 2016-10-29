package com.leet;

//Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
//
//Example:
//Given num = 16, return true. Given num = 5, return false.
//
//Follow up: Could you solve it without loops/recursion?

//Two Sigma
//Easy
public class PowerFour {

	public PowerFour() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC
	//Power of 4 (binary):  1,  100, 10000, 1000000
    public boolean isPowerOfFour(int num) {
        if (num <= 0) return false;
        
        return (num > 0) && ((num & (num -1)) == 0) && ((num & 0x55555555) == num);
        //(num & (num -1)) == 0 makes sure it is 10, 100, 1000, 10000, 100000, 1000000
        //0x55555555 = binary: 01010101010101010101010101010101
        //(num & 0x55555555) == num makes sure the "1" in num is on position which is power of 4
    }
    
	
	//ACC
    public boolean isPowerOfFourA(int num) {
        if (num <= 0) return false;
        
        while (num >= 4) {
        	if (num % 4 != 0) return false;
        	num = num >> 2;
        }
        
        if (num == 1) return true;
        return false;
    }

}
