package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
//
//You have the following 3 operations permitted on a word:
//
//a) Insert a character
//b) Delete a character
//c) Replace a character

public class EditDistance {

	public EditDistance() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		String word1 = "zoologicoarchaeologist";
		String word2 = "zoogeologist";
//		String word1 = "zoolog";
//		String word2 = "zoog";

//		String word1 = "sea";
//		String word2 = "eat";
	
//		String word1 = "mart";
//		String word2 = "karma";
		
//		String word1 = "dinitrophenylhydrazine";
//		String word2 = "acetylphenylhydrazine";
		
		System.out.println("Distance = " + minDistance(word1, word2));
	}

	
	//Strategy: DTW
    public int minDistance(String word1, String word2) {
        if ((word1 == null && word2 == null) || (word1.length() == 0 && word2.length() == 0))  return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
    
        int i, j;
        int[][] narrarrDist = new int[n1+1][n2+1];  //Min steps by i, j in word1, word2
        int nCost = 0;
        
        for (i=1; i<=n1; i++) narrarrDist[i][0] = i;   //Corresponding to:  if word2 = ""; the edit distance to each position of word1
        for (j=1; j<=n2; j++) narrarrDist[0][j] = j;
                
        narrarrDist[0][0] = 0;
        
        int nMin = 0;
        
        for (i=1; i<=n1; i++) {
        	for (j=1; j<=n2; j++) {
        		
        		if (word1.charAt(i-1) == word2.charAt(j-1)) {
        			nCost = 0;
        		} else {
        			nCost = 1;
        		}
        		
        		//Here, slightly different from raw DTW algorithm
        		//If use narrarrDist[i-1][j], corresponds to insert one along j (or say, delete one along i), 
        		//     i.e. word1[0,i-1] is converted to word2[0,j], so there word1[i] need to be matched, so need one step (either insert one in word2, or delete one from word1)
        		//If use narrarrDist[i][j-1], corresponds to delete one along j (or say, insert one along i)
        		//If use narrarrDist[i-1][j-1], corresponds to change both (i.e. replace) or do nothing (based on the cost)
        		nMin = Math.min(narrarrDist[i-1][j]+1, Math.min(narrarrDist[i][j-1]+1, narrarrDist[i-1][j-1] + nCost));
        		
        		narrarrDist[i][j] = nMin;
        	}
        }
        
        return narrarrDist[n1][n2];
    }
	
	
