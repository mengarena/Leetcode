/*

531. Lonely Pixel I

Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position 
where the same row and same column don't have any other black pixels.

Example:
Input: 
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].


Medium
*/

class Solution {
public:
    // 80%
    // First find out the rows which has only one black (record the corresponding columns), 
    // Later check those columns to see whether has only one black
    int findLonelyPixel(vector<vector<char>>& picture) {
        int m = picture.size();
        int n = picture[0].size();
        unordered_map<int, int> rowscols;
        
        // Check rows, and save the rows which have only one Black
        for (int i=0; i<m; ++i) {
            int blackCnt = 0;
            int col = -1;
            for (int j=0; j<n; ++j) {
                if (picture[i][j] == 'B') {
                    blackCnt++;
                    col = j;
                    if (blackCnt > 1) break;
                }
            }
            if (blackCnt == 1) rowscols[i] = col;
        }
        
        if (rowscols.size() == 0) return 0;
        int ans = 0;
        
        // Check columns for the rows which has only one Black
        for (auto it=rowscols.begin(); it!=rowscols.end(); ++it) {
            int col = it->second;
            int blackCnt = 0;
            for (int i=0; i<m; ++i) {
                if (picture[i][col] == 'B') blackCnt++;
                if (blackCnt > 1) break;
            }
            
            if (blackCnt == 1) ans++;
        }
        
        return ans;
    }
    

    // 42%
    // Difference from above:  Added a map to remember whether column is checked in 2nd for loop
    int findLonelyPixel(vector<vector<char>>& picture) {
        int m = picture.size();
        int n = picture[0].size();
        unordered_map<int, int> rowscols;
        unordered_map<int, bool> colChecked;
        
        // Check rows, and save the rows which have only one Black
        for (int i=0; i<m; ++i) {
            int blackCnt = 0;
            int col = -1;
            for (int j=0; j<n; ++j) {
                if (picture[i][j] == 'B') {
                    blackCnt++;
                    col = j;
                    if (blackCnt > 1) break;
                }
            }
            if (blackCnt == 1) {
                rowscols[i] = col;
                colChecked[col] = false;
            }
        }
        
        if (rowscols.size() == 0) return 0;
        int ans = 0;
        
        // Check columns for the rows which has only one Black
        for (auto it=rowscols.begin(); it!=rowscols.end(); ++it) {
            int col = it->second;
            if (colChecked[col]) continue;
            int blackCnt = 0;
            for (int i=0; i<m; ++i) {
                if (picture[i][col] == 'B') blackCnt++;
                if (blackCnt > 1) break;
            }
            
            if (blackCnt == 1) ans++;
            colChecked[col] = true;
        }
        
        return ans;
    }
    
};
