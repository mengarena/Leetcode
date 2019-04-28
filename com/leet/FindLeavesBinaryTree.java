package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, collect a tree's nodes as if you were doing this: 
//Collect and remove all leaves, repeat until the tree is empty.
//
//Example:
//Given binary tree 
//          1
//         / \
//        2   3
//       / \     
//      4   5    
//      
//Returns [4, 5, 3], [2], [1].
//
//Explanation:
//1. Removing the leaves [4, 5, 3] would result in this tree:
//
//          1
//         / 
//        2    
//        
//2. Now removing the leaf [2] would result in this tree:
//
//          1       
//          
//3. Now removing the leaf [1] would result in the empty tree:
//
//          []  
//        		  
//Returns [4, 5, 3], [2], [1].


//Linkedin
public class FindLeavesBinaryTree {
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	
	
	//ACC: 26%
	//Strategy:  The leaves could be divided layers, not get the leaves at the same layer at once.
	//The bottom leaves (raw leaves) is at level 0
	//O(n), O(n)
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> lstlstLeaves = new ArrayList<List<Integer>>();

        if (root == null) return lstlstLeaves;
        
        findLeavesHelper(root, lstlstLeaves);
        
        return lstlstLeaves;
    }
    
    private int findLeavesHelper(TreeNode root, List<List<Integer>> lstlstLeaves) {
        if (root == null) return -1;
        
	// Valid Level starts from 0 (if a node is null, its level is -1)
        int level = 1 + Math.max(findLeavesHelper(root.left, lstlstLeaves), findLeavesHelper(root.right, lstlstLeaves));
        
	// Since valid level starts from 0, so need to use +1 to compare with size()
        if (level+1 > lstlstLeaves.size()) {
            List<Integer> lstLeaves = new ArrayList<>();
            lstlstLeaves.add(lstLeaves);
        }
        
        lstlstLeaves.get(level).add(root.val);
        
        return level;
        
    }
	
	
	
    private List<List<Integer>> lstlstLeaves = new ArrayList<List<Integer>>();
    
    public List<List<Integer>> findLeavesA(TreeNode root) {
        if (root == null) return lstlstLeaves;
        
        findLeavesHelper(root);
        
        return lstlstLeaves;
    }
    
    private int findLeavesHelper(TreeNode root) {
        if (root == null) return -1;
        
        int level = 1 + Math.max(findLeavesHelper(root.left), findLeavesHelper(root.right));
        
        if (level+1 > lstlstLeaves.size()) {
            List<Integer> lstLeaves = new ArrayList<>();
            lstlstLeaves.add(lstLeaves);
        }
        
        lstlstLeaves.get(level).add(root.val);
        
        return level;
        
    }
}