/*	
    public int minDistance(String word1, String word2) {
        if ((word1 == null && word2 == null) || (word1.length() == 0 && word2.length() == 0))  return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
                
        if ((n1 == n2) && (word1.compareTo(word2) == 0)) return 0;
     
        int nMax = Math.max(n1,  n2);
        int nDistance = nMax;
        
        List<Character> lstWord1 = new ArrayList<Character>();
        List<Character> lstWord2 = new ArrayList<Character>();
        int i;
        
        for (i=0; i<n1; i++) lstWord1.add(word1.charAt(i));
        for (i=0; i<n2; i++) lstWord2.add(word2.charAt(i));
        
      //  for (i=0; i<n1; i++) {
        	List<Character> lstWord1Back = new ArrayList<Character>(lstWord1);
        	List<Character> lstWord2Back = new ArrayList<Character>(lstWord2);
        	
        	nDistance = Math.min(nDistance, minDistanceHH(lstWord1, 0, lstWord2));
        	
        	lstWord1 = new ArrayList<Character>(lstWord1Back);
        	lstWord2 = new ArrayList<Character>(lstWord2Back);
      //  }
        
      //  for (i=0; i<n2; i++) {
        //	List<Character> lstWord1Back = new ArrayList<Character>(lstWord1);
        //	List<Character> lstWord2Back = new ArrayList<Character>(lstWord2);
        	
        	nDistance = Math.min(nDistance, minDistanceHH(lstWord2, 0, lstWord1));
        	
        //	lstWord1 = new ArrayList<Character>(lstWord1Back);
        //	lstWord2 = new ArrayList<Character>(lstWord2Back);
       // }
        
        return nDistance;
        
    }

	
    private int minDistanceHH(List<Character>lstWord1, int nStartPos, List<Character> lstWord2) {
    	if (nStartPos == lstWord1.size()) return lstWord2.size();
    	int nDistance = 0;
    	int nPrevPos2 = 0;
    	int nPos1 = nStartPos;
    	int nIdx = 0;
    	char cElm = 0;
    	int nFirst1 = -1;
    	int nLast1 = -1;
    	int nFirst2 = -1;
    	int nLast2 = -1;
    	int nTmpCnt = 0;
    	
    	while (nPos1 < lstWord1.size() && !lstWord1.isEmpty() && !lstWord2.isEmpty()) {
    		cElm = lstWord1.get(nPos1);
    		nIdx = lstWord2.indexOf(cElm);
    		
    		while (nIdx != -1 && nIdx < nPrevPos2) {
    			lstWord2.remove(nIdx);
    			nPrevPos2--;
    			if (nIdx < nFirst2) {
    				nFirst2--;
    				nLast2--;
    			}
    			nDistance++;
    			nIdx = lstWord2.indexOf(cElm);
    		}
    		
    		if (nIdx >= nPrevPos2) {
    			if (nFirst1 == -1) nFirst1 = nPos1;
    			nLast1 = nPos1;
    			
    			if (nFirst2 == -1) nFirst2 = nIdx;
    			nLast2 = nIdx;
    			
    			lstWord2.remove(nIdx);
    			lstWord1.remove(nPos1);
    			nDistance = nTmpCnt + nDistance;
    			nTmpCnt = 0;
    			nPrevPos2 = nIdx;
    		} else {
    			nTmpCnt++;
    			nPos1++;
    		}
    	}
    	
    	if (nFirst1 > -1) {
    		return nDistance + Math.max(nFirst1, nFirst2) + Math.max(lstWord1.size() - nLast1, lstWord2.size() - nLast2);
    	} else {
    		return Math.max(lstWord1.size(), lstWord2.size());
    	}
    }
*/    
    
    
    
    
    
	//Function is correct, but Exceed time limit
    public int minDistanceB(String word1, String word2) {
        if ((word1 == null && word2 == null) || (word1.length() == 0 && word2.length() == 0))  return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
                
        if ((n1 == n2) && (word1.compareTo(word2) == 0)) return 0;
        
        int nMax = Math.max(n1,  n2);
        int nDistance = nMax;
                    
        nDistance = minDistanceHelper(word1, word2, nMax);
        
        return nDistance;
    }

	
    private int minDistanceHelper(String word1, String word2, int nMaxLen) {
        if ((word1 == null && word2 == null) || (word1.length() == 0 && word2.length() == 0))  return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
                
        if ((n1 == n2) && (word1.compareTo(word2) == 0)) return 0;
        int nMin = Math.max(n1, n2) - Math.min(n1, n2);
        
        if (nMin == nMaxLen) return nMin;
        if (nMin > nMaxLen) return Integer.MAX_VALUE;
        
        int nDistance = 0;
        int i = 0, j = 0;
        
    	if (word1.charAt(i) == word2.charAt(j)) {
    		while (i < n1 && j < n2 && word1.charAt(i) == word2.charAt(j)) {
    			i++;
    			j++;
    		}
    		
        	nDistance = minDistanceHelper(word1.substring(i), word2.substring(j), nMaxLen-i);
        } else {
        	nDistance = minDistanceHelper(word1.substring(1), word2, Math.max(n1-1, n2));
        	nDistance = Math.min(nDistance, minDistanceHelper(word2.substring(1), word1, Math.max(n1, n2-1)));
        	nDistance = Math.min(nDistance, minDistanceHelper(word1.substring(1), word2.substring(1), nMaxLen-1));
        	if (nDistance < Integer.MAX_VALUE) nDistance++;
        }
        
        return nDistance;
    }
   
    
    
    
    
	//Function is correct, but Exceed time limit
    public int minDistanceAA(String word1, String word2) {
        if ((word1 == null && word2 == null) || (word1.length() == 0 && word2.length() == 0))  return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
                
        if ((n1 == n2) && (word1.compareTo(word2) == 0)) return 0;
        
        int nMax = Math.max(n1,  n2);
        int nDistance = nMax;
                    
        if (word1.charAt(0) == word2.charAt(0)) {
        	nDistance = minDistanceAA(word1.substring(1), word2.substring(1));
        } else {
        	nDistance = 1+minDistanceAA(word1.substring(1), word2);
        	nDistance = Math.min(nDistance, 1 + minDistanceAA(word2.substring(1), word1));
        	nDistance = Math.min(nDistance, 1 + minDistanceAA(word1.substring(1), word2.substring(1)));
        }
        
        return nDistance;
    }
    
    
    
	
