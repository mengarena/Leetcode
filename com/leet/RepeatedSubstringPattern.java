/*
459. Repeated Substring Pattern

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.

Example 1:
Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.

Example 2:
Input: "aba"
Output: False

Example 3:
Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
*/

//Google,Amazon
//Easy

public class Solution {
    public boolean repeatedSubstringPattern(String str) {
        if (str.isEmpty()) return true;
        int n = str.length();
        int i = 0;
        boolean bPossible = false;
        
        for (i=1; i<=n/2; i++) {
            if ((n % i) != 0) continue;
            
            String seed = str.substring(0, i);
            int startPos = i;
            
            bPossible = true;
            
            while (startPos < n) {
                String tmp = str.substring(startPos, startPos+i);
                if (!tmp.equals(seed)) {
                    bPossible = false;
                    break;
                }
                startPos += i;
            }
            
            if (bPossible) break;
        }
        
        return bPossible;
    }
}

