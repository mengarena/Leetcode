/*

785. Is Graph Bipartite?

Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B 
such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
Each node is an integer between 0 and graph.length - 1.  
There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.

Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 
Note:
graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].

Medium
*/

class Solution {
public:
    bool setColor(vector<vector<int>>& graph, vector<int>& node_color, int nodeIdx, int color) {
        if (node_color[nodeIdx] != -1) return node_color[nodeIdx] == color;
        node_color[nodeIdx] = color;
        
        vector<int> adjNodes = graph[nodeIdx];
        
        for (auto adjNode:adjNodes) {
            if (!setColor(graph, node_color, adjNode, 1-color)) return false;
        }
        
        return true;
    }
    
    // 66%
    bool isBipartite(vector<vector<int>>& graph) {
        int n = graph.size();
        if (n < 2) return true;
        vector<int> node_color(n, -1);
        
        for (int i = 0; i<n; ++i) {
            if (node_color[i] == -1) {
                if (!setColor(graph, node_color, i, 0)) return false;
            }
        }
        
        return true;
    }
};
