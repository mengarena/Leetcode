package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//A 2d grid map of m rows and n columns is initially filled with water. 
//We may perform an addLand operation which turns the water at position (row, col) into a land. 
//Given a list of positions to operate, count the number of islands after each addLand operation. 
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
//You may assume all four edges of the grid are all surrounded by water.
//
//Example:
//
//Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
// Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
//0 0 0
//0 0 0
//0 0 0
//
//
//Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
//1 0 0
//0 0 0   Number of islands = 1
//0 0 0
//
//
//Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
//1 1 0
//0 0 0   Number of islands = 1
//0 0 0
//
//
//Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
//1 1 0
//0 0 1   Number of islands = 2
//0 0 0
//
//Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
//1 1 0
//0 0 1   Number of islands = 3
//0 1 0
//
//
//We return the result as an array: [1, 1, 2, 3]
//
//Challenge:
//
//Can you do it in time complexity O(k log mn), where k is the length of the positions?

//Google
//Hard
public class NumberIslandsII {

    //AC:  96%
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
	    List<Integer> lstIsland = new ArrayList<Integer>();
	    if (positions == null || positions.length == 0) return lstIsland;
	    int k = positions.length;
	    int i,j;
	    int[] roots = new int[m*n+1];   //To be 1-starting
	    int count = 0;
	    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
				
	    for (i=0; i<k; i++) {
	    	int x = positions[i][0], y = positions[i][1];
	    	int node = x*n + y + 1;  //Make it based on 1 starting
	    	
	    	roots[node] = node;
	    	count++;   //Turned into land, a new island
	    	
	    	//Check its neighbors
	    	for (j=0; j<4; j++) {
	    		int xx = x + dirs[j][0];
	    		int yy = y + dirs[j][1];
	    		int id = xx*n + yy + 1;   //To be 1 starting
	    		
	    		if (xx < 0 || xx >= m || yy < 0 || yy >= n || roots[id] == 0) continue;
	    		
	    		int rootId = get_root(roots, id);
	    		
	    		if (rootId != roots[node]) {
	    		    roots[node] = rootId;
	    		    node = rootId;   //This make the surrounding node chained
	    		    count--;
	    		}
	    	}
	    	
	    	lstIsland.add(count);
	    }
	    
