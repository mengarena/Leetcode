package com.leet;

import java.util.ArrayList;
import java.util.List;


//Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. 
//The valid operators are +, - and *.
//
//
//Example 1
//Input: "2-1-1".
//
//((2-1)-1) = 0
//(2-(1-1)) = 2
//Output: [0, 2]
//
//
//Example 2
//Input: "2*3-4*5"
//
//(2*(3-(4*5))) = -34
//((2*3)-(4*5)) = -14
//((2*(3-4))*5) = -10
//(2*((3-4)*5)) = -10
//(((2*3)-4)*5) = 10
//Output: [-34, -14, -10, -10, 10]
//		

public class DifferentWaysAddParentheses {

	public DifferentWaysAddParentheses() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String sInput = "11";
		List<Integer> lstResult = diffWaysToCompute(sInput);
		
		for (Integer nResult:lstResult) System.out.print(nResult + ",");
		
	}
	
	
	//Divide and Conquer
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> lstResult = new ArrayList<Integer>();
        int nLen = input.length();
        int i, j, k;
        char cElement;
        boolean bOperator = false;
        
        for (i=0; i<nLen; i++) {
        	cElement = input.charAt(i);
        	if (cElement == '+' || cElement == '-' || cElement == '*') {
        		bOperator = true;
        		List<Integer> lstLeft = new ArrayList<Integer>();
        		List<Integer> lstRight = new ArrayList<Integer>();
        		lstLeft = diffWaysToCompute(input.substring(0, i));
        		lstRight = diffWaysToCompute(input.substring(i+1));
        		
        		for (j=0; j<lstLeft.size(); j++) {
        			for (k=0; k<lstRight.size(); k++) {
        				if (cElement == '+') {
        					lstResult.add(lstLeft.get(j) + lstRight.get(k));
        				} else if (cElement == '-') {
        					lstResult.add(lstLeft.get(j) - lstRight.get(k));
        				} else {
        					lstResult.add(lstLeft.get(j) * lstRight.get(k));
        				}
        			}
        		}
        	}
        }
        
        if (bOperator == false) {
        	lstResult.add(Integer.valueOf(input));
        }
        
        return lstResult;
    }
	
    
}
