package com.leet;

import java.util.HashMap;
import java.util.Map;

//Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//
//If the fractional part is repeating, enclose the repeating part in parentheses.
//
//For example,
//
//Given numerator = 1, denominator = 2, return "0.5".
//Given numerator = 2, denominator = 1, return "2".
//Given numerator = 2, denominator = 3, return "0.(6)".

//Google
public class FractionRecurringDecimal {

	public FractionRecurringDecimal() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int numerator = -2147483648;
		//int numerator = -50;	
		//int numerator = 2147483647;
		
		int numerator = -1;
		
		//int denominator = 1999;  // 1/997: recurring: 0010030090270812437311935807422266800401203610832497492477432296890672016048144433299899699097291875626880641925777331995987963891675025075225677031093279839518555667
		//int denominator = 8;
		//int denominator = 370000;
		//int denominator = 997;
		int denominator = -2147483648;
		
		long aa = System.currentTimeMillis();
		
		String sRet = fractionToDecimal(numerator, denominator);
		
		long tm = System.currentTimeMillis() - aa;
		
		System.out.println(sRet);
		System.out.println();
		System.out.println("Time: " + tm + " ms");
		
	}

	

	
	//AC
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "";
        if (numerator == 0) return "0";
        StringBuffer sbRet = new StringBuffer();
        long nNumerator =  Math.abs((long)numerator);    //HERE:  inside Math.abs, "long" is important, otherwise, if the value is -2147483648, it will be wrong
        long nDenominator = Math.abs((long)denominator);
                
        if (numerator < 0 ^ denominator < 0) sbRet.append("-");  //Decide the sign
        
    	sbRet.append(nNumerator / nDenominator);  //The part before "."
        
        long nRemained = nNumerator % nDenominator;
        
        if (nRemained == 0) return sbRet.toString();
        
        sbRet.append("." + getRecurringPart(nRemained, nDenominator));
        
        return sbRet.toString();
    }
	
    //Strategy:  Remember each remained
    //If see a remained again, it starts to repeating
    private String getRecurringPart(long nRemained, long nDenominator) {
    	StringBuffer sbRet = new StringBuffer();
    	long nRem = nRemained*10;
    	Map<Long, Integer> mapRemained = new HashMap<Long, Integer>();   //Remained, Index
    	
    	int nPos = 0;
    	
    	while (true) {
    		
    		sbRet.append(nRem/nDenominator);
    		mapRemained.put(nRem, nPos);
    		
    		nPos++;
    		
    		nRem = (nRem % nDenominator) * 10;	
    		
    		if (nRem == 0) break;
    		
    		if (mapRemained.containsKey(nRem)) {  //Find the repeat part
    			sbRet.insert(mapRemained.get(nRem), "(");
    			sbRet.append(")");
    			break;
    		}
    	}
    	
    	return sbRet.toString();
    }
   
    
    
    
    
///////////////////////////////////////////////////////
/*
 
	//Works, but exceeded time limit
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) return "";
        if (numerator == 0) return "0";
        StringBuffer sbRet = new StringBuffer();
        
        sbRet.append(numerator / denominator);
        
        int nRemained = numerator % denominator;
        
        if (nRemained == 0) return sbRet.toString();
        
        sbRet.append("." + getRecurringPart(nRemained, denominator));
        
        return sbRet.toString();
    }
	
    private String getRecurringPart(int nRemained, int denominator) {
    	StringBuffer sbRet = new StringBuffer();
    	int nRem = nRemained*10;
    	int nMinLen = String.valueOf(denominator).length();
    	
    	while (true) {
    		sbRet.append(nRem/denominator);
    		
    		int nLen = sbRet.length();
    		
    		for (int i=nLen/2; i>= nMinLen; i--) {
    			if (sbRet.substring(nLen-i).equals(sbRet.substring(nLen-2*i, nLen-i))) {
    				String sRet = sbRet.substring(0, nLen-2*i) + "(" + sbRet.substring(nLen-i) + ")";
    				return sRet;
    			}
    		}
    		
    		nRem = (nRem % denominator) * 10;	
    	}
    	
    }
 
 
 * */    
    
    
}
