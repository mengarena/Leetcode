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

//Bloomberg
public class ReverseWordsInAString {

	public ReverseWordsInAString() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
//		String s = "     the   sky is    blue        ";
		String s = "       ";
		
		System.out.println(reverseWords(s));
	}
	
	
	//ACC: 63%
	//Strategy:  Reverse whole string, and then reverse each word
	//Consecutive ' ' becomes one ' '
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        char[] carr = s.toCharArray();
        int n = carr.length;
        int i = 0, j = 0;
        char cPrev;
        cPrev = carr[0];
        int start = 0;
        int end = 0;
        
        reverseArray(carr, 0, n-1);
        
        for (i=0; i<n; i++) {
            if (carr[i] != ' ' && carr[i] != '\t') {
                carr[end++] = carr[i];
            } else if (i > 0 && carr[i-1] != ' ' && carr[i-1] != '\t') {
                reverseArray(carr, start, end-1);
                carr[end++] = ' ';
                start = end;
            }
        }
        
        reverseArray(carr, start, end-1);
        
        return new String(carr, 0, end > 0 && carr[end-1] == ' ' ? end-1 : end);
    }
    
    private void reverseArray(char[] carr, int start, int end) {
        char cElem;
        
        while (start < end) {
            cElem = carr[start];
            carr[start] = carr[end];
            carr[end] = cElem;
            
            start++;
            end--;
        }
    }	
	
	
	
	//ACC
    public String reverseWordsA(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        int i = 0;
        int j = 0;
        
        while (i < n) {
        	while (i < n && (s.charAt(i) == ' ' || s.charAt(i) == '	')) i++;  //Space/Tab
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
