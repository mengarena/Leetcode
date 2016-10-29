package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
//
//Note: The input string may contain letters other than the parentheses ( and ).
//
//Examples:
//"()())()" -> ["()()()", "(())()"]
//"(a)())()" -> ["(a)()()", "(a())()"]
//")(" -> [""]

//Facebook
//Hard
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
	
	
	//ACC:  78%
	//DFS
	public List<String> removeInvalidParentheses(String s) {
		 Set<String> setStr = new HashSet<String>();
		 int leftParents = 0, rightParents = 0;
		 
		 for (int i=0; i<s.length(); i++) {
			 if (s.charAt(i) == '(') leftParents++;
			 if (s.charAt(i) == ')') {
				 if (leftParents == 0) {
					 rightParents++;
				 } else {
					 leftParents--;
				 }
			 }
		 }
		 
		 getValidParenthese(s.toCharArray(), 0, s.length(), leftParents, rightParents, 0, setStr, new StringBuilder());
		 
		 return new ArrayList<String>(setStr);
	}
	
	public void getValidParenthese(char[] carr, int nIdx, int nLen, int leftParents, int rightParents, int nOpenParentCnt, Set<String> setStr, StringBuilder sb) {
		if (leftParents == 0 && rightParents == 0 && nIdx == nLen && nOpenParentCnt == 0) {
			setStr.add(sb.toString());
			return;
		}
		
		if (leftParents < 0 || rightParents < 0 || nOpenParentCnt < 0 || nIdx == nLen) return;
		
		char c = carr[nIdx];
		int sbLen = sb.length();
		
		if (c == '(') {
			getValidParenthese(carr, nIdx+1, nLen, leftParents-1, rightParents, nOpenParentCnt, setStr, sb);   //Remove the '('
			getValidParenthese(carr, nIdx+1, nLen, leftParents, rightParents, nOpenParentCnt+1, setStr, sb.append(c));
		} else if (c == ')') {
			getValidParenthese(carr, nIdx+1, nLen, leftParents, rightParents-1, nOpenParentCnt, setStr, sb);   //Remove the ')'
			getValidParenthese(carr, nIdx+1, nLen, leftParents, rightParents, nOpenParentCnt-1, setStr, sb.append(c));
		} else {
			getValidParenthese(carr, nIdx+1, nLen, leftParents, rightParents, nOpenParentCnt, setStr, sb.append(c));
		}
		
		//Backtracking. Above three 3 conditions, each add a char into sb,
		//so set it to original length (= remove last added) to return to the condition where we start trying
		//Otherwise, this is DFS, it directly goes to length, no other path could be travelled
		sb.setLength(sbLen);
	}
	
	//ACC: 38%
    //BFS
    public List<String> removeInvalidParenthesesB(String s) {
        List<String> lstParentheses = new ArrayList<String>();
        Queue<String> quStr = new LinkedList<String>();
        Set<String> hmStr = new HashSet<String>();
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
        	    if (nValidLen > 0) {
        	        if (sTmp.length() == nValidLen) continue;
        	        if (sTmp.length() < nValidLen) break;
        	    }
        	    
            	for (i=0; i<sTmp.length(); i++) {
            		if (sTmp.charAt(i) == ')' || sTmp.charAt(i) == '(') {
            			String sNewTmp = sTmp.substring(0,i) + sTmp.substring(i+1);
            			if (!hmStr.contains(sNewTmp)) {   //If not using Hashset or hashmap, directly using Queue to judge, will exceed time limit
            				hmStr.add(sNewTmp);
            				quStr.offer(sNewTmp);
            			}
            		}
            	}
        	}
        	
        }
        
        return lstParentheses;
    }

	
	
	
    public List<String> removeInvalidParenthesesA(String s) {
        List<String> lstParentheses = new ArrayList<String>();  //Result
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
