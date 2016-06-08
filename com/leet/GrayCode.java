package com.leet;

import java.util.ArrayList;
import java.util.List;

//The gray code is a binary numeral system where two successive values differ in only one bit.
//
//Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
//A gray code sequence must begin with 0.
//
//For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
//
//00 - 0
//01 - 1
//11 - 3
//10 - 2
//Note:
//For a given n, a gray code sequence is not uniquely defined.
//
//For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
//
//For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.

//Amazon
public class GrayCode {

	public GrayCode() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 1;
		List<Integer> lstGrayCode = grayCode(n);
		
		for(Integer nGrayCode:lstGrayCode) System.out.println(nGrayCode);
	}
	

	//ACC
    public List<Integer> grayCode(int n) {
        List<Integer> lstGray = new ArrayList<Integer>();
        if (n == 0) {
            lstGray.add(0);
            return lstGray;
        }
        
        int size = 0;
        int append = 0;
        lstGray.add(0); lstGray.add(1);
        
        for (int i=2; i<=n; i++) {
            size = lstGray.size();
            append = 1 << (i-1);
            for (int j = size-1; j>=0; j--) {
                lstGray.add(lstGray.get(j) + append);
            }
        }
        
        return lstGray;
    }
	
	
	//ACC
	//https://en.wikipedia.org/wiki/Gray_code
	//for n, first half will be the same as n-1, the second half will be the reverse order of n-1 plus a bit 1 at the beginning
    public List<Integer> grayCodeA(int n) {
        List<Integer> lstGrayCode = new ArrayList<Integer>();
        
        if (n == 0) {
        	lstGrayCode.add(0);
        	return lstGrayCode;
        }
        
        if (n == 1) {
        	lstGrayCode.add(0);
        	lstGrayCode.add(1);
        	return lstGrayCode;
        }
        
        lstGrayCode = grayCode(n-1);
        
        int k = lstGrayCode.size();
        
        for (int i=k-1; i>=0; i--) {
        	lstGrayCode.add(lstGrayCode.get(i) + (int)Math.pow(2, n-1));
        }
        
        return lstGrayCode;
    }
	
}
