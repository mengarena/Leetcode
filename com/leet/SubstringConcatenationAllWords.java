package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//You are given a string, s, and a list of words, words, that are all of the same length. 
//Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and 
//without any intervening characters.
//
//For example, given:
//s: "barfoothefoobarman"
//words: ["foo", "bar"]
//
//You should return the indices: [0,9].
//(order does not matter).


public class SubstringConcatenationAllWords {

	public SubstringConcatenationAllWords() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		//String s = "barfoothefoobarman";
		//String s = "barfoofoobarthefoobarman";
		String s = "wordgoodgoodgoodbestword";
		//String[] words = {"foo", "bar", "the"};
        String[] words = {"word","good","best","good"};
		List<Integer> lstIdx = findSubstring(s, words);
		
		for (Integer idx:lstIdx) {
			System.out.print(idx + ",");
		}
		
		System.out.println();
	}
	
	//https://leetcode.com/discuss/83202/accepted-java-solution-12ms-with-explanation
	//https://leetcode.com/discuss/75252/java-12ms-beats-100%25
	//https://leetcode.com/discuss/20151/an-o-n-solution-with-detailed-explanation
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> lstIdx = new ArrayList<Integer>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return lstIdx; 
        int n = s.length();
        int cnt = words.length;
        int i, j, m;
        int wordLen = words[0].length();
        Map<String, Integer> dict = new HashMap<String, Integer>();
        int totalWordLen = wordLen*cnt;
        if (n < totalWordLen) return lstIdx;
        int startIdx = 0;
        
        for (i=0; i<cnt; i++) {
        	if (dict.containsKey(words[i])) {
        		dict.put(words[i], dict.get(words[i])+1);  //Words in words[] could be duplicate
        	} else {
        		dict.put(words[i], 1);
        	}
        }
        
        //Only need to try #wordLen starting point
        //From the starting point, try substring by step length of wordLen
        for (i=0; i<wordLen; i++) {
        	startIdx = i;
        	
        	Map<String, Integer> curDict = new HashMap<>(dict);
        	
        	for (j=i; j+wordLen<=n; j+=wordLen) {   //Here, pay attention:  j+wordLen <= n   (not <)
        		
        		String testWord = s.substring(j, j+wordLen);
        		
        		if (curDict.containsKey(testWord)) {
        			int count = curDict.get(testWord);
        			if (count == 1) {
        				curDict.remove(testWord);
        			} else {
        				curDict.put(testWord, count-1);
        			}
        			
        			if (curDict.isEmpty()) {
        				lstIdx.add(startIdx);
        				String sword = s.substring(startIdx, startIdx+wordLen);
        				curDict.put(sword, 1);    //Recover for the first word of the valid substring
        				startIdx = startIdx+wordLen;   //Move one word forward
        			}
        			
        		} else if (!dict.containsKey(testWord)) {
        			
        			m = startIdx;
        			String sword = s.substring(m, m+wordLen);
        			
        			while (m+wordLen <= j) {
        				if (curDict.containsKey(sword)) {
        					curDict.put(sword, curDict.get(sword)+1);
        				} else {
        					curDict.put(sword, 1);
        				}
        				
        				m = m+wordLen;
        				sword = s.substring(m, m+wordLen);
        			}
        		
        			startIdx = j+wordLen;
	
        		} else {    //Not in curDict, but is a valid word (in dict), i.e. curDict has used all the quota for this word, 
        			        //so need to recover the quota for this word (then all other words before meeting the first of this word will be recovered 
        			        // (i.e. removed from current valid substring)
        			
        			//Not contains current word, previous trial fails, recovery the dictionary to its original point 
        			//based on what has been removed by previous substrings
        			m = startIdx;
        			String sword = s.substring(m, m+wordLen);
        			
        			while (m+wordLen <= j && !sword.equals(testWord)) {  //Recovery until the first testWord
        				if (curDict.containsKey(sword)) {
        					curDict.put(sword, curDict.get(sword)+1);
        				} else {
        					curDict.put(sword, 1);
        				}
        				
        				m = m+wordLen;
        				sword = s.substring(m, m+wordLen);
        			}
        		        			
        			startIdx = m + wordLen;   //Next startIdx is after the previous first testWord
        		}
        	}
        	
        }
        
        return lstIdx;
    }

}
