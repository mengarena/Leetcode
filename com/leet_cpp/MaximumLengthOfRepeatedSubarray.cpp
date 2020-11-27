/*

718. Maximum Length of Repeated Subarray

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].

Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100

Medium
*/

class Solution {
public:

    // 68%
    int findLength(vector<int>& A, vector<int>& B) {
        int m = A.size();
        int n = B.size();
        vector<vector<int>> dp(m, vector<int>(n, 0));  
                                           // dp[i][j] -- The length of subarray between A, B which ends at A[i] and B[j]
        int maxLen = 0;
        
        for (int i=0; i<n; i++) {
            if (A[0] == B[i]) {
                dp[0][i] = 1;
                maxLen = 1;
            }
        }

        for (int i=0; i<m; i++) {
            if (A[i] == B[0]) {
                dp[i][0] = 1;
                maxLen = 1;
            }
        }

        for (int i=1; i<m; ++i) {
            for (int j=1; j<n; ++j) {
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if (maxLen < dp[i][j]) maxLen = dp[i][j];
                }
            }
        }
        
        return maxLen;
    }
};
