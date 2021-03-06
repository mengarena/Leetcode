package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//There is a new alien language which uses the latin alphabet. 
//However, the order among letters are unknown to you. 
//You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
//Derive the order of letters in this language.
//
//For example,
//Given the following words in dictionary,
//
//[
//  "wrt",
//  "wrf",
//  "er",
//  "ett",
//  "rftt"
//]
//The correct order is: "wertf".
//
//Note:
//You may assume all letters are in lowercase.
//If the order is invalid, return an empty string.
//There may be multiple valid order of letters, return any one of them is fine.


//Google, Facebook, Airbnb, Snapchat, Twitter, Pocket Gems
//Hard
public class AlienDictionary {

	public AlienDictionary() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		String words[] = {"za", "zb", "ca", "cb"};
		
		String sOrder = alienOrder(words);
		
		System.out.println(sOrder);
	}

	//New ACC
public String alienOrder(String[] words) {
    List<Set<Integer>> adj = new ArrayList<>();   // the letters behind me （use difference with 'a' to stand for letters)
    
    for (int i = 0; i < 26; i++) {
        adj.add(new HashSet<Integer>());
    }
    
    int[] degree = new int[26];  //in degree (i.e the number of letter before me)
    Arrays.fill(degree, -1);
    
    for (int i = 0; i < words.length; i++) {
        for (char c : words[i].toCharArray()) {
            if (degree[c - 'a'] < 0) {
                degree[c - 'a'] = 0;  //letter appears
            }
        }
        if (i > 0) {
            String w1 = words[i - 1], w2 = words[i];
            int len = Math.min(w1.length(), w2.length());
            for (int j = 0; j < len; j++) {
                int c1 = w1.charAt(j) - 'a', c2 = w2.charAt(j) - 'a';
                if (c1 != c2) {
                    if (!adj.get(c1).contains(c2)) {
                        adj.get(c1).add(c2);
                        degree[c2]++;
                    }
                    break;
                }
                if (j == w2.length() - 1 && w1.length() > w2.length()) { // "abcd" -> "ab" (in dictionary, "ab" should appear before "abcd"
                    return "";
                }
            }
        }
    }
    
    Queue<Integer> q = new LinkedList<>();
    for (int i = 0; i < degree.length; i++) {
        if (degree[i] == 0) {
            q.add(i);       
        }
    }
    
    StringBuilder sb = new StringBuilder();
    while (!q.isEmpty()) {
        int i = q.poll();
        sb.append((char) ('a'  + i));
        for (int j : adj.get(i)) {
            degree[j]--;
            if (degree[j] == 0) {
                q.add(j);        
            }
        }
    }
    
    for (int d : degree) { // there is a loop
        if (d > 0) {
            return "";
        }
    }
    
    return sb.toString();
}
	
	
	//Old: if input is ["wrtka", "wrt"] should return "", because in dictionary, "wrt" should appear before "wrtka"
	//In the earlier version, this test case is not included
	//NOT ACC:  15%   Similar to Course Schedule II
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        Map<Character, Set<Character>> hmGraph = new HashMap<Character, Set<Character>>();
        int i,j;
        int n = words.length;
        Set<Character> setChar = new HashSet<Character>();
        List<Character> lstOrder = new ArrayList<Character>();
        char c1, c2;
        
        if (n == 1) {
        	StringBuilder sb = new StringBuilder();
        	for (i=0; i<words[0].length(); i++) {
        		c1 = words[0].charAt(i);
        		if (!setChar.contains(c1)) {
        			setChar.add(c1);
        			sb.append(c1);
        		}
        	}
        	
        	return sb.toString();
        }
        
        //Construct Graph
        for (i=1; i<n; i++) {
        	String s1 = words[i-1];
        	String s2 = words[i];
        	
        	for (j=0; j<Math.max(s1.length(), s2.length()); j++) {
        		if (j < s1.length()) {
        		    c1 = s1.charAt(j);
        		} else {
        			c1 = 0;
        		}
        		
        		if (j < s2.length()) {
        			c2 = s2.charAt(j);
        		} else {
        			c2 = 0;
        		}
        		
        		if (c1 != 0 && !setChar.contains(c1)) setChar.add(c1);
        		if (c2 != 0 && !setChar.contains(c2)) setChar.add(c2);
        		
        		//Here, when c1 != c2, the (previous) substring before c1, c2 must be the same, 
        		//otherwise (the previous substring has decided the order) , meaningless to compare c1, c2
        		if (c1 != 0 && c2 != 0 && c1 != c2 && s1.substring(0, j).equals(s2.substring(0, j))) {
        			if (!hmGraph.containsKey(c1)) {
        				Set<Character> setAdj = new HashSet<Character>();
        				setAdj.add(c2);
        				hmGraph.put(c1,  setAdj);
        			} else {
        				Set<Character> setAdj = hmGraph.get(c1);
        				if (!setAdj.contains(c2)) {
        					setAdj.add(c2);
        					hmGraph.put(c1, setAdj);
        				}
        			}
        		}
        	}
        	
        }
        
        Map<Character, Boolean> hmVisited = new HashMap<Character, Boolean>();
        for (Character c:setChar) hmVisited.put(c, false);
        
        //In lstOrder, the character's order lower (i.e. should occur in front), the character is added later
        for (Character c:setChar) {
        	if (!hmVisited.get(c)) {
        		boolean bret = dfsFindOrder(hmGraph, hmVisited, c, lstOrder);
        		if (bret == false) return "";
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        for (Character c:lstOrder) sb.insert(0, c);
        
        Set<Character> setV = hmVisited.keySet();
        for (Character c:setV) {
        	if (hmVisited.get(c) == false) sb.append(c);
        }
        
        return sb.toString();
        
    }
    
    
    private boolean dfsFindOrder(Map<Character, Set<Character>> hmGraph,  Map<Character, Boolean> hmVisited, char c, List<Character> lstOrder) {
    	hmVisited.put(c,  true);
    	
    	Set<Character> setAdj = hmGraph.get(c);
    	if (setAdj == null) {
    		lstOrder.add(c);
    		return true;
    	}
    	
    	for (Character cc:setAdj) {
    		if (lstOrder.indexOf(cc) == -1 && hmVisited.get(cc) == true) return false;   //Loop exist
    		
    		if (!hmVisited.get(cc)) {
    			boolean bRet = dfsFindOrder(hmGraph, hmVisited, cc, lstOrder);
    			if (bRet == false) return false;
    		}
    	}
    	
    	//Before adding c, its adjacents node (i.e. the character comes after it has been added)
    	lstOrder.add(c);
    	
    	return true;
    }
    
	
/*	
    public String alienOrder(String[] words) {
        int charOccur[] = new int[26];
        if (words == null || words.length == 0) return "";
        Map<Character, Integer> hmRoundOrder = new HashMap<Character, Integer>();
        List<Character> lstOrderTotal = new ArrayList<Character>();
        
        StringBuilder sb = new StringBuilder();
        int n = words.length;
        int i;
        int charIdx = 0;
        char cprev = 0;
        char ccur = 0;
        int norder = 0;
        
        List<List<Integer>> lstlstCommonIdx = new ArrayList<List<Integer>>();
        List<List<Character>> lstlstRelativeOrder = new ArrayList<List<Character>>();
        
    	cprev = 0;
    	ccur = 0;
    	norder = 0;

        ccur = words[0].charAt(0);
        lstOrderTotal.add(ccur);
        cprev = ccur;
        List<Integer> lstCommon = new ArrayList<Integer>();
        lstCommon.add(0);
        
    	for (i=1; i<n; i++) {
    		ccur = words[i].charAt(0);
    		
    		if (ccur != cprev) {
    			if (lstOrderTotal.contains(ccur)) return "";
    			
    			lstOrderTotal.add(ccur);
    			
    			if (lstCommon.size() > 1) {
    				lstlstCommonIdx.add(lstCommon);
    			}
    			
    			lstCommon = new ArrayList<Integer>();
    			lstCommon.add(i);
    		} else {
    			lstCommon.add(i);
    		}
            
    	}
        	
    	
    	for (List<Integer> lstCommonIdx:lstlstCommonIdx) {
    		charIdx = 1; 
    		
    		
    	}
        
        
        
    }
*/

}
