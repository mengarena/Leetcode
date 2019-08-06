/*
There is a ball in a maze with empty spaces and walls. 
The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.


Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), 
but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/

//Medium
//Google

class Solution {
	
public:

    //Strategy: Use a set to record the tried start point
    //From each start point, try to move in four direction
    //When the ball hit wall (i.e. border or wall in the matrix), 
    //the stop position becomes the new start point for next searching
    bool hasPath(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        set<vector<int>> visitedStart;
        return dfs(maze, visitedStart, start, destination);
    }
    
    bool dfs(vector<vector<int>>& maze, set<vector<int>>& visitedStart, vector<int>& start, vector<int>& destination) {
        if (start == destination) return true;
        if (visitedStart.find(start) != visitedStart.end()) return false;
        
        visitedStart.insert(start);
        vector<vector<int>> dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        
        for (int i=0; i<4; i++) {
            vector<int> ret = goUntilStop(maze, start, dirs[i]);
            if (ret == destination) return true;
            //Try to search from the stop point
            if (dfs(maze, visitedStart, ret, destination)) return true;
        }
        
        return false;
    }
    
    //Search from start until it stops, return the stop point
    vector<int> goUntilStop(vector<vector<int>>& maze, vector<int>& start, vector<int>& dir) {
        int x = start[0] + dir[0];
        int y = start[1] + dir[1];
        int m = maze.size();
        int n = maze[0].size();
        
        //If next position is wall, it stops
        if (x < 0 || x >= m || y < 0 || y >= n || maze[x][y] == 1) return start;
        
        vector<int> newStart = {x, y};
        
        //Keep rolling in the same direction
        return goUntilStop(maze, newStart, dir);
    }
};

