package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
//
//
//OJ's undirected graph serialization:
//Nodes are labeled uniquely.
//
//We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
//As an example, consider the serialized graph {0,1,2#1,2#2,2}.
//
//The graph has a total of three nodes, and therefore contains three parts as separated by #.
//
//First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
//Second node is labeled as 1. Connect node 1 to node 2.
//Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
//Visually, the graph looks like the following:
//
//       1
//      / \
//     /   \
//    0 --- 2
//         / \
//         \_/
//         


//public class UndirectedGraphNode {
//	int label;
//	List<UndirectedGraphNode> neighbors;
//	
//	UndirectedGraphNode(int x) { 
//		label = x; 
//		neighbors = new ArrayList<UndirectedGraphNode>(); 
//	}	
//};


//Facebook
public class CloneGraph {

	public CloneGraph() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
	
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        //For each visited (unique) node in the old graph,  create a (new) corresponding node and put in the hashtable
        HashMap<UndirectedGraphNode, UndirectedGraphNode> hmGraph = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        //The queue corresponds to the hashtable. It is used for processing the neighbors of each visited node
        Queue<UndirectedGraphNode> quNode = new LinkedList<UndirectedGraphNode>();
        
        UndirectedGraphNode root = new UndirectedGraphNode(node.label);
        hmGraph.put(node, root);
        quNode.offer(node);

        while (!quNode.isEmpty()) {
        	UndirectedGraphNode tmpNode = quNode.poll();
        	List<UndirectedGraphNode> lstOldNeighs = tmpNode.neighbors;
        	
        	//Create new nodes corresponding to the nodes in neighbor list
        	for (UndirectedGraphNode tmpOldNeigh:lstOldNeighs) {
        		if (!hmGraph.containsKey(tmpOldNeigh)) {
        			UndirectedGraphNode tmpNewNeigh = new UndirectedGraphNode(tmpOldNeigh.label);  //Create a new node corresponding to the old node (tmpNeigh)
        			hmGraph.put(tmpOldNeigh, tmpNewNeigh);
        			quNode.offer(tmpOldNeigh);
        		}
        		
        		//Process neighbors of the new node corresponding to (the old node) tmpNode (i.e. organize the neighbors for the new node)
        		//The new node corresponding to tmpOldNeigh should be added as the neighbor for the new node corresponding to tmpNode
        		hmGraph.get(tmpNode).neighbors.add(hmGraph.get(tmpOldNeigh));  
        	}
        	
        }
             
        return root;
    }
	
}
