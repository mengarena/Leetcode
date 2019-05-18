/*

773. Sliding Puzzle

On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

Given a puzzle board, return the least number of moves required so that the state of the board is solved.
If it is impossible for the state of the board to be solved, return -1.

Examples:

Input: board = [[1,2,3],[4,0,5]]
Output: 1
Explanation: Swap the 0 and the 5 in one move.
Input: board = [[1,2,3],[5,4,0]]
Output: -1
Explanation: No number of moves will make the board solved.
Input: board = [[4,1,2],[5,0,3]]
Output: 5
Explanation: 5 is the smallest number of moves that solves the board.
An example path:
After move 0: [[4,1,2],[5,0,3]]
After move 1: [[4,1,2],[0,5,3]]
After move 2: [[0,1,2],[4,5,3]]
After move 3: [[1,0,2],[4,5,3]]
After move 4: [[1,2,0],[4,5,3]]
After move 5: [[1,2,3],[4,5,0]]
Input: board = [[3,2,4],[1,5,0]]
Output: 14
Note:

board will be a 2 x 3 array as described above.
board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].


Hard
*/


class Solution {
public:
    int slidingPuzzle(vector<vector<int>>& board) {
        vector<vector<int>> expected{{1,2,3},{4,5,0}};
        
        if (board == expected) return 0;
        
        int m = board.size();
        int n = board[0].size();
        
        set<vector<vector<int>>> visited;  // Canot use unordered_set since there is no std::hash specialization for vector
        queue<pair<int,int>> qPos;
        queue<vector<vector<int>>> qBoard;
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (board[i][j] == 0) {
                    qPos.push(make_pair(i, j));
                    break;
                }
            }
        }
        
        visited.insert(board);
        qBoard.push(board);
        vector<vector<int>> dirs{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int step = 0;
        
        // BFS
        while (!qPos.empty()) {
            int size = qPos.size();
            step++;
            
            for (int i=0; i<size; i++) {
                pair<int, int> curPos = qPos.front();
                qPos.pop();
                vector<vector<int>> curBoard = qBoard.front();
                qBoard.pop();
            
                int x = curPos.first, y = curPos.second;
            
                for (auto dir:dirs) {
                    int tmpx = x + dir[0];
                    int tmpy = y + dir[1];
                
                    if (tmpx >= 0 && tmpx < m && tmpy>=0 && tmpy < n) {
                        vector<vector<int>> tmpBoard = curBoard;
                        tmpBoard[x][y] = tmpBoard[tmpx][tmpy];
                        tmpBoard[tmpx][tmpy] = 0;
                        if (tmpBoard == expected) return step;
                        if (visited.count(tmpBoard)) continue;
                        visited.insert(tmpBoard);
                        qPos.push(make_pair(tmpx, tmpy));
                        qBoard.push(tmpBoard);
                    }
                }
            }
            
        }
        
        return -1; 
    }
};
