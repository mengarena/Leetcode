package com.leet;

//Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
//
//Examples:
//
//s = "leetcode"
//return 0.
//
//s = "loveleetcode",
//return 2.
//		
//Note: You may assume the string contain only lowercase letters.


//Amazon, Bloomberg, Microsoft
//Easy
public class FirstUniqueCharacterInAString {

	public FirstUniqueCharacterInAString() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:
	//Strategy: For each character, if meet onces, set its index; if meet more than twice, set it to -1
    public int firstUniqChar(String s) {
        if (s == null || s.isEmpty()) return -1;
        int n = s.length();
        int i;
        int idx = Integer.MAX_VALUE;
        int[] idxArr = new int[26];  //Initial 0
        
        for (i=0; i<n; i++) {
            if (idxArr[s.charAt(i)-'a'] == 0) {
                idxArr[s.charAt(i)-'a'] = i+1;
            } else if (idxArr[s.charAt(i)-'a'] > 0) {
                idxArr[s.charAt(i)-'a'] = -1;
            }
        }
        
        for (i=0; i<26; i++) {
            if (idxArr[i] > 0 && idxArr[i] < idx) {
                idx = idxArr[i];
            }
        }
        
        if (idx == Integer.MAX_VALUE) return -1;
        
        return idx-1;   //Need to -1 to be the actual index
    }
}
