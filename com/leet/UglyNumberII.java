package com.leet;

import java.util.ArrayList;
import java.util.List;

//Write a program to find the n-th ugly number.
//
//Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
//For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
//
//Note that 1 is typically treated as an ugly number.
//
//Hint:
//
//The naive approach is to call isUgly for every number until you reach the nth one. 
//Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
//An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
//The key is how to maintain the order of the ugly numbers. 
//Try a similar approach of merging from three sorted lists: L1, L2, and L3.
//Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).

public class UglyNumberII {

	public UglyNumberII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int n = 10;
		
		System.out.println(n + "th Ugly number is: " + nthUglyNumber(n));
	}
	
    public int nthUglyNumber(int n) {
    	if (n == 1) return 1;
    	
    	List<Integer> lstNum2 = new ArrayList<Integer>();
    	List<Integer> lstNum3 = new ArrayList<Integer>();
    	List<Integer> lstNum5 = new ArrayList<Integer>();
    	int i = 1;	
    	int nCurNum = 1;
    	
    	while (i<n) {
    		lstNum2.add(nCurNum*2);
    		lstNum3.add(nCurNum*3);
    		lstNum5.add(nCurNum*5);
    		
    		nCurNum = Math.min(lstNum2.get(0), lstNum3.get(0));
    		nCurNum = Math.min(nCurNum, lstNum5.get(0));
    		
    		i++;
    		
    		if (lstNum2.get(0) == nCurNum) lstNum2.remove(0);
    		if (lstNum3.get(0) == nCurNum) lstNum3.remove(0);
    		if (lstNum5.get(0) == nCurNum) lstNum5.remove(0);    		
    	}
    	
    	return nCurNum;
    }	
}
