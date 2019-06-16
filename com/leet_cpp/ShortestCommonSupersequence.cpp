/*

1092. Shortest Common Supersequence

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
If multiple answers exist, you may return any of them.

(A string S is a subsequence of string T if deleting some number of characters from T 
(possibly 0, and the characters are chosen anywhere from T) results in the string S.)

Example 1:
Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation: 
str1 = "abac" is a substring of "cabac" because we can delete the first "c".
str2 = "cab" is a substring of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
 
Note:
1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters.

Hard
*/


class Solution {
public:
    
    // Find the longest common subsequence
    // if A = "earbac",  B = "cayb"
    // it returns "ab"
    string lcs(const string& A, const string& B) {
        int m = A.length();
        int n = B.length();
        vector<vector<string>> dp(m+1, vector<string>(n+1, ""));
        
        for (int i=0; i<m; ++i) {
            for (int j=0; j<n; ++j) {
                if (A[i] == B[j]) {
                    dp[i+1][j+1] = dp[i][j] + A[i];  // Add common character
                } else {
                    if (dp[i+1][j].length() > dp[i][j+1].length()) {
                        dp[i+1][j+1] = dp[i+1][j];
                    } else {
                        dp[i+1][j+1] = dp[i][j+1];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
    
    
    string shortestCommonSupersequence(string str1, string str2) {
        string res = "";
        string common = lcs(str1, str2);
        int i = 0, j = 0;
        
        // From str1, str2, the non-common characters are added first
        for (auto c:common) {
            while (i < str1.length() && str1[i] != c) res.push_back(str1[i++]);
            while (j < str2.length() && str2[j] != c) res.push_back(str2[j++]);
            res.push_back(c);    // Add the common character, which is str1[i] and str2[j]
            i++;
            j++;
        }
        
        res += str1.substr(i) + str2.substr(j);
        
        return res;
    }
};
