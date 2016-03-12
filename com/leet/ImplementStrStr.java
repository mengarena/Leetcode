package com.leet;

//Implement strStr().
//
//Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

public class ImplementStrStr {

	public ImplementStrStr() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String haystack = "mengrufeng";
		String needle = "engruadfadfadfad";
		
		System.out.println("Index of " + needle + " : " + strStr(haystack, needle));
	}
	
    public int strStr(String haystack, String needle) {
        int nIdx = -1;
        int nHayLen = 0;
        int nNeedleLen = 0;
        int i, j;
        boolean bRet;
        
        if (haystack == null || needle == null) return -1;
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        
        nHayLen = haystack.length();
        nNeedleLen = needle.length();
        
        for (i=0; i<=nHayLen-nNeedleLen; i++) {
        	bRet = true;
        	
        	for (j=0; j<nNeedleLen; j++) {
        		if (haystack.charAt(i+j) != needle.charAt(j)) {
        			bRet = false;
        			break;
        		}
        	}
        	
        	if (bRet == true) {
        		nIdx = i;
        		break;
        	}
        }
        
        return nIdx;
    }
	
}
