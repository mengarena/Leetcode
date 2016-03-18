package com.leet;

//Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
//
//For example,
//Given:
//s1 = "aabcc",
//s2 = "dbbca",
//
//When s3 = "aadbbcbcac", return true.
//When s3 = "aadbbbaccc", return false.

public class InterleavingString {

	public InterleavingString() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String s1 = "aabcc";
		String s2 = "dbbca";
		String s3 = "aadbbcbcac";
		
		System.out.println(isInterleave(s1, s2, s3));
	}
	
	
	//Accepted
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null && s2 == null && s3 == null) return true;
        if ((s1 == null || s1.length() == 0) && s2 != null) {
            if (s3 == null) return false;
            return s3.equals(s2);
        }
        
        if (s1 != null && (s2 == null || s2.length() == 0)) {
            if (s3 == null) return false;
            return s3.equals(s1);
        }
        
        if (s1.length() + s2.length() != s3.length()) return false;
        
        int n1 = s1.length();
        int n2 = s2.length();
        int i, j;
        
        boolean match[][] = new boolean[n2+1][n1+1];
        
        //match[i][j] ---- i from s2, j from s1 matched, so in s3, totally will be i+j matched
        
        match[0][0] = true;
        
        for (i=1; i<=n1; i++) match[0][i] = match[0][i-1] && (s1.charAt(i-1) == s3.charAt(i-1));  //0 from s2 matched, to see how s1 could be matched with s3
        for (i=1; i<=n2; i++) match[i][0] = match[i-1][0] && (s2.charAt(i-1) == s3.charAt(i-1));  //0 from s1 matched, to see how s2 could be matched with s3
        
        for (i=1; i<=n2; i++) {
            for (j=1; j<=n1; j++) {
                match[i][j] = (match[i-1][j] && (s2.charAt(i-1) == s3.charAt(i-1+j))) || (match[i][j-1] && (s1.charAt(j-1) == s3.charAt(j-1+i)));
                
                //index i in match corresponding to index i-i in string
            }
        }

        return match[n2][n1];    	
    }	
	
	
	//Recursive, time exceeded limit
    public boolean isInterleaveA(String s1, String s2, String s3) {
        if (s1 == null && s2 == null && s3 == null) return true;
        if ((s1 == null || s1.length() == 0) && s2 != null) {
            if (s3 == null) return false;
            return s3.equals(s2);
        }
        
        if (s1 != null && (s2 == null || s2.length() == 0)) {
            if (s3 == null) return false;
            return s3.equals(s1);
        }
        
        if (s1.length() + s2.length() != s3.length()) return false;
        
        int p1 = 0, p2 = 0, p3 = 0;
        
        if (s1.charAt(p1) == s3.charAt(p3) && s2.charAt(p2) != s3.charAt(p3)) {
            return isInterleave(s1.substring(1), s2, s3.substring(1));
        } else if (s2.charAt(p2) == s3.charAt(p3) && s1.charAt(p1) != s3.charAt(p3)) {
            return isInterleave(s1, s2.substring(1), s3.substring(1));
        } else if (s1.charAt(p1) == s3.charAt(p3) && s2.charAt(p2) == s3.charAt(p3)) {
            return isInterleave(s1.substring(1), s2, s3.substring(1)) || isInterleave(s1, s2.substring(1), s3.substring(1));
        } else {
            return false;
        }

    }}
