package com.leet;

//Given two binary strings, return their sum (also a binary string).
//
//For example,
//a = "11"
//b = "1"
//Return "100".


//Facebook
//Easy
public class AddBinary {

	public AddBinary() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String a = "1100000";
		String b = "11";
		
		System.out.println(a + " + " + b + " = " + addBinary(a, b));
	}

    public String addBinary(String a, String b) {
        if ((a == null || a.length() == 0) && (b == null || b.length() ==0)) return "";
        if ((a == null || a.length() == 0) && b != null) return b;
        if (a != null && (b == null || b.length() == 0)) return a;
        
        int nLenA = a.length(), nLenB = b.length();
        int nDigitA, nDigitB;
        int nOverFlow = 0;
        int nSum = 0;
        String sSum = "";   //Better to use StringBuffer, will be more efficient
        
        //Process each digit from right to left
        while (nLenA > 0 || nLenB > 0) {
        	if (nLenA > 0) {
        		nDigitA = a.charAt(nLenA-1) - '0';
        	} else {
        		nDigitA = 0;
        	}
        	
        	if (nLenB > 0) {
        		nDigitB = b.charAt(nLenB-1) - '0';
        	} else {
        		nDigitB = 0;
        	}
        	
        	nSum = nDigitA + nDigitB + nOverFlow;
        	
        	sSum = (nSum % 2) + sSum;
        	nOverFlow = nSum / 2;
        	nLenA = nLenA - 1;
        	nLenB = nLenB - 1;
        }
        
        if (nOverFlow > 0) sSum = nOverFlow + sSum; 
        
        return sSum;
    }
	
}
