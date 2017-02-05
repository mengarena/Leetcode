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
	
	
	//ACC
	//Time complexity: O(4^n)
    public List<String> letterCombinations(String digits) {
        List<String> lstComb = new ArrayList<String>();
        String[] sarrLetter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits == null || digits.length() == 0) return lstComb;
        
        List<String> lstCombRemained = letterCombinations(digits.substring(1));
        
        String sHead = sarrLetter[digits.charAt(0) - '0'];
        if (sHead.length() == 0) return lstCombRemained;
        
        if (lstCombRemained.size() > 0) {
            for (int i=0; i<sHead.length(); i++) {
                for (String s:lstCombRemained) {
                    s = sHead.charAt(i) + s;
                    lstComb.add(s);
                }
            }
        } else {
            for (int i=0; i<sHead.length(); i++) {
                lstComb.add(sHead.charAt(i) + "");
            }
        }
        
        return lstComb;
    }
    
    	
    	
    	
    	
    //ACC: Use FIFO,  	
    //Actually, it is like BFS
    //Complexity:  worst case: O(4 + 4*4 + 4*4*4 + .. + 4^n)  = O(4^n) (or more accurately: O(4^(n-1)*5)
    //n is the number of digits
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        if (digits == null || digits.length() == 0) return ans;
        int nullDigitCnt = 0;
        int digit = 0;
        
        ans.offer("");
        
        for (int i=0; i<digits.length(); i++) {
            digit = digits.charAt(i)-'0';
            
            if (digit <= 1) {   //If the digit is 0 or 1, ignore
                nullDigitCnt++;
                continue;
            }
            
            //The temp string which has the same length as previously processed digits need to add the letters for the new digit
            while (ans.peek().length() == i-nullDigitCnt) {  
                String s = ans.poll();
                
                for (int j=0; j<mapping[digit].length(); j++) {
                    ans.offer(s + mapping[digit].charAt(j));
                }
            }
        }
        
        return ans;
    }    	
    	
    	
    	

    public List<String> letterCombinationsA(String digits) {
        List<String> lstComb = new ArrayList<String>();
        String[] sarrLetter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
                             // 0   1    2      3      4      5      6      7       8      9  (number and index)
        
        if (digits == null || digits.length() == 0) return lstComb;
        int i;
        int nCurDigit = digits.charAt(0) - '0';
                
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
