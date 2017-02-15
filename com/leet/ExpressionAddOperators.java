package com.leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

//Given a string that contains only digits 0-9 and a target value, 
//return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
//
//Examples: 
//"123", 6 -> ["1+2+3", "1*2*3"] 
//"232", 8 -> ["2*3+2", "2+3*2"]
//"105", 5 -> ["1*0+5","10-5"]
//"00", 0 -> ["0+0", "0-0", "0*0"]
//"3456237490", 9191 -> []

//Google, Facebook
//Hard
public class ExpressionAddOperators {

	public ExpressionAddOperators() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		String num = "3456237490";
		String num = "10";

		int target = 1;
		List<String> lstExpression = addOperators(num, target);
		for (String sExpression:lstExpression) System.out.print(sExpression + ", ");
	}
	
	
	//ACC:  93%
    public List<String> addOperatorsK(String num, int target) {
        List<String> lstExp = new ArrayList<String>();
        if (num == null || num.length() == 0) return lstExp;
        char[] digits = num.toCharArray();
        int len = num.length();
        char[] path = new char[len*2-1];  //The digits in num could at most form a path (i.e. expression) with length len*2-1
        
        long n = 0;
        
        for (int i=0; i<len; i++) {
            n = n*10  + (digits[i] - '0');
            path[i] = digits[i];
            dfs(lstExp, digits, target, path, i+1, 0, n, i+1);
            if (n == 0) break;  //If the digit is 0, only if it a one-digit number, it is valid, otherwise, not valid
        }
        
        return lstExp;
    }
    
    private void dfs(List<String> lstExp, char[] digits, int target, char[] path, int pathLen, long left, long cur, int pos) {
        if (left + cur == target && pos == digits.length) {
            lstExp.add(new String(path, 0, pathLen));
        }
        
        long n = 0;
        int opPos = pathLen+1;
        for (int i=pos; i<digits.length; i++) {
            n = n*10 + digits[i] - '0';
            path[opPos++] = digits[i];
            
            path[pathLen] = '+';
            dfs(lstExp, digits, target, path, opPos, left + cur, n, i+1);
            
            path[pathLen] = '-';
            dfs(lstExp, digits, target, path, opPos, left + cur, -n, i+1);
            
            path[pathLen] = '*';
            dfs(lstExp, digits, target, path, opPos, left, cur*n, i+1);
            
            if (digits[pos] == '0') break;  //If the digit is 0, only if it a one-digit number, it is valid, otherwise, not valid
        }
    }
	
	
	
	//ACC:  76%  (almost the same as addOperatorsB, but here use StringBuilder, which make it faster
    public List<String> addOperators(String num, int target) {
        List<String> lstExp = new ArrayList<String>();
        if (num == null || num.length() == 0) return lstExp;
        
        StringBuilder sb = new StringBuilder();
        
        dfsHelper(num, target, lstExp, sb, 0l, 0l);
        
        return lstExp;
    }
    
    private void dfsHelper(String num, int target, List<String> lstExp, StringBuilder sbExp, long prev, long sum) {
        if (num.length() == 0 && sum == target) {
            lstExp.add(sbExp.toString());
            return;
        }
        
        for (int i=1; i<=num.length(); i++) {
            String sInitNum = num.substring(0, i);
            
            if (sInitNum.length() > 1 && sInitNum.charAt(0) == '0') break;
            
            long lInitNum = Long.valueOf(sInitNum).longValue();
            int len = sbExp.length();
            
            if (sbExp.length() != 0) {
                dfsHelper(num.substring(i), target, lstExp, sbExp.append("+").append(sInitNum), lInitNum, sum+lInitNum);
                sbExp.setLength(len);
                dfsHelper(num.substring(i), target, lstExp, sbExp.append("-").append(sInitNum), -lInitNum, sum-lInitNum);
                sbExp.setLength(len);
                dfsHelper(num.substring(i), target, lstExp, sbExp.append("*").append(sInitNum), prev*lInitNum, sum-prev+prev*lInitNum);
                sbExp.setLength(len);
            } else {
                dfsHelper(num.substring(i), target, lstExp, sbExp.append(sInitNum), lInitNum, lInitNum);
                sbExp.setLength(len);
            }
        }
    }
	
	
	
	//ACC: 23%
    public List<String> addOperatorsB(String num, int target) {
        List<String> lstExp = new ArrayList<String>();
        if (num == null || num.length() == 0) return lstExp;
        
        dfsHelper(num, target, lstExp, "", 0l, 0l);
        
        return lstExp;
    }
    
    private void dfsHelper(String num, int target, List<String> lstExp, String sExp, long prev, long sum) {
        if (num.length() == 0 && sum == target) {
            lstExp.add(sExp);
            return;
        }
        
        for (int i=1; i<=num.length(); i++) {
            String sInitNum = num.substring(0, i);
            
            if (sInitNum.length() > 1 && sInitNum.charAt(0) == '0') continue;
            
            long lInitNum = Long.valueOf(sInitNum).longValue();
            
            if (sExp.length() != 0) {
                dfsHelper(num.substring(i), target, lstExp, sExp + "+" + sInitNum, lInitNum, sum+lInitNum);
                dfsHelper(num.substring(i), target, lstExp, sExp + "-" + sInitNum, -lInitNum, sum-lInitNum);
                dfsHelper(num.substring(i), target, lstExp, sExp + "*" + sInitNum, prev*lInitNum, sum-prev+prev*lInitNum);
            } else {
                dfsHelper(num.substring(i), target, lstExp, sInitNum, lInitNum, lInitNum);
            }
        }
    }
    	
	
	

	//ACC
    public List<String> addOperatorsA(String num, int target) {
        List<String> lstExpression = new ArrayList<String>();
        if (num == null || num.length() == 0) return lstExpression;

        dfsHelper(num, "", target, 0, 0, lstExpression);
        
        return lstExpression;
    }
    
    
    private void dfsHelper(String sNum, String sExp, int target, long nPrevNum, long nSum, List<String> lstExpression) {
    	if (nSum == target && sNum.length() == 0) {
    		lstExpression.add(sExp);
    		return;
    	}
    	
    	for (int i=1; i<=sNum.length(); i++) {
    		String sInit = sNum.substring(0, i);
    		if (sInit.charAt(0) == '0' && sInit.length() > 1) return;

    		long nCurNum = Long.parseLong(sInit);
    		String sNumNext = sNum.substring(i);
    		
    		if (sExp.length() != 0) {
    			dfsHelper(sNumNext, sExp + "+" + nCurNum, target, nCurNum,          nSum+nCurNum,                     lstExpression);
    			dfsHelper(sNumNext, sExp + "-" + nCurNum, target, -nCurNum,         nSum-nCurNum,                     lstExpression);
    			dfsHelper(sNumNext, sExp + "*" + nCurNum, target, nPrevNum*nCurNum, nSum-nPrevNum + nPrevNum*nCurNum, lstExpression);
    		} else {
    			dfsHelper(sNumNext, sInit, target, nCurNum, nCurNum, lstExpression);
    		}
    	}
    }
    
    
