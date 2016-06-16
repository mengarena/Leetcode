package com.leet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import com.leet.NestedListWeightSum.NestedInteger;

//Flatten Nested List Iterator

//Given a nested list of integers, implement an iterator to flatten it.
//
//Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
//Example 1:
//Given the list [[1,1],2,[1,1]],
//
//By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
//
//Example 2:
//Given the list [1,[4,[6]]],
//
//By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


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

//Google
//ACC
public class NestedIterator implements Iterator<Integer> {

	private Stack<NestedInteger> stkNum = new Stack<NestedInteger>();
	
    public NestedIterator(List<NestedInteger> nestedList) {
        int size = nestedList.size();
        for (int i=size-1; i>=0; i--) {
            stkNum.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stkNum.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stkNum.isEmpty()) {
            if (stkNum.peek().isInteger()) {
                return true;
            } else {  //is list
                NestedInteger ni = stkNum.pop();
                int size = ni.getList().size();
                for (int i=size-1; i>=0; i--) {
                    stkNum.push(ni.getList().get(i));
                }
            }
        }
        
        return false;
    }
}


//ACC
class NestedIteratorA implements Iterator<Integer> {

	private List<Integer> lstNum = new ArrayList<Integer>();
	
    public NestedIteratorA(List<NestedInteger> nestedList) {
        for (NestedInteger ni:nestedList) {
        	if (ni.isInteger()) {
        		lstNum.add(ni.getInteger());
        	} else {
        		flatNested(ni.getList());
        	}
        }
    }

    private void flatNested(List<NestedInteger> nestedList) {
    	for (NestedInteger ni:nestedList) {
    		if (ni.isInteger()) {
    			lstNum.add(ni.getInteger());
    		} else {
    			flatNested(ni.getList());
    		}
    	}
    }
    
    @Override
    public Integer next() {
        return lstNum.remove(0);
    }

    @Override
    public boolean hasNext() {
        return !lstNum.isEmpty();
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */