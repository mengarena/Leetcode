/*
467. Unique Substrings in Wraparound String

Consider the string s to be the infinite wraparound string of "abcdefghijklmnopqrstuvwxyz", 
so s will look like this: "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".

Now we have another string p. 
Your job is to find out how many unique non-empty substrings of p are present in s. 
In particular, your input is the string p and you need to output the number of different non-empty substrings of p in the string s.

Note: p consists of only lowercase English letters and the size of p might be over 10000.

Example 1:
Input: "a"
Output: 1

Explanation: Only the substring "a" of string "a" is in the string s.

Example 2:
Input: "cac"
Output: 2
Explanation: There are two substrings "a", "c" of string "cac" in the string s.

Example 3:
Input: "zab"
Output: 6
Explanation: There are six substrings "z", "a", "b", "za", "ab", "zab" of string "zab" in the string s.
*/

//MAQ Software
//Medium

public class Solution {
	//ACC
	//To get the maximum continuous length ending at each letter
	//For example, there are two substrings ending at "f", cdef, abcdef, we only need to consider "abcdef", because it contains all the substrings of "cdef"
	//For a substring "abcdef", the number of unique substring (considering the ending letter) is the length of the substring
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.isEmpty()) return 0;
        int maxCurLen = 0;
        int[] lenByLetter = new int[26];  //Max continuous length by ith letter 
        int i;
        
        for (i=0; i<p.length(); i++) {
            if (i > 0 && (p.charAt(i) - p.charAt(i-1) == 1 || p.charAt(i-1) - p.charAt(i) == 25)) {
                maxCurLen++;
            } else {
                maxCurLen = 1;
            }
            
            int idx = p.charAt(i)-'a';
            lenByLetter[idx] = Math.max(lenByLetter[idx], maxCurLen);
        }
        
        int total = 0;
        for (i=0; i<26; i++) {
            total += lenByLetter[i];
        }
        
        return total;        
    }
}

