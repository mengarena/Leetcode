package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
//
//Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
//
//The order of output does not matter.
//
//Example 1:
//
//Input:
//s: "cbaebabacd" p: "abc"
//
//Output:
//[0, 6]
//
//Explanation:
//The substring with start index = 0 is "cba", which is an anagram of "abc".
//The substring with start index = 6 is "bac", which is an anagram of "abc".
//
//Example 2:
//
//Input:
//s: "abab" p: "ab"
//
//Output:
//[0, 1, 2]
//
//Explanation:
//The substring with start index = 0 is "ab", which is an anagram of "ab".
//The substring with start index = 1 is "ba", which is an anagram of "ab".
//The substring with start index = 2 is "ab", which is an anagram of "ab".

//Amazon
public class FindAllAnagramsInAString {

	public FindAllAnagramsInAString() {
		// TODO Auto-generated constructor stub
	}

	//ACC
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> lstIdx = new ArrayList<>();
        if (s == null || s.length() < p.length()) return lstIdx;
        int np = p.length();
        int ns = s.length();
        Map<Character, Integer> hm = new HashMap<Character, Integer>(); //letter, count
        int i;
        int startIdx = 0;
        char c = 0;
        int tmpCount = 0;
        
        //Put all letters in p in hm
        for (i=0; i<np; i++) {
            if (!hm.containsKey(p.charAt(i))) {
                hm.put(p.charAt(i), 1);
            } else {
                hm.put(p.charAt(i), hm.get(p.charAt(i))+1);
            }
        }
        
        //Deduct the first np letters in s
        for (i=0; i < np; i++) {
            c = s.charAt(i);
            
            if (!hm.containsKey(c)) {
                hm.put(c, -1);
            } else {
                tmpCount = hm.get(c);
                if (tmpCount == 1) {
                    hm.remove(c);
                } else {
                    hm.put(c, tmpCount-1);
                }
            }
        }
        
        if (hm.isEmpty()) lstIdx.add(0);
        
        //Move the startIdx
        for (startIdx=1; startIdx+np-1 < ns; startIdx++) {
            c = s.charAt(startIdx+np-1);  //Add a new letter at end
            
            if (!hm.containsKey(c)) {
                hm.put(c, -1);
            } else {
                tmpCount = hm.get(c);
                if (tmpCount == 1) {
                    hm.remove(c);
                } else {
                    hm.put(c, tmpCount-1);
                }                
            }
            
            c = s.charAt(startIdx-1);  //Remove the old letter at the head
            
            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            } else {
                tmpCount = hm.get(c);
                if (tmpCount == -1) {
                    hm.remove(c);
                } else {
                    hm.put(c, tmpCount+1);
                }
            }
            
            if (hm.isEmpty()) lstIdx.add(startIdx);
        }
        
        return lstIdx;
    }

}
