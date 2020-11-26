/*
695. Max Area of Island

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.

Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.

Medium:
Uber
*/

class Solution {
public:
    // Complexity: O(m*n)  --- m*n is the total size of the grid 
    // 79%
    int maxAreaOfIsland(vector<vector<int>>& grid) {
        int maxarea = 0;
        int m = grid.size();
        int n = grid[0].size();
        vector<vector<bool>> visited(m, vector<bool>(n, false));
        
        for (int i = 0; i<m; i++) {
            for (int j = 0; j<n; j++) {
                if (grid[i][j] == 1) {
                    int area = 0;
                    findIsland(grid, visited, m, n, i, j, area);
                    maxarea = max(maxarea, area);
                }
            }
        }
        
        return maxarea;
    }
    
    void findIsland(vector<vector<int>>& grid, vector<vector<bool>>& visited, int m, int n, int i, int j, int& area) {
        if (visited[i][j]) return;
        visited[i][j] = true;
        area++;
        
        if (i-1 >= 0 && grid[i-1][j] == 1) findIsland(grid, visited, m, n, i-1, j, area);
    
        if (i+1 < m && grid[i+1][j] == 1) findIsland(grid, visited, m, n, i+1, j, area);
        
        if (j-1 >= 0 && grid[i][j-1] == 1) findIsland(grid, visited, m, n, i, j-1, area);
        
        if (j+1 < n && grid[i][j+1] == 1) findIsland(grid, visited, m, n, i, j+1, area);
    }
};
