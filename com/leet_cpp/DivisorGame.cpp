/*
1025. Divisor Game

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there is a number N on the chalkboard.  
On each player's turn, that player makes a move consisting of:

Choosing any x with 0 < x < N and N % x == 0.
Replacing the number N on the chalkboard with N - x.
Also, if a player cannot make a move, they lose the game.

Return True if and only if Alice wins the game, assuming both players play optimally.

 

Example 1:

Input: 2
Output: true
Explanation: Alice chooses 1, and Bob has no more moves.
Example 2:

Input: 3
Output: false
Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 

Note:

1 <= N <= 1000

Easy
*/

class Solution {
public:
    bool divisorGame(int N) {        
        vector<bool> dp(N+1, false);   // First play wins or not at number X
        
        dp[1] = false;
        
        for (int i=2; i<=N; ++i) {
            for (int j=1; j*j <= i; ++j) {
                if (i%j == 0 && !dp[i-j]) dp[i] = true;  // i.e. we know i-j will fail, so now I have i, 
                                                         // I can take j, which leave the other play i-j, he will lose, 
                                                         // so I win
            }
        }
        
        return dp[N];
    }
};
