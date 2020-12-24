package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

//For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. 
//Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
//Given such a graph, write a function to find all the MHTs and return a list of their root labels.
//
//Format
//The graph contains n nodes which are labeled from 0 to n - 1. 
//You will be given the number n and a list of undirected edges (each edge is a pair of labels).
//
//You can assume that no duplicate edges will appear in edges. 
//Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
//
//Example 1:
//
//Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
//
//        0
//        |
//        1
//       / \
//      2   3
//return [1]
//
//Example 2:
//
//Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
//
//     0  1  2
//      \ | /
//        3
//        |
//        4
//        |
//        5
//return [3, 4]
//
//Hint:
//
//How many MHTs can a graph have at most?
//Note:
//
//(1) According to the definition of tree on Wikipedia: 
//¡°a tree is an undirected graph in which any two vertices are connected by exactly one path. 
//In other words, any connected graph without simple cycles is a tree.¡±
//
//(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

//The height of a rooted tree is the number of edges on the longest downward path between root and a leaf.

//Google
public class MinimumHeightTrees {

	public MinimumHeightTrees() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
//		int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
//		int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4},{5,6},{5,7}, {4, 8}};
		int[][] edges = {{0,1}};
		
		int n = 2;
		
		List<Integer> lstMinHeightTree = findMinHeightTrees(n, edges);
		
		for (Integer nVal:lstMinHeightTree) System.out.print(nVal + ",");
		
	}
	

 
	//The MHT root must be on the longest path of the tree
	//If the #node on the longest path is even, there will be two roots (in the middle) with MHT; otherwise, there will be only one root (the middle point)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> lstMinHeightTree = new ArrayList<Integer>();
        int nEdgeCnt = edges.length;
        int i;
        
        if (n == 1) {
        	lstMinHeightTree.add(0);
        	return lstMinHeightTree;
        } else if (n == 2) {
        	lstMinHeightTree.add(0);
        	lstMinHeightTree.add(1);
        	return lstMinHeightTree;
        }
        
        HashMap<Integer, List<Integer>> hmTree = new HashMap<Integer, List<Integer>>();   //Node, its adjacents
        
        //Construct the graph
        for (i=0; i<nEdgeCnt; i++) {
        	int[] edge = edges[i];
        	if (hmTree.containsKey(edge[0])) {
        		List<Integer> lstNeigh = hmTree.get(edge[0]);
        		lstNeigh.add(edge[1]);
        		hmTree.put(edge[0], lstNeigh);
        	} else {
        		List<Integer> lstNeigh = new ArrayList<Integer>();
        		lstNeigh.add(edge[1]);
        		hmTree.put(edge[0], lstNeigh);
        	}
        	
        	if (hmTree.containsKey(edge[1])) {
        		List<Integer> lstNeigh = hmTree.get(edge[1]);
        		lstNeigh.add(edge[0]);
        		hmTree.put(edge[1], lstNeigh);
        	} else {
        		List<Integer> lstNeigh = new ArrayList<Integer>();
        		lstNeigh.add(edge[0]);
        		hmTree.put(edge[1], lstNeigh);
        	}	
        }
        
    	List<Integer> lstLeaf = new ArrayList<Integer>();
        
    	//Find all the leaves
    	for (i=0; i<n; i++) {
    		if (hmTree.get(i) != null && hmTree.get(i).size() == 1) {  //Only one neighbor
    			lstLeaf.add(i);
    		}
    	}
    
        while (n > 2) {
        	
        	List<Integer> lstLeafTmp = new ArrayList<Integer>();  //Remember the new leaves
        	
        	//Remove current leaves, and find new leaves (after removing current leaf, if a node has only one neighbor left, it becomes a new leaf)
        	for (int nLeaf:lstLeaf) {
        		List<Integer> lstNeigh = hmTree.get(nLeaf);   //only one node in lstNeigh
        		if (lstNeigh != null) {
        			int nNeigh = lstNeigh.get(0);
        			
        			List<Integer> lstNeighTmp = hmTree.get(nNeigh);
        			if (lstNeighTmp != null) {
        				lstNeighTmp.remove(Integer.valueOf(nLeaf));   //Remove the nLeaf related edge
        				if (lstNeighTmp.size() == 1)  lstLeafTmp.add(nNeigh);  //Key step
        			}
        			
        		}
        		
        	}
        	
        	n = n - lstLeaf.size();
        	lstLeaf = lstLeafTmp;
        }
        
        lstMinHeightTree = lstLeaf;
        
        return lstMinHeightTree;
    }	
    
    
    
    
    
 /*   
    //Prune leaves round by round
    private void pruneTree(HashMap<Integer, List<Integer>> hmTree, int n) {
    	List<Integer> lstLeaf = new ArrayList<Integer>();
    	
    	if (hmTree.size() <= 2) return;
    	
    	//Find all the leaves
    	for (int i=0; i<n; i++) {
    		if (hmTree.get(i) != null && hmTree.get(i).size() == 1) {
    			lstLeaf.add(i);
    		}
    	}
    	
    	//Prune the leaves
    	for (Integer nLeaf:lstLeaf) {
    		List<Integer> lstNeigh = hmTree.get(nLeaf);
    		if (lstNeigh != null) {
    			int nNeigh = lstNeigh.get(0);
    			
    			List<Integer> lstNeighTmp = hmTree.get(nNeigh);
    			if (lstNeighTmp != null) {
    				lstNeighTmp.remove(Integer.valueOf(nLeaf));   //Remove the nLeaf related edge
    			}
    			
    		}
    		
    		hmTree.remove(nLeaf);
    	}
    	    	
		pruneTree(hmTree, n);
    }
*/    
    
}
