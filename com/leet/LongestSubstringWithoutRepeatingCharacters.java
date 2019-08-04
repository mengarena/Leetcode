package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a string, find the length of the longest substring without repeating characters. 
//For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. 
//For "bbbbb" the longest substring is "b", with the length of 1.

//Bloomberg
public class LongestSubstringWithoutRepeatingCharacters {

	public LongestSubstringWithoutRepeatingCharacters() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		//String s = "abcabcbb";
		String s = "bababbb";
		
		System.out.println("Len of Longest = " + lengthOfLongestSubstring(s));
	}
	
	
    //Also could use HashMap<Character, Integer>  (character, position), and needs to remember the starting position
    public int lengthOfLongestSubstring(String s) {
    	if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        List<Character> lstSubstr = new ArrayList<Character>();
        int nMaxLen = 0;
        int nIdx = -1;
        int i;
        
        for (i=0; i<n; i++) {
        	if (lstSubstr.isEmpty()) {
        		nMaxLen = Math.max(nMaxLen, 1);
        		lstSubstr.add(s.charAt(i));
        	} else {
        		nIdx = lstSubstr.indexOf(s.charAt(i));
        		if (nIdx == -1) {
        			lstSubstr.add(s.charAt(i));
        		} else {
        			nMaxLen = Math.max(nMaxLen, lstSubstr.size());
        			//Find a duplicate character, 
				//remove all the heading character including the duplicate character in the list
        			for (int j=0; j<=nIdx; j++) {
        				lstSubstr.remove(0);
        			}
        			lstSubstr.add(s.charAt(i));
        		}
        	}
        }
    	
        nMaxLen = Math.max(nMaxLen, lstSubstr.size());
        
        return nMaxLen;
    }	
}
