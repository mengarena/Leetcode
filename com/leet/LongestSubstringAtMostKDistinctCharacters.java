package com.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given a string, find the length of the longest substring T that contains at most k distinct characters.
//
//For example, Given s = ¡°eceba¡± and k = 2,
//
//T is "ece" which its length is 3.

//Google
//Hard
public class LongestSubstringAtMostKDistinctCharacters {

	public LongestSubstringAtMostKDistinctCharacters() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		String s = "ababffzzeee";
		int k = 2;
		
		int nLen = lengthOfLongestSubstringKDistinct(s, k);
		
		System.out.println(nLen);
	}

	
	//AC
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) return 0;
        char[] carr = s.toCharArray();
        int n = s.length();
        int i;
        int nMaxLen = 0;
        int nLen = 0;

        int firstPos = 0;
        
        Map<Character, Integer> hmLastPos = new HashMap<Character, Integer>();   //Last position of each character
                
        hmLastPos.put(carr[0], 0);
        nMaxLen = 1;
        
        for (i = 1; i < n; i++) {      	
        	if (!hmLastPos.containsKey(carr[i]) && hmLastPos.size() < k) {               
                        hmLastPos.put(carr[i], i);
        		nMaxLen = i - firstPos + 1;
        	} else if (hmLastPos.containsKey(carr[i])) {
        		hmLastPos.put(carr[i], i);
        		nLen = i - firstPos + 1;
        		nMaxLen = Math.max(nMaxLen, nLen);
        	} else if (!hmLastPos.containsKey(carr[i]) && hmLastPos.size() == k) {    		
        		int nMin = getMinLastPos(hmLastPos);
        		
        		firstPos = nMin + 1;
        		
    			hmLastPos.put(carr[i], i);
    			
    			nLen = i - firstPos + 1;
    			nMaxLen = Math.max(nMaxLen, nLen);        		
        	}
        }
                
        return nMaxLen;
    }	
    

    private int getMinLastPos(Map<Character, Integer> hmLastPos) {
    	Set<Character> setChar = hmLastPos.keySet();
    	char letter = 0;
    	int nMin = Integer.MAX_VALUE;
    	
    	for (Character c:setChar) {
    		if (nMin > hmLastPos.get(c)) {
    		    nMin = hmLastPos.get(c);
    		    letter = c;
    		}
    	}
    	
    	hmLastPos.remove(letter);  //Attention
    	return nMin;
    }
        
}
