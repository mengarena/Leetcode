/*

935. Knight Dialer

https://leetcode.com/problems/knight-dialer/

A chess knight can move as indicated in the chess diagram below:

This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  
Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), 
it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

Example 1:
Input: 1
Output: 10

Example 2:
Input: 2
Output: 20

Example 3:
Input: 3
Output: 46
 
Note:
1 <= N <= 5000

Medium

Facebook, Google, Microsoft, Box
*/


class Solution {
public:

    // 80%
    // Strategy: DP
    // Say currently at node x, and have experienced n steps, dp[x, n] remembers the total #combination
    // Say node a,b could reach x in one step
    // so dp[x, n] = dp[a, n-1] + dp[b, n-1]
    int knightDialer(int N) {
        int NN = 1e9 + 7;
        
        // On each number, where can it move next;
        // it is also the nodes from where can come to this node
        // e.g. from 4,6 can reach 0
        vector<vector<int>> moves{{4,6}, {6,8}, {7,9}, {4,8}, {0,3,9}, {}, {0,1,7}, {2,6}, {1,3}, {2,4}};
        
        // In this dp, dp[0][i] and dp[1][i] are used to recorded the result to this node at n-1 and n steps
        // Note:  could be [0] for n-1th step, [1] for nth step; [1] for n-1th step, [0] for nth step;
        vector<vector<int>> dp(2, vector<int>(10, 0));  //2: one for current, one for prev
        
        // Just assume there are 10 knights starting from each node initially and move step by step
        for (int hops = 0; hops < N; ++hops) {
            int cur = hops % 2;
            for (int node = 0; node < 10; ++node) {
                if (hops == 0) {
                    dp[cur][node] = 1;
                    continue;
                }
                int sum = 0;
                for (auto prev:moves[node]) {
                    sum = (sum + dp[1-cur][prev]) % NN;
                }
                dp[cur][node] = sum;
            }
        }
        
        int cur = (N-1) % 2;
        int total = 0;
        for (int i=0; i<10; ++i) {
            total = (total + dp[cur][i]) % NN;
        }
        
        return total;
    }
};


