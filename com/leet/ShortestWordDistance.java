package com.leet;

//Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
//For example,
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
//Given word1 = "makes", word2 = "coding", return 1.
//
//Note:
//You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

//Linkedin
//Easy
public class ShortestWordDistance {

	public ShortestWordDistance() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words.length == 2) return 1;
        int nRecentPos1 = Integer.MAX_VALUE;
        int nRecentPos2 = Integer.MAX_VALUE;
        int nDistance = Integer.MAX_VALUE;
        int nTmpDist = 0;
        
        for (int i=0; i<words.length; i++) {
            if (words[i].equals(word1)) {
                nRecentPos1 = i;
                nTmpDist = Math.abs(nRecentPos1 - nRecentPos2);
                if (nTmpDist < nDistance) nDistance = nTmpDist;
            } else if (words[i].equals(word2)) {
                nRecentPos2 = i;
                nTmpDist = Math.abs(nRecentPos1 - nRecentPos2);
                if (nTmpDist < nDistance) nDistance = nTmpDist;
            }
        }
        
        return nDistance;        
    }
	
}