/*
    public int minDistance(String word1, String word2) {
        if ((word1 == null && word2 == null) || (word1.length() == 0 && word2.length() == 0))  return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
    
        int i, j;
        int[][] narrarrDist = new int[n1+1][n2+1];
        int nCost = 0;
        for (i=1; i<=n1; i++) narrarrDist[i][0] = Integer.MAX_VALUE;
        for (j=1; j<=n2; j++) narrarrDist[0][j] = Integer.MAX_VALUE;
                
        narrarrDist[0][0] = 0;
        
        int nMin = 0;
        
        for (i=1; i<=n1; i++) {
        	for (j=1; j<=n2; j++) {
        		if (word1.charAt(i-1) == word2.charAt(j-1)) {
        			nCost = 0;
        		} else {
        			nCost = 1;
        		}
        		
        		nMin = Math.min(narrarrDist[i-1][j], Math.min(narrarrDist[i][j-1], narrarrDist[i-1][j-1]));
        		
        		if (nMin == narrarrDist[i-1][j-1]) {
        			narrarrDist[i][j] = nCost + nMin;
        		} else {
        			narrarrDist[i][j] = nCost + nMin + 1;
        		}
        	}
        }
        
        return narrarrDist[n1][n2];
    }
*/    
    
    
/*
    for (i=1; i<=n1; i++) {
    	for (j=1; j<=n2; j++) {
    		if (word1.charAt(i-1) == word2.charAt(j-1) && !bUsed1[i-1] && !bUsed2[j-1]) {
    			nCost = 0;
    			bUsed1[i-1] = true;
    			bUsed2[j-1] = true;
    		} else {
    			nCost = 1;
    		}
    		
    		narrarrDist[i][j] = nCost + Math.min(narrarrDist[i-1][j], Math.min(narrarrDist[i][j-1], narrarrDist[i-1][j-1]));
    	}
    }
*/            
    

/*
 
        while (i <= n1 && j <= n2) {
    		if (word1.charAt(i-1) == word2.charAt(j-1)) {
    			nCost = 0;
    		} else {
    			nCost = 1;
    		}
    		
    		int nMin = Math.min(narrarrDist[i-1][j], Math.min(narrarrDist[i][j-1], narrarrDist[i-1][j-1]));
    		narrarrDist[i][j] = nCost + nMin;
    		nTmpCost = narrarrDist[i][j];
    		
    		if (nMin == narrarrDist[i-1][j-1]) {
    			i++;
    			j++;
    		} else if (nMin == narrarrDist[i][j-1]) {
    			i++;
    		} else {
    			j++;
    		}
        }
        
        //if (i < n1) narrarrDist[n1][n2] = nTmpCost + n1-i;
        //if (j < n2) narrarrDist[n1][n2] = nTmpCost + n2-j;
        if (i < n1 || j < n2) narrarrDist[n1][n2] = nTmpCost + 1;
     
 */
    
    
	
	
	

    
    
    
    
    public int minDistanceA(String word1, String word2) {
        if ((word1 == null && word2 == null) || (word1.length() == 0 && word2.length() == 0))  return 0;
        int n1 = word1.length();
        int n2 = word2.length();
        
        if (n1 == 0) return n2;
        if (n2 == 0) return n1;
                
        if ((n1 == n2) && (word1.compareTo(word2) == 0)) return 0;
        
        int nMax = Math.max(n1,  n2);
        int nDistance = nMax;
        
        int nP1 = 0, nP2 = 0;
        int i;
        
        if (n1 < n2) {
        	while (nP1 < n1 && nP2 < n2 && n2 - nP2 >= n1 - nP1) {
        		if (word1.charAt(nP1) == word2.charAt(nP2)) {
        			nDistance--;
        			nP1++;
        			nP2++;
        		} else {
        			nP2++;
        		}
        	}
        	
        } else if (n1 > n2) {
        	while (nP1 < n1 && nP2 < n2 && n1 - nP1 >= n2 - nP2) {
        		if (word2.charAt(nP2) == word1.charAt(nP1)) {
        			nDistance--;
        			nP1++;
        			nP2++;
        		} else {
        			nP1++;
        		}
        	}
        	
        } else {
        	for (i=1; i<n1; i++) {
        		nDistance = Math.min(minDistance(word1.substring(i), word2)+i, nDistance);
        	}
        	
        	for (i=1; i<n2; i++) {
        		nDistance = Math.min(minDistance(word2.substring(i), word1)+i, nDistance);
        	}
        }
                
        return nDistance;	
    }
    	
}
