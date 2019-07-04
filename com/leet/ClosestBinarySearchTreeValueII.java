package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
//
//Note:
//Given target value is a floating point.
//You may assume k is always valid, that is: k ¡Ü total nodes.
//You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
//
//Follow up:
//Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
//
//Hint:
//
//Consider implement these two helper functions:
//getPredecessor(N), which returns the next smaller node to N.
//getSuccessor(N), which returns the next larger node to N.
//Try to assume that each node has a parent pointer, it makes the problem much easier.
//Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
//You would need two stacks to track the path in finding predecessor and successor node separately.


//Google
//Hard
public class ClosestBinarySearchTreeValueII {

	public ClosestBinarySearchTreeValueII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right = new TreeNode(4);
		root.left.left = new TreeNode(1);
		
		List<Integer> lstKValues = closestKValues(root, 1.00000, 3);
		
		for (Integer nval:lstKValues) System.out.println(nval);
		
		System.out.println();
	}
	
	
	//ACC:  34%
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> lstKValues = new ArrayList<Integer>();
        if (root == null || k == 0) return lstKValues;
        Stack<TreeNode> stkPred = new Stack<TreeNode>();
        Stack<TreeNode> stkSucc = new Stack<TreeNode>();
        
        setPredStack(root, target, stkPred);
        setSuccStack(root, target, stkSucc);
        
        if (!stkPred.isEmpty() && !stkSucc.isEmpty() && stkPred.peek().val == stkSucc.peek().val) {  
		//In case the same node is both pushed to stkPred and stkSucc
        	getPrecedessor(stkPred);    
        }
        
        while (k > 0) {
        	if (stkSucc.isEmpty()) {
        		lstKValues.add(getPrecedessor(stkPred));
        	} else if (stkPred.isEmpty()) {
        		lstKValues.add(getSuccessor(stkSucc));
        	} else {
        		if (Math.abs(stkPred.peek().val - target) > Math.abs(stkSucc.peek().val - target) ) {
        			lstKValues.add(getSuccessor(stkSucc));
        		} else {
        			lstKValues.add(getPrecedessor(stkPred));
        		}
        	}
        	
        	k--;
        }
        
        return lstKValues;
    }
	
    
    private void setPredStack(TreeNode root, double target, Stack<TreeNode> stkPred) {
    	while (root != null) {
    		if (root.val == target) {
    			stkPred.push(root);
    			break;
    		} else if (root.val < target) {
    			stkPred.push(root);
    			root = root.right;
    		} else {
    			root = root.left;
    		}
    	}
    }

    private void setSuccStack(TreeNode root, double target, Stack<TreeNode> stkSucc) {
    	while (root != null) {
    		if (root.val == target) {
    			stkSucc.push(root);
    			break;
    		} else if (root.val < target) {
    			root = root.right;
    		} else {
    			stkSucc.push(root);
    			root = root.left;
    		}
    	}
    }
    
    
    private int getPrecedessor(Stack<TreeNode> stkPred) {
    	TreeNode tn = stkPred.pop();
    	TreeNode tmp = tn.left;
    	
    	while (tmp != null) {
    		stkPred.push(tmp);
    		tmp = tmp.right;
    	}
    	
    	return tn.val;
    }
    
    
    
    private int getSuccessor(Stack<TreeNode> stkSucc) {
    	TreeNode tn = stkSucc.pop();
    	TreeNode tmp = tn.right;
    	
    	while (tmp != null) {
    		stkSucc.push(tmp);
    		tmp = tmp.left;
    	}
    	
    	return tn.val;
    }
    
    
    
    
    
    
    
