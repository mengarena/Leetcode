/*

647. Palindromic Substrings

Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings 
even they consist of same characters.

Example 1:

Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
 
Example 2:

Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 

Note:

The input string length won't exceed 1000.

Medium
*/


class Solution {
public:

    // 79%
    int countSubstrings(string s) {
        int n = s.length();
        bool dp[n][n] = {false};
                
        int count = 0;
        
        // The order of these "for" are important
        for (int len=1; len<=n; ++len) {
            for (int i=0; i<n; i++) {   
                int j = i+len-1;
                if (j >= n) break;
                if (len <= 2) {
                    if (s[i] == s[j]) {
                        dp[i][j] = true;
                        count++;
                    }
                } else {
                    if (s[i] == s[j] && dp[i+1][j-1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }
            }
        }
        
        return count;   
    }
};
