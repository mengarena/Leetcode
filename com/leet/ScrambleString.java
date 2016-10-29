package com.leet;

//Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
//
//Below is one possible representation of s1 = "great":
//
//    great
//   /    \
//  gr    eat
// / \    /  \
//g   r  e   at
//           / \
//          a   t
//To scramble the string, we may choose any non-leaf node and swap its two children.
//
//For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
//
//    rgeat
//   /    \
//  rg    eat
// / \    /  \
//r   g  e   at
//           / \
//          a   t
//We say that "rgeat" is a scrambled string of "great".
//
//Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
//
//    rgtae
//   /    \
//  rg    tae
// / \    /  \
//r   g  ta  e
//       / \
//      t   a
//We say that "rgtae" is a scrambled string of "great".
//
//Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

//Hard
public class ScrambleString {

	public ScrambleString() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//String s1 = "aa";
		//String s2 = "ab";
		
		String s1 = "abcdefghijklmnopq";
		String s2 = "efghijklmnopqcadb";
		
	//	String s1 = "tqxpxeknttgwoppemjkivrulaflayn";
	//	String s2 = "afaylnlurvikjmeppowgttnkexpxqt";
		
		
		System.out.println(isScramble(s1, s2));
		

	}
	
	
	private boolean isAnagram(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		int n = s1.length();
		int i;
		int narrCharCnt[] = new int[26];
		
		for (i=0; i<n; i++) narrCharCnt[s1.charAt(i)-'a']++;
		
		for (i=0; i<n; i++) narrCharCnt[s2.charAt(i)-'a']--;
		
		for (i=0; i<26; i++) {
			if (narrCharCnt[i] != 0) return false;
		}
		
		return true;
	}
	
    
    public boolean isScramble(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null && s2 != null) return false;
        if (s1 != null && s2 == null) return false;
        int n1 = s1.length();
        int n2 = s2.length();
        
        if (n1 != n2) return false;
        if (s1.equals(s2)) return true;
        if (n1 == 1 && n2 == 1) return false;
        
        for (int i=1; i<n1; i++) {  //Length
        	String s11 = s1.substring(0, i);
        	String s12 = s1.substring(i);
        	
        	String s21 = s2.substring(0, i);
        	String s22 = s2.substring(i);
        	
        	String s31 = s2.substring(n2-i);
        	String s32 = s2.substring(0, n2-i);
        	
        	//In this step, if isAnagram is not used: works, but much less efficient
        	if ( (isAnagram(s11, s21) && isAnagram(s12, s22) && isScramble(s11, s21) && isScramble(s12, s22)) || 
        		 (isAnagram(s11, s31) && isAnagram(s12, s32) && isScramble(s11, s31) && isScramble(s12, s32))) return true;       	
        }
        
        return false;
        
    }
	
    
    
	
}
