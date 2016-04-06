package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a string s, return all the palindromic permutations (without duplicates) of it. 
//		Return an empty list if no palindromic permutation could be form.
//
//For example:
//
//Given s = "aabb", return ["abba", "baab"].
//
//Given s = "abc", return [].
//
//Hint:
//
//If a palindromic permutation exists, we just need to generate the first half of the string.
//To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.

public class PalindromePermutationII {

	public PalindromePermutationII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String s = "aabbcc";
		
		List<String> lstPP = generatePalindromes(s);
		
		for (String ss:lstPP) System.out.println(ss);
		
		System.out.println();
	}
	
	
	//AC: 66%
    public List<String> generatePalindromes(String s) {
    	List<String> lstPalindromes = new ArrayList<String>();
    	if (s == null || s.length() == 0) return lstPalindromes;
    	int n = s.length();
    	if (n == 1) {
    		lstPalindromes.add(s);
    		return lstPalindromes;
    	}
        char[] carr = s.toCharArray();
        int narrCnt[] = new int[256];
        int nOddCnt = 0;
        int nOddIdx = 0;
        int i,j;
        
        for (i=0; i<n; i++) narrCnt[carr[i]]++;
        
        for (i=0; i<256; i++) {
        	if (narrCnt[i] % 2 == 1) {
        		nOddCnt++;
                nOddIdx = i;       		
        		if (nOddCnt > 1) return lstPalindromes;
        	}
        }
        
        char[] carrHalf = new char[n/2];
        int nLen = 0;
        
        for (i=0; i<256; i++) {
        	for (j=0; j<narrCnt[i]/2; j++) {
        		carrHalf[nLen++] = (char) i;
        	}
        }
        
        String sOddPart = "";
        if (nOddCnt == 1) sOddPart = "" + (char) nOddIdx;
        boolean bVisited[] = new boolean[nLen];
        StringBuilder sb = new StringBuilder();
        
        getPermutation(lstPalindromes, carrHalf, nLen, 0, bVisited, sb, sOddPart);
        
        return lstPalindromes;
        
    }

    private void getPermutation(List<String> lstPalindromes, char[] carrHalf, int nLen, int nStart, boolean[] bVisited, StringBuilder sb, String sOddPart) {
    	if (nLen == nStart) {
    		lstPalindromes.add(sb.toString() + sOddPart + sb.reverse().toString());
    		return;
    	}
    	
    	int i = 0;
    	
    	while (i < nLen) {
    		if (!bVisited[i]) {
    			bVisited[i] = true;
    			
    			String sOrg = sb.toString();
    			sb.append(carrHalf[i]);
    			
    			getPermutation(lstPalindromes, carrHalf, nLen, nStart+1, bVisited, sb, sOddPart);
    			
    			sb.replace(0, sb.length(), sOrg);
    			
    			bVisited[i] = false;
    			
    			while (i < nLen-1 && carrHalf[i+1] == carrHalf[i]) i++;
    		}
    		
    		i++;
    	}
    }
    
}
