package com.leet;

//Given an integer, write a function to determine if it is a power of three.
//
//Follow up:
//Could you do it without using any loop / recursion?

//Google
public class PowerThree {

	public PowerThree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 243;
		boolean bRet = isPowerOfThree(n);
		if (bRet) {
			System.out.println(n + " IS Power of Three !");
		} else {
			System.out.println(n + " IS NOT Power of Three !");
		}
	}
	
    public boolean isPowerOfThree(int n) {
    	if (n == 0) return false;
    	        
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        
        //OR return n>0 && Math.pow(3, (int)(Math.log(0x7fffffff)/Math.log(3)))%n==0;   //0x7fffffff--Max Integer
    }
	
}
