package com.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
//the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
//
//Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
//
//Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
//
//Note:
//The order of returned grid coordinates does not matter.
//Both m and n are less than 150.
//
//Example:
//
//Given the following 5x5 matrix:
//
//  Pacific ~   ~   ~   ~   ~ 
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * Atlantic
//
//Return:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
//
//Google
public class PacificAtlanticWaterFlow {

	public PacificAtlanticWaterFlow() {
		// TODO Auto-generated constructor stub
	}

	//The question is asking: if I pour some water on some cell, whether it could flow into both Pacific and Atlantic, 
	//i.e. whether there is a path from this cell to the edges at Pacific and Atlantic
	//
	//ACC: 45ms
    class Position {
        int row;
        int col;
        
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public boolean equals(Object o) {
            Position tmp = (Position) o;
            return this.row == tmp.row && this.col == tmp.col; 
        }
        
        //The following functions are needed
        public int hashCode() {
            return row * 150 + col;
        }
        
        public String toString() {
            return "(" + row + "," + col + ")";
        }        
    }
    

    //Strategy: For Pacific related path, start from (0, 0) to find the possible cells
    //For Atlantic related path, start from (m-1, n-1) to find the possible cells
    //And then find the intersection between the two sets
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        
        Set<Position> setPacific = findFloodArea(matrix, m, n, 0, 0, true);
        Set<Position> setAtlantic = findFloodArea(matrix, m, n, m-1, n-1, false);

        List<int[]> lstGrid = new ArrayList<>();
        Set<Position> setSmall = setPacific.size() < setAtlantic.size()? setPacific:setAtlantic;
        Set<Position> setBig = setPacific.size() < setAtlantic.size()? setAtlantic:setPacific;
        
        for (Position pos:setSmall) {
            if (setBig.contains(pos)) lstGrid.add(new int[]{pos.row, pos.col});
        }
        
        return lstGrid;
    }


    //Actually, the processing starts from first row, first column;  
    //And last row, last column, based on the calling on this function in the above function
    private Set<Position> findFloodArea(int[][] matrix, int m, int n, int row, int col, boolean direction) {
        Set<Position> setPos = new HashSet<>();
        Queue<Position> quPos = new LinkedList<Position>();
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[m][n];
        int i;
        
        //Process all positions on this row
        for (i=0; i<n; i++) {
            Position tmp = new Position(row, i);
            quPos.add(tmp);
            setPos.add(tmp);
            visited[tmp.row][tmp.col] = true;
        }
        
        int start = direction? 1:0;
        int end = direction? m:m-1;
        
        //Process all position on this column
        for (i=start; i<end; i++) {
            Position tmp = new Position(i, col);
            quPos.add(tmp);
            setPos.add(tmp);
            visited[tmp.row][tmp.col] = true;
        }
        
        //For each position (cell), check its neighbor cells, 
        //To see whether it is possible for the water to flow from its neighbor to current cell;
        //If yes, the neighbor is needed
        //All visited positions are saved in setPos
        while (!quPos.isEmpty()) {
            Position tmp = quPos.poll();
            for (int[] dir:dirs) {
                int newRow = tmp.row + dir[0];
                int newCol = tmp.col + dir[1];
                
                if (newRow >= 0 && newRow <= m-1 && newCol >= 0 && newCol <= n-1 && !visited[newRow][newCol]) {
                    Position tmptmp = new Position(newRow, newCol);
                    if (matrix[newRow][newCol] >= matrix[tmp.row][tmp.col]) {
                        quPos.add(tmptmp);
                        setPos.add(tmptmp);
                        visited[tmptmp.row][tmptmp.col] = true;
                    }
                }
            }
        }
        
        return setPos;
    }
 
}
