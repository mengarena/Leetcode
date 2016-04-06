package com.leet;

import java.util.Stack;

//Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
//
//The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

//Google, Amazon, Bloomberg
public class ValidParentheses {

	public ValidParentheses() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "";
		boolean bValid = isValid(s);
		
		if (bValid) {
			System.out.println(s + " IS Valid !");
		} else {
			System.out.println(s + " IS NOT Valid !");
		}
	
	}
	
    public boolean isValid(String s) {
    	Stack<Integer> stkParentheses = new Stack<Integer>();

        int n = 0;
        char cSingle;
        int nVal;
        
        if (s == null || s.length() == 0) return true;
        if (s.length() == 1) return false;
        
        n = s.length();
        
        for (int i=0; i<n; i++) {
        	cSingle = s.charAt(i);
        	if (cSingle == '(') {
        		stkParentheses.push(1);
        	} 
        	
        	if (cSingle == ')') {
        		if (stkParentheses.size() == 0) return false;
        		nVal = stkParentheses.pop(); 
        		if (nVal != 1) return false;
        	}
        	
        	if (cSingle == '[') {
        		stkParentheses.push(2);
        	}
        	
        	if (cSingle == ']') {
        		if (stkParentheses.size() == 0) return false;
        		nVal = stkParentheses.pop(); 
        		if (nVal != 2) return false;
        	}
        	
        	if (cSingle == '{') {
        		stkParentheses.push(3);
        	}
        	
        	if (cSingle == '}') {
        		if (stkParentheses.size() == 0) return false;
        		nVal = stkParentheses.pop(); 
        		if (nVal != 3) return false;
        	}
        	
        }
        
        if (stkParentheses.size() != 0) return false;
        
        return true;
    }
	
}
