package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a digit string, return all possible letter combinations that the number could represent.
//
//A mapping of digit to letters (just like on the telephone buttons) is given below.
//
//
//
//Input:Digit string "23"
//Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

//Although the above answer is in lexicographical order, your answer could be in any order you want.

//Facebook, Uber
public class LetterCombinationsPhoneNumber {

	public LetterCombinationsPhoneNumber() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String digits = "234";
		List<String> lstLetterComb = letterCombinations(digits);
		for (String sComb:lstLetterComb) System.out.print(sComb + ",");
	}
	

    public List<String> letterCombinations(String digits) {
        List<String> lstComb = new ArrayList<String>();
        String[] sarrLetter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        if (digits == null || digits.length() == 0) return lstComb;
        int i;
        int nCurDigit = digits.charAt(0) - '0';
        
        if (digits.length() == 1) {
        	if (sarrLetter[nCurDigit].length() == 0) return lstComb;
        	for (i=0; i<sarrLetter[nCurDigit].length(); i++) {
        		lstComb.add(sarrLetter[nCurDigit].charAt(i) + "");
        	}
        	
        	return lstComb;
        }
        
        
        List<String> lstCombTmp = letterCombinations(digits.substring(1));
        
        if (sarrLetter[nCurDigit].length() == 0) return lstCombTmp;
        
        for (i=0; i<sarrLetter[nCurDigit].length(); i++) {
        	if (lstCombTmp != null && lstCombTmp.size() > 0) {
	        	for (String sCombTmp:lstCombTmp) {
	        		sCombTmp = sarrLetter[nCurDigit].charAt(i) + sCombTmp;
	        		lstComb.add(sCombTmp);
	        	}
        	} else {
        		lstComb.add(sarrLetter[nCurDigit].charAt(i) + "");
        	}
        }
        
        return lstComb;
    }
	
}
