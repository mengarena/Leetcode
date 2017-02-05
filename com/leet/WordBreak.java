package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//Given a string s and a dictionary of words dict, 
//determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//
//For example, given
//s = "leetcode",
//dict = ["leet", "code"].
//
//Return true because "leetcode" can be segmented as "leet code".

//Bloomberg
public class WordBreak {

	public WordBreak() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		Set<String> wordDict = new HashSet<String>();
		wordDict.add("leet");
		wordDict.add("code");
		
		String s = "leetcfode";
		
		System.out.println(wordBreak(s, wordDict));
		
	}
	
	//ACC
	//O(n^2) * O(operation of wordDict.contains(x))
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return true;
        if (wordDict == null || wordDict.isEmpty()) return false;
        int n = s.length();
        boolean[] dp = new boolean[n+1];   //The substring to position i is breakable
        dp[0] = true;
        
        for (int i=1; i<=n; i++) {
            for (int j=i-1; j>=0; j--) {
                if (dp[j] && wordDict.contains(s.substring(j,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
         
        return dp[n];   
    }	
	

    public boolean wordBreakA(String s, Set<String> wordDict) {
        if (s == null || s.isEmpty()) return true;
        if (wordDict == null || wordDict.isEmpty()) return false;
        int n = s.length();
        boolean bDP[] = new boolean[n+1];   //The substring to position i is breakable
        int i, j;
        
        for (i=0; i<n+1; i++) bDP[i] = false;
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
        
	
/*	DFS,  Exceeded Time Limit
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.isEmpty()) return true;
        if (wordDict == null || wordDict.isEmpty()) return false;
        int nMinLen = Integer.MAX_VALUE;
        int nMaxLen = 0;
        int nWordLen = 0;
              
        Iterator<String> it = wordDict.iterator();
        while (it.hasNext()) {
        	String sWord = it.next();
        	nWordLen = sWord.length();
        	if (nWordLen < nMinLen)  nMinLen = nWordLen;
        	if (nWordLen > nMaxLen)  nMaxLen = nWordLen;        	
        }
        
        boolean bRet = wordBreakHelper(s, wordDict, nMinLen, nMaxLen);
        
        return bRet;
    }
    
    
    private boolean wordBreakHelper(String s, Set<String> wordDict, int nMinLen, int nMaxLen) {
    	if (s == null || s.length() == 0) return true;
    	int i;
    	boolean bPossible = false;
    	int nRemainedLen = 0;
    	int n = s.length();
    	
    	if (n < nMinLen) return false;
    	
    	nRemainedLen = Math.min(nMaxLen, n);
    	
		for (i=nMinLen; i<=nRemainedLen; i++) {
			
			String sPossibleWord = s.substring(0, i);
			
			if (!wordDict.contains(sPossibleWord)) continue;
			
			boolean bRetTmp = wordBreakHelper(s.substring(i), wordDict, nMinLen, nMaxLen);
			
			if (bRetTmp == true) {
				bPossible = true;
				break;
			}
		}
    		
		if (bPossible == false) return false;
    	
    	return true;
    }
*/	
	
/*	
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null || s.isEmpty()) return true;
        if (wordDict == null || wordDict.isEmpty()) return false;
        int nMinLen = Integer.MAX_VALUE;
        int nMaxLen = 0;
        int nWordLen = 0;
      
        HashMap<Character, List<String>> hmDict = new HashMap<Character, List<String>>();
        
        Iterator<String> it = wordDict.iterator();
        while (it.hasNext()) {
        	String sWord = it.next();
        	nWordLen = sWord.length();
        	if (nWordLen < nMinLen)  nMinLen = nWordLen;
        	if (nWordLen > nMaxLen)  nMaxLen = nWordLen;
        	
        	if (hmDict.containsKey(sWord.charAt(0))) {
        		List<String> lstWords = hmDict.get(sWord.charAt(0));
        		lstWords.add(sWord);
        		hmDict.replace(sWord.charAt(0), lstWords);
        	} else {
        		List<String> lstWords = new ArrayList<String>();
        		lstWords.add(sWord);
        		hmDict.put(sWord.charAt(0), lstWords);        		
        	}
        }
        
        boolean bRet = wordBreakHelper(s, hmDict, nMinLen, nMaxLen);
        
        return bRet;
    }
    
    
    private boolean wordBreakHelper(String s, HashMap<Character, List<String>> hmDict, int nMinLen, int nMaxLen) {
    	if (s == null || s.length() == 0) return true;
    	int i;
    	boolean bPossible = false;
    	int nRemainedLen = 0;
    	int n = s.length();
    	
    	if (n < nMinLen) return false;
    	
    	nRemainedLen = Math.min(nMaxLen, n);
    	
		for (i=nMinLen; i<=nRemainedLen; i++) {
			
			String sPossibleWord = s.substring(0, i);
			
			if (!isValidWord(sPossibleWord, hmDict)) continue;
			
			boolean bRetTmp = wordBreakHelper(s.substring(i), hmDict, nMinLen, nMaxLen);
			
			if (bRetTmp == true) {
				bPossible = true;
				break;
			}
		}
    		
		if (bPossible == false) return false;
    	
    	return true;
    }
	
    
    private boolean isValidWord(String s, HashMap<Character, List<String>> hmDict) {
    	List<String> lstWords = hmDict.get(s.charAt(0));
    	
    	if (lstWords == null) return false;
    	if (lstWords.contains(s)) return true;
    	
    	return false;
    }
*/
	
}
