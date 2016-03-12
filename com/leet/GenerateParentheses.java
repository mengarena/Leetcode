package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
//
//For example, given n = 3, a solution set is:
//
//"((()))", "(()())", "(())()", "()(())", "()()()"

public class GenerateParentheses {

	public GenerateParentheses() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int n = 4;
		List<String> lstPar = generateParenthesis(n);
		for (String sPar:lstPar) System.out.println(sPar);
	}
	
    public List<String> generateParenthesis(int n) {
    	List<String> lstPar = new ArrayList<String>();
    	
    	getParent("", n, n, lstPar);
    	
    	return lstPar;
    }
    
    public void getParent(String sPar, int nLeftRemainedCnt, int nRightRemainedCnt, List<String> lstPar) {
    	if (nLeftRemainedCnt > nRightRemainedCnt) return;
    	
    	if (nLeftRemainedCnt == 0 &&  nRightRemainedCnt == 0) {
    		lstPar.add(sPar);
    		return;
    	}
    	
    	if (nLeftRemainedCnt > 0) {
    		getParent(sPar+"(", nLeftRemainedCnt-1, nRightRemainedCnt, lstPar);
    	}
    	
    	if (nRightRemainedCnt > 0) {
    		getParent(sPar+")", nLeftRemainedCnt, nRightRemainedCnt-1, lstPar);    		
    	}
    	
    }
	
}
