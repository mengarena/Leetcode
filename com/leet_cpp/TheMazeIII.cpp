/*
There is a ball in a maze with empty spaces and walls. 
The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction. 
There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. 
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). 
Output the moving directions by using 'u', 'd', 'l' and 'r'. 

Since there could be several different shortest ways, you should output the lexicographically smallest way. 
If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. 
You may assume that the borders of the maze are all walls. 
The ball and the hole coordinates are represented by row and column indexes.

Example 1:
Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (0, 1)

Output: "lul"
Explanation: There are two shortest ways for the ball to drop into the hole.
The first way is left -> up -> left, represented by "lul".
The second way is up -> left, represented by 'ul'.
Both ways have shortest distance 6, but the first way is lexicographically smaller because 'l' < 'u'. So the output is "lul".

Example 2:
Input 1: a maze represented by a 2D array

0 0 0 0 0
1 1 0 0 1
0 0 0 0 0
0 1 0 0 1
0 1 0 0 0

Input 2: ball coordinate (rowBall, colBall) = (4, 3)
Input 3: hole coordinate (rowHole, colHole) = (3, 0)
Output: "impossible"

Explanation: The ball cannot reach the hole.

Note:
There is only one ball and one hole in the maze.
Both the ball and hole exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

Show Tags
Show Similar Problems

*/

class Solution {
public:
    string findShortestWay(vector<vector<int>>& maze, vector<int>& ball, vector<int>& hole) {
        return roll(maze, ball[0], ball[1], hole, 0, 0, 0, "", pair<string, int>() = {"impossible", INT_MAX});
    }
    
    string roll(vector<vector<int>>& maze, int rowBall, int colBall, const vector<int>& hole, int dirRow, int dirCol, int steps, 
                const string& path, pair<string, int>& ret) {
        if (steps >= ret.second) {
            return ret.first;
        }
        
        if (dirRow != 0 || dirCol != 0) {
	    //Move in one direction
            while (rowBall + dirRow >= 0 && rowBall + dirRow < maze.size() &&
                   colBall + dirCol >= 0 && colBall + dirCol < maze[0].size() &&
                   maze[rowBall + dirRow][colBall + dirCol] != 1) {
                       
                       rowBall += dirRow;
                       colBall += dirCol;
                       steps++;
                       
                       //If meet hole, check steps and save path
                       if (rowBall == hole[0] && colBall == hole[1] && steps < ret.second) ret = {path, steps};
                   }
        }
        
        if (maze[rowBall][colBall] == 0 || steps + 2 < maze[rowBall][colBall]) {
            maze[rowBall][colBall] = steps + 2;
                
            //To make the result in lexicographical order, roll in order of d-l-r-u    
            if (dirRow == 0) roll(maze, rowBall, colBall, hole, 1, 0, steps, path+"d", ret); 
            if (dirCol == 0) roll(maze, rowBall, colBall, hole, 0, -1, steps, path+"l", ret); 
            if (dirCol == 0) roll(maze, rowBall, colBall, hole, 0, 1, steps, path+"r", ret);
            if (dirRow == 0) roll(maze, rowBall, colBall, hole, -1, 0, steps, path+"u", ret);
        }       
        
        return ret.first;
    }
};

