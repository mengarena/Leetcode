package com.leet;

//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
//Write a function to determine if a number is strobogrammatic. The number is represented as a string.
//
//For example, the numbers "69", "88", and "818" are all strobogrammatic.

//Google
public class StrobogrammaticNumber {

	public StrobogrammaticNumber() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
    //Accepted
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.isEmpty()) return true;
        int n = num.length();
        
        if (num.charAt(0) == '0' && n > 1) return false;
        char carr[] = num.toCharArray();
        int i;
        
        for (i=0; i<=n/2-1; i++) {
            if ((carr[i] == '0' || carr[i] == '1' || carr[i] == '8') && carr[n-1-i] == carr[i]) continue;
            if ((carr[i] == '6' && carr[n-1-i] == '9') || (carr[i] == '9' && carr[n-1-i] == '6')) continue;
            return false;
        }
        
        if (n % 2 == 1) {
            if (!(carr[n/2] == '0' || carr[n/2] == '1' || carr[n/2] == '8')) return false;
        }
        
        return true;        
    }
}
