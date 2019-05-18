/*

661. Image Smoother

Given a 2D integer matrix M representing the gray scale of an image, 
you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) 
of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].

Easy
*/

class Solution {
public:

    // Another idea:  since the value in M is in [0, 255], which only occupies one byte (last byte of int),
    //                we can utilize the last 2nd byte to save the new status and at the end, right shift the new status
    //                In this way, we don't need to use extra space, only operate on M

    // 8%
    vector<vector<int>> imageSmoother(vector<vector<int>>& M) {
        int m = M.size();
        int n = M[0].size();
        vector<vector<int>> ans(m, vector<int>(n, 0));
        vector<vector<int>> poss{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1,0}, {1,1}};
        
        for (int i=0; i<m; ++i) {
            for (int j=0; j<n; ++j) {
                int sum = M[i][j];
                int count = 1;
                
                for (auto pos:poss) {
                    int x = i + pos[0];
                    int y = j + pos[1];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        count++;
                        sum += M[x][y];
                    }
                }
                
                ans[i][j] = floor(sum/count);
            }
        }
        
        return ans;
    }
};
