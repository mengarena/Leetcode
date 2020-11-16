/*
133. Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
         
//Pocket Gems, Google, Uber, Facebook
//Medium
*/

/**
 * Definition for undirected graph.
 * struct UndirectedGraphNode {
 *     int label;
 *     vector<UndirectedGraphNode *> neighbors;
 *     UndirectedGraphNode(int x) : label(x) {};
 * };
 */
              
class Solution {
public:
    UndirectedGraphNode *cloneGraph(UndirectedGraphNode *node) {
        if (node == NULL) return NULL;
        
        unordered_map<UndirectedGraphNode*, UndirectedGraphNode*> hmGraph;
        queue<UndirectedGraphNode*> quNode;
        
        UndirectedGraphNode *root = new UndirectedGraphNode(node->label);
        
        //All following 4 are correct
        //hmGraph.insert(pair<UndirectedGraphNode*, UndirectedGraphNode*>(node, root));
        hmGraph.insert(make_pair(node, root));
        //hmGraph.insert({node, root});
        //hmGraph[node] = root;
        
        quNode.push(node);
        
        while (!quNode.empty()) {
            UndirectedGraphNode *tmpNode = quNode.front();
            quNode.pop();
            
            vector<UndirectedGraphNode *> oldNeighbors = tmpNode->neighbors;
            
            for (UndirectedGraphNode* tmpOldNeighbor:oldNeighbors) {
                if (hmGraph.find(tmpOldNeighbor) == hmGraph.end()) {
                    UndirectedGraphNode *tmpNewNeighbor = new UndirectedGraphNode(tmpOldNeighbor->label);
                    
                    //hmGraph.insert(pair<UndirectedGraphNode*, UndirectedGraphNode*>(tmpOldNeighbor, tmpNewNeighbor));
                    hmGraph.insert(make_pair(tmpOldNeighbor, tmpNewNeighbor));
                    //hmGraph.insert({tmpOldNeighbor, tmpNewNeighbor});
                    //hmGraph[tmpOldNeighbor] = tmpNewNeighbor;
                    
                    quNode.push(tmpOldNeighbor);
                }
                
                hmGraph[tmpNode]->neighbors.push_back(hmGraph[tmpOldNeighbor]);
            }
        }
        
        return root;
    }
};
         
