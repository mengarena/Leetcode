/*
449. Serialize and Deserialize BST

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary search tree. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

The encoded string should be as compact as possible.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

*/

//Amazon
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
public class Codec {

    //ACC:  72%
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        
        sb = preorder(root);
        
        return sb.toString();
    }
    
    private StringBuilder preorder(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        
        if (root == null) return sb;
        sb.append(root.val + ",");
        
        if (root.left != null) sb.append(preorder(root.left));
        if (root.right != null) sb.append(preorder(root.right));
        
        return sb;
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        
        String[] datas = data.split(",");
        int[] datan = new int[datas.length];
        
        for (int i=0; i<datas.length; i++) {
            datan[i] = Integer.parseInt(datas[i]);
        }
        
        TreeNode root = deserializeHelper(datan, 0, datan.length-1);
        
        return root;
    }
    
    private TreeNode deserializeHelper(int[] datan, int start, int end) {
        if (end < start) return null;
        if (start == end) return new TreeNode(datan[start]);
        
        //Find the position whose value is smaller than datan[start], then from start+1 to nPos belong to the left subtree;
        //nPos+1 to end belong to right tree
        int nPos = findPos(datan, start+1, end, datan[start]);
        
        TreeNode root = new TreeNode(datan[start]);
        root.left = deserializeHelper(datan, start+1, nPos);
        root.right = deserializeHelper(datan, nPos+1, end);
        
        return root;
    }
    
    private int findPos(int[] datan, int start, int end, int target) {
        int mid = 0;
        
        while (start <= end) {
            mid = start + (end-start)/2;
            
            if (datan[mid] >= target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        
        return end;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

