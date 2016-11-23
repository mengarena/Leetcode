package com.leet;

import java.util.PriorityQueue;
import java.util.Comparator;

//Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
//compute the volume of water it is able to trap after raining.
//
//Note:
//Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
//
//Example:
//
//Given the following 3x6 height map:
//[
//  [1,4,3,1,3,2],
//  [3,2,1,3,2,4],
//  [2,3,3,2,3,1]
//]
//
//Return 4.
//
//The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.
//
//After the rain, water are trapped between the blocks. The total volume of water trapped is 4.


//Google, Twitter
//Hard
public class TrappingRainWaterII {

	public TrappingRainWaterII() {
		// TODO Auto-generated constructor stub
	}

	
    public class Cell implements Comparable<Cell> {
        int row;
        int col;
        int height;
        
        public Cell (int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
        
        public int compareTo(Cell o) {
            return this.height - o.height;   //Small, Large
        }
    }
    
    //ACC:  71%
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length <= 2 || heightMap[0].length <= 2) return 0;
        int r = heightMap.length;
        int c = heightMap[0].length;
        int sum = 0;
        int i;
        int[][] neighArr = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int neiRow, neiCol;
        int spillVol;
        boolean visited[][] = new boolean[r][c];
        
        PriorityQueue<Cell> pq = new PriorityQueue<Cell>();
        
        for (i=0; i<r; i++) {
            visited[i][0] = true;
            visited[i][c-1] = true;
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, c-1, heightMap[i][c-1]));
        }
        
        for (i=0; i<c; i++) {
            visited[0][i] = true;
            visited[r-1][i] = true;
            pq.offer(new Cell(0, i, heightMap[0][i]));
            pq.offer(new Cell(r-1, i, heightMap[r-1][i]));
        }
        
        while (!pq.isEmpty()) {
            Cell ce = pq.poll();
            
            for (int[] neigh:neighArr) {
                neiRow = ce.row + neigh[0];
                neiCol = ce.col + neigh[1];
                
                if (neiRow >= 0 && neiRow < r && neiCol >= 0 && neiCol < c && !visited[neiRow][neiCol]) {
                    visited[neiRow][neiCol] = true;
                    sum += Math.max(0, ce.height - heightMap[neiRow][neiCol]);
                    pq.offer(new Cell(neiRow, neiCol, Math.max(ce.height, heightMap[neiRow][neiCol])));
                }
            }
        }

        
        return sum;
    }
	
	
	
	
    public class CellK {
        int row;
        int col;
        int height;
        
        public CellK (int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    
    //ACC: 90%
    //Strategy:  
    //Use a priorityqueue, minHeap
    //First, start from the borders, put all the border cells into pq
    //Get the lowest cell from the pq and check its neighbors
    //If a neighbor is lower than this cell, need to add (trap) water at that neighbor;
    //And actually neighbor's height become the new height (i.e. the height after trapped water)
    //
    //Complexity: rc*log(r+c)*4
    public int trapRainWaterK(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;
        int r = heightMap.length;
        int c = heightMap[0].length;
        int sum = 0;
        int i;
        int[][] neighArr = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int neiRow, neiCol;
        boolean visited[][] = new boolean[r][c];
        
        PriorityQueue<CellK> pq = new PriorityQueue<CellK>(1, new Comparator<CellK>() {
            public int compare(CellK a, CellK b) {
                return a.height - b.height;
            }
        });
        
        //Process the borders
        for (i=0; i<r; i++) {
            visited[i][0] = true;
            visited[i][c-1] = true;
            pq.offer(new CellK(i, 0, heightMap[i][0]));
            pq.offer(new CellK(i, c-1, heightMap[i][c-1]));
        }
        
        for (i=0; i<c; i++) {
            visited[0][i] = true;
            visited[r-1][i] = true;
            pq.offer(new CellK(0, i, heightMap[0][i]));
            pq.offer(new CellK(r-1, i, heightMap[r-1][i]));
        }
        
        //For each cell, process its neighbors
        while (!pq.isEmpty()) {
        	CellK ce = pq.poll();
            
            //For each cell, check its neighbors
            for (int[] neigh:neighArr) {
                neiRow = ce.row + neigh[0];
                neiCol = ce.col + neigh[1];
                
                if (neiRow >= 0 && neiRow < r && neiCol >= 0 && neiCol < c && !visited[neiRow][neiCol]) {
                    visited[neiRow][neiCol] = true;
                    sum += Math.max(0, ce.height - heightMap[neiRow][neiCol]);  //trap water for the neighbor
                    pq.offer(new CellK(neiRow, neiCol, Math.max(ce.height, heightMap[neiRow][neiCol])));   //Put the neighbor into pq (which has trapped water)
                }
            }
        }

        
        return sum;
    }
	
	
	
	//ACC: 23%
	//Only need to check the cells which are not on the border
    //Complxity: O((rc)^2)
    public int trapRainWaterA(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;
        int r = heightMap.length;
        int c = heightMap[0].length;
        int[][] wetMap = new int[r][c];
        int sum = 0;
        int i, j, k;
        int[][] neigh = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        int curHeight, neiHeight;
        boolean spillWater = true;
        int neiRow, neiCol;
        int spillVol;
        
        //Rain enough water
        for (i=1; i<r-1; i++) {
           for (j=1; j<c-1; j++) {
               sum += wetMap[i][j] = 100000000;
           }
        }
        
        //Check each cell and its neighbors to spill water
        while (spillWater) {
            spillWater = false;
            
            for (i=1; i<r-1; i++) {
                for (j=1; j<c-1; j++) {
                    if (wetMap[i][j] != 0) {
                        for (k=0; k<4; k++) {
                            neiRow = i + neigh[k][0];
                            neiCol = j + neigh[k][1];
                            curHeight = heightMap[i][j] + wetMap[i][j];
                            neiHeight = heightMap[neiRow][neiCol] + wetMap[neiRow][neiCol];
                            
                            if (curHeight > neiHeight) {
                                spillVol = curHeight - Math.max(neiHeight, heightMap[i][j]);   //How much water to spill
                                
                                wetMap[i][j] -= spillVol;
                                sum -= spillVol;
                                
                                spillWater = true;
                            }
                        }
                    }
                }
            }
        }
        
        return sum;
    }

}
