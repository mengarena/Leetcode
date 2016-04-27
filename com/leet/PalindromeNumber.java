package com.leet;

//Determine whether an integer is a palindrome. Do this without extra space.
//
//click to show spoilers.
//
//Some hints:
//Could negative integers be palindromes? (ie, -1)
//
//If you are thinking of converting the integer to string, note the restriction of using extra space.
//
//You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
//
//There is a more generic way of solving this problem.

public class PalindromeNumber {

	public PalindromeNumber() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int n = -2147447412; //-2147483648;
		boolean bRet = isPalindrome(n);
				
		if (bRet == false) {
			System.out.println(n + " IS NOT Palindrome !");
		} else {
			System.out.println(n + " IS Palindrome !");
		}
		
	}
	
	//ACC: 94%
	//sign is also taken into consideration, so if it is negative, it could not be Palindrome
    public boolean isPalindrome(int x) {
    	int nNew = 0;
    	int nRemained = x;
    	
    	if (x < 0) return false;
    	if (x < 10) return true;
    	
    	while (nRemained/10 > 0) {
    		nNew = nNew*10 + (nRemained % 10);
    		nRemained = nRemained/10;
    	}
    	
    	nNew = nNew*10 + nRemained;
    	
    	return (nNew == x);
    }
	
}
