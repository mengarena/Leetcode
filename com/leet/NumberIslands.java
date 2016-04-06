package com.leet;

//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
//You may assume all four edges of the grid are all surrounded by water.
//
//Example 1:
//
//11110
//11010
//11000
//00000
//Answer: 1
//
//Example 2:
//
//11000
//11000
//00100
//00011
//Answer: 3

//Google, Facebook, Microsoft, Amazon, Zenefits
public class NumberIslands {

	public NumberIslands() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		char[][] grid = {{'1','1','1','1', '0'}, {'1','1','0','1', '0'},{'1','1','0','0', '0'}, {'0','0','0','0', '0'}};
//		char[][] grid = {{'1','1','0','0', '0'}, {'1','1','0','0', '0'},{'0','0','1','0', '0'}, {'0','0','0','1', '1'}};
		char[][] grid = {{'1','0','1','1','1'},{'1','0','1','0','1'},{'1','1','1','0','1'}};
		System.out.println("#Island = " + numIslands(grid));
		
	}
	
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;   //Row Count
        int n = grid[0].length;  //Column Count
        int i,j;
        int nIslandCnt = 0;
        
        for (i=0; i<m; i++) {
        	for (j=0; j<n; j++) {
        		if (grid[i][j] == '1') {
        			nIslandCnt++;
        			dfsSearch(grid, i, j);  //DFS search all its neighbors
        		}
        	}
        }
        
        return nIslandCnt;
        
    }	
    
    private void dfsSearch(char[][] grid, int i, int j) {
        int m = grid.length;   //Row Count
        int n = grid[0].length;  //Column Count

    	grid[i][j] = '0';
    	    	
    	if (i > 0 && grid[i-1][j] == '1') dfsSearch(grid, i-1, j);
    	if (j > 0 && grid[i][j-1] == '1') dfsSearch(grid, i, j-1);
    	if (i<m-1 && grid[i+1][j] == '1') dfsSearch(grid, i+1, j);
    	if (j<n-1 && grid[i][j+1] == '1') dfsSearch(grid, i, j+1);    	
    }
    
}
