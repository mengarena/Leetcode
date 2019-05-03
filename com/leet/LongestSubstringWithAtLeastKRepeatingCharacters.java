package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Find the length of the longest substring T of a given string (consists of lowercase letters only) 
//such that every character in T appears no less than k times.
//
//Example 1:
//
//Input:
//s = "aaabb", k = 3
//
//Output:
//3
//
//The longest substring is "aaa", as 'a' is repeated 3 times.
//Example 2:
//
//Input:
//s = "ababbc", k = 2
//
//Output:
//5
//
//The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

//Baidu
public class LongestSubstringWithAtLeastKRepeatingCharacters {

	public LongestSubstringWithAtLeastKRepeatingCharacters() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:  5 ms
	//Strategy: Go through the string and calculate the count for each character
	//If one character has less than k times, the whole string could be broken into several segments by this character
	//Another trick is to use the HashMap to remember processed string
	//Average O(nlogn), worst O(n^2)
	//In the average case, because we divide evenly, so T(n) = s * T(n/s) + n and T(n) = O(nlogn); 
	//if we divide very pathologically, then T(n) = T(n - 2) + n, so T(n) = O(n^2).
    public int longestSubstring(String s, int k) {
	    return longestSubstring(s, k, new HashMap<String,Integer>());
    }
    
    public int longestSubstring(String s, int k, Map<String,Integer> longestMap) {
    	if (s == null || s.isEmpty() || k > s.length()) return 0;
    	
    	if (longestMap.containsKey(s)) {
    		return longestMap.get(s);
    	}
    	
    	int[] charCnt = new int[26];
    	int i;
    	int n = s.length();
    	
    	for (i=0; i<n; i++) charCnt[s.charAt(i)-'a']++;
    	
    	boolean bValid = true;
    	
    	for (i=0; i<26; i++) {
    	    if (charCnt[i] > 0 && charCnt[i] < k) bValid = false;
    	}
    	
    	if (bValid == true) {
    	    longestMap.put(s, n);
    	    return n;
    	}
    	
    	int max = 0;
    	int start = 0;
    	
    	//Divide the string based on every invalid character
    	for (i=0; i<n; i++) {
    	    if (charCnt[s.charAt(i)-'a'] < k) {
    	        max = Math.max(max, longestSubstring(s.substring(start, i), k, longestMap));
    	        start = i+1;
    	    }
    	}
    	
    	max = Math.max(max, longestSubstring(s.substring(start), k, longestMap));
    	
    	longestMap.put(s, max);
    	return max;
    }
	
    
    //ACC: 6 ms
    public int longestSubstringB(String s, int k) {
	    return longestSubstring(s, k, new HashMap<String,Integer>()); 
	                                                      // The hashmap record: string and len of its longest substr
    }
    
    public int longestSubstringB(String s, int k, Map<String,Integer> longestMap) {
    	if (s == null || s.isEmpty() || k > s.length() || k == 0) return 0;
    	
    	if (longestMap.containsKey(s)) {
    		return longestMap.get(s);
    	}
    	
    	int[] charCnt = new int[26];
    	int i;
    	int n = s.length();
    	
    	for (i=0; i<n; i++) charCnt[s.charAt(i)-'a']++;
    	
    	List<Integer> lstPos = new ArrayList<Integer>();
    	
    	//Remember the position to break the string
    	for (i=0; i<n; i++) {
    	    if (charCnt[s.charAt(i)-'a'] < k) lstPos.add(i);
    	}
    	
    	if (lstPos.size() == 0) {
    	    longestMap.put(s, n);
    	    return n;
    	}
    	
    	int max = 0;
        int start = 0;
        int end = 0;
        
    	lstPos.add(0, -1);
    	lstPos.add(n);
    	
    	for (i=1; i<lstPos.size(); i++) {
            start = lstPos.get(i-1) + 1;
            end = lstPos.get(i);
            
            max = Math.max(max, longestSubstring(s.substring(start, end), k, longestMap));
    	}
    	
    	longestMap.put(s, max);
    	
    	return max;
    }
    
    
	//ACC:  5ms
    public int longestSubstringK(String s, int k) {
    	if (s == null || s.isEmpty() || k > s.length() || k == 0) return 0;
    	
    	int[] charCnt = new int[26];
    	int i;
    	int n = s.length();
    	
    	for (i=0; i<n; i++) charCnt[s.charAt(i)-'a']++;
    	
    	List<Integer> lstPos = new ArrayList<Integer>();
    	
    	for (i=0; i<n; i++) {
    	    if (charCnt[s.charAt(i)-'a'] < k) lstPos.add(i);
    	}
    	
    	if (lstPos.size() == 0) return n;
    	
    	int max = 0;
        int start = 0;
        int end = 0;
        
    	lstPos.add(0, -1);
    	lstPos.add(n);
    	
    	for (i=1; i<lstPos.size(); i++) {
            start = lstPos.get(i-1) + 1;
            end = lstPos.get(i);
            
            max = Math.max(max, longestSubstring(s.substring(start, end), k));
    	}
    	
    	return max;
    }
    
    
	
	//ACC:  25ms
    public int longestSubstringA(String s, int k) {
	    return longestSubstring(s, k, new HashMap<String,Integer>());
    }
    
    public int longestSubstringA(String s, int k, Map<String,Integer> longestMap) {
    	if (s == null || s.isEmpty() || k > s.length()) return 0;
    	
    	if(longestMap.containsKey(s)) {
    		return longestMap.get(s);
    	}
    	
    	Map<Character,Integer> charCounts = new HashMap<>();
    	
    	char[] charsS = s.toCharArray();
    	//Count the count of each character's occurrence
    	for(char c: charsS) {
    		if(charCounts.containsKey(c)) {
    			charCounts.put(c, charCounts.get(c)+1);
    		} else {
    			charCounts.put(c, 1);
    		}
    	}
    	
    	int max = s.length();
    	
    	//Find the character which has less than k times and break the string by the character
    	//If no such kind of character exist, the whole string is valid
    	for(char c: charsS) {
    		if(charCounts.get(c)<k) {
    			max = 0;
    			String[] tokens = s.split(String.valueOf(c));   //break the string into segments by the character
    			
    			for(String t: tokens) {
    				max = Math.max(max, longestSubstring(t, k, longestMap));
    			}
    			
    			break;
    		}
    	}
    	
    	longestMap.put(s, max);
    	return max;
    }
    
    
    //ACC:  59ms
    //No recursive
    //For each h, apply two pointer technique to find the longest substring with at least K repeating characters and 
    //the number of unique characters in substring is h.
    //O(mn) or more (depends on complexicity of Arrays.fill()),  m <= 26  
    public int longestSubstringM(String s, int k) {
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        int h, i, j, idx, max = 0, unique, noLessThanK;
        
        for (h = 1; h <= 26; h++) {
            Arrays.fill(counts, 0);
            i = 0; //Left pointer
            j = 0; //Right pointer
            unique = 0;
            noLessThanK = 0;
            
            while (j < str.length) {
                if (unique <= h) {
                    idx = str[j] - 'a';
                    if (counts[idx] == 0)
                        unique++;
                    
                    counts[idx]++;
                    if (counts[idx] == k)
                        noLessThanK++;
                    j++;
                }
                else {
                    idx = str[i] - 'a';
                    if (counts[idx] == k)
                        noLessThanK--;
                    
                    counts[idx]--;
                    if (counts[idx] == 0)
                        unique--;
                    i++;
                }
                
                if (unique == h && unique == noLessThanK)
                    max = Math.max(j - i, max);
            }
        }
        
        return max;
    }

}
