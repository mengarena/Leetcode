package com.leet;

//Given a string S, find the longest palindromic substring in S. 
//You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

//Amazon, Microsoft, Bloomberg
public class LongestPalindromicSubstring {

	public LongestPalindromicSubstring() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String s = "kabcb";
		
		System.out.println("Longest palindromic substring =  " + longestPalindrome(s));
	}

	
	//Loop from left to right to check each char
	//Once meet a new char, try to check the substring before this new char (inclusive), with len = nMaxLen + 1 or nMaxLen + 2, 
	//to see whether the substring is palindromic substring
	public String longestPalindrome(String s) {
		if (s == null || s.isEmpty()) return "";
		int n = s.length();
		char[] carr = s.toCharArray();
		int nMaxLen = 0;
		int nStartPos = 0, nEndPos = 0;
		
		for (int i=0; i<n; i++) {
			if (IsPalindrome(carr, i-nMaxLen, i)) { //Check the substring with len nMaxLen + 1
				nStartPos = i-nMaxLen;
				nEndPos = i;
				nMaxLen = nMaxLen + 1;
			} else if (IsPalindrome(carr, i-nMaxLen-1, i)) {  //Check the substring with len nMaxLen + 2
				nStartPos = i-nMaxLen-1;
				nEndPos = i;
				nMaxLen = nMaxLen + 2;	
			}
		}
		
		//Char index is nStartPos to nEndPos, so to extract the substring, needs to use nEndPos+1
		return s.substring(nStartPos, nEndPos+1);  
	}
	
	
	private boolean IsPalindrome(char[] carr, int nS, int nE) {
		if (nS < 0) return false;
		
		while (nS < nE) {
			if (carr[nS++] != carr[nE--]) return false;
		}
		
		return true;
	}
	
/*	
	//Works, but still exceeded time limit
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        String sRet = "";
        int n = s.length();
        String sS = new StringBuilder(s).reverse().toString();
        
        for (int i=n; i>=1; i--) {  //Possible length of the longest palindromic substring
        	int nRemainedLen = n-i;   //Total length of the non-palindromic part (including the content both before and after the palindromic substring
        	
        	for (int j = 0; j<=nRemainedLen; j++) {
        		String sSub = s.substring(j, j+i);
        		if (sS.indexOf(sSub) != -1) {
        			return sSub;
        		}
        	}
        }
        
        return sRet;
    }	
*/    
	
/*	
	//Works, but exceeded time limit
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";
        String sRet = "";
        int n = s.length();
        String sS = new StringBuilder(s).reverse().toString();
        
        for (int i=n; i>=1; i--) {  //Possible length of the longest palindromic substring
        	int nRemaimedLen = n-i;   //Total length of the non-palindromic part (including the content both before and after the palindromic substring
        	
        	for (int j = 0; j<=nRemaimedLen/2; j++) {
        		if (s.substring(j, j+i).compareTo(sS.substring(nRemaimedLen-j, nRemaimedLen-j+i)) == 0) { 
        			return s.substring(j, j+i);
        		}
        		
        		if (sS.substring(j, j+i).compareTo(s.substring(nRemaimedLen-j, nRemaimedLen-j+i)) == 0) {
        			return sS.substring(j, j+i);
        		}
        	}
        	
        }
        
        return sRet;
    }	
*/
    
}
