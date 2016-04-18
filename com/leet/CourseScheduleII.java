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
//Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
//
//For example:
//
//2, [[1,0]]
//There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
//
//4, [[1,0],[2,0],[3,1],[3,2]]
//There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
//Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
//
//Note:
//The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.

//Hints:
//This problem is equivalent to finding the topological order in a directed graph. 
//If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
//Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
//Topological sort could also be done via BFS.


//Facebook, Zenefits
public class CourseScheduleII {

	public CourseScheduleII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int numCourses = 2;
		int[][] prerequisites = {};
		int[] kk = findOrder(numCourses, prerequisites);
		
		System.out.println("dddd");
	}
	
	
	//acc;  24%
	//Task: Anyway you have to complete the courses, even there is no prerequisites between courses exist
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	int[] narrCourseOrder = new int[numCourses];
    	int nEdgeCnt = prerequisites.length;
        int i;
        
        //Important:  Anyway, has to complete all courses. So there is not prerequisites relationship between courses, 
        //Still need to take the courses. So can take the course in order
        //This "if" block could also be omitted, because the for (marked as ** below) covers the function of this part
    	if (nEdgeCnt == 0) {    //Could be removed
        	for (i=0; i<numCourses; i++) {
        		narrCourseOrder[i] = i;
        	}
        	return narrCourseOrder;    		
    	}
    	
        HashMap<Integer, Set<Integer>> hmGraph = new HashMap<Integer, Set<Integer>>();    //Parent Node, Neighbors (children)

        List<Boolean> lstVisited = new ArrayList<Boolean>();
        List<Integer> lstCourses = new ArrayList<Integer>();  //Ordered courses, last one comes first
        
        for (i=0; i<numCourses; i++) lstVisited.add(false);
 
        //Construct the graph
        for (i=0; i<nEdgeCnt; i++) {
        	int nParent = prerequisites[i][1];
        	int nChild = prerequisites[i][0];
        	        	
        	if (hmGraph.containsKey(nParent)) {
        		Set<Integer> setChildren = hmGraph.get(nParent);
        		setChildren.add(nChild);
        		hmGraph.replace(nParent, setChildren);
        	} else {
        		Set<Integer> setChildren = new HashSet<Integer>();
        		setChildren.add(nChild);
        		hmGraph.put(nParent, setChildren);
        	}
        }
                 
        for (i=0; i<numCourses; i++) {   //** Node
        	if (!lstVisited.get(i)) {
        		boolean bRet = dfsFindCourses(hmGraph, i, lstVisited, lstCourses);
        		if (bRet == false) return new int[0];   //If there a loop, return []
        	}
        }
        
        narrCourseOrder = new int[numCourses];
        
        //Reverse order
        for (i=lstCourses.size()-1; i>=0; i--) {
        	narrCourseOrder[lstCourses.size()-1-i] = lstCourses.get(i);
        }
        
        return narrCourseOrder;
    }
	
    
    private boolean dfsFindCourses(HashMap<Integer, Set<Integer>> hmGraph, int nStartPos, List<Boolean> lstVisited, List<Integer> lstCourses) {
    	lstVisited.set(nStartPos, true);
    	
    	Set<Integer> setAdj = hmGraph.get(nStartPos);
    	if (setAdj == null) {
    		lstCourses.add(nStartPos);
    		return true;
    	}
    	
    	for (int nNode:setAdj) {
    		//Detect whether loop exists
    		if (nNode < nStartPos && !lstCourses.contains(nNode) && lstVisited.get(nNode)) return false;
    		
    		if (!lstVisited.get(nNode)) {
    			boolean bRet = dfsFindCourses(hmGraph, nNode, lstVisited, lstCourses);
    			if (bRet == false) return false;
    		}
    	}
    	
    	lstCourses.add(nStartPos);
    	
    	return true;
    }
    
}
