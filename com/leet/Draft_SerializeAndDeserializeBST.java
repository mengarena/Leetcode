package com.leet;

import java.util.LinkedList;
import java.util.Queue;

//Serialization is the process of converting a data structure or object into a sequence of bits 
//so that it can be stored in a file or memory buffer, 
//or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
//Design an algorithm to serialize and deserialize a binary search tree. 
//There is no restriction on how your serialization/deserialization algorithm should work. 
//You just need to ensure that a binary search tree can be serialized to a string and 
//this string can be deserialized to the original tree structure.
//
//The encoded string should be as compact as possible.
//
//Note: Do not use class member/global/static variables to store states. 
//Your serialize and deserialize algorithms should be stateless.

//Amazon
//Medium
public class Draft_SerializeAndDeserializeBST {

	public Draft_SerializeAndDeserializeBST() {
		// TODO Auto-generated constructor stub
	}

	//Solution A
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) { // preorder
	    StringBuilder sb = new StringBuilder();
	    serializedfs(root, sb);
	    return sb.toString();
	}

	private void serializedfs(TreeNode root, StringBuilder sb){
	    if(root == null) return; // no "null "
	    sb.append(root.val).append(" ");
	    serializedfs(root.left, sb);
	    serializedfs(root.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
	    if(data.length() == 0) return null;
	    String[] list = data.split(" ");
	    TreeNode dummy = new TreeNode(0);
	    deserializedfs(list, 0, dummy, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
	    return dummy.left;
	}

	//tree: preorder
    private int deserializedfs(String[] list, int pos, TreeNode par, boolean isleft, 
	                                                    int lower, int upper){
	    if(pos >= list.length) return pos;

	    int val = Integer.valueOf(list[pos]);
	    if(val < lower || val > upper) return pos-1; // have not used this pos, so minus one
	    TreeNode cur = new TreeNode(val);
	    
	    if(isleft) par.left = cur;
	    else       par.right = cur;

	    pos = deserializedfs(list, ++pos, cur, true, lower, val);
	    pos = deserializedfs(list, ++pos, cur, false, val, upper);
	    return pos;
	}

	
	///////////////////////////////////////////
	
	//Solution B
//	Each tree node can be represented by "val/num/", 
//	where val is the value of the node and num indicate his children situation 
//	(num == 3 meaning having two children, num == 2 meaning having only left child, num == 1 meaning having only right child, 
//	num == 0 meaning having no child).
//
//	The time complexity for both serialize and deserialize is O(n), 
//	where n is the number of nodes in BST. 
//	The trade-off here is that I use an extra char "num" as in val/num/.	
	
	// Encodes a tree to a single string.
	public String serializeB(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int num = 0;
            if (node.left != null && node.right != null) {
                // 3 indicates having both left and right child
                num = 3;
                queue.offer(node.left);
                queue.offer(node.right);
            } else if (node.left != null) {
                // 2 indicates having left child
                num = 2;
                queue.offer(node.left);
            } else if (node.right != null) {
                // 1 indicates having right child
                num = 1;
                queue.offer(node.right);
            } // 0 indicates having no child
            
            sb.append(node.val).append("/").append(num).append("/");
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeB(String data) {
        if (data == null || data.length() < 4) return null;
        char[] text = data.toCharArray();
        int i = 0;
        TreeNode root = new TreeNode(-1);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            // Set node value
            i = readNode(text, i, node);
            // Read node's child number
            int num = 0;
            while (text[i] != '/') {
                num = num*10 + Character.getNumericValue(text[i]);
                i++;
            }
            i++;
            if (num == 3) {
                addLeftNode(node, queue);
                addRightNode(node, queue);
            } else if (num == 2) {
                addLeftNode(node, queue);
            } else if (num == 1) {
                addRightNode(node, queue);
            }
        }
        return root;
    }
    
    private int readNode(char[] text, int i, TreeNode node) {
        int val = 0;
        while (text[i] != '/') {
            val = val*10 + Character.getNumericValue(text[i]);
            i++;
        }
        node.val = val;
        return i+1;
    }
    
    private void addLeftNode(TreeNode parent, Queue<TreeNode> queue) {
        TreeNode node = new TreeNode(-1);
        parent.left = node;
        queue.offer(node);
    }
    
    private void addRightNode(TreeNode parent, Queue<TreeNode> queue) {
        TreeNode node = new TreeNode(-1);
        parent.right = node;
        queue.offer(node);
    }	
}
