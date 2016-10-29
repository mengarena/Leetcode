package com.leet;

//Given two strings s and t which consist of only lowercase letters.
//
//String t is generated by random shuffling string s and then add one more letter at a random position.
//
//Find the letter that was added in t.
//
//Example:
//
//Input:
//s = "abcd"
//t = "abcde"
//
//Output:
//e
//
//Explanation:
//'e' is the letter that was added.

//Google
//Easy
public class FindTheDifference {

	public FindTheDifference() {
		// TODO Auto-generated constructor stub
	}


	//ACC
    public char findTheDifference(String s, String t) {
        char ret = 0;
        int i;
        
        for (i=0; i<s.length(); i++) ret ^= s.charAt(i);
        for (i=0; i<t.length(); i++) ret ^= t.charAt(i);
        
        return ret;
    }	
	
	
	//ACC: Naive
    public char findTheDifferenceA(String s, String t) {
        if (s == null || s.isEmpty()) {
            if (t == null || t.isEmpty()) {
                return ' ';
            } else if (t.length() > 1) {
                return ' ';
            } else {
                return t.charAt(0);
            }
        } 
        
        if (t.length() != s.length() + 1) return ' ';
        
        int[] narrCnt = new int[26];
        int i;
        char ret = ' ';
        
        for (i=0; i<t.length(); i++) narrCnt[t.charAt(i)-'a']++;
        
        for (i=0; i<s.length(); i++) narrCnt[s.charAt(i)-'a']--;
        
        for (i=0; i<26; i++) {
            if (narrCnt[i] == 1) {
                ret = (char)('a' + i);
                break;
            }
        }
        
        return ret;
    }

}
