package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//There are a total of n courses you have to take, labeled from 0 to n - 1.
//
//Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
//For example:
//
//2, [[1,0]]
//There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
//
//2, [[1,0],[0,1]]
//There are a total of 2 courses to take. 
//To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
//
//Note:
//The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
//
//click to show more hints.
//
//Hints:
//This problem is equivalent to finding if a cycle exists in a directed graph. 
//If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
//Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
//Topological sort could also be done via BFS.


//   https://www.youtube.com/watch?v=jksMzq4LgfM
	
//Zenefits
public class CourseSchedule {

	public CourseSchedule() {
		// TODO Auto-generated constructor stub
	}

	
	public void run() {
		int[][] prerequisites = {{1,0}};   //true
//		int[][] prerequisites = {{1,0},{0,1}};  //false
//		int[][] prerequisites = {{2,0},{2,1}};  //true
//		int[][] prerequisites = {{0,1},{0,2},{1,2}};  //true
		
		int numCourses = 2;
		
		System.out.println("canFinish = " + canFinish(numCourses, prerequisites));
	}
	
	
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    	if (prerequisites == null || prerequisites.length == 1) return true;
        int nEdgeCnt = prerequisites.length;
        HashMap<Integer, Set<Integer>> hmGraph = new HashMap<Integer, Set<Integer>>();  //Node, Neighbors
        int i;
        List<Boolean> lstVisited = new ArrayList<Boolean>();  //Default value is false, visited or not
        Set<Integer> setVisited = new HashSet<Integer>();   //Record node all whose neighbors have been processed
       
        //Construct the graph
        for (i=0; i<nEdgeCnt; i++) {
        	int nParent = prerequisites[i][1];
        	int nChild = prerequisites[i][0];
        	
        	if (hmGraph.get(nParent) != null) {
        		Set<Integer> setAdj= hmGraph.get(nParent);
        		setAdj.add(nChild);
        		hmGraph.replace(nParent, setAdj);
        	} else {
        		Set<Integer> setAdj = new HashSet<Integer>();
        		setAdj.add(nChild);
        		hmGraph.put(nParent, setAdj);
        	}
        }
    	
        for (i=0; i<numCourses; i++) {
        	if (!lstVisited.get(i)) {
        		boolean bRet = dfsGraph(hmGraph, i, lstVisited, setVisited);
        		if (bRet == false) return false;
        	}
        }
        
        return true;
    }	
    
    
    private boolean dfsGraph(HashMap<Integer, Set<Integer>> hmGraph, int nStartPos, List<Boolean> lstVisited, Set<Integer> setVisited) {
    	lstVisited.set(nStartPos, true);
    	
    	Set<Integer> setAdj = hmGraph.get(nStartPos);
    	if (setAdj == null) {
    		setVisited.add(nStartPos);
    		return true;
    	}
    	
    	for (int nNode:setAdj) { 
    		//If the node has been visited, but has not been put in visited set, and it is less than one of its parent (or grand..parent), there should be a cycle
    		if (nNode < nStartPos && !setVisited.contains(nNode) && lstVisited.get(nNode)) return false;   
    		
    		if (!lstVisited.get(nNode)) {
    			boolean bRet = dfsGraph(hmGraph, nNode, lstVisited, setVisited);
    			if (bRet == false) return false;
    		}
    	}
    	
    	setVisited.add(nStartPos);
    	
    	return true;
    }
}
