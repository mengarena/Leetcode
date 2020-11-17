/*

741. Cherry Pickup

In a N x N grid representing a field of cherries, each cell is one of three possible integers.

0 means the cell is empty, so you can pass through;
1 means the cell contains a cherry, that you can pick up and pass through;
-1 means the cell contains a thorn that blocks your way.
 
Your task is to collect maximum number of cherries possible by following the rules below:

Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells 
(cells with value 0 or 1);
After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
 
Example 1:

Input: grid =
[[0, 1, -1],
 [1, 0, -1],
 [1, 1,  1]]
Output: 5
Explanation: 
The player started at (0, 0) and went down, down, right right to reach (2, 2).
4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
Then, the player went left, up, up, left to return home, picking up one more cherry.
The total number of cherries picked up is 5, and this is the maximum possible.
 
Note:
grid is an N by N 2D array, with 1 <= N <= 50.
Each grid[i][j] is an integer in the set {-1, 0, 1}.
It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.

Hard

Uber
*/

class Solution {
public:
    
    // 99%
    // Idea: one path go and back = two paths go forward
    // dp[x1][x2] means two length path, one arrives at x1, the other arrives x2
    int cherryPickup(vector<vector<int>>& grid) {
        int n = grid.size();
        int PLen = 2*n-1;  // Length of path
        
        vector<vector<int>> dp(n, vector<int>(n, -1));  // max Number of cherries
        dp[0][0] = grid[0][0];   // Value for pathlen =1
        
        for (int p=2; p<=PLen; ++p) {
            for (int x1 = n-1; x1 >= 0; --x1) {  // Possible x position of path1
                int y1 = p-1-x1;    // from x, decide the value of y
             
                for (int x2 = x1; x2 >= 0; --x2) {   // Possible x position of path2
                    int y2 = p-1-x2;
                    
                    if (y1 < 0 || y2 < 0 || y1 >= n || y2 >= n) continue;
                    if (grid[y1][x1] < 0 || grid[y2][x2] < 0) {
                        dp[x1][x2] = -1;
                        continue;
                    }
                    
                    // Get the cherries at current position of the two paths
                    // i.e. <y1,x1> and <y2, x2>
                    int delta = grid[y1][x1];
                    if (x1 != x2) {  // then y1 != y2, otherwise same point
                        delta += grid[y2][x2];
                    }
                    
                    int best = -1;
                    
                    // (Draw on board about the two heads of the two paths to understand)
                    // To reach <y1,x1> and <y2, x2>
                    // The steps before <y1, x1> could be <y1, x1-1>(left) and <y1-1, x1> (above)
                    // The steps before <y2, x2> could be <y2, x2-1>(left) and <y2-1, x2>(above)
                    // Here four if to choose best path
                    if (x1 > 0 && x2 > 0 && dp[x1-1][x2-1] >= 0) { // left, left
                        best = max(best, delta + dp[x1-1][x2-1] );
                    }
                    
                    if (x1 > 0 && y2 > 0 && dp[x1-1][x2] >= 0) {  // left, above
                        best = max(best, delta + dp[x1-1][x2]);
                    }
                    
                    if (y1 > 0 && x2 > 0 && dp[x1][x2-1] >= 0) {  // above, left
                        best = max(best, delta + dp[x1][x2-1]);
                    }
                    
                    if (y1 > 0 && y2 > 0 && dp[x1][x2] >= 0) {  // above, above
                        best = max(best, delta + dp[x1][x2]);
                    }
                    
                    dp[x1][x2] = best;
                }
            }
        }
        
        return dp[n-1][n-1] < 0 ? 0:dp[n-1][n-1];
    }
};
