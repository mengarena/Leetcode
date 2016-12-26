package com.leet;

//Given an integer, convert it to a roman numeral.
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


public class IntegerRoman {

	public IntegerRoman() {
		// TODO Auto-generated constructor stub
	}

	//Rule for Roman:
	/*
	 * I -- 1;    V -- 5;  X -- 10;  L -- 50;  C -- 100;  D -- 500;  M -- 1000
	 * if ab (a < b), value = b-a
	 */
	public void run() {
		int num = 1900;
		
		System.out.println(num + " is converted to Roman: " + intToRoman(num));
	}
	
	//ACC
	public String intToRoman(int num) {
	    String[] romanArr = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
	    int[] intArr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<romanArr.length; i++) {
		    while (num >= intArr[i]) {
			    num -= intArr[i];
			    sb.append(romanArr[i]);
			}
		}
		
		return sb.toString();	
	}
	
	
	//ACC
    public String intToRomanB(int num) {
        String sLetterTen = "MCXI";
        String sLetterFive = "DLV";
        int[] arrNumsTen = {1000, 100, 10, 1};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int cnt = 0;
        
        for (i=0; i<=3; i++) {
            cnt = num / arrNumsTen[i];
            
            if (cnt == 0) continue;
            
            if (cnt == 4) {
                sb.append(sLetterTen.charAt(i));
                sb.append(sLetterFive.charAt(i-1));
            } else if (cnt == 9) {
                sb.append(sLetterTen.charAt(i));
                sb.append(sLetterTen.charAt(i-1));
            } else {
                if (cnt >= 5) {
                    sb.append(sLetterFive.charAt(i-1));
                    for (int j=0; j<cnt-5; j++) sb.append(sLetterTen.charAt(i));
                } else {
                    for (int j=0; j<cnt; j++) sb.append(sLetterTen.charAt(i));
                }
            }

            num = num - arrNumsTen[i]*cnt;
        }
        
        return sb.toString();
    }
	
    
    //ACC
    public String intToRomanA(int num) {
        String sRoman = "";
        String sBase ="IXCM";   //1, 10, 100, 1000
        String sBaseFive = "VLD";   //5, 50, 500
        int nRemained;
        int nBitPos = 0;
        int nBigThanFive = 0;
        int nBigThanFiveRemained = 0;
        String sTmp = "";
        
        while (num > 0) {
        	nRemained = num % 10;
        	num = num / 10;
        	
        	nBitPos = nBitPos + 1;
        	
        	if (nRemained != 4 && nRemained != 9) {
        		nBigThanFive = nRemained / 5;
        		nBigThanFiveRemained = nRemained % 5;
        		sTmp = "";
        		
        		if (nBigThanFiveRemained > 0) {
        			for (int i=1; i<=nBigThanFiveRemained; i++) sTmp = sTmp + sBase.charAt(nBitPos-1);
        		}
        		
        		sRoman = sTmp + sRoman;
        		
        		if (nBigThanFive == 1) {
        			sRoman = sBaseFive.charAt(nBitPos-1) + sRoman;
        		}
        		
        	} else {
        		if (nRemained == 4) {
        			sTmp = sBase.charAt(nBitPos-1) + "" + sBaseFive.charAt(nBitPos-1);
        		} else {
        			sTmp = sBase.charAt(nBitPos-1) + "" + sBase.charAt(nBitPos);
        		}
        		
        		sRoman = sTmp + sRoman;
        	}
        }
        
        return sRoman;
    }
	
}
