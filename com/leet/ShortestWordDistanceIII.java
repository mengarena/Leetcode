package com.leet;

//This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
//
//Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
//word1 and word2 may be the same and they represent two individual words in the list.
//
//For example,
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//Given word1 = ¡°makes¡±, word2 = ¡°coding¡±, return 1.
//Given word1 = "makes", word2 = "makes", return 3.
//
//Note:
//You may assume word1 and word2 are both in the list.

public class ShortestWordDistanceIII {

	public ShortestWordDistanceIII() {
		// TODO Auto-generated constructor stub
	}


    public void run() {
    	
    }

    //Accepted:  58%
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words.length == 2) return 1;
        int nRecentPos1 = Integer.MAX_VALUE;
        int nRecentPos2 = Integer.MAX_VALUE;
        int nDistance = Integer.MAX_VALUE;
        int nTmpDist = 0;
        boolean bSame = word1.equals(word2);
        
        for (int i=0; i<words.length; i++) {
            if (words[i].equals(word1)) {
                nRecentPos1 = i;
                nTmpDist = Math.abs(nRecentPos1 - nRecentPos2);
                if (nTmpDist < nDistance) nDistance = nTmpDist;
                
                if (bSame) nRecentPos2 = nRecentPos1;
            } else if (words[i].equals(word2)) {
                nRecentPos2 = i;
                nTmpDist = Math.abs(nRecentPos1 - nRecentPos2);
                if (nTmpDist < nDistance) nDistance = nTmpDist;
            }
        }
        
        return nDistance;                
    }    
}
