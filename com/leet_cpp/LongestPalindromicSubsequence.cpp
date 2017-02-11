/*
Given a string s, find the longest palindromic subsequence's length in s. 
You may assume that the maximum length of s is 1000.

Example 1:
Input:

"bbbab"
Output:
4
One possible longest palindromic subsequence is "bbbb".

Example 2:
Input:

"cbbd"
Output:
2
One possible longest palindromic subsequence is "bb".

*/

//Medium
//Amazon, Uber

class Solution {
public:

    int longestPalindromeSubseq(string s) {
        int n = s.length();
        vector<vector<int>> ans(n, vector<int>(n, 0));
        
        for (int i=0; i<n; i++) ans[i][i] = 1;
        
        for (int len=2; len<=n; len++) {
            for (int st=0; st<=n-len; st++) {
                int en = st+len-1;
                
                ans[st][en] = s[st] == s[en]? ans[st+1][en-1]+2:max(ans[st][en-1], ans[st+1][en]);
            }
        }
        
        return ans[0][n-1];
    }

};

