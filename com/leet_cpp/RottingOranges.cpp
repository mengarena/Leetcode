/*

994. Rotting Oranges

In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  
If this is impossible, return -1 instead.

Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, 
because rotting only happens 4-directionally.

Example 3:
Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 
Note:
1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2.


Easy

Google, Amazon
*/

class Solution {
public:

    // 65%
    // BFS
    int orangesRotting(vector<vector<int>>& grid) {
        vector<vector<int>> dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        
        queue<vector<int>> q;
        int m = grid.size();
        int n = grid[0].size();
        int freshOrangeCnt = 0;
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    freshOrangeCnt++;
                } else if (grid[i][j] == 2) {
                    q.push(vector<int>{i, j});
                }
            }
        }
        
        if (freshOrangeCnt == 0) return 0;
        if (q.size() == 0) return -1;
        
        int min = 0;
        
        while (!q.empty()) {
            int cnt = q.size();
            min++;
            
            for (int i = 0; i < cnt; ++i) {
                vector<int> pos = q.front();
                q.pop();
                
                for (auto dir:dirs) {
                    int x = pos[0] + dir[0];
                    int y = pos[1] + dir[1];
                    
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        grid[x][y] = 2;
                        freshOrangeCnt--;
                        if (freshOrangeCnt == 0) break;
                        q.push(vector<int>{x, y});
                    }
                }
                
                if (freshOrangeCnt == 0) break;
            }
            
            if (freshOrangeCnt == 0) break;

        }
        
        if (freshOrangeCnt == 0) return min;
        
        return -1;
    }
};
