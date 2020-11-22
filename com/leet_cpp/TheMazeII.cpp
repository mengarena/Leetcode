/*
There is a ball in a maze with empty spaces and walls. 
The ball can go through empty spaces by rolling up, down, left or right, 
but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, 
find the shortest distance for the ball to stop at the destination. 
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 
1 means the wall and 0 means the empty space. 
You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.

Example 1:
Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: 12
Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:
Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1
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

    //Strategy:  use a matrix (dists) to record the shortest distance from start
    //Use a queue to record all the possible starting point, from each starting point, 
    //do BFS
    //Every time meet a stop point, if the new distance arriving this stop point is smaller, 
    //put this stop point as a future starting point
    //Only record the distance of all the stop points, so if at destination, the ball never stops, the distance is kept as -1
    int shortestDistance(vector<vector<int>>& maze, vector<int>& start, vector<int>& destination) {
        if (start == destination) return 0;
        vector<pair<int, int>> dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        int m = maze.size();
        int n = maze[0].size();
        queue<pair<int,int>> q;   //Record all the start points (i.e. the points where stoped and start next round search)
        vector<vector<int>> dists(m, vector<int>(n, -1));  //record shortest distance from start to [i, j] in maze
        
        dists[start[0]][start[1]] = 0;
        q.push({start[0], start[1]});
        
        while (!q.empty()) {
            int i = q.front().first;
            int j = q.front().second;
            q.pop();
            int dist = dists[i][j];
            
            //From <i, j>, try to roll in 4 directions
            for (int t=0; t<4; t++) {
                int step = 0;
                int x = i;
                int y = j;
                
                //Along one direction, keep rolling, stop until hits a wall
                while (x + dirs[t].first < m && x + dirs[t].first >= 0 && 
                       y + dirs[t].second < n && y + dirs[t].second >= 0 && 
                       maze[x+dirs[t].first][y+dirs[t].second] != 1) {
                    
                    step++;
                    
                    x = x + dirs[t].first;
                    y = y + dirs[t].second;
                }

                //For the stop point, set its distance from the very original "start"
                //Only when the new distance is smaller than previous distance or 
                //this is the first time visiting the stop point, 
                //set the distance
                //
                //* For other cells on the road, their distances from "start" are not set (i.e. kept as -1) 
                if (dists[x][y] == -1 || dists[x][y] > dist + step) {
                    dists[x][y] = dist + step;
                    q.push({x, y});
                }
            }
        }
        
        return dists[destination[0]][destination[1]];
    }
};
