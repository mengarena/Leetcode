package com.leet;

//Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
//return the length of last word in the string.
//
//If the last word does not exist, return 0.
//
//Note: A word is defined as a character sequence consists of non-space characters only.
//
//For example, 
//Given s = "Hello World",
//return 5.

//Easy
public class LengthLastWord {

	public LengthLastWord() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "a";
		
		System.out.println("Last Word Length = " + lengthOfLastWord(s));
	}
	
    public int lengthOfLastWord(String s) {
        int nRetLen = 0;
        boolean bWord = false;
        int nSingleElement;
        String sNew;
        
        if (s == null || s.length() == 0) return 0;
        
        sNew = s.toLowerCase();
        
        for (int i=sNew.length()-1; i>=0; i--) {
        	nSingleElement = sNew.charAt(i) - 'a';
        	if (nSingleElement >= 0 && nSingleElement <=25) {
        		if (bWord == false) bWord = true;
        		
        		nRetLen = nRetLen + 1;
        	} else {
        		if (bWord == false) {
        			continue;
        		} else {
        			break;
        		}
        	}
        }
        
        return nRetLen;
    }
	
}
