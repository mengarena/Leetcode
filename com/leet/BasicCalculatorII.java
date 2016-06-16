package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BasicCalculatorII {

	public BasicCalculatorII() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:  23%
    public int calculate(String s) {
        if (s == null || s.isEmpty()) return 0;
        Stack<Integer> stk = new Stack<Integer>();
        List<String> lstElem = new ArrayList<String>();
        int n = s.length();
        int i = 0, j = 0;
        boolean bOp = false;
        boolean bNum = false;
        char cElm;
        int nOp = 0;  //0:  no,   1: 
        int nNum = 0;
        int nNumTmp;
        int ret = 0;
        
        while (j < n) {
            cElm = s.charAt(j);
            if (cElm == '+' || cElm == '-' || cElm == '*' || cElm == '/') {
                if (bNum == true) {
                    lstElem.add(s.substring(i, j));
                    bNum = false;
                }
                
                i = j;
                bOp = true;
            } else if (cElm == ' ' || cElm == ' ') {
                if (bNum == true) {
                    lstElem.add(s.substring(i, j));
                    bNum = false;   
                    i = j;
                }
                
                if (bOp == true) {
                    lstElem.add(s.substring(i, j));
                    bOp = false;
                    i = j;
                }
                
            } else {
                if (bOp == true) {
                    lstElem.add(s.substring(i, j));
                    bOp = false;
                }

                if (bNum == false) {
                    i = j;
                }
                
                bNum = true;
            }
            
            j++;
        }
        
        if (bOp == true || bNum == true) {
            lstElem.add(s.substring(i, j));
        }
        
        for (String ss: lstElem) {
            if (ss.equals("+")) {
                nOp = 1;
            } else if (ss.equals("-")) {
                nOp = 2;
            } else if (ss.equals("*")) {
                nOp = 3;
            } else if (ss.equals("/")) {
                nOp = 4;
            } else {
                nNum = Integer.valueOf(ss).intValue();
                if (nOp == 0 || nOp == 1) {
                    stk.push(nNum);
                } else if (nOp == 2) {
                    stk.push(-1*nNum);
                } else if (nOp == 3) {
                    nNumTmp = stk.pop();
                    nNumTmp = nNumTmp*nNum;
                    stk.push(nNumTmp);
                } else {
                    nNumTmp = stk.pop();
                    nNumTmp = nNumTmp/nNum;
                    stk.push(nNumTmp);
                }
                nOp = 0;
            }
        }
        
        while (!stk.isEmpty()) {
            ret = ret + stk.pop();
        }
        
        return ret;

    }

}
