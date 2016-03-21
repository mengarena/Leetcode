package com.leet;

//Given a string S and a string T, count the number of distinct subsequences of T in S.
//
//A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters 
//without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//
//Here is an example:
//S = "rabbbit", T = "rabbit"
//
//Return 3.

public class DistinctSubsequences {

	public DistinctSubsequences() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String s = "ccc";
		String t = "cc";
		
		System.out.println(numDistinct(s, t));
	}
	
	
	//Accepted
	//Explained well:  https://leetcode.com/discuss/26680/easy-to-understand-dp-in-java
    public int numDistinct(String s, String t) {
        if ((s == null || s.isEmpty()) && (t != null && !t.isEmpty())) return 0;
        int ns = s.length();
        int nt = t.length();
        
        if (ns == nt) {
        	if (s.equals(t)) {
        		return 1;
        	} else {
        		return 0;
        	}
        } else if (ns < nt) {
        	return 0;
        }
        
        int i,j;
        int[][] dp = new int[nt+1][ns+1];    //number of solutions of get sequence t[0...i] from s[0...j] 
        for (i=0; i<=ns; i++) dp[0][i] = 1;  //t = "";  to get subsequence from s at every position, there is only one solution (by deleting all characters)
        for (j=1; j<=nt; j++) dp[j][0] = 0;  //This line of code could be ignored, but the value at these positions will be set to 0 by default
        
        for (i=1; i<=nt; i++) {
        	for (j=1; j<=ns; j++) {
        		if (t.charAt(i-1) == s.charAt(j-1)) {
        			dp[i][j] = dp[i-1][j-1] + dp[i][j-1];   //two cases:  match s[j] and t[i], match rest in s[0..j-1], t[0..i-1]; or we just don't use s[j], and use previous one, i.e match s[j-1] and t[i]
        		} else {
        			dp[i][j] = dp[i][j-1];   //use previous one, i.e match s[j-1] and t[i]
        		}
        	}
        }
        
        return dp[nt][ns];
    }
    
    
    
	//Works, but exceed time limit
    public int numDistinctA(String s, String t) {
        if ((s == null || s.isEmpty()) && (t != null && !t.isEmpty())) return 0;
        int ns = s.length();
        int nt = t.length();
        
        if (ns == nt) {
        	if (s.equals(t)) {
        		return 1;
        	} else {
        		return 0;
        	}
        } else if (ns < nt) {
        	return 0;
        }
        
        int nCount = 0;
        for (int i=0; i<=ns-nt; i++) {
        	if (s.charAt(i) == t.charAt(0)) {
        		if (t.length() == 1) {
        			nCount++;
        		} else {
        			nCount += numDistinct(s.substring(i+1), t.substring(1));
        		}
        	}
        }
        
        return nCount;
    }
	
}
