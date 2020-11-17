/*

913. Cat and Mouse

A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.

The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.

Mouse starts at node 1 and goes first, Cat starts at node 2 and goes second, and there is a Hole at node 0.

During each player's turn, they must travel along one edge of the graph that meets where they are.
For example, if the Mouse is at node 1, it must travel to any node in graph[1].

Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)

Then, the game can end in 3 ways:

If ever the Cat occupies the same node as the Mouse, the Cat wins.
If ever the Mouse reaches the Hole, the Mouse wins.
If ever a position is repeated (ie. the players are in the same position as a previous turn, 
and it is the same player's turn to move), the game is a draw.
Given a graph, and assuming both players play optimally, return 1 if the game is won by Mouse, 
2 if the game is won by Cat, and 0 if the game is a draw.

Example 1:
Input: [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
Output: 0
Explanation:
4---3---1
|   |
2---5
 \ /
  0
 
Note:
3 <= graph.length <= 50
It is guaranteed that graph[1] is non-empty.
It is guaranteed that graph[2] contains a non-zero element. 

Hard

Google
*/

class Solution {
public:

     // 70%
     int catMouseGame(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<vector<vector<int>>> color(n, vector<vector<int>>(n, vector<int>(2,0)));  // cat node, mouse node, move (0-mouse, 1 cat)
        vector<vector<vector<int>>> outdegree(n, vector<vector<int>>(n, vector<int>(2,0)));
     
        for (int i=0; i<n; ++i) {  // cat
            for (int j=0; j<n; ++j) {  // mouse
                outdegree[i][j][0] = graph[j].size();  // mouse move
                outdegree[i][j][1] = graph[i].size();  // cat move
                for (auto k : graph[i]) {
                    if (k == 0) {  // Cat cannot be in 0
                        outdegree[i][j][1]--;
                        break;
                    }
                }
            }
        }
        
        queue<vector<int>> q;
        for (int k=1; k<n; ++k) {
            for (int m = 0; m<2; ++m) {
                color[k][0][m] = 1;   // when mouse is at 0, mouse win
                q.push({k, 0, m, 1});
                color[k][k][m] = 2;   // when cat and mouse at same node, cat win
                q.push({k, k, m, 2});
            }
        }
        
        // move == 0  mouse move
        while (!q.empty()) {
            vector<int> cur = q.front(); q.pop();
            int cat = cur[0], mouse = cur[1], curMove = cur[2], c = cur[3];
            if (cat == 2 && mouse == 1 && curMove == 0) return c;
            
            int prevMove = 1 - curMove;
            
            for (auto prev:graph[prevMove==1? cat:mouse]) {
                int prevCat = prevMove == 1? prev:cat;
                int prevMouse = prevMove == 1? mouse:prev;
                if (prevCat == 0) continue;
                if (color[prevCat][prevMouse][prevMove] > 0) continue;
                
                if ((prevMove == 1 && c == 2)  || (prevMove == 0 && c == 1) ||
                    (--outdegree[prevCat][prevMouse][prevMove] == 0)) {
                    color[prevCat][prevMouse][prevMove] = c;
                    q.push({prevCat, prevMouse, prevMove, c});
                }
            }
        }
        
        return color[2][1][0];
    }

 
 
 
    // Pass 40/49 test cases
    int catMouseGame(vector<vector<int>>& graph) {
        int mouseNode = 1;
        int catNode = 2;
        set<vector<int>> visited;
        unordered_set<int> mouseVisited;
        unordered_set<int> catVisited;
                
        int ret = game(graph, mouseNode, catNode, 1, visited, mouseVisited, catVisited);
        return ret;
    }
    
    int game(vector<vector<int>>& graph, int mouseNode, int catNode, int turn, set<vector<int>>& visited, 
             unordered_set<int>& mouseVisited, unordered_set<int>& catVisited) {
        
        vector<int> status = {turn, mouseNode, catNode};
        
        if (visited.count(status)) return 0;
        visited.insert(status);
        
        vector<int> nexts;
        
        if (turn == 1) {
            mouseVisited.insert(mouseNode);
            nexts = graph[mouseNode];
            if (find(nexts.begin(), nexts.end(), 0) != nexts.end()) return 1;
            bool canMove = false;
            
            for (auto next:nexts) {
                if (next == catNode) continue;
                if (mouseVisited.count(next)) continue;
                int nextRet = game(graph, next, catNode, 2, visited, mouseVisited, catVisited);
                if (nextRet == 2) continue;
                if (nextRet == 1) {
                    return 1;
                }
                canMove = true;
            }
            
            if (canMove == false) return 2;
            
        } else {
            catVisited.insert(catNode);
            nexts = graph[catNode];
            if (find(nexts.begin(), nexts.end(), mouseNode) != nexts.end()) return 2;
            
            bool canMove = false;
            for (auto next:nexts) {
                if (next == 0) continue;
                if (catVisited.count(next)) continue;
                int nextRet = game(graph, mouseNode, next, 1, visited, mouseVisited, catVisited);
                if (nextRet == 1) continue;
                if (nextRet == 2) {
                    return 2;
                }
                canMove = true;
            }
            
            if (canMove == false) return 1;
        }
        
        return 0;
    }
};
