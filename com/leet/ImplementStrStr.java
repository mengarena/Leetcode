package com.leet;

//Implement strStr().
//
//Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.


//Facebook, Microsoft, Apple, Pocket Gems
//Easy
public class ImplementStrStr {

	public ImplementStrStr() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String haystack = "mengrufeng";
		String needle = "engruadfadfadfad";
		
		System.out.println("Index of " + needle + " : " + strStr(haystack, needle));
	}
	
	
	//ACC: 25%
	public int strStrK(String haystack, String needle) {
            for (int i=0; ; i++) {
                for (int j=0; ;j++) {
                    if (j == needle.length()) return i;
                
                    if (i+j == haystack.length()) return -1;
                
                    if (needle.charAt(j) != haystack.charAt(i+j)) break; 
                }
            }
        }
	
	
	
	//ACC: 53% 
	//KMP Algorithm:  Refer to https://tekmarathon.com/2013/05/14/algorithm-to-find-substring-in-a-string-kmp-algorithm/
	/**
	 * Pre processes the pattern array based on proper prefixes and proper
	 * suffixes at every position of the array
	 *
	 * @param ptrn
	 *            word that is to be searched in the search string
	 * @return partial match table which indicates
	 */
	public int[] preProcessPattern(char[] ptrn) {
	    int i = 0, j = -1;
	    int ptrnLen = ptrn.length;
	    int[] b = new int[ptrnLen + 1];
	 
	    b[i] = j;
	    while (i < ptrnLen) {            
	         while (j >= 0 && ptrn[i] != ptrn[j]) {
	            // if there is mismatch consider the next widest border
	            // The borders to be examined are obtained in decreasing order from 
	            // the values b[i], b[b[i]] etc.
	            j = b[j];
	        }
	         
	        i++;
	        j++;
	        b[i] = j;
	    }
	    return b;
	}
	
	
	/**
     * Based on the pre processed array, search for the pattern in the text
     *
     * @param text
     *            text over which search happens
     * @param ptrn
     *            pattern that is to be searched
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        if (needle.length() == 0) return 0;
        if (haystack.length() < needle.length()) return -1;
        
    	char[] text = haystack.toCharArray();
    	char[] ptrn = needle.toCharArray();
        int i = 0, j = 0;
        // pattern and text lengths
        int ptrnLen = ptrn.length;
        int txtLen = text.length;
 
        // initialize new array and preprocess the pattern
        int[] b = preProcessPattern(ptrn);
 
        while (i < txtLen) {
            while (j >= 0 && text[i] != ptrn[j]) {
                j = b[j];
            }
            
            i++;
            j++;
 
            // a match is found
            if (j == ptrnLen) {
                //System.out.println("found substring at index:" + (i - ptrnLen));
                return i - ptrnLen;
            }
        }
        
        return -1;
    }	
    
    
    
    
	//Works
    public int strStrA(String haystack, String needle) {
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
