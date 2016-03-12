package com.leet;

//Given an integer, write a function to determine if it is a power of two.

public class PowerTwo {

	public PowerTwo() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		boolean bPowerTwo = isPowerOfTwo(-4);
		
		if (bPowerTwo == true) {
			System.out.println("Is Power Two");
		} else {
			System.out.println("Is NOT Power Two");
		}
	}

	
    public boolean isPowerOfTwo(int n) {
    	boolean isPowerTwo = true;
        int remained = 0;
        
        if (n <= 0) return false;
                
        while (n != 0) {
        	remained = n & 0x1;
        	if (remained == 1 && n > 1) {
        		isPowerTwo = false;
        		break;
        	}
        	n = n >> 1;
        }
        
        return isPowerTwo;
    }
	
}
