package com.leet;

//Given a string that consists of only uppercase English letters, 
//you can replace any letter in the string with another letter at most k times. 
//Find the length of a longest substring containing all repeating letters you can get after performing the above operations.
//
//Note:
//Both the string's length and k will not exceed 104.
//
//Example 1:
//
//Input:
//s = "ABAB", k = 2
//
//Output:
//4
//
//Explanation:
//Replace the two 'A's with two 'B's or vice versa.
//
//Example 2:
//
//Input:
//s = "AABABBA", k = 1
//
//Output:
//4
//
//Explanation:
//Replace the one 'A' in the middle with 'B' and form "AABBBBA".
//The substring "BBBB" has the longest repeating letters, which is 4.

//Pocket Gems
public class LongestRepeatingCharacterReplacement {

	public LongestRepeatingCharacterReplacement() {
		// TODO Auto-generated constructor stub
	}

	//ACC
	//Replace any letter, not one letter
	//The statement of this question is somehow misleading
	//It is allowed to replace any letter with any other letter
	//Not a one-one replacement
	//Strategy: Using a sliding window
    public int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int start = 0;
        int end = 0;
        int maxLen = Integer.MIN_VALUE;
        int maxCount = Integer.MIN_VALUE;
        int[] arrCnt = new int[26];
        
        for (end=0; end<s.length(); end++) {
            maxCount = Math.max(maxCount, ++arrCnt[s.charAt(end)-'A']);   //Remember the count of the most frequent letter
            
            while (end-start+1 - maxCount > k) {   //The number of letters which are different from the most frequent letter, 
            	                                   //at least has this many different (from most frequent) letters
                arrCnt[s.charAt(start)-'A']--;
                start++;
            }
            
            maxLen = Math.max(maxLen, end-start+1);
        }
        
        return maxLen;
    }
}
