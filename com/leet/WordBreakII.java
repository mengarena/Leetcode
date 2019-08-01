package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Given a string s and a dictionary of words dict, add spaces in s to construct a sentence 
//where each word is a valid dictionary word.
//
//Return all such possible sentences.
//
//For example, given
//s = "catsanddog",
//dict = ["cat", "cats", "and", "sand", "dog"].
//
//A solution is ["cats and dog", "cat sand dog"].

//Google, Uber, Dropbox, Snapchat, Twitter
//Hard
public class WordBreakII {

	public WordBreakII() {
		// TODO Auto-generated constructor stub
	}

	
//ACC
//If there is no memo (i.e. hm), the time complexity is O(2^n) --- at every position, there could be a cut, or not a cut
//If with memo (i.e. hm), the time complexity is O(n^2)
	
//https://discuss.leetcode.com/topic/27855/my-concise-java-solution-based-on-memorized-dfs/23	
//Let's start form an example. Assume we have a word "leet" and length is four(just forget the dictionary). 
//We want to get final result. Assume we have a function "p()" to solve this solution, that is "res = p(leet)";
//In the brut force method(back tracking), we have the following solution:
//res = Math.min("l" + p("eet") , "le" + p("et") , "lee" + p("t") , "leet");
//(for "le" + p("et") the left part is atomic , there is no cut in "le").
//we use the cutting position to show every calculate. We can get an recursion tree just like 
//but in the memo solution, we don't need to solve subproblems over and over again, 
//we can use the result we have worked out before, so we can pruning the above tree in 
//(all of the pictures are from "Introduction to Algorithms")
//So we can reduce the runtime from exponential-time to polynominal-time.
//now we have an dictionary, we even not need to call our recursion function letter by letter so the running time is even less.
//(I don't calculate the "substring()" into notice because it is build in method)
	
    private Map<String, List<String>> hm = new HashMap<String,List<String>>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) return res;
        int maxLen = 0;
        int minLen = Integer.MAX_VALUE;
        
        for (String word:wordDict) {
            maxLen = Math.max(maxLen, word.length());
            minLen = Math.min(minLen, word.length());
        }
        
        res = wordBreakHelper(s, wordDict, maxLen, minLen);
        
        return res;
    }
    
    private List<String> wordBreakHelper(String s, List<String> wordDict, int maxLen, int minLen) {
        List<String> res = new ArrayList<>();
        
        if (hm.containsKey(s)) return hm.get(s);
        
        for (int i=minLen; i<=Math.min(maxLen, s.length()); i++) {
            String sFirst = s.substring(0, i);
    
            if (wordDict.contains(sFirst)) {
                List<String> tmpRes = wordBreakHelper(s.substring(i), wordDict, maxLen, minLen);
                
                if (!tmpRes.isEmpty()) {
                    for (String sTmp:tmpRes) res.add(sFirst + " " + sTmp);
                } else {
                    if (i == s.length()) res.add(s);
                }
            }
        }
        
        hm.put(s, res);
        
        return res;
    }	
	
	
	
	
    //ACC: 91%
    private Map<String, List<String>> hm = new HashMap<>();
    
    public List<String> wordBreakK(String s, Set<String> wordDict) {
        int maxLen = 0;
        
        for (String sWord:wordDict) maxLen = Math.max(maxLen, sWord.length());
        
        return wordBreakHelper(s, wordDict, maxLen);
    }
    
    public List<String> wordBreakHelper(String s, Set<String> wordDict, int maxLen) {
        List<String> lstBreaks = new ArrayList<String>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return lstBreaks;
        int n = s.length();
        int i;
        
        if (hm.containsKey(s)) return hm.get(s);  //Important, avoid duplicated processing
                                                  //For example, s = "aaaaaaaaaaaa"
                                                  //it could be divided/processed as "aa" "aaa" "aaaaaaa"
                                                  //or:                              "a" "aaaa" "aaaaaaa"
                                                  //Then "aaaaaaa" will be processed twice.  
                                                  //With this "if", it could save the effort
        
        for (i=0; i<Math.min(n, maxLen); i++) {  //Compare the solution below, use maxLen help recude unnecessary processing
            String sFirst = s.substring(0, i+1);
            
            if (wordDict.contains(sFirst)) {
                List<String> lstTmpBreaks = wordBreakHelper(s.substring(i+1), wordDict, maxLen);
                
                if (!lstTmpBreaks.isEmpty()) {
                    for (String sTmpBreak:lstTmpBreaks) lstBreaks.add(sFirst + " " + sTmpBreak);
                } else {
                    if (i == n-1) lstBreaks.add(sFirst);
                }
            }
        }

        hm.put(s, lstBreaks);
        
        return lstBreaks;
    }
	
	
	
    //ACC:  50%
    //private Map<String, List<String>> hm = new HashMap<>();
    
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> lstBreaks = new ArrayList<String>();
        if (s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0) return lstBreaks;
        int n = s.length();
        int i;
        
        if (hm.containsKey(s)) return hm.get(s);
        
        for (i=0; i<n; i++) {
            String sFirst = s.substring(0, i+1);
            
            if (wordDict.contains(sFirst)) {
                List<String> lstTmpBreaks = wordBreak(s.substring(i+1), wordDict);
                
                if (!lstTmpBreaks.isEmpty()) {
                    for (String sTmpBreak:lstTmpBreaks) lstBreaks.add(sFirst + " " + sTmpBreak);
                } else {
                    if (i == n-1) lstBreaks.add(sFirst);
                }
            }
        }

        hm.put(s, lstBreaks);
        
        return lstBreaks;
    }
	
	
	
	
	//ACC: 24%
    public List<String> wordBreakB(String s, Set<String> wordDict) {
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
