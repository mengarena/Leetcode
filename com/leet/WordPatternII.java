package com.leet;

import java.util.HashMap;
import java.util.Map;

public class WordPatternII {
	
	public void run() {
		String pattern = "ab";
		String str = "aa";
		
		System.out.println(wordPatternMatch(pattern, str));
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
    			//Reason for using this "if" statement: If hmPatStr contains this sunbstring, but hmPatStr does not contains the pattern.charAt(0), 
    			//then a substring will have two different keys, that's not valid in this question. 
    			if (hmPatStr.containsValue(str.substring(0, i))) continue;
    			
    			hmPatStr.put(pattern.charAt(0), str.substring(0, i));
    			if (wordPatternMatch(pattern.substring(1), str.substring(i))) return true;
    			hmPatStr.remove(pattern.charAt(0));
    		}
    	}
    	
    	return false;
    }
}
