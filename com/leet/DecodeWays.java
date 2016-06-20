package com.leet;

import java.util.ArrayList;
import java.util.List;

//message containing letters from A-Z is being encoded to numbers using the following mapping:
//
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//Given an encoded message containing digits, determine the total number of ways to decode it.
//
//For example,
//Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
//
//The number of ways decoding "12" is 2.

//Facebook, Microsoft
public class DecodeWays {

	public DecodeWays() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String s = "4757562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948";
		
		System.out.println("#Way for decoding: " + numDecodings(s));
	}
	
	
	//ACC: 30%
    public int numDecodings(String s) {
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
        int n = s.length();
        int dp[] = new int[n+1];   //#Decodings by position i in string s
        
        dp[0] = 1;   //One way to decoding ""
        dp[1] = 1;   //One way to decoding the first character
        
        //For example, 1120; when comes to last 0, the "2" has to be consumed by the last "0", so the total valid #decoding will be the same as "11"  (two digit number case)
        //For 1129,   29 is not valid, so when comes to last 9, the last 9 must be independent, 
        //it does not affect the whole number by 112, so total valid #decoding will be the same as "112"  (one digit number case)
        for (int i=2; i<=n; i++) {
            int twoDigitNum = Integer.valueOf(s.substring(i-2, i));
            int oneDigitNum = Integer.valueOf(s.substring(i-1, i));
            
            if (twoDigitNum >= 10 && twoDigitNum <= 26) {    //Check the two digit number
                dp[i] = dp[i-2];
            }
            
            if (oneDigitNum >= 1 && oneDigitNum <= 9) {   //Check the one digit number
                dp[i] = dp[i] + dp[i-1];
            } 
        }
        
        return dp[n];
    }	
	
	
	//Strategy:
	//1) Process the element one by one
	//2) Each time check whether the current one char number and the two-char number (current+previous one) is valid
	//3) If continuously valid, the number of ways will be a Fibonacci sequence (1,1,2,3,5,8....)
	//4) say three continuous chars are: ABX,   X is the current one
	//                           4.1) If X is valid, but BX is not valid, the #ways of this segment does not change; 
	//                                 but the string could be segmented at B; X will be the beginning of the next segment
	//                           4.2) If BX is valid, but X is not valid, the #ways of this segment will be the #ways at A
	//                                 the string is segmented at X, next segment starts at the char after X
	//5) Total #ways will be the product of the #ways in all the segments  (
    public int numDecodingsA(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        int nTotalWays = 0;
        int i;
        int n1Num, n2Num;
        int nContinueValidLen = 0;
        List<Integer> lstResult = new ArrayList<>();
        List<Integer> lstFib = new ArrayList<Integer>();
        
        lstFib.add(1);   //0 digit
        lstFib.add(1);   //1 digit
        
        i = 0;    	
    	nContinueValidLen = 0;
    	
        while (i < n) {        	
        	n1Num = Integer.valueOf(s.substring(i, i+1)).intValue();
        	if (nContinueValidLen >= 1) {
        		n2Num = Integer.valueOf(s.substring(i-1, i+1)).intValue();
        	} else {
        		n2Num = -1;
        		
        	}
        	
        	if (n2Num == -1) {
        		if (n1Num == 0) return 0;
        		
        		nTotalWays = 1;
        		nContinueValidLen = 1;
        	} else {
	        	if (n1Num > 0 && (n2Num >= 10 && n2Num <= 26)) {  //Both valid
	        		nTotalWays = lstFib.get(nContinueValidLen) + lstFib.get(nContinueValidLen-1);
	        		if (lstFib.size() <= nContinueValidLen+1) lstFib.add(nTotalWays);
	        		nContinueValidLen++;
	        	} else if (n1Num > 0 && (n2Num < 10 || n2Num > 26)) {      //4.1
	        		//nTotalWays does not change
	        		lstResult.add(nTotalWays);
	        		nTotalWays = 1;
	        		nContinueValidLen = 1;
	        	} else if (n1Num == 0 && (n2Num >= 10 && n2Num <= 26)) {   //4.2
	        		nTotalWays = lstFib.get(nContinueValidLen-1);
	        		lstResult.add(nTotalWays);
	        		nTotalWays = 1;
	        		nContinueValidLen = 0;	        		
	        	} else {
	        		return 0;
	        	}
        	}
        	
        	i++;
        }
        
        //Last segment might not been added in lstResult, so pay attention to nTotalWays
        if (lstResult.size() > 0) {
        	if (nTotalWays == 0) nTotalWays = 1;
        	for (Integer nResult:lstResult) nTotalWays = nTotalWays*nResult;
        	return nTotalWays;
        	
        } else if (nContinueValidLen > 0) {
        	return nTotalWays;
        } else {	
        	return 0;
        }
        
    }
	
}
