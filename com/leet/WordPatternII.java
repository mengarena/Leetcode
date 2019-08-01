package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a pattern and a string str, find if str follows the same pattern.
//
//Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
//
//Examples:
//pattern = "abab", str = "redblueredblue" should return true.
//pattern = "aaaa", str = "asdasdasdasd" should return true.
//pattern = "aabb", str = "xyzabcxzyabc" should return false.
//		
//Notes:
//You may assume both pattern and str contains only lowercase letters.

//Dropbox, Uber
//Hard
public class WordPatternII {

	public WordPatternII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String pattern = "ab";
		String str = "aa";
		
		System.out.println(wordPatternMatch(pattern, str));
		
		System.out.println();
	}
	
	
	
	//ACC:  85%
	Map<Character, String> hmPatStr = new HashMap<Character, String>();
	
    public boolean wordPatternMatch(String pattern, String str) {
    	if (pattern.isEmpty()) return str.isEmpty();
    
    	if (hmPatStr.containsKey(pattern.charAt(0))) {
    	    String sval = hmPatStr.get(pattern.charAt(0));
    	    if (str.length() < sval.length() || !str.substring(0, sval.length()).equals(sval)) return false;
    	    if (wordPatternMatch(pattern.substring(1), str.substring(sval.length()))) return true;
    	} else {
    	    int n = str.length();
    	    int nSize = pattern.length();
    		
    	    for (int i=1; i<=n-nSize+1; i++) {
    	        //Reason for using this "if" statement: If hmPatStr contains this substring, 
		//but hmPatStr does not contains the pattern.charAt(0), 
    		//then a substring will have two different keys, that's not valid in this question. 
    		if (hmPatStr.containsValue(str.substring(0, i))) continue;
    			
    		hmPatStr.put(pattern.charAt(0), str.substring(0, i));
    			
    		if (wordPatternMatch(pattern.substring(1), str.substring(i))) return true;
    			
    		hmPatStr.remove(pattern.charAt(0));
    	    }
    	}
    	
    	return false;
    }
    
    
    
    
    
    
    
    
    
	
	//Works, Exceeded time limit
    public boolean wordPatternMatchA(String pattern, String str) {
        if ((pattern == null && str == null) || (pattern.length() == 0 && str.length() == 0) || pattern.length() == 1 && str.length() > 0) return true;
        if ((pattern == null && str != null) || (pattern != null && str == null)) return false;
        if ((pattern.length() == 0 && str.length() > 0) || pattern.length() > str.length()) return false;
        int n = pattern.length();
        
        List<List<String>> lstlstStr = new ArrayList<List<String>>();
        int i;
        boolean valid = false;
        
        lstlstStr = divideStr(str, n);
        
        for (List<String> lstStr:lstlstStr) {
        	Map<String, Integer> hmStr = new HashMap<String, Integer>();
        	Map<Character, Integer> hmPattern = new HashMap<Character, Integer>();
        	
        	if (lstStr.size() == n) {
        		valid = true;
        		for (i=0; i<n; i++) {
        			if (hmPattern.containsKey(pattern.charAt(i)) && hmStr.containsKey(lstStr.get(i))) {
        				if (hmPattern.get(pattern.charAt(i)) != hmStr.get(lstStr.get(i))) {
        					valid = false;
        					break;
        				}
        			} else if (hmPattern.containsKey(pattern.charAt(i)) || hmStr.containsKey(lstStr.get(i))) {
        				valid = false;
        				break;
        			} else {
        				hmPattern.put(pattern.charAt(i), i);
        				hmStr.put(lstStr.get(i), i);
        			}
        		}
        		
        		if (valid == true) return true;
        	}
        }
        
        return false;
    }
    
    
    
    private List<List<String>> divideStr(String str, int nSize) {
    	List<List<String>> lstlstStr = new ArrayList<List<String>>();
    	int n = str.length();
    	int i;
    	if (n < nSize) return null;
    	
    	if (nSize == 1) {    		
    		List<String> lstTmp = new ArrayList<String>();
    		lstTmp.add(str);
    		lstlstStr.add(lstTmp);
    		return lstlstStr;
    	}
    	
    	for (i=1; i<=n-nSize+1; i++) {  //Length of first sub string
    		List<List<String>> lstlstStrTmp = divideStr(str.substring(i), nSize-1);
    	    
    		if (lstlstStrTmp != null) {
    			for (List<String> lstStrTmp:lstlstStrTmp) {
    				lstStrTmp.add(0, str.substring(0, i));
    				lstlstStr.add(lstStrTmp);
    			}
    		} 
    	}
    	
    	return lstlstStr;
    }
}
