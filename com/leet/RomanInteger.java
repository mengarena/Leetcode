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

//Uber, Bloomberg
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
        char cPrev;
        int nCurIdx;
        int nPrevIdx;
        int nIdx = 1;
        
        if (nLen == 0) return 0;
        
        cPrev = s.charAt(0);
        nPrevIdx = sBase.indexOf(cPrev);
        
        while (nIdx < nLen) {
        	cCur = s.charAt(nIdx);
        	nCurIdx = sBase.indexOf(cCur);
        	if (nPrevIdx < nCurIdx) {
        		nNumVal = nNumVal - narrBase[nPrevIdx];
        	} else {
        		nNumVal = nNumVal + narrBase[nPrevIdx];
        	}
        	
        	cPrev = cCur;
        	nPrevIdx = nCurIdx;
        	nIdx = nIdx + 1;
        }
        
        nNumVal = nNumVal + narrBase[nPrevIdx];
        
    	return nNumVal;
    }
	
}
