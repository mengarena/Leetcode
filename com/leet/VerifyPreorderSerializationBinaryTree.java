package com.leet;

//One way to serialize a binary tree is to use pre-order traversal. 
//When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
//
//_9_
///   \
//3     2
/// \   / \
//4   1  #  6
/// \ / \   / \
//# # # #   # #
//For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", 
//where # represents a null node.
//
//Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. 
//Find an algorithm without reconstructing the tree.
//
//Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
//
//You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
//
//Example 1:
//"9,3,4,#,#,1,#,#,2,#,6,#,#"
//Return true
//
//Example 2:
//"1,#"
//Return false
//
//Example 3:
//"9,#,#,1"
//Return false

public class VerifyPreorderSerializationBinaryTree {

	public VerifyPreorderSerializationBinaryTree() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//		String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
//		String preorder = "9,#,92,#,#";
//		String preorder = "1,#";
//		String preorder = "9,#,#,1";
		String preorder = "#";
		
		System.out.println(preorder + "    IS " + isValidSerialization(preorder));
	}

	
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) return true;
        int n = preorder.length();
        
        if (n == 1) {
        	if (preorder.charAt(0) == '#') {
        		return true;
        	} else {
        		return false;
        	}
        }
        
//        if (preorder.charAt(n-1) == ',') {
//        	if ((n-2) % 4 != 0) return false;
//        	if (preorder.charAt(n-2) != '#') return false;
//        } else {  
//        	if ((n-1) % 4 != 0) return false;
//        	if (preorder.charAt(n-1) != '#') return false;
//        }
                
        String sReplace = "";
        if (preorder.charAt(n-1) == ',') {
        	sReplace = preorder.substring(0, n-1);
        } else {
        	sReplace = preorder;
        }
        
        int nLen = sReplace.length();
        
        while (nLen > 0) {
        	sReplace = replaceSubtree(sReplace);
        	
        	if (sReplace.charAt(0) == '#') {
        		if (sReplace.length() == 1) {
        			return true;
        		} else {
        			return false;
        		}
        	}
        	
        	if (nLen == sReplace.length()) return false;
        	nLen = sReplace.length();
        }
        
        return true;
    }
	
    
    private String replaceSubtree(String sReplace) {
    	int nPos;
    	nPos = sReplace.indexOf("#,#");
    	
    	if (nPos == -1) return sReplace;
    	
    	int nStartPos = 0;
    	boolean bDigit = false;
    	
    	for (int i=nPos; i>=0; i--) {
    		if (sReplace.charAt(i) >= '0' && sReplace.charAt(i) <= '9') {
    			bDigit = true;
    		}
    		
    		if (sReplace.charAt(i) == ',' && bDigit == true) {
    			nStartPos = i;
    			break;
    		}
    	}
    	
    	if (bDigit == false) return sReplace;
    	
    	if (nStartPos > 0) {
    		sReplace = sReplace.substring(0, nStartPos+1) + "#" + sReplace.substring(nPos + 3);
    	} else {
    		sReplace = "#" + sReplace.substring(nPos + 3);
    	}
		
    	return sReplace;
    }
    
}
