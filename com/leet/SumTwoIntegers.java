package com.leet;

//Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
//
//Example:
//Given a = 1 and b = 2, return 3.
		
//Hulu
//Easy
public class SumTwoIntegers {

	public SumTwoIntegers() {
		// TODO Auto-generated constructor stub
	}

	//ACC:
	//Refer to:  http://www.geeksforgeeks.org/add-two-numbers-without-using-arithmetic-operators/
	//
	//Sum of two bits: XOR (^)
	//Carry bit can be obtained by performing AND (&) of two bits
	//
	//If x and y don¡¯t have set bits at same position(s), then bitwise XOR (^) of x and y gives the sum of x and y. 
	//To incorporate common set bits also, bitwise AND (&) is used. Bitwise AND of x and y gives all carry bits. 
	//We calculate (x & y) << 1 and add it to x ^ y to get the required result.
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;
        int carry = 0;
        
        while (b != 0) {
            carry = a & b;  //Get all the carry bits, but when want to use it, need to shift left by one
            
            a = a ^ b;   //Sum of a, b, the carry bits are left out (to be processed next round)
            
            b = carry << 1;
        }
        
        return a;
    }

}
