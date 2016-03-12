package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Implement a basic calculator to evaluate a simple expression string.
//
//The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
//You may assume that the given expression is always valid.
//
//Some examples:
//"1 + 1" = 2
//" 2-1 + 2 " = 3
//"(1+(4+5+2)-3)+(6+8)" = 23
//Note: Do not use the eval built-in library function.


//Calculator II
//Implement a basic calculator to evaluate a simple expression string.
//
//The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
//The integer division should truncate toward zero.
//
//You may assume that the given expression is always valid.
//
//Some examples:
//"3+2*2" = 7
//" 3/2 " = 1
//" 3+5 / 2 " = 5
//Note: Do not use the eval built-in library function.

public class BasicCalculator {

	class ClsSymbol {
		int nType;   //0: number;  1: (;  2:  );   3: +;  4:  -;  5: *;   6: /   
		int nNum;    //Number value
		int nSymbol; //For "(" or ")" to remember the symbol before it
		int nPos;    //In the string, next should process from this position
		
		ClsSymbol() {
			nType = -1;
			nNum = 0;
			nSymbol = 1;
			nPos = 0;
		}
	}
	
	
	//Number has symbols (+/-)
	//( has symbols (+/-)
	//when meet ")", pop-up until meeting a ")"
	//When meets "+/-", don't pop or push, only remember it by using previous symbol
	//When preivous is "*" or "/", and current is number, pop-out, until not "*", "/";  if current is "(", push into stack 
	public void run() {
		//String s = " (1+(4+5+2)-3)+(6+8)   " ;
		//String s = "-(2)+(5+(3) + (3-4))";
		String s = "3*(5*6)*4+(2-4)*2";
		//String s = "3*(5*6)";
		//String s = "3*(6/3 - 1 + 2) - 1 + (4/2*(3+5))";
		//String s = " 3+5 / 2 ";
		System.out.println("Result = " + calculate(s));
	}

	
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int nRet = 0;
        int nPrevSymbol = 0;     //0: meaningless;   1: +;  -1: -
        Stack<ClsSymbol> stk = new Stack<ClsSymbol>();
        ClsSymbol csTmpUnit;
        
        List<ClsSymbol> lstUnits = parseExpression(s);
        if (lstUnits.size() == 0) return 0;
        
        for (ClsSymbol csUnit:lstUnits) {
        	switch (csUnit.nType) {  //Current unit
        	case 0:   //Number
        		if (nPrevSymbol != 0) {
        			csUnit.nNum = csUnit.nNum * nPrevSymbol;
        			nPrevSymbol = 0;
        		}
        			
				if (stk.isEmpty()) {
					stk.push(csUnit);
					break;
				}
				
				csTmpUnit = stk.peek();
				if (csTmpUnit.nType == 5 || csTmpUnit.nType == 6) {   // "*" or "/"
					stk.pop();
					ClsSymbol csTmpNum = stk.pop();
					if (csTmpUnit.nType == 5) csUnit.nNum = csTmpNum.nNum*csUnit.nNum;
					if (csTmpUnit.nType == 6) csUnit.nNum = (int) csTmpNum.nNum/csUnit.nNum;    
					stk.push(csUnit);
				} else {
					stk.push(csUnit);
				}
				
        		break;
        		
        	case 1:   //"("
        		if (nPrevSymbol == -1) csUnit.nSymbol = nPrevSymbol;
        		stk.push(csUnit);
        		nPrevSymbol = 0;
        		break;
        		
        	case 2:   //")"
        		int nTmpRet = 0;
        		while (!stk.isEmpty()) {
        			ClsSymbol csTmp = stk.pop();
        			if (csTmp.nType == 1) {  //"("
        				csUnit.nNum = nTmpRet*csTmp.nSymbol;
        				csUnit.nType = 0;  //number
        				//stk.push(csUnit);
        				break;
        			} else {
        				nTmpRet = nTmpRet + csTmp.nNum;
        			}
        		}
        		
        		nPrevSymbol = 0;
        		
        		if (stk.isEmpty()) {
        			stk.push(csUnit);
        			break;
        		}
        		
				csTmpUnit = stk.peek();
				if (csTmpUnit.nType == 5 || csTmpUnit.nType == 6) {   // "*" or "/"
					stk.pop();
					ClsSymbol csTmpNum = stk.pop();
					if (csTmpUnit.nType == 5) csUnit.nNum = csTmpNum.nNum*csUnit.nNum;
					if (csTmpUnit.nType == 6) csUnit.nNum = (int) csTmpNum.nNum/csUnit.nNum;    
					stk.push(csUnit);
				} else {
					stk.push(csUnit);
				}
        		
        		break;
        		
        	case 3:   // +
        		nPrevSymbol = 1;
        		break;
        		
        	case 4:   // -
        		nPrevSymbol = -1;
        		break;
        	case 5:   // *
        		nPrevSymbol = 0;
        		stk.push(csUnit);
        		break;
        		
        	case 6:   // /
        		nPrevSymbol = 0;
        		stk.push(csUnit);
        		break;
        	default:
        		break;
        		
        	}  //Switch
        	
        //	s = s.substring(csUnit.nPos);
        }  //for
        
        
        while (!stk.isEmpty()) {
        	nRet = nRet + stk.pop().nNum;
        }
        
