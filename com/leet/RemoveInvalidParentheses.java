package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
//
//Note: The input string may contain letters other than the parentheses ( and ).
//
//Examples:
//"()())()" -> ["()()()", "(())()"]
//"(a)())()" -> ["(a)()()", "(a())()"]
//")(" -> [""]

public class RemoveInvalidParentheses {

	public RemoveInvalidParentheses() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		String s = "()())()";
		String s = "(a)())()";
//		String s = ")(";
//		String s = ")()))())))";
//		String s = ")((())))))()(((l((((";
		
		List<String> lstValid = removeInvalidParentheses(s);
		
		for (String sValid:lstValid) System.out.print(sValid + ", ");
	}
	
	
    public List<String> removeInvalidParentheses(String s) {
        List<String> lstParentheses = new ArrayList<String>();
        Queue<String> quStr = new LinkedList<String>();
        HashMap<String, Integer> hmStr = new HashMap<String, Integer>();   //Record the valid Strings
        int i;
        int nValidLen = -1;
        
        if (s == null) return lstParentheses;
        if (s.length() == 0) {
        	lstParentheses.add(s);
        	return lstParentheses;
        }
                
    	quStr.offer(s);
        
        while (!quStr.isEmpty()) {
        	String sTmp = quStr.poll();
        	if (IsValid(sTmp)) {
        		if (nValidLen == -1) {
        			nValidLen = sTmp.length();
        			lstParentheses.add(sTmp);
        			if (nValidLen == 0) break;
        		} else {
        			if (sTmp.length() < nValidLen) {
        				break;
        			} else {
    					lstParentheses.add(sTmp);
        			}
        		}
        	} else {
        		if (nValidLen > 0 && sTmp.length() <= nValidLen) continue;
            	for (i=0; i<sTmp.length(); i++) {
            		if (sTmp.charAt(i) == ')' || sTmp.charAt(i) == '(') {   //Try to remove each possible ")" or "("
            			String sNewTmp = sTmp.substring(0,i) + sTmp.substring(i+1);
            			if (!hmStr.containsKey(sNewTmp)) {
            				hmStr.put(sNewTmp, 1);
            				quStr.offer(sNewTmp);
            			}
            		}
            	}
        	}
        	
        }
        
        return lstParentheses;
    }
    
    
    private boolean IsValid(String s) {
    	int i;
    	int nCnt = 0;
    	
    	for (i=0; i<s.length(); i++) {
    		if (s.charAt(i) == '(') {
    			nCnt++;
    		} else if (s.charAt(i) == ')') {
    			nCnt--;
    		}
    		
    		if (nCnt < 0) return false;
    	}
    	
    	if (nCnt != 0) return false;
    	
    	return true;
    }
	
}
