package com.leet;

//Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
//
//For example,
//Given n = 3, there are a total of 5 unique BST's.
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3

public class UniqueBinarySearchTrees {

	public UniqueBinarySearchTrees() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		System.out.println("Tree Cnt = " + numTrees(7));
	}
	
	
    public int numTrees(int n) {        
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int[] narrDPTreeCnt = new int[n+1];
        
        narrDPTreeCnt[0] = 1;
        narrDPTreeCnt[1] = 1;
        narrDPTreeCnt[2] = 2;
        
        for (int i=3; i<=n; i++) {
        	int nLeft = 0;
        	int nRight = i-1-nLeft;
        	
        	int nTmpCnt = 0;
        	while (nRight >= 0) {
        		nTmpCnt = nTmpCnt + narrDPTreeCnt[nLeft]*narrDPTreeCnt[nRight];
        		nLeft = nLeft + 1;
        		nRight = nRight - 1;
        	}
        	
        	narrDPTreeCnt[i] = nTmpCnt;
        }
        
        return narrDPTreeCnt[n];
    }	

}
