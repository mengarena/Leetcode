/*

542. 01 Matrix

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.

Example 1:
Input:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Output:
[[0,0,0],
 [0,1,0],
 [0,0,0]]

Example 2:
Input:
[[0,0,0],
 [0,1,0],
 [1,1,1]]

Output:
[[0,0,0],
 [0,1,0],
 [1,2,1]]
 
Note:

The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.


Medium:
Uber
*/

class Solution {
public:
    // 87%
    // Process from every cell (first from 0s)
    // For each cell, check its (up to) 4 neighbors
    vector<vector<int>> updateMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        vector<vector<int>> ans(m, vector<int>(n, m+n)); // max distance will be m+n
        queue<pair<int, int>> qu;
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 0) {
                    ans[i][j] = 0;
                    qu.push(make_pair(i, j));
                }
            }
        }
        
        int neb[4][2] = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!qu.empty()) {
            pair<int, int> p = qu.front();
            qu.pop();
            
            int x = p.first, y = p.second;
            
            for (int i=0; i<4; i++) {
                int x1 = x + neb[i][0];
                int y1 = y + neb[i][1];
                
                if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n) {
                    if (ans[x1][y1] == m + n) qu.push(make_pair(x1, y1));
                    ans[x1][y1] = min(ans[x1][y1], ans[x][y] + 1);
                }
            }
        }
        
        return ans;
    }


    // 5%
    vector<vector<int>> updateMatrix(vector<vector<int>>& matrix) {
        int m = matrix.size();
        int n = matrix[0].size();
        vector<vector<int>> ans(m, vector<int>(n, m+n)); // max distance will be m+n
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 0) {
                    ans[i][j] = 0;
                    checkZeroDistance(matrix, ans, m, n, i, j, 0);
                }
            }
        }
        
        return ans;
    }
    
    void checkZeroDistance(vector<vector<int>>& matrix, vector<vector<int>>& ans, int m, int n, int i, int j, int dist) {
        if (i-1 >= 0 && matrix[i-1][j] != 0 && ans[i-1][j] > dist+1) {
            ans[i-1][j] = dist+1;
            checkZeroDistance(matrix, ans, m, n, i-1, j, ans[i-1][j]);
        }
        if (i+1 < m && matrix[i+1][j] != 0 && ans[i+1][j] > dist+1) {
            ans[i+1][j] = dist+1;
            checkZeroDistance(matrix, ans, m, n, i+1, j, ans[i+1][j]);
        }
        if (j-1 >= 0 && matrix[i][j-1] != 0 && ans[i][j-1] > dist+1) {
            ans[i][j-1] = dist+1;
            checkZeroDistance(matrix, ans, m, n, i, j-1, ans[i][j-1]);
        }
        if (j+1 < n && matrix[i][j+1] != 0 && ans[i][j+1] > dist+1) {
            ans[i][j+1] = dist+1;
            checkZeroDistance(matrix, ans, m, n, i, j+1, ans[i][j+1]);
        }
    }
};
