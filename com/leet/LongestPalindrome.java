package com.leet;

//Given a string which consists of lowercase or uppercase letters, 
//find the length of the longest palindromes that can be built with those letters.
//
//This is case sensitive, for example "Aa" is not considered a palindrome here.
//
//Note:
//Assume the length of given string will not exceed 1,010.
//
//Example:
//
//Input:
//"abccccdd"
//
//Output:
//7
//
//Explanation:
//One longest palindrome that can be built is "dccaccd", whose length is 7.

//Google
//Easy
public class LongestPalindrome {

	public LongestPalindrome() {
		// TODO Auto-generated constructor stub
	}

	//ACC: 18ms
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] narrCnt = new int[52];
        int n = s.length();
        int i;
        int count = 0;
        char c = 0;
        boolean bOdd = false;
        
        for (i=0; i<n; i++) {
            c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                narrCnt[c - 'a' + 26]++;
            } else if (c >= 'A' && c <= 'Z') {
                narrCnt[c-'A']++;
            }
        }
        
        for (i=0; i<52; i++) {
            count += narrCnt[i] % 2 == 0? narrCnt[i]:narrCnt[i]-1;
            
            if (narrCnt[i] % 2 == 1 && bOdd == false) bOdd = true; 
        }
        
        if (bOdd) count++;
        
        return count;
    }

}
