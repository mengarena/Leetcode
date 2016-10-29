package com.leet;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Serialization is the process of converting a data structure or object into a sequence of bits 
//so that it can be stored in a file or memory buffer, 
//or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
//Design an algorithm to serialize and deserialize a binary tree. 
//There is no restriction on how your serialization/deserialization algorithm should work. 
//You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
//For example, you may serialize the following tree
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. 
//You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
//
//Note: Do not use class member/global/static variables to store states. 
//Your serialize and deserialize algorithms should be stateless.

//Facebook, Linkedin, Microsoft, Yahoo, Bloomberg, Google, Amazon, Uber
//Hard
public class SerializeDeserializeBinaryTree {

	public SerializeDeserializeBinaryTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(1);
		root.left = null; //new TreeNode(9);
		root.right  = new TreeNode(2);
		//root.left.left = new TreeNode(4); root.left.left.left = null; root.left.left.right = null;
		
		//root.left.right = null;
		
		root.right.left = null; //new TreeNode(15);   root.right.left.left = null;  root.right.left.right = null;
		root.right.right = null; //new TreeNode(7);
		//root.right.right.left = null;
		//root.right.right.right = new TreeNode(8);   root.right.right.right.left = null;  root.right.right.right.right = null;
		
		String sTree = serialize(root);
		System.out.println("Searalized Tree: " + sTree);
		
		TreeNode newRoot = deserialize(sTree);
		
		String sNewTree = serialize(newRoot);
		System.out.println("New Searalized Tree: " + sNewTree);
	}
	

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sTree = new StringBuilder();
        
        Queue<TreeNode> quTree = new LinkedList<TreeNode>();
        
        quTree.offer(root);
        while (!quTree.isEmpty()) {
        	TreeNode node = quTree.poll();
        	if (node == null) {
        		
        		if (sTree.length() > 0) {
        			sTree.append(",#");
        		} else {
        			sTree.append("#");
        		}
        	} else {
        		if (sTree.length() > 0) {
        			sTree.append("," + node.val);
        		} else {
        			sTree.append(node.val + "");
        		}
            	quTree.offer(node.left);
            	quTree.offer(node.right);
        	}
        }
        
        return sTree.toString();
    }
    
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String sNodes[] = data.split(",");
        int nLen = sNodes.length;
        int i;
        TreeNode tnNode[] = new TreeNode[nLen];
        int narrNullCnt[] = new int[nLen];
        int nNullCnt = 0;
        
        for (i=0; i<nLen; i++) {
        	narrNullCnt[i] = nNullCnt;
        	if (sNodes[i].equals("#")) {
        		tnNode[i] = null;
        		nNullCnt = nNullCnt + 1;
        	} else {
        		tnNode[i] = new TreeNode(Integer.valueOf(sNodes[i]));
        	}
        }
        
        for (i=0; i<nLen; i++) {
        	if (tnNode[i] == null) continue;
        	
        	int nLeftChildIdx = 2*(i-narrNullCnt[i])+1;   //Every time, a null occurs before this (parent) node, whose childrens' index will be moved forward by 2
        	int nRightChildIdx = 2*(i-narrNullCnt[i])+2;
        	
        	tnNode[i].left = tnNode[nLeftChildIdx];
        	tnNode[i].right = tnNode[nRightChildIdx];
        }
        
        
        return tnNode[0]; 
    }
    
/* Works, not efficient
	
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        String sTree = "";
        List<TreeNode> lstLevel = new ArrayList<TreeNode>();
        int nLen;
        int i;
        
        lstLevel.add(root);
        sTree = root.val + "";
        
        while (lstLevel.size() > 0) {
        	nLen = lstLevel.size();
        	for (i=0; i<nLen; i++) {
        		TreeNode tnNode = lstLevel.remove(0);
        		if (tnNode.left != null) {
        			sTree = sTree + "," + tnNode.left.val;
        			lstLevel.add(tnNode.left);
        		} else {
        			sTree = sTree + ",#";
        		}
        		
        		if (tnNode.right != null) {
        			sTree = sTree + "," + tnNode.right.val;
        			lstLevel.add(tnNode.right);
        		} else {
        			sTree = sTree + ",#";
        		}
        		
        	}
        }
                
        return sTree;
    	
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        TreeNode root = null;
        int nEndPos;
        List<String> lstNodes = new ArrayList<String>();
        int nHasChildNodeCnt = 0;
        int nLen;
        String sNode;
        List<TreeNode> lstValidNode = new ArrayList<TreeNode>();
        
        nEndPos = data.indexOf(",");
        
        while (nEndPos != -1) {
        	lstNodes.add(data.substring(0, nEndPos));
        	data = data.substring(nEndPos+1);
        	nEndPos = data.indexOf(",");
        }
        
        if (nEndPos == -1) lstNodes.add(data);
        
        nLen = lstNodes.size();
        if (nLen >= 1) {
        	sNode = lstNodes.remove(0);
        	if (sNode.equals("#")) return null;
        	root = new TreeNode(Integer.valueOf(sNode));
        	lstValidNode.add(root);
        	nHasChildNodeCnt = 1;
        }
        
        while (nHasChildNodeCnt > 0) {
        	for (int i=0; i<nHasChildNodeCnt; i++) {
        		TreeNode tnValidTmp = lstValidNode.remove(0);
        		String sNodeLeft = lstNodes.remove(0);
        		String sNodeRight = lstNodes.remove(0);
        		
        		if (sNodeLeft.equals("#")) {
        			tnValidTmp.left = null;
        		} else {
        			tnValidTmp.left = new TreeNode(Integer.valueOf(sNodeLeft));
        			lstValidNode.add(tnValidTmp.left);
        		}
        		
        		if (sNodeRight.equals("#")) {
        			tnValidTmp.right = null;
        		} else {
        			tnValidTmp.right = new TreeNode(Integer.valueOf(sNodeRight));
        			lstValidNode.add(tnValidTmp.right);
        		}
        	}
        	
        	nHasChildNodeCnt = lstValidNode.size();
        }
        
        
        return root;
    }
   */ 
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
