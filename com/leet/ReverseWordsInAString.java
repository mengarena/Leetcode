package com.leet;

//Given an input string, reverse the string word by word.
//
//For example,
//Given s = "the sky is blue",
//return "blue is sky the".
//
//Update (2015-02-12):
//For C programmers: Try to solve it in-place in O(1) space.
//
//click to show clarification.
//
//Clarification:
//What constitutes a word?
//A sequence of non-space characters constitutes a word.
//Could the input string contain leading or trailing spaces?
//Yes. However, your reversed string should not contain leading or trailing spaces.
//How about multiple spaces between two words?
//Reduce them to a single space in the reversed string.

public class ReverseWordsInAString {

	public ReverseWordsInAString() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		String s = "     the   sky is    blue        ";
		String s = "       ";
		
		System.out.println(reverseWords(s));
	}
	
    public String reverseWords(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        int i = 0;
        int j = 0;
        
        while (i < n) {
        	while (i < n && (s.charAt(i) == ' ' || s.charAt(i) == '	')) i++;
        	if (i == n) break;
        	
        	j = i;
        	while (j < n && (s.charAt(j) != ' ' && s.charAt(j) != '	')) j++;
        	
        	if (sb.length() > 0) sb.insert(0, " ");
        	sb.insert(0, s.substring(i, j));
        	
        	i = j;
        }
        
        return sb.toString();
    }
	
}
