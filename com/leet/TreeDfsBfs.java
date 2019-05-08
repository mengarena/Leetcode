package com.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Get Tree Path with BFS and DFS

public class TreeDfsBfs {

	public TreeDfsBfs() {
		// TODO Auto-generated constructor stub
	}

    public void run() {
    	TreeNode head = new TreeNode(1);
    	head.left = new TreeNode(2);
    	head.right = new TreeNode(3);
    	
    	head.left.left = new TreeNode(4);
    	head.left.right = new TreeNode(5);
    	
    	head.right.left = new TreeNode(6);
    	
    	head.left.left.left = new TreeNode(7);
    	
    	head.left.right.left = new TreeNode(8);
    	head.left.right.right = new TreeNode(9);
    	head.left.right.left.right = new TreeNode(11);
    	
    	head.right.left.right = new TreeNode(10);
    	
    	List<List<Integer>> lstlstBFS = TreeBFS(head);
    	System.out.println("BFS:");
    	printList(lstlstBFS);
    	
    	List<List<Integer>> lstlstDFS = TreeDFS(head);
    	System.out.println("DFS:");
    	printList(lstlstDFS);
    	
    	List<List<Integer>> lstlstDFS_Recursive = TreeDFS_Recursive(head);
    	System.out.println("Recursive DFS:");
    	printList(lstlstDFS_Recursive);
    }
    
    private void printList(List<List<Integer>> lstlstPath) {
    	
    	for (List<Integer> lstPath:lstlstPath) {
    		System.out.print("[");
    		for (int num:lstPath) {
    			System.out.print(num + ",");
    		}
    		System.out.println("]");
    	}
    }

    
    private List<List<Integer>> TreeBFS(TreeNode head) {
    	List<List<TreeNode>> lstlstPath = new ArrayList<List<TreeNode>>();
    	List<TreeNode> lstPath = new ArrayList<TreeNode>();
    	List<List<Integer>> lstlstBFS = new ArrayList<List<Integer>>();
    	
    	if (head == null) return lstlstBFS;
    	lstPath.add(head);
    	lstlstPath.add(lstPath);
    	
    	boolean bEnd = false;
    	int i;
    	int size;
    	TreeNode endNode = null; 
    	
    	while (bEnd == false) {
    	    bEnd = true;
    	    size = lstlstPath.size();
    	    for (i=size-1; i>=0; i--) {
    	    	lstPath = lstlstPath.get(i);
    	    	endNode = lstPath.get(lstPath.size()-1);
    	    	if (endNode.left == null && endNode.right == null) continue;
    	    	
    	    	bEnd = false;
    	    	if (endNode.right != null) {
    	    	    List<TreeNode> lstPathNew = new ArrayList<TreeNode>(lstPath);
    	    	    lstPathNew.add(endNode.right);
    	    	    lstlstPath.set(i, lstPathNew);
    	    	}
    	    	
    	    	if (endNode.left != null) {
    	    	    lstPath.add(endNode.left);
    	    	    if (endNode.right != null) {
    	    	        lstlstPath.add(i, lstPath);
    	    	    } 
    	    	}
    	    }
    	}
    	
    	lstlstBFS = getListValues(lstlstPath);
    	
    	return lstlstBFS;
    }
    
    
    private List<List<Integer>> getListValues(List<List<TreeNode>> lstlstPath) {
    	List<List<Integer>> lstlstBDFS = new ArrayList<List<Integer>>();
    	
    	for (List<TreeNode> lstPathTmp:lstlstPath) {
    		List<Integer> lstBDFS = new ArrayList<Integer>();
    		
    		for (TreeNode tmpNode:lstPathTmp) {
    		    lstBDFS.add(tmpNode.val);
    		}
    		
    		lstlstBDFS.add(lstBDFS);
    	}
    	
    	return lstlstBDFS;
    }
    
    
    private List<List<Integer>> TreeDFS(TreeNode head) {
    	List<List<TreeNode>> lstlstPath = new ArrayList<List<TreeNode>>();
    	List<List<Integer>> lstlstDFS = new ArrayList<List<Integer>>();
    	List<TreeNode> lstPath = new ArrayList<TreeNode>();
    	Set<TreeNode> setRight = new HashSet<TreeNode>();
    	TreeNode tmpNode = null;
    	
    	if (head == null) return lstlstDFS;
        while (head != null) {
            lstPath.add(head);
            head = head.left;
        }
    	
    	while (!lstPath.isEmpty()) {
    		tmpNode = lstPath.get(lstPath.size()-1);
            
    		if (tmpNode.left == null && tmpNode.right == null) {
    			lstlstPath.add(new ArrayList<TreeNode>(lstPath));
    			lstPath.remove(lstPath.size()-1);
    		} else if (tmpNode.right != null && !setRight.contains(tmpNode.right)) {
    			setRight.add(tmpNode.right);
    			tmpNode = tmpNode.right;
    			while (tmpNode != null) {
    				lstPath.add(tmpNode);
    				tmpNode = tmpNode.left;
    			}
    		} else {
    			lstPath.remove(lstPath.size()-1);
    		}
    	}
    	
    	lstlstDFS = getListValues(lstlstPath);
    	
    	return lstlstDFS;
    	
    } 
    
    ////////////////////////////////////////
    private List<List<Integer>> TreeDFS_Recursive(TreeNode head) {
    	List<List<Integer>> lstlstFS = new ArrayList<List<Integer>>();
    	if (head == null) return lstlstFS;
    	
    	if (head.left == null && head.right == null) {
    		List<Integer> lstFS = new ArrayList<Integer>();
    		lstFS.add(head.val);
    		lstlstFS.add(lstFS);
    		return lstlstFS;
    	}
    	
    	List<List<Integer>> lstlstLeft = TreeDFS_Recursive(head.left);
    	List<List<Integer>> lstlstRight = TreeDFS_Recursive(head.right);
    	
    	for (List<Integer> lstLeft:lstlstLeft) {
    		lstLeft.add(0, head.val);
    		lstlstFS.add(lstLeft);
    	}
    	
    	for (List<Integer> lstRight:lstlstRight) {
    		lstRight.add(0, head.val);
    		lstlstFS.add(lstRight);
    	}
    	
    	return lstlstFS;
    }

}

