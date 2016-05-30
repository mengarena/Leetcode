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
	
	
	//ACC
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        char carr[] = s.toCharArray();
        Stack<Character> stk = new Stack<Character>();
        
        stk.push(carr[0]);
        
        for (int i=1; i<s.length(); i++) {
            if (carr[i] == '(' || carr[i] == '[' || carr[i] == '{') {
                stk.push(carr[i]);    
            } else {
                if (carr[i] == ')') {
                    if (!stk.isEmpty() && stk.peek() == '(') {
                        stk.pop();
                    } else {
                        return false;
                    }
                } else if (carr[i] == ']') {
                    if (!stk.isEmpty() && stk.peek() == '[') {
                        stk.pop();
                    } else {
                        return false;
                    }
                } else if (carr[i] == '}') {
                    if (!stk.isEmpty() && stk.peek() == '{') {
                        stk.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        if (!stk.isEmpty()) return false;
        
        return true;
    }	
	
	
    //ACC
	//'('---1;  '[' --- 2;  '{' -- 3
    public boolean isValidA(String s) {
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
