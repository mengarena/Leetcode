/*
407. Trapping Rain Water II

Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
compute the volume of water it is able to trap after raining.

Note:
Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:
Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

Google, Twitter
Hard
*/

class Solution {
public:
    struct Cell {
        int row;
        int col;
        int height;
        
        Cell(int r, int c, int h) {
            row = r;
            col = c;
            height = h;
        }
                
        //bool operator < (Cell a) {  //Wrong
        //bool operator < (const Cell &a) {  //Wrong
        bool operator < (const Cell &a) const {  //Correct
        //bool operator < (Cell a) const {   //Correct
            return height > a.height;   //Equals to "greater", lead to order: Small, Large
        }
    };
    
    
    struct cmp {
        bool operator()(Cell a, Cell b) {
            return a.height > b.height;    //Equals to "greater", lead to order: Small, Large
        }  
    };
    
    //Correct
    int trapRainWaterA(vector<vector<int>>& heightMap) {
        if (heightMap.size() <= 2 || heightMap[0].size() <= 2) return 0;
        int r = heightMap.size();
        int c = heightMap[0].size();
        int sum = 0;
        int i;
        int neigh[4][2] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int neiRow, neiCol;
        vector<vector<bool>> visited(r, vector<bool>(c, false));
        
        priority_queue<Cell, vector<Cell>, cmp> pq;   //Correct, Small on top
        priority_queue<Cell> pq;  //Small on top
       
        for (i=0; i<r; i++) {
            visited[i][0] = true;
            visited[i][c-1] = true;
            Cell tmp1(i, 0, heightMap[i][0]);
            pq.push(tmp1);
            Cell tmp2(i, c-1, heightMap[i][c-1]);
            pq.push(tmp2);
        }
        
        for (i=0; i<c; i++) {
            visited[0][i] = true;
            visited[r-1][i] = true;
            Cell tmp1(0, i, heightMap[0][i]);
            pq.push(tmp1);
            Cell tmp2(r-1, i, heightMap[r-1][i]);
            pq.push(tmp2);
        }
        
        while (!pq.empty()) {
            Cell pCe = pq.top();
            pq.pop();
            
            for (i=0; i<sizeof(neigh)/sizeof(neigh[0]); i++) {
                neiRow = pCe.row + neigh[i][0];
                neiCol = pCe.col + neigh[i][1];
                
                if (neiRow >= 0 && neiRow < r && neiCol >= 0 && neiCol < c && !visited[neiRow][neiCol]) {
                    visited[neiRow][neiCol] = true;
                    sum += max(0, pCe.height - heightMap[neiRow][neiCol]);
                    
                    Cell tmp(neiRow, neiCol, max(pCe.height, heightMap[neiRow][neiCol]));
                    pq.push(tmp);
                }
            }
        }
        
        return sum;
    }
    
    
    //Correct
    int trapRainWaterB(vector<vector<int>>& heightMap) {
        if (heightMap.size() <= 2 || heightMap[0].size() <= 2) return 0;
        int r = heightMap.size();
        int c = heightMap[0].size();
        int sum = 0;
        int i;
        int neigh[4][2] = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int neiRow, neiCol;
        vector<vector<bool>> visited(r, vector<bool>(c, false));
        
        priority_queue<Cell> pq;   // Smallest on top
       
        for (i=0; i<r; i++) {
            visited[i][0] = true;
            visited[i][c-1] = true;
            
            pq.push(Cell(i, 0, heightMap[i][0]));
            pq.push(Cell(i, c-1, heightMap[i][c-1]));
        }
        
        for (i=0; i<c; i++) {
            visited[0][i] = true;
            visited[r-1][i] = true;

            pq.push(Cell(0, i, heightMap[0][i]));
            pq.push(Cell(r-1, i, heightMap[r-1][i]));
        }
        
        while (!pq.empty()) {
            Cell pCe = pq.top();
            pq.pop();
            
            for (i=0; i<sizeof(neigh)/sizeof(neigh[0]); i++) {
                neiRow = pCe.row + neigh[i][0];
                neiCol = pCe.col + neigh[i][1];
                
                if (neiRow >= 0 && neiRow < r && neiCol >= 0 && neiCol < c && !visited[neiRow][neiCol]) {
                    visited[neiRow][neiCol] = true;
                    sum += max(0, pCe.height - heightMap[neiRow][neiCol]);
                    
                    pq.push(Cell(neiRow, neiCol, max(pCe.height, heightMap[neiRow][neiCol])));
                }
            }
        }
        
        return sum;
    }    
    
};
