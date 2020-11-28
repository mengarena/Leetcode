/*

887. Super Egg Drop

You are given K eggs, and you have access to a building with N floors from 1 to N. 
Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, 
and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?

Example 1:
Input: K = 1, N = 2
Output: 2
Explanation: 
Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
If it didn't break, then we know with certainty F = 2.
Hence, we needed 2 moves in the worst case to know what F is with certainty.

Example 2:
Input: K = 2, N = 6
Output: 3

Example 3:
Input: K = 3, N = 14
Output: 4
 
Note:
1 <= K <= 100
1 <= N <= 10000

Hard

Google
*/

class Solution {
public:

    // 45%
    /*
    The dp equation is:
      dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1,
      assume, dp[m-1][k-1] = n0, dp[m-1][k] = n1
      the first floor to check is n0+1.
      if egg breaks, F must be in [1,n0] floors, we can use m-1 moves and k-1 eggs to find out F is which one.
      if egg doesn't breaks and F is in [n0+2, n0+n1+1] floors, we can use m-1 moves and k eggs to find out F is which one.
      So, with m moves and k eggs, we can find out F in n0+n1+1 floors, whichever F is.
    */
    // Time complexity: O(KlogN) 
    // The adding part (line 66), dp[m][i] is almost 2*dp[m-1][j], so it is 2 times to approach N, so logN
    int superEggDrop(int K, int N) {
        vector<vector<int>> dp(N+1, vector<int>(K+1, 0));  // dp: max floors could be checked by k egges, n moves
        int m = 0;
        
        while (dp[m][K] < N) {
            m++;
            
            for (int i=1; i<=K; ++i) {
                dp[m][i] = dp[m-1][i] + dp[m-1][i-1] + 1;   // Or draw a 2D table to check, basically
                                                            // to reach <m,i>, there are two ways,
                                                            // one is from <m-1, i> (does not consume egg, i.e. egg not broken)
                                                            // the other is from <m-1, i-1> (consume one egg, i.e. egg broken)
             
                                                            // The last "+1" is a new floor could be check, NOT egg!
                                                            // i.e. with m-1 move, we can check dp[m-1][i] and  dp[m-1][i-1] floors, 
                                                            // now with [m][i], we can check their sum + 1 floors  
            }
        }
        
        return m;
    }
    

    // TLE
    // Complexity O(K*N^2)
    // https://www.youtube.com/watch?v=iOaRjDT0vjc
    // https://github.com/bephrem1/backtobackswe/blob/master/Dynamic%20Programming%2C%20Recursion%2C%20%26%20Backtracking/eggDrop.java
    int superEggDrop(int K, int N) {
        vector<vector<int>> dp(K+1, vector<int>(N+1, 0));
        
        for (int i=1; i<=N; ++i) {
            dp[1][i] = i;
        }
        
        for (int j=1; j<=K; ++j) {
            dp[j][0] = 0;
            dp[j][1] = 1;
        }
        
        for (int i=2; i<=K; ++i) {
            for (int j=2; j<=N; ++j) {
                int maxMove = INT_MAX;
                for (int t=1; t<=j; ++t) {
                    maxMove = min(maxMove, max(dp[i][j-t], dp[i-1][t-1]) + 1);
                }
                dp[i][j] = maxMove;
            }
        }
        
        return dp[K][N];
    }
    
};

