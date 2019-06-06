/*

489. Robot Room Cleaner

Given a robot cleaner in a room modeled as a grid.
Each cell in the grid can be empty or blocked.
The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.
When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}

Example:
Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.

Notes:
The input is only given to initialize the room and the robot's position internally. 
You must solve this problem "blindfolded". 
In other words, you must control the robot using only the mentioned 4 APIs, 
without knowing the room layout and the initial robot's position.
The robot's initial position will always be in an accessible cell.
The initial direction of the robot will be facing up.
All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
Assume all four edges of the grid are all surrounded by wall.

Hard

Google, Facebook, Amazon
*/


/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * class Robot {
 *   public:
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     bool move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     void turnLeft();
 *     void turnRight();
 *
 *     // Clean the current cell.
 *     void clean();
 * };
 */
class Solution {
public:
    
    // For each cell, in right-hand order (i.e. keep turning right)
    // to move to next cell and clean if possible
    void backtrack(Robot& robot, int row, int col, int dir) {
        visited.insert(make_pair(row, col));
        robot.clean();
        
        // clocking wise: 0-up, 1-right, 2-down, 3-left
        for (int i = 0; i<dirs.size(); ++i) {
            int newDir = (dir + i) % 4;  // Next direction
            int newRow = row + dirs[newDir][0];
            int newCol = col + dirs[newDir][1];
            
            if (visited.count(make_pair(newRow, newCol)) == 0 && robot.move()) {
                backtrack(robot, newRow, newCol, newDir);  // Clean on the new direction
                goBack(robot);  // Go back to the previous cell
            }
            
            robot.turnRight();  // Try next direction
        }
    }
    
    // 77%
    void cleanRoom(Robot& robot) {
        backtrack(robot, 0, 0, 0);
    }
    
private:
    // Move the robot back to the previous cell
    void goBack(Robot& robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight(); 
    }    
    
    set<pair<int, int>> visited;
    vector<vector<int>> dirs{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
};
