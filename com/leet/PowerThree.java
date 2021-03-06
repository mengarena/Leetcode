package com.leet;

//Given an integer, write a function to determine if it is a power of three.
//
//Follow up:
//Could you do it without using any loop / recursion?

//Google
//Easy
public class PowerThree {

	public PowerThree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 243;
		boolean bRet = isPowerOfThreeA(n);
		if (bRet) {
			System.out.println(n + " IS Power of Three !");
		} else {
			System.out.println(n + " IS NOT Power of Three !");
		}
	}
	
	
	//ACC
    public boolean isPowerOfThree(int n) {
        return (n > 0) && (Math.pow(3, (int)(Math.log(Integer.MAX_VALUE)/Math.log(3))) % n) == 0;
    }
		
	
	
	//ACC
    public boolean isPowerOfThree(int n) {
        if (n <= 0) return false; 
        int nMax = (int) Math.pow(3, (int)(Math.log(Integer.MAX_VALUE)/Math.log(3)));
        
        if ((nMax % n) == 0) return true;
        return false;
    }
    
    //ACC
    public boolean isPowerOfThreeA(int n) {
    	if (n == 0) return false;
    	        
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;   //log10()--double,  this equals log_3(n)
        
        //OR return n>0 && Math.pow(3, (int)(Math.log(0x7fffffff)/Math.log(3)))%n==0;   //0x7fffffff--Max Integer
    }
	
}
