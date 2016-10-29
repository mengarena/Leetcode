package com.leet;

//Related to question Excel Sheet Column Title
//
//Given a column title as appear in an Excel sheet, return its corresponding column number.
//
//For example:
//
//    A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 

//Uber, Microsoft
//Easy
public class ExcelSheetColumnNumber {

	public ExcelSheetColumnNumber() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = null;
		
		System.out.println("Column Number: " + titleToNumber(s));
	}

    public int titleToNumber(String s) {
        int nColNum = 0;
        int nNum;
        
        if (s == null) return 0;
        
        for (int i=0; i<s.length(); i++) {
        	nNum = s.charAt(i) - 'A' + 1;
        	nColNum =  nColNum*26 + nNum;
        }
                
        return nColNum;
    }
	
}
