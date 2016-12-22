/*
463. Island Perimeter   

You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
Grid cells are connected horizontally/vertically (not diagonally). 
The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). 
The island doesn't have "lakes" (water inside that isn't connected to the water around the island). 
One cell is a square with side length 1. 
The grid is rectangular, width and height don't exceed 100. 
Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:
*/

//Google
//Easy

public class Solution {
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int perimeter = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    perimeter += getPeri(grid, m, n, i, j);
                }
            }
        }
        
        return perimeter;
    }
    
    //Based on the neighbors, to decide how many peri could get from each cell
    public int getPeri(int[][] grid, int m, int n, int i, int j) {
        int peri = 0;
        
        if (i == 0) peri = 1;
        if (i == m-1) peri += 1;
        
        if (j == 0) peri += 1;
        if (j == n-1) peri += 1;
        
        if (i > 0 && grid[i-1][j] == 0) peri += 1;
        if (i < m-1 && grid[i+1][j] == 0) peri += 1;
        if (j > 0 && grid[i][j-1] == 0) peri += 1;
        if (j < n-1 && grid[i][j+1] == 0) peri += 1;
        
        return peri;
    }
}