	    return lstIsland;
	}		
	
	private int get_root(int[] roots, int node) {
	    while (node != roots[node]) {
		roots[node] = roots[roots[node]];
		node = roots[node];
	    }
	    return node;
        }
   
    
    //AC: 82%  Union Find
    public List<Integer> numIslands2B(int m, int n, int[][] positions) {
		List<Integer> lstIsland = new ArrayList<Integer>();
		if (positions == null || positions.length == 0) return lstIsland;
		int k = positions.length;
		int i,j;
		int[] roots = new int[m*n];
		int count = 0;
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		
		Arrays.fill(roots, -1);
		
	    for (i=0; i<k; i++) {
	    	int x = positions[i][0], y = positions[i][1];
	    	int node = x*n + y;
	    	
	    	roots[node] = node;
	    	count++;
	    	
	    	for (j=0; j<4; j++) {
	    		int xx = x + dirs[j][0];
	    		int yy = y + dirs[j][1];
	    		int id = xx*n + yy;
	    		
	    		if (xx < 0 || xx >= m || yy < 0 || yy >= n || roots[id] == -1) continue;
	    		
	    		int rootId = root(roots, id);
	    		
	    		if (rootId != roots[node]) {
	    		    roots[node] = rootId;
	    		    node = rootId;
	    		    count--;
	    		}
	    	}
	    	
	    	lstIsland.add(count);
	    }
	    
	    return lstIsland;
	}	
	
	
	
	

	
	//ACC:  3%
	public List<Integer> numIslands2A(int m, int n, int[][] positions) {
		List<Integer> lstIsland = new ArrayList<Integer>();
		if (positions == null || positions.length == 0) return lstIsland;
		int k = positions.length;
		int i;
		int[][] land = new int[m][n];
		int islandIdx = 0;

		if (positions[0][0] >= 0 && positions[0][0] < m && positions[0][1] >= 0 && positions[0][1] < n) {
			lstIsland.add(1);
			land[positions[0][0]][positions[0][1]] = 1;
			islandIdx++;
		} else {
			lstIsland.add(0);
		}

		for (i = 1; i < k; i++) {
			if (positions[i][0] >= 0 && positions[i][0] < m && positions[i][1] >= 0 && positions[i][1] < n && land[positions[i][0]][positions[i][1]] == 0) {
				// Calculate number of island
				List<Integer> lstSurroundIsland = getSurroundIsland(land, m, n, positions[i][0], positions[i][1]);

				if (lstSurroundIsland.size() == 0) { // New island
					islandIdx++;
					land[positions[i][0]][positions[i][1]] = islandIdx;
					lstIsland.add(lstIsland.get(lstIsland.size() - 1) + 1);
				} else if (lstSurroundIsland.size() == 1) {
					lstIsland.add(lstIsland.get(lstIsland.size() - 1)); // #Island does not change
					land[positions[i][0]][positions[i][1]] = lstSurroundIsland.get(0);
				} else {
					// Merge island
					for (int s = 0; s < m; s++) {
						for (int t = 0; t < n; t++) {
							if (land[s][t] == lstSurroundIsland.get(1)) land[s][t] = lstSurroundIsland.get(0);
							if (lstSurroundIsland.size() >= 3 && land[s][t] == lstSurroundIsland.get(2)) land[s][t] = lstSurroundIsland.get(0);
							if (lstSurroundIsland.size() >= 4 && land[s][t] == lstSurroundIsland.get(3)) land[s][t] = lstSurroundIsland.get(0);
						}
					}

					land[positions[i][0]][positions[i][1]] = lstSurroundIsland.get(0);

					lstIsland.add(lstIsland.get(lstIsland.size() - 1) - (lstSurroundIsland.size() - 1)); // Merge island, #Island decrease
				}

			} else {
				lstIsland.add(lstIsland.get(lstIsland.size() - 1));
			}
		}

		return lstIsland;
	}

	private List<Integer> getSurroundIsland(int[][] land, int m, int n, int x, int y) {
		List<Integer> lstSurroundIsland = new ArrayList<Integer>();

		if (x - 1 >= 0 && land[x - 1][y] != 0) lstSurroundIsland.add(land[x - 1][y]);
		if (x + 1 < m && land[x + 1][y] != 0 && lstSurroundIsland.indexOf(land[x + 1][y]) == -1) lstSurroundIsland.add(land[x + 1][y]);
		if (y - 1 >= 0 && land[x][y - 1] != 0 && lstSurroundIsland.indexOf(land[x][y - 1]) == -1) lstSurroundIsland.add(land[x][y - 1]);
		if (y + 1 < n && land[x][y + 1] != 0 && lstSurroundIsland.indexOf(land[x][y + 1]) == -1) lstSurroundIsland.add(land[x][y + 1]);

		return lstSurroundIsland;
	}

}

