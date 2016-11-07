package com.leet;

//Given two numbers represented as strings, return multiplication of the numbers as a string.
//
//Note: The numbers can be arbitrarily large and are non-negative.

//Facebook
public class MultiplyStrings {

	public MultiplyStrings() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String num1 = "12";
		String num2 = "1456";
		
		System.out.println("Result = " + multiply(num1, num2));
	}
	
	//Strategy:  Reverse the string, easy to process
	//Each time process one digit from both sides
	//If two numbers have A and B digits, their result from multiply could not exceed A+B digits
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.isEmpty() || num2 == null || num2.isEmpty()) return "";
        if (num1.compareTo("0") == 0 || num2.compareTo("0") == 0) return "0";

        int nLen1 = num1.length();
        int nLen2 = num2.length();
        int i,j;
        String sNum1 = new StringBuilder(num1).reverse().toString();
        String sNum2 = new StringBuilder(num2).reverse().toString();
        
        int[] digits = new int[nLen1 + nLen2];
        int nCarry = 0;
        int nSingleDit = 0;
        
        for (i=0; i<nLen1; i++) {
        	int digitA = sNum1.charAt(i) - '0';
        	for (j=0; j<nLen2; j++) {
        		digits[i+j] += digitA * (sNum2.charAt(j) - '0');    //Key step
        	}
        }
        
        StringBuilder sResult = new StringBuilder();
        
        for (i=0; i<nLen1+nLen2; i++) {
        	nSingleDit = (digits[i] + nCarry) % 10;
        	nCarry = (digits[i] + nCarry)/10;

        	sResult.insert(0, nSingleDit);
        }
        
        if (nCarry != 0) sResult.insert(0, nCarry);
        
        while (sResult.length() > 0 && sResult.charAt(0) == '0') sResult.deleteCharAt(0);   //Defined nLen1+nLen2 for digits, so might have "0" elements in digits (at the end), should be removed
        
        return sResult.toString();
    }







	
/* wThe following solution is accepted, but not very efficient	
    public String multiply(String num1, String num2) {
        String sResult = "";
        String sTmpResult = "";
        if (num1 == null || num1.isEmpty() || num2 == null || num2.isEmpty()) return sResult;
        int nLen = num2.length();
        int i;
        String sZero = "";
        
        if (num1.compareTo("0") == 0 || num2.compareTo("0") == 0) return "0";
        
        for (i=1; i<=nLen; i++) {
        	int nDigit = num2.charAt(nLen-i)-'0';
        	        	
        	sTmpResult = multiplyByOneDigit(num1, nDigit) + sZero;
        	
        	sResult = addStrings(sResult, sTmpResult);
        	
        	sZero = sZero + "0";
        }
         
        return sResult;
    }
	
    
    private String multiplyByOneDigit(String sNum, int nDigit) {
//    	String sResult = "";
    	StringBuilder sResult = new StringBuilder();
    	
    	int n = sNum.length();
    	int nRemained = 0;
    	if (nDigit == 0) return "0";
    	
    	for (int i=1; i<=n; i++) {
    		int nDigitTmp = sNum.charAt(n-i) - '0';
    		int nRet = nDigitTmp*nDigit + nRemained;
    		
    		nRemained = nRet/10;
    		//sResult = (nRet % 10) + sResult;
    		sResult.insert(0, (nRet % 10));
    	}
    	
    	if (nRemained != 0) {
    		//sResult = nRemained + "" + sResult;
    		sResult.insert(0, nRemained);
    	}
    	
    	return sResult.toString();
    }
    
    
    private String addStrings(String num1, String num2) {
    	//String sResult = "";
    	StringBuilder sResult = new StringBuilder();
    	int n1 = num1.length();
    	int n2 = num2.length();
    	int nMin = Math.min(n1, n2);
    	int nSum;
    	int nRemained = 0;
    	int i;
    	
    	for (i=1; i<=nMin; i++) {
    		int digit1 = num1.charAt(n1-i)-'0';
    		int digit2 = num2.charAt(n2-i)-'0';
    		nSum = digit1 + digit2 + nRemained;
    		nRemained = nSum/10;
    		//sResult = (nSum % 10) + sResult;
    		sResult.insert(0, (nSum % 10));
    	}
    	
    	if (n1 > n2) {
    		for (i=nMin+1; i<=n1; i++) {
    			nSum = (num1.charAt(n1-i)-'0') + nRemained;
    			nRemained = nSum/10;
    			//sResult = (nSum % 10) + sResult;
    			sResult.insert(0, (nSum % 10));
    		}
    	} else if (n1 < n2) {
    		for (i=nMin+1; i<=n2; i++) {
    			nSum = (num2.charAt(n2-i)-'0') + nRemained;
    			nRemained = nSum/10;
    			//sResult = (nSum % 10) + sResult;
    			sResult.insert(0, (nSum % 10));
    		}    		
    	}
    	
    	if (nRemained != 0) {
    		//sResult = "1" + sResult;
    		sResult.insert(0, nRemained);
    	}
    	
    	return sResult.toString();
    }
*/    
    
}