        return nRet;
    }

    
    private List<ClsSymbol> parseExpression(String s) {
    	List<ClsSymbol> lstUnit = new ArrayList<ClsSymbol>();
    	int n = s.length();
    	int i;
    	String sUnit = "";
    	
    	for (i=0; i<n; i++) {
    		char c = s.charAt(i);
    		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')') {
    			if (sUnit.length() > 0) {
    				ClsSymbol csUnit = new ClsSymbol();
    				csUnit.nNum = Integer.valueOf(sUnit);
    				csUnit.nType = 0;
    				lstUnit.add(csUnit);
    				sUnit = "";
    			}
    			
    			ClsSymbol csUnitTmp = new ClsSymbol();
    			switch(c) {
    			case '(':
    				csUnitTmp.nType = 1;
    				break;
    			case ')':
    				csUnitTmp.nType = 2;
    				break;
    			case '+':
    				csUnitTmp.nType = 3;
    				break;
    			case '-':
    				csUnitTmp.nType = 4;
    				break;
    			case '*':
    				csUnitTmp.nType = 5;
    				break;
    			case '/':
    				csUnitTmp.nType = 6;
    				break;
    			}
    			
    			lstUnit.add(csUnitTmp);
    		} else if (c == ' ') {
    			continue;
    		} else {
    			sUnit = sUnit + c;
    		}
 
    	}
    	
    	if (sUnit.length() > 0) {
			ClsSymbol csUnit = new ClsSymbol();
			csUnit.nNum = Integer.valueOf(sUnit);
			csUnit.nType = 0;
			lstUnit.add(csUnit);    		
    	}
    	
    	return lstUnit;
    }
    
    
