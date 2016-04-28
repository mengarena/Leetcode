package com.leet;

import java.util.Stack;

//Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//
//You may assume each number in the sequence is unique.
//
//Follow up:
//Could you do it using only constant space complexity?

//Zenefits
public class VerifyPreorderSequenceBinarySearchTree {

	public VerifyPreorderSequenceBinarySearchTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] preorder = {2,3,1};
		
		boolean bRet = verifyPreorder(preorder);
		
		System.out.println(bRet);
	}


	//AC: 63%
	//
	//Strategy:  Simulate (preorder) tree traversal
	//If new node smaller than previous node, it is on left (sub)trees
	//When a new node larger than previous node, it means the traversal goes to a right branch, then it pops out all smaller value (which should be on the corresponding left branch of the same sub tree)
	//Then it find the low bound for the new node (the low bound node actually is the root of this sub tree)
	//Then all other coming nodes should be larger than this low bound (because it is preorder traversal), otherwise the tree is wrong
    public boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 1) return true;
        Stack<Integer> stkNode = new Stack<Integer>();
        int nLowBound = Integer.MIN_VALUE;
        
        for (int i:preorder) {
            if (i < nLowBound) return false;
            
            while (!stkNode.isEmpty() && i > stkNode.peek()) nLowBound = stkNode.pop();   
            //When come to this, it is on the right branch, i is the first node on right branch (i.e. sub-root of the sub right tree). by the end of this while, nLowBound is the root 
            //Pop the nodes on the corresponding left branch and find the low bound (which should be the root of this sub tree)
            //After the series of pop(), the top node on the stkNode is the root's root of the current right branch (while the nLowBound is the value of the root node of current right branch)
            //All the coming node should be larger than the low bound, if not, wrong.
            
            
            stkNode.push(i);
        }
 
        return true;
    }	
	
    
    
	
	//Accepted:  7%
    public boolean verifyPreorderA(int[] preorder) {
        if (preorder == null || preorder.length == 1) return true;
        int n = preorder.length;
 
        return verifyPreorderHelper(preorder, 0, n-1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }	
    
    
    private boolean verifyPreorderHelper(int[] preorder, int nLeft, int nRight, int nLowBound, int nUpBound) {
         if (nRight < nLeft) return true;	
    	 if (nRight == nLeft) {
        	if (preorder[nLeft] > nLowBound && preorder[nLeft] < nUpBound) return true;
        	return false;
         }
    	 
    	 boolean bOrder = true;
    	 int i, nMid = nLeft;
         boolean bLeftValid = true;
         boolean bRightValid = true;
         
    	 for (i=nLeft+1; i<=nRight; i++) {
    	     if (preorder[i] < preorder[i-1]) {
    	         bOrder = false;
    	         break;
    	     }
    	 }
    	 
    	 //If the array is ordered, don't need to divide and process any more, otherwise, it will cause stack overflow
    	 if (bOrder) {
    	     if (preorder[nLeft] > nLowBound && preorder[nRight] < nUpBound) {
    	         return true;
    	     } else {
    	         return false;
    	     }
    	 }    
    	     
    	 for (i=nLeft+1; i<=nRight; i++) {
    		 if (preorder[i] < preorder[nLeft]) {
    			 nMid = i;
    		 } else {
    			 break;
    		 }
    	 }
    	 
    	 if (nMid > nLeft) bLeftValid = verifyPreorderHelper(preorder, nLeft+1, nMid, nLowBound, preorder[nLeft]);
    	 if (nMid >= nLeft && nMid  < nRight) bRightValid = verifyPreorderHelper(preorder, nMid+1, nRight, preorder[nLeft], nUpBound);
    	 
    	 return bLeftValid && bRightValid;
    }
	
}
