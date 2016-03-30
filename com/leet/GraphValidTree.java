package com.leet;

//Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
//write a function to check whether these edges make up a valid tree.
//
//For example:
//
//Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
//
//Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
//
//Hint:
//
//Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
//According to the definition of tree on Wikipedia: ¡°a tree is an undirected graph in which any two vertices are connected by exactly one path. 
//In other words, any connected graph without simple cycles is a tree.¡±
//Note: you can assume that no duplicate edges will appear in edges. 
//Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.


//Google, Facebook, Zenefits
public class GraphValidTree {

	public GraphValidTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//int[][] edges = {{2, 3}, {1, 2}, {1, 3}};
		int[][] edges = {{0, 1}, {0, 2}, {1, 2}};
		
		boolean bRet = validTree(3, edges);
		
		System.out.println(bRet);
	}
	
	
	//Union Find:   Refer to:  https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
	//Similar to [Number Connected Component Undirected Graph]
	//AC: 72%
    public boolean validTree(int n, int[][] edges) {
        if (edges == null || edges.length < n-1 || (edges.length > 0 && edges[0].length < 2) || edges.length >= n) return false;
        int nEdgeCnt = edges.length;
        int roots[] = new int[n];
        int i;
        int nCount = n;
        
        for (i=0; i<n; i++) roots[i] = i;
        
        for (i=0; i<nEdgeCnt; i++) {
            int root1 = root(roots, edges[i][0]);
            int root2 = root(roots, edges[i][1]);
        
            if (root1 == root2) return false;
            
            if (root1 != root2) {
                roots[root1] = root2;
                nCount--;
            }
        }
        
        return nCount == 1;
    }	
    
    
    private int root(int[] roots, int i) {
        while (i != roots[i]) {
            roots[i] = roots[roots[i]];
            i = roots[i];
        }
        
        return i;
    }
}
