/*
450. Delete Node in a BST

Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
Return the root node reference (possibly updated) of the BST.

Basically, the deletion can be divided into two stages:

Search for a node to remove.
If the node is found, delete the node.
Note: Time complexity should be O(height of tree).

Example:

root = [5,3,6,2,4,null,7]
key = 3

    5
   / \
  3   6
 / \   \
2   4   7

Given key to delete is 3. So we find the node with value 3 and delete it.

One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

    5
   / \
  4   6
 /     \
2       7

Another valid answer is [5,2,6,null,4,null,7].

    5
   / \
  2   6
   \   \
    4   7
*/

//Uber
//Medium

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
public class Solution {
	
	
	//ACC: 
	//Complexity: O(h), h is height
	//Worst case occurs:  1) deleting a leaf  2) deleting any node on a full BST
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode minNode = findMin(root.right);
                root.val = minNode.val;
                root.right = deleteNode(root.right, root.val);
            }
        }

        return root;
    }
    
    private TreeNode findMin(TreeNode root) {
        while (root.left != null) root = root.left;
        
        return root;
    }
	
	
	
	
	//ACC
	//Basic Idea: 
	//1) check whether key exists
	//2) Find the target node, and replace it with the smallest node on its right sub tree
    public TreeNode deleteNodeA(TreeNode root, int key) {
        TreeNode target = root;
        TreeNode parent = null;
        boolean bLeftChild = false;
        
        while (target != null && target.val != key) {
            if (target.val < key) {
                parent = target;
                bLeftChild = false;
                target = target.right;
            } else if (target.val > key) {
                parent = target;
                bLeftChild = true;
                target = target.left;
            } else {
                break;
            }
        }
        
        if (target == null) return root;  //didn't find the key
        
        //Replace the target node with the smallest node on its right sub tree
        if (parent == null) {  //key is in the root node (target == root)
            if (root.right == null) {
                return root.left;
            } else {
                TreeNode next = root.right;
                TreeNode nextParent = null;
                while (next.left != null) {
                    nextParent = next;
                    next = next.left;
                }
                
                if (nextParent == null) {
                    next.left = root.left;
                    return next;
                } else {
                    nextParent.left = next.right;
                    next.left = root.left;
                    next.right = root.right;
                    return next;
                }
            }
        } else {
            if (target.right == null) {
                if (bLeftChild) {
                    parent.left = target.left;
                } else {
                    parent.right = target.left;
                }
            } else {
                TreeNode next = target.right;
                TreeNode nextParent = null;
                while (next.left != null) {
                    nextParent = next;
                    next = next.left;
                }
                
                if (nextParent == null) {
                    if (bLeftChild) {
                        parent.left = next;
                        next.left = target.left;
                    } else {
                        parent.right = next;
                        next.left = target.left;
                    }
                } else {
                    nextParent.left = next.right;
                    next.left = target.left;
                    next.right = target.right;
                    if (bLeftChild) {
                        parent.left = next;
                    } else {
                        parent.right = next;
                    }
                }
            }
            
            return root;
        }
        
    }

}