/* The following solution is ACCEPTED, but not efficient enough	
    public List<String> addOperators(String num, int target) {
        List<String> lstExpression = new ArrayList<String>();
        if (num == null || num.length() == 0) return lstExpression;
        List<List<Integer>> lstlstNums = new ArrayList<List<Integer>>();
        
        lstlstNums = splitNums(num);
 
        for (List<Integer> lstNums:lstlstNums) {
        	if (lstNums.size() == 1) {
        		if (lstNums.get(0) == target) lstExpression.add(target + "");
        	} else {
        		findExpression(lstNums, target, lstExpression);
        	}
        }
        
        return lstExpression;
    }
    
    
    private void findExpression(List<Integer> lstNums, int nTarget, List<String> lstExpression) {
    	int n = lstNums.size();
    	List<List<Integer>> lstlstOp = findOp(n-1);
    	
    	for (List<Integer> lstOp:lstlstOp) {
    		String sExp = CalExpression(lstNums, lstOp, nTarget);
    		if (sExp.length() > 0) lstExpression.add(sExp);
    	}
    	    	
    }
    
    
    private String CalExpression(List<Integer> lstNums, List<Integer> lstOp, int nTarget) {
    	StringBuilder sExp = new StringBuilder();
    	char[] carrOp = {'*','+','-'};
    	
    	int nExpResult = CalExpr(lstNums, lstOp);
    	if (nExpResult != nTarget) return "";
    	
    	for (int i=0; i<lstOp.size(); i++) {
    		sExp.append(lstNums.get(i));
    		sExp.append(carrOp[lstOp.get(i)]);
    	}
    	
    	sExp.append(lstNums.get(lstNums.size()-1));
    	
    	return sExp.toString();
    }
    
    
    private int CalExpr(List<Integer> lstNums, List<Integer> lstOp) {
    	int nResult = 0;
    	Queue<Integer> stkNums = new LinkedList<Integer>();
    	Queue<Integer> stkOps = new LinkedList<Integer>();
    	int nOp;
    	int nPrev = lstNums.get(0);
    	int nCur;
    	
    	for (int i=0; i<lstOp.size(); i++) {
    		nOp = lstOp.get(i);
    		nCur = lstNums.get(i+1);
    		
    		if (nOp == 0) {
    			nPrev = nCur*nPrev;
    		} else {
    			stkNums.offer(nPrev);
    			stkOps.offer(nOp);
    			nPrev = nCur;
    		}
    	}
    	
    	stkNums.offer(nPrev);
    	
    	nResult = stkNums.poll();
    	while (!stkNums.isEmpty()) {
    		nOp = stkOps.poll();
    		nCur = stkNums.poll();
    		
    		if (nOp == 1) {
    			nResult = nResult + nCur;
    		} else {
    			nResult = nResult - nCur;
    		}
    	}
    	
    	return nResult;
    }
    
    
    private List<List<Integer>> findOp(int n) {
    	List<List<Integer>> lstlstOp = new ArrayList<List<Integer>>();
    	
    	if (n == 1) {
    		for (int i=0; i<=2; i++) {
    			List<Integer> lstOp = new ArrayList<Integer>();
    			lstOp.add(i);
    			lstlstOp.add(lstOp);
    		}
    		
    		return lstlstOp;
    	}
    	
    	List<List<Integer>> lstlstOpTmp = findOp(n-1);
    	for (List<Integer> lstOpTmp:lstlstOpTmp) {
    		for (int i=0; i<=2; i++) {
    			List<Integer> lstOpTmpTmp = new ArrayList<Integer>(lstOpTmp);
    			lstOpTmpTmp.add(0, i);
    			lstlstOp.add(lstOpTmpTmp);
    		}
    	}
    	
    	return lstlstOp;
    }
    
    private List<List<Integer>> splitNums(String num) {
    	List<List<Integer>> lstlstNums = new ArrayList<List<Integer>>();
    	if (num == null || num.length() == 0) return lstlstNums;
    	int n = num.length();
    	if (n == 1) {
    		List<Integer> lstNums = new ArrayList<Integer>();
    		lstNums.add(Integer.valueOf(num));
    		lstlstNums.add(lstNums);
    		return lstlstNums;
    	}
    	
    	String sMax = Integer.MAX_VALUE+"";
    	int nZeroCnt = 0;
    	
    	for (int i=1; i<=n; i++) {
    		if (num.charAt(i-1) == '0') {
    			nZeroCnt++;
    		} else {
    			break;
    		}
    	}
    	
    	String numTmp = num.substring(nZeroCnt);
    	n = numTmp.length();
    	   	
    	for (int i=1; i<=n;) {   //Len of first number
    		String sInitNum = numTmp.substring(0, i);
    		if (sInitNum.length() == sMax.length() && sInitNum.compareTo(sMax) == 1) break;
    		
    		List<List<Integer>> lstlstNumsTmp = splitNums(numTmp.substring(i));
    		if (lstlstNumsTmp.size() > 0) {
	    		for (List<Integer> lstNumsTmp:lstlstNumsTmp) {
	    			
	    			lstNumsTmp.add(0, Integer.valueOf(sInitNum));
	
	    			if (nZeroCnt > 0) {
	    				for (int j=1; j<=nZeroCnt; j++) lstNumsTmp.add(0,0);
	    			} 
	    			
	    			lstlstNums.add(lstNumsTmp);
	    		}
    		} else {
    			List<Integer> lstNumsTmp = new ArrayList<Integer>();
    			lstNumsTmp.add(Integer.valueOf(sInitNum));
    			
    			if (nZeroCnt > 0) {
    				for (int j=1; j<=nZeroCnt; j++) lstNumsTmp.add(0,0);
    			} 
    			
    			lstlstNums.add(lstNumsTmp);
    		}
    		    		
    		i++;
    	}
    	
    	if (n == 0) {
    		List<Integer> lstNums = new ArrayList<Integer>();
    		for (int i=1; i<=nZeroCnt; i++) lstNums.add(0,0);
    		lstlstNums.add(lstNums);
    	}
    	
    	return lstlstNums;
    }
*/
	
}