/*	
	//ACC:  909 ms
	private Stack<Integer> stkA = new Stack<Integer>();
	private Stack<Integer> stkB = new Stack<Integer>();
	
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> lstKValues = new ArrayList<Integer>();
        if (root == null || k == 0) return lstKValues;
        stkA = new Stack<Integer>();
        stkB = new Stack<Integer>();
        
    	inorderTraverse(root);
        
        int nCount = 0;
        int nPred = Integer.MAX_VALUE, nSucc = Integer.MIN_VALUE;
        double predTarget = target;
        double succTarget = target;
        
        while (nCount < k) {
        	nPred = getPrecedessor(predTarget);
        	nSucc = getSuccessor(succTarget);
        	
        	if (nPred == Integer.MAX_VALUE && nSucc == Integer.MIN_VALUE) break;
        	
        	if (nPred == Integer.MAX_VALUE && nSucc != Integer.MIN_VALUE) {
        		lstKValues.add(nSucc);
        		succTarget = nSucc+0.1;
        	} else if (nPred != Integer.MAX_VALUE && nSucc == Integer.MIN_VALUE) {
        		//lstKValues.add(0, nPred);
        		lstKValues.add(nPred);
        		predTarget = nPred-0.1;
        	} else {
        		if (Math.abs(nSucc - target) < Math.abs(nPred - target)) {
        			lstKValues.add(nSucc);
        			succTarget = nSucc+0.1;
        		} else {
        			//lstKValues.add(0, nPred);
        			lstKValues.add(nPred);
        			predTarget = nPred-0.1;
        		}
        	}
        	
        	nCount++;
        }
        
        return lstKValues;
    }

    private void inorderTraverse(TreeNode root) {
    	if (root == null) return;
    	
    	inorderTraverse(root.left);
    	
    	stkA.push(root.val);
    	
    	inorderTraverse(root.right);
    }
    
    
    private int getPrecedessor(double N) {
    	if (!stkA.isEmpty() && stkA.peek() >= N) {
	        while (!stkA.isEmpty() && stkA.peek() >= N) {
	        	stkB.push(stkA.pop());
	        }
	        
	        if (stkA.isEmpty()) return Integer.MAX_VALUE;
	        return stkA.peek();
    	} else if (!stkA.isEmpty() && stkA.peek() < N) {
    		if (stkB.isEmpty()) return stkA.peek();
    		
    		if (!stkB.isEmpty() && stkB.peek() >= N) {
    			return stkA.peek();
    		} else {
    			int nRet = Integer.MAX_VALUE;
    			while (!stkB.isEmpty() && stkB.peek() < N) {
    				nRet = stkB.pop();
    				stkA.push(nRet);
    			}
    			return nRet;
    		}
    	} else {   //(stkA.isEmpty())
    		int nRet = Integer.MAX_VALUE;
    		while (!stkB.isEmpty() && stkB.peek() < N) {
    			nRet = stkB.pop();
    			stkA.push(nRet);
    		}
    		return nRet;
    	}
        
    }
    
    
    private int getSuccessor(double N) {
    	if (!stkA.isEmpty() && stkA.peek() >= N) {
    		int nRet = Integer.MIN_VALUE;
	        while (!stkA.isEmpty() && stkA.peek() >= N) {
	        	nRet = stkA.pop();
	        	stkB.push(nRet);
	        }
	        return nRet;
    	} else if (!stkA.isEmpty() && stkA.peek() < N) {    		
    		if (!stkB.isEmpty() && stkB.peek() >= N) {
    			return stkB.peek();
    		} else if (!stkB.isEmpty() && stkB.peek() < N) {
    			while (!stkB.isEmpty() && stkB.peek() < N) {
    				stkA.push(stkB.pop());
    			}
    			if (stkB.isEmpty()) return Integer.MIN_VALUE;
    			return stkB.peek();
    		} else {  //stkB.isEmpty()
    			return Integer.MIN_VALUE;
    		}
    	} else {   //(stkA.isEmpty())
    		int nRet = Integer.MIN_VALUE;
	    	while (!stkB.isEmpty() && stkB.peek() < N) {
	    		stkA.push(stkB.pop());
	    	}
	    	if (!stkB.isEmpty() && stkB.peek() >= N) return stkB.peek();
	    	return nRet;
    	}
    }
    
*/    
}
