package com.leet;

//Given a string containing just the characters '(' and ')', 
//find the length of the longest valid (well-formed) parentheses substring.
//
//For "(()", the longest valid parentheses substring is "()", which has length = 2.
//
//Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

//Hard
public class LongestValidParentheses {

	public LongestValidParentheses() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//String s = ")()((())";  //4
		//String s = "(()";  // 2
		//String s = "()(((((";
		String s = ")()))";
		//String s = "(()(((()";
		
		System.out.println("Longest Valid = " + longestValidParentheses(s));
	}
	
	
	//AC Strategy: first round, replace all "()" (i.e. "(" and ")" are direct neighbors) with "11";
	//Second round, from the digits, propagate to the left and right, once find a "(" on the left and a ")" on the right, 
	//replace them with "1" "1" each
	//Last: Count continuous "1" to calculate the length
	//In Second round, for the consecutive "1", only the first one is based on to propagate
	public int longestValidParentheses(String s) {
        if (s == null || s.length() <= 1) return 0;
        int n = s.length();
        int i;
        int nMaxLen = 0;
        char[] carr = s.toCharArray();
        
        //Replace "()" with "11"
        for (i=1; i<n; i++) {
        	if (carr[i] == ')' && carr[i-1] == '(') {
        		carr[i] = 1;
        		carr[i-1] = 1;
        	}
        }
        
        i=1;
        boolean bPrevDigit = false;
        int nLeft = 0;
        int nRight = 0;
        
        while (i < n-1) {
        	if (carr[i] == 1) {
        		if (bPrevDigit == true) {
        			i++;
        			continue;
        		} else {
        			bPrevDigit = true;
        			
        			//Propagate to left and right to search for "(" and ")" pairs
        			nLeft = i-1;
        			nRight = i+1;
        			
        			while (nLeft >= 0 && nRight <= n-1) {
        				while (nLeft >= 0 && carr[nLeft] == 1) nLeft--;
        				while (nRight <= n-1 && carr[nRight] == 1) nRight++;
        				
        				if (nLeft >= 0 && nRight <= n-1 && carr[nLeft] == '(' && carr[nRight] == ')') {
        					carr[nLeft] = 1;
        					carr[nRight] = 1;
        				} else {
        					break;
        				}
        			}
        			
        			i++;
        		}
        	} else {
        		bPrevDigit = false;
        		i++;
        	}
        }
        
        
        //Count continuous "1" to calculate length
        int nTmpLen = 0;
        
        for (i=0; i<n; i++) {
        	if (carr[i] == 1) {
        		nTmpLen++;
        	} else {
        		nMaxLen = Math.max(nTmpLen, nMaxLen);
        		nTmpLen = 0;
        	}
        }
        
        nMaxLen = Math.max(nTmpLen, nMaxLen);;
        
        return nMaxLen;
	}
	

	
	
	
	private boolean isValidParenthese(String s) {
		if (s == null || s.length() == 0) return true;
		int n = s.length();
		int i;
		int nParentCnt = 0;
		char cElement;
		
		for (i=0; i<n; i++) {
			cElement = s.charAt(i);
			if (cElement == '(') nParentCnt++;
			if (cElement == ')') nParentCnt--;
			
			if (nParentCnt < 0) return false;
		}
		
		if (nParentCnt != 0) return false;
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	public int longestValidParentheseskk(String s) {
        if (s == null || s.length() <= 1) return 0;
        int n = s.length();
        int nParentCnt = 0;
        int i;
        char cElement;
        int nMaxLen = 0;
        int nTmpValidLen = 0;
        int nLeftCnt = 0;
        int nTmpLenByRight = 0;
        
        for (i=0; i<n; i++) {
        	cElement = s.charAt(i);
        	
        	if (cElement == '(') {
        		if (nParentCnt < 0) {
        			nMaxLen = Math.max(nTmpValidLen, nMaxLen);
        			
        			nTmpValidLen = 0;
        			nParentCnt = 0;
        			nLeftCnt = 0;
        		}
        		
        		nLeftCnt++;
        		nParentCnt++;
        	} else {
        		nParentCnt--;
        		
                if (nParentCnt == 0) {
                	nTmpValidLen = nTmpValidLen + nLeftCnt*2;
                	nLeftCnt = 0;
                	nTmpLenByRight = 0;
                }
                
                if (nParentCnt > 0) nTmpLenByRight = nTmpLenByRight + 2;
        	}
        }

        nMaxLen = Math.max(nTmpValidLen, nMaxLen);
        
//        if (Math.abs(nParentCnt) < nLeftCnt) {
//        	nMaxLen = Math.max((nLeftCnt - Math.abs(nParentCnt))*2, nMaxLen);
//        }
        
        nMaxLen = Math.max(nTmpLenByRight, nMaxLen);
        
        return nMaxLen;
	}
	
	//This is wrong, because it is different from what is required in the question. 
	//(the require only asks for a substring)
	public int longestValidParenthesesA(String s) {
        if (s == null || s.length() <= 1) return 0;
        int n = s.length();
        int nParentCnt = 0;
        int nInValidCnt = 0;
        int i;
        char cElement;
        
        for (i=0; i<n; i++) {
        	cElement = s.charAt(i);
        	
        	if (cElement == '(') {
        		if (nParentCnt < 0) {
        			nInValidCnt = nInValidCnt + Math.abs(nParentCnt);
        			nParentCnt = 0;
        		}
        		
        		nParentCnt++;
        	} else {
        		nParentCnt--;
        	}
        }
        
        nInValidCnt = nInValidCnt + Math.abs(nParentCnt);
        
        return n - nInValidCnt;
    }
	
}
