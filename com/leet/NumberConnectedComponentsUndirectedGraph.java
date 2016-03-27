package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
//
//Example 1:
//     0          3
//     |          |
//     1 --- 2    4
//Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
//
//Example 2:
//     0           4
//     |           |
//     1 --- 2 --- 3
//Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
//
//Note:
//You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

//Google
public class NumberConnectedComponentsUndirectedGraph {

	public NumberConnectedComponentsUndirectedGraph() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[][] edges = {{2,3}, {1,2}, {1,3}};
		
		System.out.println(countComponents(4, edges));
	}
	
	
	//Refer to:  https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
    public int countComponents(int n, int[][] edges) {
    	if (edges == null || edges.length == 0 || edges[0].length == 0) return n;    	    	
        int[] roots = new int[n];
        int m = edges.length;
        int i;
        int nCount = n;
	
        for (i=0; i<n; i++) roots[i] = i;
        
        for (i=0; i<m; i++) {
        	int root1 = root(roots, edges[i][0]);
        	int root2 = root(roots, edges[i][1]);
        	
        	if (root1 != root2) {
        		roots[root1] = root2;    //Connect
        	    nCount--;
        	}
        }
        
        return nCount;
    }
    
    
    private void unite(int[] roots, int p, int q) {
    	int i = root(roots, p);
    	int j = root(roots, q);
    	roots[i] = j;
    }
    
    private int root(int[] roots, int i) {
    	while (i != roots[i]) {
    		roots[i] = roots[roots[i]];  //Path compression
    		i = roots[i];
    	}
    	
    	return i;
    }
	
    //Check whether two nodes are connected
    private boolean IsConnected(int[] roots, int p, int q) {
    	return roots[p] == roots[q];
    }
    
    
	//AC:  27%
	//For each edge, for example, [1, 2], record both [1, 2] and [2, 1] in hashmap
	//And then use DFS to search
    public int countComponentsA(int n, int[][] edges) {
    	if (edges == null || edges.length == 0 || edges[0].length == 0) return n;    	    	
        boolean barrVisited[] = new boolean[n];
        int m = edges.length;
        int i;
        int nCount = 0;

        Map<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
        
        for (i=0; i<m; i++) {
    		if (hm.containsKey(edges[i][0])) {
    			hm.get(edges[i][0]).add(edges[i][1]);
    		} else {
    			List<Integer> lstConnected = new ArrayList<Integer>();
    			lstConnected.add(edges[i][1]);
    			hm.put(edges[i][0], lstConnected);
    		}
    		
    		if (hm.containsKey(edges[i][1])) {
    			hm.get(edges[i][1]).add(edges[i][0]);
    		} else {
    			List<Integer> lstConnected = new ArrayList<Integer>();
    			lstConnected.add(edges[i][0]);
    			hm.put(edges[i][1], lstConnected);
    		}
    		
        }
                
        for (i=0; i<n; i++) {
            if (barrVisited[i] == false) {
                nCount++;
                
                barrVisited[i] = true;
                
                if (hm.containsKey(i)) {
                    List<Integer> lstConnected = hm.get(i);
                    
                    processConnected(barrVisited, lstConnected, hm);    
                }
                
            }
        }
                
        return nCount;
    }
    
    //DFS
    private void processConnected(boolean[] barrVisited, List<Integer> lstConnected, Map<Integer, List<Integer>> hm) {
         for (int i:lstConnected) {
             if (barrVisited[i] == true) continue;
             
             barrVisited[i] = true;
             List<Integer> tmplstConnected = hm.get(i);
             processConnected(barrVisited, tmplstConnected, hm);
         }   
    }

}
