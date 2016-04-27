package com.leet;

//Write a function that takes a string as input and returns the string reversed.
//
//Example:
//Given s = "hello", return "olleh".

public class ReverseString {

	public ReverseString() {
		// TODO Auto-generated constructor stub
	}

    public String reverseStringA(String s) {
        if (s == null || s.length() <= 1) return s;
        char carr[] = s.toCharArray();
        char tmp = 0;
        int n = s.length();
        
        for (int i=1; i<=s.length()/2; i++) {
        	tmp = carr[i-1];
        	carr[i-1] = carr[n-i];
        	carr[n-i] = tmp;
        }
        
        return new String(carr);
        
    }
    
    
    public String reverseString(String s) {
        if (s == null || s.length() <= 1) return s;
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        
        for (int i=n-1; i>=0; i--) {
        	sb.append(s.charAt(i));
        }
        
        return sb.toString();
    }
}