/*  Function is correct for both Calculator I, II, but exceed time limit  
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        int nRet = 0;
        int nPrevSymbol = 0;     //0: meaningless;   1: +;  -1: -
        Stack<ClsSymbol> stk = new Stack<ClsSymbol>();
        ClsSymbol csTmpUnit;
        
        while (!s.isEmpty()) {
        	ClsSymbol csUnit = getUnit(s);

        	switch (csUnit.nType) {
        	case 0:   //Number
        		if (nPrevSymbol != 0) {
        			csUnit.nNum = csUnit.nNum * nPrevSymbol;
        			nPrevSymbol = 0;
        		}
        			
				if (stk.isEmpty()) {
					stk.push(csUnit);
					break;
				}
				
				csTmpUnit = stk.peek();
				if (csTmpUnit.nType == 5 || csTmpUnit.nType == 6) {   // "*" or "/"
					stk.pop();
					ClsSymbol csTmpNum = stk.pop();
					if (csTmpUnit.nType == 5) csUnit.nNum = csTmpNum.nNum*csUnit.nNum;
					if (csTmpUnit.nType == 6) csUnit.nNum = (int) csTmpNum.nNum/csUnit.nNum;    
					stk.push(csUnit);
				} else {
					stk.push(csUnit);
				}
				
        		break;
        		
        	case 1:   //"("
        		if (nPrevSymbol == -1) csUnit.nSymbol = nPrevSymbol;
        		stk.push(csUnit);
        		nPrevSymbol = 0;
        		break;
        		
        	case 2:   //")"
        		int nTmpRet = 0;
        		while (!stk.isEmpty()) {
        			ClsSymbol csTmp = stk.pop();
        			if (csTmp.nType == 1) {  //"("
        				csUnit.nNum = nTmpRet*csTmp.nSymbol;
        				csUnit.nType = 0;
        				//stk.push(csUnit);
        				break;
        			} else {
        				nTmpRet = nTmpRet + csTmp.nNum;
        			}
        		}
        		
        		nPrevSymbol = 0;
        		
        		if (stk.isEmpty()) {
        			stk.push(csUnit);
        			break;
        		}
        		
				csTmpUnit = stk.peek();
				if (csTmpUnit.nType == 5 || csTmpUnit.nType == 6) {   // "*" or "/"
					stk.pop();
					ClsSymbol csTmpNum = stk.pop();
					if (csTmpUnit.nType == 5) csUnit.nNum = csTmpNum.nNum*csUnit.nNum;
					if (csTmpUnit.nType == 6) csUnit.nNum = (int) csTmpNum.nNum/csUnit.nNum;    
					stk.push(csUnit);
				} else {
					stk.push(csUnit);
				}
        		
        		break;
        		
        	case 3:   // +
        		nPrevSymbol = 1;
        		break;
        		
        	case 4:   // -
        		nPrevSymbol = -1;
        		break;
        	case 5:   // *
        		nPrevSymbol = 0;
        		stk.push(csUnit);
        		break;
        		
        	case 6:   // /
        		nPrevSymbol = 0;
        		stk.push(csUnit);
        		break;
        	default:
        		break;
        	}
        	
        	s = s.substring(csUnit.nPos);
        }
        
        
        while (!stk.isEmpty()) {
        	nRet = nRet + stk.pop().nNum;
        }
        
        return nRet;
    }
    
	private ClsSymbol getUnit(String s) {
		int n = s.length();
		String sRet = "";
		ClsSymbol csRet = new ClsSymbol();
		
		for (int i=0; i<n;) {
			if (s.charAt(i) == ' ') {
				i++;
			} else if (s.charAt(i) == '(') {
				if (sRet.length() == 0) {
					csRet.nType = 1;
					csRet.nPos = i+1;
				} else {
					csRet.nNum = Integer.valueOf(sRet).intValue();
					csRet.nType = 0;
					csRet.nPos = i;
				}

				return csRet;
				
			} else if (s.charAt(i) == ')') {
				if (sRet.length() == 0) {
					csRet.nType = 2;
					csRet.nPos = i+1;
				} else {
					csRet.nNum = Integer.valueOf(sRet).intValue();
					csRet.nType = 0;
					csRet.nPos = i;
				}

				return csRet;
			} else if (s.charAt(i) == '+') {
				if (sRet.length() == 0) {
					csRet.nType = 3;
					csRet.nPos = i+1;
				} else {
					csRet.nNum = Integer.valueOf(sRet).intValue();
					csRet.nType = 0;
					csRet.nPos = i;
				}

				return csRet;
			} else if (s.charAt(i) == '-') {
				if (sRet.length() == 0) {
					csRet.nType = 4;
					csRet.nPos = i+1;
				} else {
					csRet.nNum = Integer.valueOf(sRet).intValue();
					csRet.nType = 0;
					csRet.nPos = i;
				}

				return csRet;
			} else if (s.charAt(i) == '*') {
				if (sRet.length() == 0) {
					csRet.nType = 5;
					csRet.nPos = i+1;
				} else {
					csRet.nNum = Integer.valueOf(sRet).intValue();
					csRet.nType = 0;
					csRet.nPos = i;
				}

				return csRet;
			} else if (s.charAt(i) == '/') {
				if (sRet.length() == 0) {
					csRet.nType = 6;
					csRet.nPos = i+1;
				} else {
					csRet.nNum = Integer.valueOf(sRet).intValue();
					csRet.nType = 0;
					csRet.nPos = i;
				}

				return csRet;

			} else {
				sRet = sRet + s.charAt(i);
				i++;
			}
		}

		csRet.nPos = n;

		if (sRet.length() > 0) {
			//Number
			csRet.nType = 0;
			csRet.nNum = Integer.valueOf(sRet).intValue();
		} else {
			csRet.nType = -1;
		}
		
		return csRet;
	}
*/	
}
