package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
//
//Return all such possible sentences.
//
//For example, given
//s = "catsanddog",
//dict = ["cat", "cats", "and", "sand", "dog"].
//
//A solution is ["cats and dog", "cat sand dog"].

//Google, Uber
public class WordBreakII {

	public WordBreakII() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC: 24%
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> lstBreaks = new ArrayList<String>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return lstBreaks;
        int n = s.length();
        List<Integer>[] breakPoints = new List[n];  //by position i in s, the possible breaking points in substring [0, i]
        int i, j;
        
        for (i=0; i<n; i++) breakPoints[i] = new ArrayList<Integer>();
        
        for (i=0; i<n; i++) {
        	//For each position i in s, find possible break points in substring [0, i], the break points are recorded in breakPoints[i]
            for (j=0; j<=i; j++) {
                if (wordDict.contains(s.substring(j, i+1)) && (j==0 || breakPoints[j-1].size() > 0)) breakPoints[i].add(j);
            }
        }
        
        retrieveString(s, breakPoints, lstBreaks, n-1, "");
        
        return lstBreaks;
    }

    
    private void retrieveString(String s, List<Integer>[] breakPoints, List<String> lstBreaks, int nPos, String sPattern) {
        if (nPos < 0) {
            lstBreaks.add(sPattern);
        } else {
            for (Integer i:breakPoints[nPos]) {
                String ss = s.substring(i, nPos+1);
                retrieveString(s, breakPoints, lstBreaks, i-1, sPattern.length() == 0? ss:ss + " " + sPattern);
            }
        }
    }
	
	
    
    
	
	//ACC:  14%
    public List<String> wordBreakA(String s, Set<String> wordDict) {
        List<String> lstBreaks = new ArrayList<String>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return lstBreaks;
    	int n = s.length();
    	int i;
    	
    	for (i=0; i<n; i++) {
    		String sub = s.substring(0, i+1);
    		String remained = s.substring(i+1);
    		if (wordDict.contains(sub) && wordBreakable(remained, wordDict)) {
    			List<String> tmpBreaks = wordBreakA(remained, wordDict);
    			if (tmpBreaks.isEmpty()) {
    				lstBreaks.add(sub);
    			} else {
	    			for (String stmp:tmpBreaks) {
	    				lstBreaks.add(sub + " " + stmp);
	    			}
    			}
    		}
    	}
    	
    	return lstBreaks;
    }

         
    private boolean wordBreakable(String s, Set<String> wordDict) {
        if (s == null || s.isEmpty()) return true;
        int n = s.length();
        boolean bDP[] = new boolean[n+1];   //The substring to position i is breakable
        int i, j;
        
        bDP[0] = true;
        
        for (i=0; i<n; i++) {
        	for (j=i; j>=0; j--) {
        		if (bDP[j] && wordDict.contains(s.substring(j, i+1))) {
        			bDP[i+1] = true;
        			break;
        		}
        	}
        }
        
        return bDP[n];    	
    }
    
       
}