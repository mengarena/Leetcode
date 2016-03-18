package com.leet;

import java.util.*;

//Given a pattern and a string str, find if str follows the same pattern.
//
//Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
//
//Examples:
//pattern = "abba", str = "dog cat cat dog" should return true.
//pattern = "abba", str = "dog cat cat fish" should return false.
//pattern = "aaaa", str = "dog cat cat dog" should return false.
//pattern = "abba", str = "dog dog dog dog" should return false.
//		
//Notes:
//You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

public class WordPattern {

	public WordPattern() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String sPattern = "abc";
		String sStr = "dang ding d";
//		int nStartPos = 0;
//		int nTrueStartPos;
//		StringBuilder sWord = new StringBuilder();
//		
//		while (nStartPos >= 0) {
//			sWord = new StringBuilder();
//			nTrueStartPos = getWord(sStr, nStartPos, sWord);
//			if (sWord.length() == 0) break;
//			
//			System.out.println(sWord);
//			
//			nStartPos = nTrueStartPos + sWord.length();
//			if (nStartPos >= sStr.length()) return;
//		}
		
		
		boolean bRet = wordPattern(sPattern, sStr);
		if (bRet) {
			System.out.println("[" + sStr  + "] HAS the same Pattern as [" + sPattern + "]");
		} else {
			System.out.println("[" + sStr  + "] HAS NO Pattern as [" + sPattern + "]");			
		}
	}
	
    public boolean wordPattern(String pattern, String str) {
        int nPatternLen = 0;
        int i;
        Map<Character, Integer> mapPattern = new HashMap<Character, Integer>();
        Map<String, Integer> mapStr = new HashMap<String, Integer>();
        char cSingle;
		int nStartPos = 0;
		int nTrueStartPos = -1;
		StringBuilder sSingle = new StringBuilder();
		String sWord;
        
        if (pattern == null && str == null) return true;
        if (pattern == null || str == null) return false;
        if (pattern.length() == 0 && str.length() != 0) return false;
        if (pattern.length() > str.length()) return false;
        
        nPatternLen = pattern.length();
        
        for (i=0; i<nPatternLen; i++) {
        	sSingle = new StringBuilder();
        	cSingle = pattern.charAt(i);
        	nTrueStartPos = getWord(str, nStartPos, sSingle);
        	if (nTrueStartPos == -1 || sSingle.length() == 0) return false;
        	
        	sWord = sSingle.toString();
        	System.out.print(sWord + ",");
        	nStartPos = nTrueStartPos + sWord.length();
        	
        	if (mapPattern.containsKey(cSingle) && mapStr.containsKey(sWord)) {
        		if (mapPattern.get(cSingle) != mapStr.get(sWord)) return false;
        	} else if (mapPattern.containsKey(cSingle) || mapStr.containsKey(sWord)) {
        		return false;
        	} else {
        		mapPattern.put(cSingle, i);
        		mapStr.put(sWord, i);
        	}
        }
        
        if (nTrueStartPos != -1) {
        	sSingle = new StringBuilder();
        	nTrueStartPos = getWord(str, nStartPos, sSingle);
        	if (sSingle.length() > 0) return false;
        }
        
        return true;
    }
	
    public int getWord(String str, int nStartPos, StringBuilder sWord) {
    	int nTrueStartPos = -1;
    	int nEndPos = 0;
    	int i;
    	int nLen = str.length();
    	if (nStartPos >= nLen) {
    		sWord.append("");
    		return -1;
    	}
    	
    	for (i=nStartPos; i<nLen; i++) {
    		if (str.charAt(i) >='a' && str.charAt(i) <= 'z') {
    			if (nTrueStartPos == -1) nTrueStartPos = i;
    		} else {
    			if (nTrueStartPos != -1) {
    				nEndPos = i;
    				break;
    			}
    		}
    	}

    	if (nTrueStartPos == -1) {
    		sWord = sWord.append("");;
    		return -1;
    	}

    	if (i == nLen) {
    		sWord.append(str.substring(nTrueStartPos, nLen));
    	} else {
    		sWord.append(str.substring(nTrueStartPos, nEndPos));
    	}
    	    	
    	return nTrueStartPos;
    }
    
}
