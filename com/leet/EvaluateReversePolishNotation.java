package com.leet;

import java.util.Stack;

//Evaluate the value of an arithmetic expression in Reverse Polish Notation.
//
//Reverse Polish notation (RPN) is a mathematical notation in which every operator follows all of its operands, in contrast to Polish notation (PN), which puts the operator in the prefix position. I
//
//Valid operators are +, -, *, /. Each operand may be an integer or another expression.
//
//Some examples:
//  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
//  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

//Linkedin
public class EvaluateReversePolishNotation {

	public EvaluateReversePolishNotation() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String[] tokens = {"2", "1", "+", "3", "*"};
		//String[] tokens = {"4", "13", "5", "/", "+"};
		System.out.println("Result = " + evalRPN(tokens));
	}
	
	
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        int n = tokens.length;
        int nRet = 0;
        Stack<Integer> stkRPN = new Stack<Integer>();
        for (int i=0; i<n; i++) {
        	if (tokens[i].compareTo("+") == 0) {
        		int nOp2 = stkRPN.pop();
        		int nOp1 = stkRPN.pop();
        		stkRPN.push(nOp1+nOp2);
        		
        	} else if (tokens[i].compareTo("-") == 0) {
        		int nOp2 = stkRPN.pop();
        		int nOp1 = stkRPN.pop();
        		stkRPN.push(nOp1-nOp2);
        		
        	} else if (tokens[i].compareTo("*") == 0) {
        		int nOp2 = stkRPN.pop();
        		int nOp1 = stkRPN.pop();
        		stkRPN.push(nOp1*nOp2);
        		
        	} else if (tokens[i].compareTo("/") == 0) {
        		int nOp2 = stkRPN.pop();
        		int nOp1 = stkRPN.pop();
        		stkRPN.push(nOp1/nOp2);
        		
        	} else {
        		stkRPN.push(Integer.valueOf(tokens[i]));
        	}
        }
        
        nRet = stkRPN.pop();
        
        return nRet;
    }	
}
