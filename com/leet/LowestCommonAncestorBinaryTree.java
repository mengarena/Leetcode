package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
//
//According to the definition of LCA on Wikipedia: 
//¡°The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants 
//(where we allow a node to be a descendant of itself).¡±
//
//        _______3______
//       /              \
//    ___5__          ___1__
//   /      \        /      \
//   6      _2       0       8
//         /  \
//         7   4
//For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. 
//Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

//Facebook, Linkedin, Microsoft
public class LowestCommonAncestorBinaryTree {

	public LowestCommonAncestorBinaryTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(5);
		root.right = new TreeNode(1);
		
		root.left.left = new TreeNode(6);  root.left.left.left = null; root.left.left.right = null;
		
		root.left.right = new TreeNode(2); 
		root.left.right.left = new TreeNode(7); root.left.right.left.left = null; root.left.right.left.right = null;
		root.left.right.right = new TreeNode(4); root.left.right.right.left = null; root.left.right.right.right = null;
		
		root.right.left = new TreeNode(0);  root.right.left.left = null;  root.right.left.right = null;
		root.right.right = new TreeNode(8);  root.right.right.left = null;  root.right.right.right = null;
		
		TreeNode p = new TreeNode(7);
		TreeNode q = new TreeNode(4);
		
		TreeNode lcaNode = lowestCommonAncestor(root, p, q);
		System.out.println("Com Node= " + lcaNode.val);
	}
	
	//This function return the first one which is p or q in each recursion
	//Complixity: O(n), just DFS
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;   //Don't use val to compare. In test case, 
	                         //there might be two nodes, which have same values, but the two nodes are different
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) return root;   //When this happens, must be: one of p,q is found in left sub tree; 
	                                            //the other if found in right sub tree. Their common node must be the root. 
        
        //If not the above case, the common node must be in the left or right sub tree. Up-propagate the common.
        if (left == null) {
        	return right;
        } else {
        	return left;
        }
    
    }
    
    
    //Iterative
    public TreeNode lowestCommonAncestorA(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> hm = new HashMap<>();
        Queue<TreeNode> qu = new LinkedList<>();
        
        hm.put(root, null);
        qu.offer(root);
        
        //Set parents for all nodes
        while (!hm.containsKey(p) || !hm.containsKey(q)) {
            TreeNode tmp = qu.poll();
            
            if (tmp.left != null) {
                hm.put(tmp.left, tmp);
                qu.offer(tmp.left);
            }
            
            if (tmp.right != null) {
                hm.put(tmp.right, tmp);
                qu.offer(tmp.right);
            }
        }
        
        Set<TreeNode> st = new HashSet<>();
        
        //Trace back to root 
        while (p != null) {
            st.add(p);
            p = hm.get(p);
        }
        
        //Trace back to root and check whether find a common ancestor
        while (!st.contains(q)) {
            q = hm.get(q);
        }
        
        return q;
    }
    
 
/*    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode lcaNode = root;
        
        List<TreeNode> lstP = getPath(root, p);
        List<TreeNode> lstQ = getPath(root, q);
        
        int nPCnt = 0; 
        int nQCnt = 0;
        int i;
        
        if (lstP != null) nPCnt = lstP.size();
        if (lstQ != null) nQCnt = lstQ.size();
        
        int nCnt = Math.min(nPCnt, nQCnt);
        TreeNode tnP, tnQ;
        
        for (i=0; i<nCnt; i++) {
        	tnP = lstP.get(i);
        	tnQ = lstQ.get(i);
        	
        	if (tnP == tnQ) {
        		lcaNode = tnP;
        	} else {
        		break;
        	}
        }
        
        return lcaNode;
    }
    
    
    public List<TreeNode> getPath(TreeNode root, TreeNode m) {
    	List<TreeNode> lstPath = null; 
    	
    	if (root == null) return null;
    	
    	if (root.val == m.val) {
    		lstPath = new ArrayList<TreeNode>();
    		lstPath.add(root);
    		return lstPath;
    	} else if (root.left == null && root.right == null) {
    		return null;
    	}
    
		lstPath = getPath(root.left, m);
		
		if (lstPath == null) {
			lstPath = getPath(root.right, m);
			if (lstPath != null) {
				lstPath.add(0, root);
			}
		} else {
			lstPath.add(0, root);
		}
    	
    	
    	return lstPath;
    }
 */   
   
}
