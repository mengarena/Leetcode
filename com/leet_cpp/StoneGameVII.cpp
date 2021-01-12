/*

1690. Stone Game VII

Alice and Bob take turns playing a game, with Alice starting first.

There are n stones arranged in a row. 
On each player's turn, they can remove either the leftmost stone or the rightmost stone from the row and 
receive points equal to the sum of the remaining stones' values in the row. 
The winner is the one with the higher score when there are no stones left to remove.

Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's difference. 
Alice's goal is to maximize the difference in the score.

Given an array of integers stones where stones[i] represents the value of the ith stone from the left, 
return the difference in Alice and Bob's score if they both play optimally.

Example 1:
Input: stones = [5,3,1,4,2]
Output: 6
Explanation: 
- Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
- Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
- Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
- Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
- Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
The score difference is 18 - 12 = 6.

Example 2:
Input: stones = [7,90,5,1,100,10,10,2]
Output: 122
 
Constraints:
n == stones.length
2 <= n <= 1000
1 <= stones[i] <= 1000

Medium
*/

class Solution {
public:

    /*
        dp[i][j]:  The max difference the first player can get if the players play on stones[i .. j]
        
        dp[i][j] = max(
                        sum(i+1, j) - dp[i+1][j],     //if the first player choose A[i]
                        sum(i, j-1) - dp[i][j-1]      //if the first player choose A[j]
                   )
        
        say from stones[i .. j], first player removes stones[i], the first player gets scores sum(i+1, j),
        the his opponent (i.e. the 2nd player) also plays optimally, will get optimal difference dp[i+1][j],
        after in a round, the first plays get overall scores (or say difference) = sum(i+1, j) - dp[i+1][j] in case (s)he removes stones[i]
    */
    int stoneGameVII(vector<int>& stones) {
        int n = stones.size();

        vector<int> sums(n+1);
        for (int i=0; i<n; ++i) sums[i+1] = sums[i] + stones[i];
        
        vector<vector<int>> dp(n, vector<int>(n));
        
        for (int len=2; len<=n; ++len) {
            for (int i=0; i<=n-len; ++i) {
                int j = i+len-1;
                
                // sum(i+1, j) <---sums[j+1] - sums[i+1]
                // sum(i, j-1) <---sums[j] - sums[i]
                dp[i][j] = max(sums[j+1] - sums[i+1] - dp[i+1][j], sums[j] - sums[i] - dp[i][j-1]);  
            }
        }
        
        return dp[0][n-1];
    }
};

