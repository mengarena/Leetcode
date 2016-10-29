package com.leet;

import java.util.ArrayList;
import java.util.List;

//The count-and-say sequence is the sequence of integers beginning as follows:
//1, 11, 21, 1211, 111221, ...
//
//1 is read off as "one 1" or 11.
//11 is read off as "two 1s" or 21.
//21 is read off as "one 2, then one 1" or 1211.
//Given an integer n, generate the nth sequence.
//
//Note: The sequence of integers will be represented as a string.

//Facebook
//Easy
public class CountSay {

	public CountSay() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
 		
		int n = 5;
		
		System.out.println("The Sequence is: " + countAndSay(n));
	}
	
    public String countAndSay(int n) {
        int i;
        String sWord = "";
        
        if (n <= 0) return "";
        if (n == 1) return "1";
                
        sWord = "1";
        
        for (i=1; i<n; i++) {
        	sWord = CountSayWord(sWord);
        }
        
        return sWord;
    }
    
    public String CountSayWord(String sWord) {
    	String sNextWord = "";
    	int nLen = sWord.length();
    	int i;
    	int nCurrent = sWord.charAt(0) - '0';
    	int nNew;
    	int nCount = 1;
    	
    	for (i=1; i<nLen; i++) {
    		nNew = sWord.charAt(i) - '0';
    		if (nCurrent == nNew) {
    			nCount = nCount + 1;
    		} else {
    			sNextWord = sNextWord + nCount + "" + nCurrent;
    			nCount = 1;
    			nCurrent = nNew;
    		}
    	}
    	
    	sNextWord = sNextWord + nCount + "" + nCurrent;
    	
    	return sNextWord;
    }
        
}
