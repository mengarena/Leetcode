package com.leet;

//Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
//The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
//Note that you can only put the bomb at an empty cell.
//
//Example:
//	
//For the given grid
//
//0 E 0 0
//E 0 W E
//0 E 0 0
//
//return 3. (Placing a bomb at (1,1) kills 3 enemies)

		
//Google		
public class BombEnemy {

	public BombEnemy() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC: 73%
	//Strategy:  not #enemy at every cell needs to be calculated; at some cell, one the previous cell has been calculate, it could directly use the previous cell's number
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int i, j;
        int[][] narrByRow = new int[m][n];   //Calculate the enemy by row. In each row, the cells are segmented by 'W'. 
                                             //In each segment, e.g. "E 0 E 0 0 E", the #reachable enemy at first 'E' is calculated,
                                             //From the second '0', it could directly use the #reachable enemy calculated in the first 'E'
        int[][] narrByCol = new int[m][n];   //Calculate the enemy by col.
        int maxEnemies = 0;
        
        for (i=0; i<m; i++) {
            for (j=0; j<n; j++) {
                if (grid[i][j] == 'W') continue;
                
                if (j > 0 && grid[i][j-1] != 'W') {
                    narrByRow[i][j] = narrByRow[i][j-1];
                } else {
                    narrByRow[i][j] = getEnemyRow(grid, i, j, n);  
                }
            }
        }

        for (j=0; j<n; j++) {
            for (i=0; i<m; i++) {
                if (grid[i][j] == 'W') continue;
                
                if (i > 0 && grid[i-1][j] != 'W') {
                    narrByCol[i][j] = narrByCol[i-1][j];
                } else {
                    narrByCol[i][j] = getEnemyCol(grid, i, j, m);  
                }
            }
        }
        
        for (i=0; i<m; i++) {
            for (j=0; j<n; j++) {
                if (grid[i][j] == '0') maxEnemies = Math.max(maxEnemies, narrByRow[i][j] + narrByCol[i][j]);   //Here must from '0'. If from 'E', the 'E' is counted twice (one at row, one at column)
            }
        }
        
        return maxEnemies;
    }
    
    private int getEnemyRow(char[][] grid, int i, int j, int nCol) {
        int nEnemy = 0;
        int t;
        
        for (t=j; t<nCol; t++) {
            if (grid[i][t] == 'E') {
                nEnemy++;
            } else if (grid[i][t] == 'W') {
                break;
            }
        }
        
        return nEnemy;
    }
    
    private int getEnemyCol(char[][] grid, int i, int j, int nRow) {
        int nEnemy = 0;
        int t;
        
        for (t=i; t<nRow; t++) {
            if (grid[t][j] == 'E') {
                nEnemy++;
            } else if (grid[t][j] == 'W') {
                break;
            }
        }
        
        return nEnemy;
    }   
	
	
	
    
    
	
	//ACC: 15%
    public int maxKilledEnemiesA(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int i, j;
        int maxEnemy = 0;
        
        for (i=0; i<m; i++) {
            for (j=0; j<n; j++) {
                if (grid[i][j] == '0') {
                    maxEnemy = Math.max(maxEnemy, killEnemy(grid, i, j, m, n));
                }
            }
        }
        
        return maxEnemy;
    }
    
    private int killEnemy(char[][] grid, int i, int j, int m, int n) {
        int nEnemy = 0;
        int t;
        
        for (t=i-1; t>=0; t--) {
            if (grid[t][j] == 'E') {
                nEnemy++;
            } else if (grid[t][j] == 'W') {
                break;
            }
        }
        
        for (t=i+1; t<m; t++) {
            if (grid[t][j] == 'E') {
                nEnemy++;
            } else if (grid[t][j] == 'W') {
                break;
            }
        }
        
        for (t=j-1; t>=0; t--) {
            if (grid[i][t] == 'E') {
                nEnemy++;
            } else if (grid[i][t] == 'W') {
                break;
            }
        }
        
        for (t=j+1; t<n; t++) {
            if (grid[i][t] == 'E') {
                nEnemy++;
            } else if (grid[i][t] == 'W') {
                break;
            }
        }      
        
        return nEnemy;
    }


}
