/*

1091. Shortest Path in Binary Matrix

In an N by N square grid, each cell is either empty (0) or blocked (1).

A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:

Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
C_1 is at location (0, 0) (ie. has value grid[0][0])
C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.

Example 1:
Input: [[0,1],[1,0]]
Output: 2

Example 2:
Input: [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
 
Note:
1 <= grid.length == grid[0].length <= 100
grid[i][j] is 0 or 1


Medium
*/



class Solution {
public:

    // 100%
    // BFS
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        if (n == 1) return 1;
        
        queue<pair<int, int>> pathHeads;   // Records the heads of all possible path
        pathHeads.push(make_pair(0, 0));
        
        int steps = 1;
        
        vector<vector<int>> dirs{{0, 1}, {1, 1}, {1, 0}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        
        while (!pathHeads.empty()) {
            int size = pathHeads.size();
            
            for (int i=0; i<size; ++i) {
                auto head = pathHeads.front();
                pathHeads.pop();
                
                for (int j=0; j<8; ++j) {
                    int x = head.first + dirs[j][0];
                    int y = head.second + dirs[j][1];
                    
                    if (x < 0 || x >= n || y < 0 || y >= n || grid[x][y] == 1) continue;
                    
                    if (x == n-1 && y == n-1) return steps+1;
                    
                    // Important: Set it as "visited"
                    // Why can set this here ? Because here we are looking for shortest path, so first time we see it,
                    // we can set it; otherwise, if it is reached later from other path, that path will be longer from 
                    // this point to the destination
                    grid[x][y] = 1;
                    
                    pathHeads.push(make_pair(x, y));
                }
            }
            
            steps++;
        }
        
        return -1;
    }


    // BFS:   LTE
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<int>> dirs{{0, 1}, {1, 1}, {1, 0}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}};
        deque<vector<int>> pathHeads;   // Remember the heads of all possible paths
        
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;
        int zeros = 0;
        for (auto rows:grid) {
            for (auto num:rows) {
                if (num == 0) zeros++;
            }
        }
        
        if (zeros == 0 || zeros < n) return -1;
        
        vector<int> start{0, 0};
        pathHeads.push_back(start);
        
        int step = 1;
        
        while (!pathHeads.empty()) {
            int size = pathHeads.size();
            bool moved = false;
            
            for (int i=0; i<size; ++i) {
                vector<int> head = pathHeads.front(); 
                pathHeads.pop_front();
 
                for (int j=0; j<8; ++j) {
                    int x = head[0] + dirs[j][0];
                    int y = head[1] + dirs[j][1];
                    if (x < 0 || y < 0 || x >= n || y >= n || grid[x][y] == 1) continue;
                    
                    moved = true;
                    if (x == n-1 && y == n-1) return step+1;
                    
                    pathHeads.push_back({x, y});
                }
                
            }
            
            if (moved) step++;
            if (step >= zeros) return -1;
        }
        
        return -1;
    }

    // DFS:   TLE
    int shortestPathBinaryMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        vector<vector<int>> dirs{{0, 1}, {1, 1}, {1, 0}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1} };
        vector<vector<bool>> visited(n, vector<bool>(n, false));
        
        if (grid[0][0] == 1) return -1;
        visited[0][0] = true;
        
        int globalMinLen = INT_MAX;
        int shorted = getMinPath(grid, visited, dirs, globalMinLen, 1, 0, 0);
        
        if (shorted == INT_MAX) shorted = -1;
        return shorted;
    }
    
    int getMinPath(vector<vector<int>>& grid, vector<vector<bool>>& visited, vector<vector<int>>& dirs, int& globalMinLen, int curLen, int i, int j) {
        int n = grid.size();
        if (i == n-1 && j == n-1) {
            globalMinLen = min(curLen, globalMinLen);
            return curLen;
        }
        
        if (curLen >= globalMinLen) return INT_MAX;
        
        int minLen = INT_MAX;
        
        for (int t = 0; t<8; ++t) {
            int x = i + dirs[t][0];
            int y = j + dirs[t][1];
            
            if (x < 0 || y < 0 || x >= n || y >= n || grid[x][y] == 1 || visited[x][y]) continue;
            
            visited[x][y] = true;
            
            int pathLen = getMinPath(grid, visited, dirs, globalMinLen, curLen+1, x, y);
            minLen = min(minLen, pathLen);
            visited[x][y] = false;
        }
        
        return minLen;
    }
};
