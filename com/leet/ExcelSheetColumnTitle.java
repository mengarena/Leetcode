package com.leet;

//Given a positive integer, return its corresponding column title as appear in an Excel sheet.
//
//For example:
//
//    1 -> A
//    2 -> B
//    3 -> C
//    ...
//    26 -> Z
//    27 -> AA
//    28 -> AB 

//Facebook, Microsoft, Zenefits
//Easy
public class ExcelSheetColumnTitle {

	public ExcelSheetColumnTitle() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int nTitle = 2;
		
		System.out.println("Title: " + convertToTitle(nTitle));
	}
	
	//Pay attention:  26-Z, 52-AZ,  53-BA
    public String convertToTitle(int n) {
        StringBuffer sRet = new StringBuffer();
        int nRemain = n % 26;

        while (n > 0) {
            sRet.insert(0, (char) ('A' + ((nRemain - 1 + 26) % 26)) + "");
            
            n = (n-1) / 26;
            
            nRemain = n % 26;
        }
        
        return sRet.toString();
    }	
}
