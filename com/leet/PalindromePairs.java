package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Given a list of unique words. 
//Find all pairs of distinct indices (i, j) in the given list, 
//so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
//
//Example 1:
//	
//Given words = ["bat", "tab", "cat"]
//Return [[0, 1], [1, 0]]
//The palindromes are ["battab", "tabbat"]
//		
//Example 2:
//	
//Given words = ["abcd", "dcba", "lls", "s", "sssll"]
//Return [[0, 1], [1, 0], [3, 2], [2, 4]]
//The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

//Google
public class PalindromePairs {

	public PalindromePairs() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		List<List<Integer>> pairs = new LinkedList<>();
		//String[] words = {"cacbmdg", "mb"};
		String[] words = {"abcd","dcba","lls","s","sssll"};
		pairs = palindromePairs(words);
		
		System.out.println();
	}
	
	
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> lstlstPairs = new ArrayList<List<Integer>>();
        if (words == null || words.length < 2) return lstlstPairs;
        int n = words.length;
        HashMap<String, Integer> hmStrIdx = new HashMap<String, Integer>();  //String, Index
        int i,j;
        int nIdx;
        String str1, str2, stmp;
        
        for (i=0; i<n; i++) hmStrIdx.put(words[i], i);
        
        for (i=0; i<n; i++) {
            for (j=0; j<=words[i].length(); j++) {
                str1 = words[i].substring(0, j);
                str2 = words[i].substring(j);
                
                if (isPalindrome(str1)) {
                    stmp = new StringBuilder(str2).reverse().toString();
                    nIdx = -1;
                    if (hmStrIdx.containsKey(stmp)) nIdx = hmStrIdx.get(stmp);
                    if (nIdx != -1 && nIdx != i) {
                        List<Integer> lstPair = new ArrayList<Integer>();
                        lstPair.add(nIdx);
                        lstPair.add(i);
                        lstlstPairs.add(lstPair);
                    }
                    
                }
                
                if (str2.length() > 0 && isPalindrome(str2)) {   
                	//The above "if" already process the division: "" + raw string (str2); here if str2 = "", it becomes raw string(str1) + "", 
                	//basically it is the same, so don't need to process this case again 
                    stmp = new StringBuilder(str1).reverse().toString();
                    nIdx = -1;
                    if (hmStrIdx.containsKey(stmp)) nIdx = hmStrIdx.get(stmp);
                    if (nIdx != -1 && nIdx != i) {
                        List<Integer> lstPair = new ArrayList<Integer>();
                        lstPair.add(i);
                        lstPair.add(nIdx);
                        lstlstPairs.add(lstPair);
                    }
                    
                }
            }
        }
        
        return lstlstPairs;
    }
    
    
    private boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        char[] carr = s.toCharArray();
        int i=0, j = carr.length-1;
        
        while (i<j) {
            if (carr[i] != carr[j]) return false;
            i++;
            j--;
        }
        
        return true;
    }
	
}
