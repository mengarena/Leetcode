package com.leet;

import java.util.List;

//Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
//
//Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
//Example 1:
//Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
//
//Example 2:
//Given the list [1,[4,[6]]], return 27. (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)

//Linkedin
//Easy
public class NestedListWeightSum {

	public NestedListWeightSum() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * // This is the interface that allows for creating nested lists.
	 * // You should not implement it, or speculate about its implementation
	 * public interface NestedInteger {
	 *
	 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
	 *     public boolean isInteger();
	 *
	 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
	 *     // Return null if this NestedInteger holds a nested list
	 *     public Integer getInteger();
	 *
	 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
	 *     // Return null if this NestedInteger holds a single integer
	 *     public List<NestedInteger> getList();
	 * }
	 */
	
	public interface NestedInteger {
		public boolean isInteger();
		public Integer getInteger();
		public List<NestedInteger> getList();
	}
	
    public int depthSum(List<NestedInteger> nestedList) {
        int nSum = 0;
        
        nSum = calSum(nestedList, 1);
        
        return nSum;
    }	
    
    private int calSum(List<NestedInteger> nestedList, int nDepth) {
    	int nSum = 0;
    	
    	for (NestedInteger ni:nestedList) {
    		if (ni.isInteger()) {
    			nSum += ni.getInteger()*nDepth;
    		} else {
    			nSum += calSum(ni.getList(), nDepth+1);
    		}
    	}
    	
    	return nSum;        
    }
}
