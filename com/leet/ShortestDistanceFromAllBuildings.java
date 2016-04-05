package com.leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
//You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
//
//Each 0 marks an empty land which you can pass by freely.
//Each 1 marks a building which you cannot pass through.
//Each 2 marks an obstacle which you cannot pass through.
//For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):
//
//1 - 0 - 2 - 0 - 1
//|   |   |   |   |
//0 - 0 - 0 - 0 - 0
//|   |   |   |   |
//0 - 0 - 1 - 0 - 0
//The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
//
//Note:
//There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

//Google, Zenefits
public class ShortestDistanceFromAllBuildings {

	public ShortestDistanceFromAllBuildings() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:  27%
	private int dirs[][] = {{-1, 0},{1, 0},{0, -1},{0, 1}};
	
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        int i, j;
        int distance = Integer.MAX_VALUE;
        int nEmptyCount = 0;
        int nBuildingCount = 0;
        int[][] distFromBuildings = new int[m][n];    //Sum of Distance from each empty to all buildings 
        int[][] reachByBuildings = new int[m][n];     //Number of reachable buildings from empty point
        
        
        for (i=0; i<m; i++) {
        	for (j=0; j<n; j++) {
        		if (grid[i][j] == 1) {
        			nBuildingCount++;
        			boolean[][] visited = new boolean[m][n]; 
        			BFS(grid, i, j, distFromBuildings, reachByBuildings, visited);
        		} else if (grid[i][j] == 0) {
        			nEmptyCount++;
        		}
        	}
        }
        
        if (nEmptyCount == 0 || nBuildingCount == 0) return -1;
        
        for (i=0; i<m; i++) {
        	for (j=0; j<n; j++) {
        	    if (distFromBuildings[i][j] != 0 && reachByBuildings[i][j] == nBuildingCount) {
        	    	distance = Math.min(distance, distFromBuildings[i][j]);
        	    }
        	}
        }
        
        if (distance == Integer.MAX_VALUE) {
        	return -1;
        } else {
        	return distance;
        }
    }

    
    //Find the the distance from the empty points to this building (x, y)
    //For the empty points, increase their reachability to this building
    private void BFS(int[][] grid, int x, int y, int[][] distFromBuildings, int[][] reachByBuildings, boolean[][] visited) {
    	Queue<Integer> quX = new LinkedList<>();
    	Queue<Integer> quY = new LinkedList<>();
    	int m = grid.length;
    	int n = grid[0].length;
    	
    	int level = 1;  //Distance from this building to other nodes
    	
    	quX.offer(x); quY.offer(y);
    	
    	while (!quX.isEmpty()) {
    		int size = quX.size();
    		
    		//One set of neighbors (based on distance)
    		for (int s = 0; s < size; s++) {
    			int i = quX.poll();
        		int j = quY.poll();
        		
        		//Neighbors
        		for (int t = 0; t < 4; t++) {
        			int nextRow = i + dirs[t][0];
        			int nextCol = j + dirs[t][1];
        			
        			//If empty point and has not visited, process
        			if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && grid[nextRow][nextCol] == 0 && !visited[nextRow][nextCol]) {
        				distFromBuildings[nextRow][nextCol] += level;
        				reachByBuildings[nextRow][nextCol]++;
        				visited[nextRow][nextCol] = true;
        				quX.offer(nextRow); quY.offer(nextCol);
        			}
        		}
    		}
    		
    		level++;
    	}
    	
    }

}
