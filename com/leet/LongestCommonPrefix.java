package com.leet;

//Write a function to find the longest common prefix string amongst an array of strings.

public class LongestCommonPrefix {

	public LongestCommonPrefix() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String[] sarrStr = {"meng", "m", "meng", "meng"};
		
		System.out.println("Longest Common Prefix String: " + longestCommonPrefix(sarrStr));
	}
	
    public String longestCommonPrefix(String[] strs) {
        String sLCP = "";
        if (strs == null || strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        
        int i, j, n;
        n = strs.length;
        int nShortestIdx = 0;
        int nShortestLen = 0;
        int nTmpLen = 0;
        int nComPreLen = 0;
        char cSingle; 
        boolean bCommon = false;
        nShortestLen = strs[0].length();
        
        //Find the shortest string
        for (i=1; i<n; i++) {
        	nTmpLen = strs[i].length();
        	if (nShortestLen > nTmpLen) {
        		nShortestLen = nTmpLen;
        		nShortestIdx = i;
        	}
        }
        
        if (nShortestLen == 0) return "";
        
        for (i=0; i<nShortestLen; i++) {
        	cSingle = strs[nShortestIdx].charAt(i);
        	bCommon = true; 
        	for (j=0; j<n; j++) {
        		if (j != nShortestIdx) {
        			if (cSingle != strs[j].charAt(i)) {
        				bCommon = false;
        				break;
        			}
        		} 
        	}
        	
        	if (bCommon == true) {
        		nComPreLen = nComPreLen + 1;
        	} else {
        		break;
        	}
        }
        
        sLCP = strs[nShortestIdx].substring(0, nComPreLen);
        return sLCP;
    }
	
}
