package com.leet;

//You are given a m x n 2D grid initialized with these three possible values.
//
//-1 - A wall or an obstacle.
//0 - A gate.
//INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
//
//Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
//
//For example, given the 2D grid:
//	
//INF  -1  0  INF
//INF INF INF  -1
//INF  -1 INF  -1
//  0  -1 INF INF
//  
//After running your function, the 2D grid should be:
//  3  -1   0   1
//  2   2   1  -1
//  1  -1   2  -1
//  0  -1   3   4
  
  
//Facebook
public class WallsGates {

	public WallsGates() {
		// TODO Auto-generated constructor stub
	}

    public void run() {
	
    }
    
    //Find "0" and then from "0", propagate the distance
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0) return;
        int m = rooms.length;
        int n = rooms[0].length;
        int i, j;
        
        for (i=0; i<m; i++) {
        	for (j=0; j<n; j++) {
        		if (rooms[i][j] == 0) {
        			propagatePath(rooms, i, j);
        		}
        	}
        }
        
        return;
    }    
    
    
    //Accepted: 55%
    private void propagatePath(int[][] rooms, int x, int y) {
        int m = rooms.length;
        int n = rooms[0].length;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  //Propagate to 4 directions
        
        for (int i=0; i<dir.length; i++) {
        	int xx = x + dir[i][0];
        	int yy = y + dir[i][1];
        	
        	if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
        		if (rooms[xx][yy] != -1) {   //This if could be omitted, because when it is -1, it could not meet the "if" below
        			if (rooms[xx][yy] > rooms[x][y] + 1) {
        				rooms[xx][yy] = rooms[x][y] + 1;
        				propagatePath(rooms, xx, yy);
        			}
        		}
        	}
        	
        }    
    }
    
    
    //Accepted: 78%, because for each direction, the if condition checking is simper than the above method, although code is longer
    private void propagatePathA(int[][] rooms, int x, int y) {
        int m = rooms.length;
        int n = rooms[0].length;
        
        if (x-1 >= 0) {
        	if (rooms[x-1][y] != -1) {  //This if could be omitted
        		if (rooms[x-1][y] > rooms[x][y] +1) {
        			rooms[x-1][y] = rooms[x][y] + 1;
        			propagatePath(rooms, x-1, y);
        		}
        	}
        } 
        
        if (x+1 < m) {
        	if (rooms[x+1][y] != -1) {  //This if could be omitted
        		if (rooms[x+1][y] > rooms[x][y] +1) {
        			rooms[x+1][y] = rooms[x][y] + 1;
        			propagatePath(rooms,  x+1, y);
        		}
        	}
        	
        }
        
        if (y-1 >= 0) {
        	if (rooms[x][y-1] != -1) {  //This if could be omitted
        		if (rooms[x][y-1] > rooms[x][y] +1) {
        			rooms[x][y-1] = rooms[x][y] + 1;
        			propagatePath(rooms, x, y-1);
        		}
        	}
        	
        }
        
        if (y+1 < n) {
        	if (rooms[x][y+1] != -1) {  //This if could be omitted
        		if (rooms[x][y+1] > rooms[x][y] +1) {
        			rooms[x][y+1] = rooms[x][y] + 1;
        			propagatePath(rooms, x, y+1);
        		}
        	}
        	
        }
    }    
}
