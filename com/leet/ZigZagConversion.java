package com.leet;

import java.util.ArrayList;
import java.util.List;

//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
//(you may want to display this pattern in a fixed font for better legibility)
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//And then read line by line: "PAHNAPLSIIGYIR"
//Write the code that will take a string and make this conversion given a number of rows:
//
//string convert(string text, int nRows);
//convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
		
public class ZigZagConversion {

	public ZigZagConversion() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String sOrgStr = "MENGRUFENG";
		int nNumRows = 3;
		
		System.out.println("ZigZag Converted String: " + convert(sOrgStr, nNumRows));
	}
	
	//ACC
    public String convert(String s, int numRows) {
        if (numRows <= 0) return "";
        if (s == null || s.length() <= 1 || numRows == 1 || s.length() <= numRows) return s;
        int blockLen = numRows*2-2;
        int n = s.length();
        int nRemained = n % blockLen;
        int nBlockCnt = n / blockLen;
        int i,j;
        StringBuilder sb = new StringBuilder();
        
        if (nRemained != 0) nBlockCnt++;
        
        for (i=0; i<numRows; i++) {
            for (j=1; j<=nBlockCnt; j++) {
                if (i == 0) {
                    sb.append(s.charAt((j-1)*blockLen));
                } else if (i < numRows-1) {
                    if ((j-1)*blockLen + i < n) {
                        sb.append(s.charAt((j-1)*blockLen + i));
                    }
                    
                    if ((j-1)*blockLen + blockLen-i < n) {
                        sb.append(s.charAt((j-1)*blockLen + blockLen-i));
                    }
                } else {
                    if ((j-1)*blockLen + numRows-1 < n) {
                        sb.append(s.charAt((j-1)*blockLen + numRows-1));
                    }
                }
            }
        }
        
        return sb.toString();
    }
	
    
	
    //ACC
    public String convertA(String s, int numRows) {
        String sRetStr = "";
        
        if (numRows == 1) return s;
        
        int nDuration = numRows*2 - 2;   //One period
        char[] chArrOrg = s.toCharArray();
        
        int nDurationCnt = s.length() / nDuration;
        int nRemainder = s.length() % nDuration;
        
        for (int i=1; i<=numRows; i++) {
        	String sRow = "";
        	
        	//Loop through all the durations for each row
        	for (int j=0; j<nDurationCnt; j++) {
        		
        		sRow = sRow + chArrOrg[nDuration*j + i-1];
        		
	        	if (i != 1 && i != numRows) {
	        		 sRow = sRow + chArrOrg[nDuration*j + numRows + numRows - i- 1];
	        	}
        	}
        	
        	//Process the remaining chars
        	if (nRemainder >= 1  && nRemainder <= numRows) {
        		if (i<=nRemainder) {
        			sRow = sRow + chArrOrg[nDuration*nDurationCnt + i - 1];
        		}
        	} else if (nRemainder >= numRows) {
    			sRow = sRow + chArrOrg[nDuration*nDurationCnt + i - 1];
    			
    	        int nRemainderRemainder = nRemainder - numRows;

    	        if (numRows - nRemainderRemainder <= i && i != numRows) {
    	        	sRow = sRow + chArrOrg[nDuration*nDurationCnt + numRows + numRows - i- 1];
    	        }
    			
        	}
        	        	
        	sRetStr = sRetStr + sRow;
        }
                
        return sRetStr;
    }	
	
}
