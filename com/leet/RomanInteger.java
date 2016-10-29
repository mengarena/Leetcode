package com.leet;

//Given a roman numeral, convert it to an integer.
//
//Input is guaranteed to be within the range from 1 to 3999.

//VII--7
//IX--9
//XV - 15
//XVII - 17
//XX - 20
//XXV - 25
//XXVI - 26
//LX - 60
//XC - 90
//CI - 101
//CCC - 300
//CD - 400
//CM - 900
//MDCC - 1700
//MCM - 1900

//Uber, Bloomberg, Microsoft, Facebook, Yahoo
//Easy
public class RomanInteger {

	public RomanInteger() {
		// TODO Auto-generated constructor stub
	}

	//Rule for Roman:
	/*
	 * I -- 1;    V -- 5;  X -- 10;  L -- 50;  C -- 100;  D -- 500;  M -- 1000
	 * if ab (a < b), value = b-a
	 */
	public void run() {
		String s = "MDC";
		
		System.out.println("Roman Numeral: " + s + "   = " + romanToInt(s));
	}
	
	
    public int romanToInt(String s) {
        int nNumVal = 0;

        String sBase ="IVXLCDM";
        int[]  narrBase = {1, 5, 10, 50, 100, 500, 1000};
        int nLen = s.length();
        char cCur;
        int nCurIdx;
        int nPrevIdx;
        int nIdx = 1;
        
        if (nLen == 0) return 0;
        
        nPrevIdx = sBase.indexOf(s.charAt(0));
        
        while (nIdx < nLen) {
        	cCur = s.charAt(nIdx);
        	nCurIdx = sBase.indexOf(cCur);
        	if (nPrevIdx < nCurIdx) {
        		nNumVal = nNumVal - narrBase[nPrevIdx];
        	} else {
        		nNumVal = nNumVal + narrBase[nPrevIdx];
        	}
        	
        	nPrevIdx = nCurIdx;
        	nIdx = nIdx + 1;
        }
        
        nNumVal = nNumVal + narrBase[nPrevIdx];
        
    	return nNumVal;
    }
	
    
    
    public int romanToIntA(String s) {
        char[] tens = {'I', 'X', 'C', 'M'};   //1, 10, 100, 1000
        char[] fives = {'V', 'L', 'D'};   //5, 50, 500
        int nTenIdx = -1;
        int nFiveIdx = -1;
       
        int nPrev = 0;
        int ret = 0;
        int i, j;
        
        if (s == null || s.length() == 0) return 0;
        
        for (i=0; i<s.length(); i++) {
            nTenIdx = -1;
            nFiveIdx = -1;
            
            for (j=0; j<tens.length; j++) {
                if (tens[j] == s.charAt(i)) {
                    nTenIdx = j;
                    break;
                }
            }
            
            if (nTenIdx == -1) {
                for (j=0; j<fives.length; j++) {
                    if (fives[j] == s.charAt(i)) {
                        nFiveIdx = j;
                        break;
                    }
                }
            }
            
            if (nTenIdx != -1) {
                if ((int)Math.pow(10, nTenIdx) > nPrev && nPrev > 0) {
                    ret = ret - nPrev + (int) Math.pow(10, nTenIdx) - nPrev;
                } else {
                    ret += (int) Math.pow(10, nTenIdx);
                }
            } else if (nFiveIdx != -1) {
                if ((int)Math.pow(10, nFiveIdx)*5 > nPrev && nPrev > 0) {
                    ret = ret - nPrev + (int) Math.pow(10, nFiveIdx)*5 - nPrev;
                } else {
                    ret += (int) Math.pow(10, nFiveIdx)*5;
                }
            }

            if (nTenIdx != -1) {
                nPrev = (int) Math.pow(10, nTenIdx);
            } else {
                nPrev = 0;
            }
        }
        
        return ret;
    }    
    
}