/*
 * //Function correct, but stack overflow when mxn too large because in the
 * recursion dfsSet, the parameter land[][] has to be copied many many times
 * public List<Integer> numIslands2(int m, int n, int[][] positions) {
 * List<Integer> lstIsland = new ArrayList<Integer>(); if (positions == null ||
 * positions.length == 0) return lstIsland; int k = positions.length; int i;
 * int[][] land = new int[m][n]; int islandIdx = 0;
 * 
 * if (positions[0][0] >= 0 && positions[0][0] < m && positions[0][1] >= 0 &&
 * positions[0][1] < n) { lstIsland.add(1);
 * land[positions[0][0]][positions[0][1]] = 1; islandIdx++; } else {
 * lstIsland.add(0); }
 * 
 * for (i=1; i<k; i++) { if (positions[i][0] >= 0 && positions[i][0] < m &&
 * positions[i][1] >= 0 && positions[i][1] < n &&
 * land[positions[i][0]][positions[i][1]] == 0) { //Calculate number of island
 * List<Integer> lstSurroundIsland = getSurroundIsland(land, m, n,
 * positions[i][0], positions[i][1]); int nBase = -1; int nCount = 0;
 * 
 * for (int j = 0; j < 4; j++) { if (lstSurroundIsland.get(j) != -1) { nCount++;
 * if (nBase == -1) nBase = lstSurroundIsland.get(j); } }
 * 
 * if (nCount == 0) { //New island islandIdx++;
 * land[positions[i][0]][positions[i][1]] = islandIdx;
 * lstIsland.add(lstIsland.get(lstIsland.size()-1) + 1); } else if (nCount == 1)
 * { lstIsland.add(lstIsland.get(lstIsland.size()-1)); //#Island does not change
 * land[positions[i][0]][positions[i][1]] = nBase; } else { //Merge island
 * 
 * land[positions[i][0]][positions[i][1]] = nBase;
 * 
 * for (int j=0; j<4; j++) { int islandIdxTmp = lstSurroundIsland.get(j); if
 * (islandIdxTmp != -1 && islandIdxTmp != nBase) { if (j == 1) dfsSet(land, m,
 * n, islandIdxTmp, nBase, positions[i][0]+1, positions[i][1]); if (j == 2)
 * dfsSet(land, m, n, islandIdxTmp, nBase, positions[i][0], positions[i][1]-1);
 * if (j == 3) dfsSet(land, m, n, islandIdxTmp, nBase, positions[i][0],
 * positions[i][1]+1); } }
 * 
 * lstIsland.add(lstIsland.get(lstIsland.size()-1)-(nCount-1)); //Merge island,
 * #Island decrease }
 * 
 * } else { lstIsland.add(lstIsland.get(lstIsland.size()-1)); } }
 * 
 * return lstIsland; }
 * 
 * private List<Integer> getSurroundIsland(int[][] land, int m, int n, int x,
 * int y) { List<Integer> lstSurroundIsland = new ArrayList<Integer>();
 * 
 * if (x-1 >= 0 && land[x-1][y] != 0) { lstSurroundIsland.add(land[x-1][y]); }
 * else { lstSurroundIsland.add(-1); }
 * 
 * if (x+1 < m && land[x+1][y] != 0 && lstSurroundIsland.indexOf(land[x+1][y])
 * == -1) { lstSurroundIsland.add(land[x+1][y]); } else {
 * lstSurroundIsland.add(-1); }
 * 
 * if (y-1 >= 0 && land[x][y-1] != 0 && lstSurroundIsland.indexOf(land[x][y-1])
 * == -1) { lstSurroundIsland.add(land[x][y-1]); } else {
 * lstSurroundIsland.add(-1); }
 * 
 * if (y+1 < n && land[x][y+1] != 0 && lstSurroundIsland.indexOf(land[x][y+1])
 * == -1) { lstSurroundIsland.add(land[x][y+1]); } else {
 * lstSurroundIsland.add(-1); }
 * 
 * return lstSurroundIsland; }
 * 
 * 
 * private void dfsSet(int[][] land, int m, int n, int rawIdx, int newIdx, int
 * i, int j) { if (land[i][j] == rawIdx) { land[i][j] = newIdx; } else { return;
 * }
 * 
 * if (i > 0 && land[i-1][j] == rawIdx) dfsSet(land, m, n, rawIdx, newIdx, i-1,
 * j); if (j > 0 && land[i][j-1] == rawIdx) dfsSet(land, m, n, rawIdx, newIdx,
 * i, j-1); if (i < m-1 && land[i+1][j] == rawIdx) dfsSet(land, m, n, rawIdx,
 * newIdx, i+1, j); if (j < n-1 && land[i][j+1] == rawIdx) dfsSet(land, m, n,
 * rawIdx, newIdx, i, j+1); }
 * 
 * }
 */
