package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;

//Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. 
//You must make sure your result is the smallest in lexicographical order among all possible results.
//
//Example:
//Given "bcabc"
//Return "abc"
//
//Given "cbacdcbc"
//Return "acdb"

//Google
public class RemoveDuplicateLetters {

	public RemoveDuplicateLetters() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String s = "bcabc";
//		String s = "cbacdcbc";
//		String s = "babacb";
//		String s = "thesqtitxyetpxloeevdeqifkz";
		
		System.out.println("After Removing Duplicate Letters : " + removeDuplicateLetters(s));
	}
	
	
	//ACC:  67%
	//Use stack to remember currently selected letters
	//When have a letter, compare current top letters in stack, 
	//if new letter is smaller and current top letter is not the last one of that letter, remove the top letter, 
	//and then put the new letter in
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        int i;
        String sRetStr = "";
        int[] narrLetterCnt = new int[26];   //Total count of each letter
        int[] narrLetterVisited = new int[26];  //Visited count of each letter
        boolean[] barrExisted = new boolean[26];  //Existing in the stack or not
        Stack<Character> stkRet = new Stack<Character>();
        
        for (i=0; i<n; i++) narrLetterCnt[s.charAt(i)-'a']++;
        
        for (i=0; i<n; i++) {
        	//Compare with the top of the stack
        	while (!stkRet.isEmpty() && s.charAt(i) < stkRet.peek() && barrExisted[s.charAt(i)-'a'] == false && narrLetterCnt[stkRet.peek()-'a'] != narrLetterVisited[stkRet.peek()-'a']) {
        		barrExisted[stkRet.peek()-'a'] = false;
        		stkRet.pop();
        	}
        	
        	if (!barrExisted[s.charAt(i)-'a']) {
        		stkRet.push(s.charAt(i));
        		barrExisted[s.charAt(i)-'a'] = true;
        	}
        	
        	narrLetterVisited[s.charAt(i)-'a']++;
        }
                
        while (!stkRet.isEmpty()) sRetStr = stkRet.pop() + sRetStr;
        
        return sRetStr;
    }
    
    
    
	/* Works, but timeout
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        int i;
        String sRetStr = "";
 
        HashMap<Character, Integer> hmLetter = new HashMap<Character, Integer>();
        
        for (i=0; i<n; i++) {
        	hmLetter.put(s.charAt(i), 1);
        }
                
        int nUniqueLetterCnt = hmLetter.size();
        
        List<String> lstResult = new ArrayList<String>();
        findString(s, 0, nUniqueLetterCnt, nUniqueLetterCnt, lstResult);
        if (lstResult.size() > 0) sRetStr = lstResult.get(0);
        
        return sRetStr;
    }
    
    
    private List<String> findString(String s, int nStartPos, int nRemainedLen, int nUniqueLetterCnt, List<String> lstResult) {
    	List<String> lstStr = new ArrayList<String>();
    	if (nRemainedLen == 0) return lstStr;
    	int n = s.length();
    	if (nStartPos+nRemainedLen == n) {
    		lstStr.add(s.substring(nStartPos));
    		return lstStr;
    	}
    	
    	for (int i=nStartPos; i<=n-nRemainedLen; i++) {
    		List<String> lstTmp = findString(s, i+1, nRemainedLen-1, nUniqueLetterCnt, lstResult);
    		if (lstTmp.size() > 0) {
	    		for (String sTmp:lstTmp) {
	    			String sTT = s.charAt(i) + sTmp;
	    			if (sTT.length() <= nUniqueLetterCnt) {
	    				lstStr.add(sTT);
	    			} else {
	    				continue;
	    			}
	    			if (sTT.length() == nUniqueLetterCnt && IsValidStr(sTT)) {
	    				if (lstResult.size() > 0) {
	    					if (sTT.compareTo(lstResult.get(0)) < 0) {
	    						lstResult.set(0, sTT);
	    					} 
	    				} else {
    						lstResult.add(sTT);
    					}
	    			}
	    		}
    		} else {
    			String sTT = s.charAt(i) + "";
    			lstStr.add(sTT);
    		}
    	}
    	
    	return lstStr;
    }
    
    */
    
    private boolean IsValidStr(String sTmp) {
    	HashMap<Character, Integer> hmStr = new HashMap<Character, Integer>();
    	
    	for (int i=0; i<sTmp.length(); i++) {
    		if (hmStr.containsKey(sTmp.charAt(i))) return false;
    		hmStr.put(sTmp.charAt(i), 1);
    	}
    	
    	return true;
    }
   
    
    
/*	
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return "";
        int n = s.length();
        int i;
        String sRetStr = "";
        char[] carrStr = s.toCharArray();
        HashMap<Character, List<Integer>> hmLetterPos = new HashMap<Character, List<Integer>>();
        HashMap<Character, Integer> hmDuplicateLetter = new HashMap<Character, Integer>();
        
        for (i=0; i<n; i++) {
        	List<Integer> lstPos = new ArrayList<Integer>();
        	if (!hmLetterPos.containsKey(carrStr[i])) {
        		lstPos.add(i);
        		hmLetterPos.put(carrStr[i], lstPos);
        	} else {
        		lstPos = hmLetterPos.get(carrStr[i]);
        		lstPos.add(i);
        		hmLetterPos.replace(carrStr[i], lstPos);
        		hmDuplicateLetter.put(carrStr[i], 1);
        	}
        }
        
        Set<Character> setLetters = hmDuplicateLetter.keySet();
        char[] carrLetters = new char[setLetters.size()];
        Iterator<Character> it = setLetters.iterator();
        i = 0;
        while (it.hasNext()) carrLetters[i++] = it.next();
        
        for (i = carrLetters.length-1; i >= 0 ; i--) {
//        for (i = 0; i <= carrLetters.length-1; i++) {

        	List<Integer> lstPos = hmLetterPos.get(carrLetters[i]);
        	boolean bSet = false;
        	for (int j=0; j<lstPos.size(); j++) {
        		int nPos = lstPos.get(j);
        		if (bSet == false) {
        			if (j == lstPos.size()-1) break;
        			char cNext = findNext(carrStr, carrLetters[i], nPos+1);
        			if (carrLetters[i] < cNext || cNext == ' ') {
        				bSet = true;
        				continue;
        			} else {
        				carrStr[nPos] = ' ';
        			}
        		} else {
        			carrStr[nPos] = ' ';
        		}
        	}
        	
        }
        
        for (i=0; i<n; i++) {
        	if (carrStr[i] != ' ') sRetStr = sRetStr + carrStr[i];
        }
        
        return sRetStr;
    }
	
    
*/    
}
