/*

797. All Paths From Source to Target

Given a directed, acyclic graph of N nodes.
Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.
graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []] 
Output: [[0,1,3],[0,2,3]] 
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

Note:
The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.

Medium

Walmart Labs, Amazon
*/


class Solution {
public:

    // 28%
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        int N = graph.size();
        vector<vector<int>> ans;
        vector<int> onepath;
        
        helper(graph, 0, onepath, ans);
        return ans;
    }
    
    void helper(vector<vector<int>>& graph, int node, vector<int> onepath, vector<vector<int>>& ans) {
        onepath.push_back(node);
        if (node == graph.size() -1) {
            ans.push_back(onepath);
            return;
        }
        
        vector<int> nexts = graph[node];
        for (auto next:nexts) {
            helper(graph, next, onepath, ans);
        }
    }
};
