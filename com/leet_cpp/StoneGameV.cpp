/*

1563. Stone Game V

There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), 
then Bob calculates the value of each row which is the sum of the values of all the stones in this row. 
Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. 
If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. 
The next round starts with the remaining row.

The game ends when there is only one stone remaining. Alice's is initially zero.

Return the maximum score that Alice can obtain.

Example 1:
Input: stoneValue = [6,2,3,4,5,5]
Output: 18
Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. 
The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). 
The game ends because only one stone is remaining in the row.

Example 2:
Input: stoneValue = [7,7,7,7,7,7,7]
Output: 28

Example 3:
Input: stoneValue = [4]
Output: 0
 
Constraints:
1 <= stoneValue.length <= 500
1 <= stoneValue[i] <= 10^6

Hard
*/


class Solution {
public:
    vector<int> prefixSum;
    vector<vector<int>> memo;    // remember the maximum score between stoneValue[i ~ j]
    
    int dp(vector<int>& stoneValue, int start, int end) {
        if (start == end) return 0;
        if (memo[start][end] != -1) return memo[start][end];
        
        memo[start][end] = 0;
        
        for (int i=start+1; i<=end; ++i) {
            int left = prefixSum[i]-prefixSum[start];
            int right = prefixSum[end+1] - prefixSum[i];
            if (left < right) {
                memo[start][end] = max(memo[start][end], left + dp(stoneValue, start, i-1));
            } else if (left > right) {
                memo[start][end] = max(memo[start][end], right + dp(stoneValue, i, end));
            } else {
                memo[start][end] = max(memo[start][end], left + max(dp(stoneValue, start, i-1), dp(stoneValue, i, end)));
            }
        }
        
        return memo[start][end];
    }
    
    int stoneGameV(vector<int>& stoneValue) {
        int n = stoneValue.size();
        memo.resize(n, vector<int>(n, -1));
        prefixSum.resize(n+1, 0);
        
        for (int i=0; i<n; ++i) {
            prefixSum[i+1] = prefixSum[i] + stoneValue[i];
        }
        
        return dp(stoneValue, 0, n-1);
    }
};
