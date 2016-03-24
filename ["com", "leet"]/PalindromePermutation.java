package com.leet;

//Given a string, determine if a permutation of the string could form a palindrome.
//
//For example,
//"code" -> False, "aab" -> True, "carerac" -> True.
//
//Hint:
//
//Consider the palindromes of odd vs even length. What difference do you notice?
//Count the frequency of each character.
//If each character occurs even number of times, then it must be a palindrome. 
//How about character which occurs odd number of times?

public class PalindromePermutation {

	public PalindromePermutation() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() <= 1) return true;
        int[] narrCnt = new int[256];
        int i;
        int nOddCnt = 0;
        
        for (i=0; i<s.length(); i++) {
            narrCnt[s.charAt(i)]++;
        }
        
        for (i=0; i<256; i++) {
            if (narrCnt[i] % 2 == 1) nOddCnt++;
        }
        
        return nOddCnt <= 1;
    }
	
}
