package com.leet;

import java.util.LinkedList;
import java.util.Queue;

//Find the sum of all left leaves in a given binary tree.
//
//Example:
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.


//Facebook
public class SumOfLeftLeaves {

	public SumOfLeftLeaves() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC: 12ms
	//Attention: All left Leaf nodes (not the most left Leaf node)
	//Root is not counted
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return 0;
        Queue<TreeNode> quLevel = new LinkedList<TreeNode>();
        Queue<Integer> quLeftRight = new LinkedList<Integer>();   //0: root,  1:  left, -1: right
        int sum = 0;
        int size = 0;
        TreeNode tmp;
        int i;
        int child = 0;

        quLevel.offer(root);
        quLeftRight.offer(1);
        
        while (!quLevel.isEmpty()) {
            size = quLevel.size();
            
            for (i=1; i<=size; i++) {
                tmp = quLevel.poll();
                child = quLeftRight.poll();
                
                if (tmp.left == null && tmp.right == null && child == 1) sum += tmp.val;
                            
                if (tmp.left != null) {
                    quLevel.offer(tmp.left);
                    quLeftRight.offer(1);
                }
                
                if (tmp.right != null) {
                    quLevel.offer(tmp.right);
                    quLeftRight.offer(-1);
                }
            }
        }
        
        return sum;
    }

}
