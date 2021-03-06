package com.leet;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Given a nested list of integers represented as a string, implement a parser to deserialize it.
//
//Each element is either an integer, or a list -- whose elements may also be integers or other lists.
//
//Note: You may assume that the string is well-formed:
//
//String is non-empty.
//String does not contain white spaces.
//String contains only digits 0-9, [, - ,, ].
//
//Example 1:
//Given s = "324",
//You should return a NestedInteger object which contains a single integer 324.
//				
//Example 2:
//Given s = "[123,[456,[789]]]",
//Return a NestedInteger object containing a nested list with 2 elements:
//
//1. An integer containing value 123.
//2. A nested list containing two elements:
//    i.  An integer containing value 456.
//    ii. A nested list with one element:
//         a. An integer containing value 789.

//Airbnb
public class MiniParser {

	public MiniParser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * // This is the interface that allows for creating nested lists.
	 * // You should not implement it, or speculate about its implementation
	 */
	//public interface NestedInteger {
	public class NestedInteger {
	      // Constructor initializes an empty nested list.
	      public NestedInteger() {};
	 
	      // Constructor initializes a single integer.
	      public NestedInteger(int value) {};
	 
	      // @return true if this NestedInteger holds a single integer, rather than a nested list.
	      public boolean isInteger()  {return true;};
	 
	      // @return the single integer that this NestedInteger holds, if it holds a single integer
	      // Return null if this NestedInteger holds a nested list
	      public Integer getInteger()  {return 0;};
	 
	      // Set this NestedInteger to hold a single integer.
	      public void setInteger(int value)  {};
	 
	      // Set this NestedInteger to hold a nested list and adds a nested integer to it.
	      public void add(NestedInteger ni)  {};
	 
	      // @return the nested list that this NestedInteger holds, if it holds a nested list
	      // Return null if this NestedInteger holds a single integer
	      public List<NestedInteger> getList()  {return new ArrayList<NestedInteger>();};
	  }
	 

	//ACC:
	//Strategy:   when meet '[', build a new layer by add a NestedInteger object; 
	//            when meet ']', add the content (it will be a NestedInteger object) between the two '[' ']' into the NestedInteger object representing '[...]'
    public NestedInteger deserialize(String s) {
        if (s == null) return null;
        if (s.isEmpty()) return new NestedInteger();
        
        NestedInteger ret = new NestedInteger();

        Stack<NestedInteger> stk = new Stack<>();
        int n = s.length();
        
        for (int i=0; i<n; i++) {
            char c = s.charAt(i);
            
            if (c == '[') {
                stk.push(new NestedInteger());
            } else if (c == ']') {
                NestedInteger tmp = stk.pop();   // the [ ... ] object
                
                if (!stk.isEmpty()) {
                    stk.peek().add(tmp);
                } else {
                    stk.push(tmp);
                }
            } else if (c == ',') {
                continue;
            } else {
                int j = i;
                while (j < n && (Character.isDigit(s.charAt(j)) || s.charAt(j) == '-')) j++;
                
                if (j > i) {
                    int num = Integer.parseInt(s.substring(i, j));
                    if (stk.isEmpty()) {
                        stk.push(new NestedInteger(num));
                    } else {
                        stk.peek().add(new NestedInteger(num));
                    }
                    
                }
            
                i = j-1;
            }
        }
        
        return stk.peek();
    }
}
