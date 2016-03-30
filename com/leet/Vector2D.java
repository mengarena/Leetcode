package com.leet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Implement an iterator to flatten a 2d vector.
//
//For example,
//Given 2d vector =
//
//[
//  [1,2],
//  [3],
//  [4,5,6]
//]
//By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
//
//Hint:
//
//How many variables do you need to keep track?
//Two variables is all you need. Try with x and y.
//Beware of empty rows. It could be the first few rows.
//To write correct code, think about the invariant to maintain. What is it?
//The invariant is x and y must always point to a valid point in the 2d vector. 
//Should you maintain your invariant ahead of time or right when you need it?
//Not sure? Think about how you would implement hasNext(). Which is more complex?
//Common logic in two different places should be refactored into a common method.
//
//Follow up:
//As an added challenge, try to code it using only iterators in C++ or iterators in Java.


//Google, Airbnb, Zenefits
public class Vector2D implements Iterator<Integer> {

    private Iterator<List<Integer>> itRows;
    private Iterator<Integer> itCols;
	
    public Vector2D(List<List<Integer>> vec2d) {
        itRows = vec2d.iterator();
    }

    @Override
    public Integer next() {
        return itCols.next();
    }

    @Override
    public boolean hasNext() {
    	//Here use "while" not "if" to jump through empty rows, in case empty row exist
        while ((itCols == null || !itCols.hasNext()) && itRows.hasNext()) itCols = itRows.next().iterator();
        
        if (itCols == null) return false;
        
        return itCols.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
