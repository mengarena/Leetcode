package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//This is a follow up of Shortest Word Distance. 
//The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. 
//How would you optimize it?
//
//Design a class which receives a list of words in the constructor, 
//and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.
//
//For example,
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
//Given word1 = "makes", word2 = "coding", return 1.
//
//Note:
//You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

public class ShortestWordDistanceII {

	public ShortestWordDistanceII() {
		// TODO Auto-generated constructor stub
	}

	
	//Accepted: 38%
	Map<String, List<Integer>> hmWordPos = new HashMap<String, List<Integer>>();
	
    public void WordDistance(String[] words) {   //Original should be (constructor): public WordDistance(String[] words) 
        if (words == null || words.length == 0) return;
        int n = words.length;
        
        for (int i=0; i<n; i++) {
        	if (hmWordPos.containsKey(words[i])) {
        		hmWordPos.get(words[i]).add(i);
        	} else {
        		List<Integer> lstPos = new ArrayList<Integer>();
        		lstPos.add(i);
        		hmWordPos.put(words[i], lstPos);
        	}
        }
    }

    
    
    public int shortest(String word1, String word2) {
    	if (hmWordPos.size() < 2) return Integer.MAX_VALUE;
    	
        List<Integer> lstPos1 = hmWordPos.get(word1);
        List<Integer> lstPos2 = hmWordPos.get(word2);
        
        return minDist(lstPos1, lstPos2);
    }

    //Faster: %75   O(n+m)
    private int minDist(List<Integer> lstPos1, List<Integer> lstPos2) {
    	int nMinDistance = Integer.MAX_VALUE;
    	int i = 0, j = 0;
    	int nPos1, nPos2;
    	
    	while (i < lstPos1.size() && j < lstPos2.size()) {
    		nPos1 = lstPos1.get(i);
    		nPos2 = lstPos2.get(j);
    		
    		nMinDistance = Math.min(nMinDistance, Math.abs(nPos1-nPos2));
    		if (nMinDistance == 1) break;
    		
    		if (nPos1 < nPos2) {
    			i++;
    		} else {
    			j++;
    		}
    	}
    	   	
    	return nMinDistance;
    }

    //Works O(n*m)
    private int minDistA(List<Integer> lstPos1, List<Integer> lstPos2) {
    	int nMinDistance = Integer.MAX_VALUE;
    	
    	for (Integer i:lstPos1) {
    		for (Integer j:lstPos2) {
    			nMinDistance = Math.min(nMinDistance, Math.abs(i-j));
    			if (nMinDistance == 1) break;
    		}
    	}
    	
    	return nMinDistance;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");