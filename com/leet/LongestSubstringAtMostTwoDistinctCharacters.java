package com.leet;

//Given a string, find the length of the longest substring T that contains at most 2 distinct characters. 
//
//For example, Given s = ¡°eceba¡±, 
//
//T is "ece" which its length is 3. 


//Google
public class LongestSubstringAtMostTwoDistinctCharacters {
	
	public void run() {
	    //String s = "eceecba";
	    String s = "ab";
	    
	    System.out.println(lengthOfLongestSubstringTwoDistinct(s));
	}
	
	
	//ACC:  70%
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] carr = s.toCharArray();
        int n = s.length();
        int i;
        int nMaxLen = 0;
        int nLen = 0;
        char letter1 = 0, letter2 = 0;
        int firstPos1 = -1, lastPos1 = -1, firstPos2 = -1, lastPos2 = -1;
        
        letter1 = carr[0]; firstPos1 = 0; lastPos1 = 0;
        
        for (i = 1; i < n; i++) {        	
        	if (carr[i] != letter1 && firstPos2 == -1) {
        		letter2 = carr[i];
        		firstPos2 = i;
        		lastPos2 = i;
        		nMaxLen = i - firstPos1 + 1;
        	} else if ((carr[i] == letter1 && firstPos2 != -1) || carr[i] == letter2) {
        	    nLen = i - Math.min(firstPos1, firstPos2) + 1;
        	    nMaxLen = Math.max(nMaxLen, nLen);
        	} else if (carr[i] != letter1 && carr[i] != letter2) {
        		if (lastPos1 < lastPos2) {
        			firstPos2 = lastPos1+1;
        			firstPos1 = i;
        			lastPos1 = i;
        			letter1 = carr[i];
        			nLen = i - firstPos2 + 1;
        			nMaxLen = Math.max(nMaxLen, nLen);
        		} else {
        			firstPos1 = lastPos2+1;
        			firstPos2 = i;
        			lastPos2 = i;
        			letter2 = carr[i];
        			nLen = i - firstPos1 + 1;
        			nMaxLen = Math.max(nMaxLen, nLen);
        		}
        	}
        	
        	if (carr[i] == letter1) lastPos1 = i;
        	if (firstPos2 != -1 && carr[i] == letter2) lastPos2 = i;
        }
        
        if (firstPos2 == -1) nMaxLen = n;
        
        return nMaxLen;
    }
}