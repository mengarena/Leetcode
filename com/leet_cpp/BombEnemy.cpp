//361. Bomb Enemy   QuestionEditorial Solution  My Submissions
//
//Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
//The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
//Note that you can only put the bomb at an empty cell.

//Example:
//For the given grid
//
//0 E 0 0
//E 0 W E
//0 E 0 0
//
//return 3. (Placing a bomb at (1,1) kills 3 enemies)
//
//Google

class Solution {
public:
    int maxKilledEnemies(vector<vector<char>>& grid) {
        if (grid.size() == 0 || grid[0].size() == 0) return 0;
        int nRow = grid.size();
        int nCol = grid[0].size();
        int i, j;
        int maxEnemy = 0;
        int **pRowBased = new int*[nRow];
        
        for (i=0; i<nRow; i++) pRowBased[i] = new int[nCol];
        
        int **pColBased = new int*[nRow];
        
        for (i=0; i<nRow; i++) pColBased[i] = new int[nCol];
        
        for (i=0; i<nRow; i++) {
            fill_n(pRowBased[i], nCol, 0);
            fill_n(pColBased[i], nCol, 0);
        }
        
        for (i=0; i<nRow; i++) {
            for (j=0; j<nCol; j++) {
                if (grid[i][j] == 'W') continue;
                
                if (j > 0 && grid[i][j-1] != 'W') {
                    pRowBased[i][j] = pRowBased[i][j-1];
                } else {
                    pRowBased[i][j] = getEnemyByRow(grid, nRow, nCol, i, j);
                }
            }
        }

        for (j=0; j<nCol; j++) {
            for (i=0; i<nRow; i++) {
                if (grid[i][j] == 'W') continue;
                
                if (i > 0 && grid[i-1][j] != 'W') {
                    pColBased[i][j] = pColBased[i-1][j];
                } else {
                    pColBased[i][j] = getEnemyByCol(grid, nRow, nCol, i, j);
                }
                
                if (grid[i][j] == '0') {
                    maxEnemy = max(maxEnemy, pRowBased[i][j] + pColBased[i][j]);
                    if (maxEnemy == nRow + nCol - 2) break;
                }
            }
            
            if (maxEnemy == nRow + nCol - 2) break;
        }        
        
        return maxEnemy;
    }
    
    
    int getEnemyByRow(vector<vector<char>>& grid, int nRow, int nCol, int i, int j) {
        int nEnemyCnt = 0;
        
        for (int k=j; k<nCol; k++) {
            if (grid[i][k] == 'E') {
                nEnemyCnt++;
            } else if (grid[i][k] == 'W') {
                break;
            }
        }
        
        return nEnemyCnt;
    }
    
    int getEnemyByCol(vector<vector<char>>& grid, int nRow, int nCol, int i, int j) {
        int nEnemyCnt = 0;
        
        for (int k=i; k<nRow; k++) {
            if (grid[k][j] == 'E') {
                nEnemyCnt++;
            } else if (grid[k][j] == 'W') {
                break;
            }
        }
        
        return nEnemyCnt;
    }    
    
};

