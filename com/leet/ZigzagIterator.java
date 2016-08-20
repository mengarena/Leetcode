package com.leet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Given two 1d vectors, implement an iterator to return their elements alternately.
//
//For example, given two 1d vectors:
//
//v1 = [1, 2]
//v2 = [3, 4, 5, 6]
//By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
//
//Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
//
//Clarification for the follow up question - Update (2015-09-18):
//The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
//If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:
//
//[1,2,3]
//[4,5,6,7]
//[8,9]
//It should return [1,4,8,2,5,9,3,6,7].
		
//Google
public class ZigzagIterator {

	public ZigzagIterator() {
		// TODO Auto-generated constructor stub
	}

	//ACC:  25%
	//Could for K list
	private List<Iterator> lstOverallK = null;
	
    public void ZigzagIteratorB(List<Integer> v1, List<Integer> v2) {
    	lstOverallK = new ArrayList<>();
        if (v1 != null && !v1.isEmpty()) lstOverallK.add(v1.iterator());
        if (v2 != null && !v2.isEmpty()) lstOverallK.add(v2.iterator());
    }

    public int nextB() {
        if (lstOverallK == null || lstOverallK.isEmpty()) return 0;
        Iterator vTmp = lstOverallK.remove(0);
        int val = (Integer) vTmp.next();   //"(Integer)" convert is needed
        
        if (vTmp.hasNext()) lstOverallK.add(vTmp);
        
        return val;
    }

    public boolean hasNextB() {
        return !lstOverallK.isEmpty();
    }    	
	
	
	
	//ACC:  25%
    //Could for K list
	private List<List<Integer>> lstlstOverall = new ArrayList<List<Integer>>();
	private int nIdxA = 0;
	
    public void ZigzagIteratorA(List<Integer> v1, List<Integer> v2) {
        if ((v1 == null || v1.isEmpty()) && (v2 == null || v2.isEmpty())) return;
        if (v1 != null && !v1.isEmpty()) lstlstOverall.add(v1);
        if (v2 != null && !v2.isEmpty()) lstlstOverall.add(v2);
        nIdxA = 0;
    }

    public int nextA() {
        if (lstlstOverall.isEmpty()) return 0;
        List<Integer> vTmp = lstlstOverall.get(nIdxA);
        int val = vTmp.remove(0);
        if (vTmp.isEmpty()) {
            lstlstOverall.remove(nIdxA);
            if (nIdxA >= lstlstOverall.size()) nIdxA = 0;
        } else {
        	nIdxA++;
            nIdxA = nIdxA % lstlstOverall.size();
        }
        return val;
    }

    public boolean hasNextA() {
        return !lstlstOverall.isEmpty();
    }    	
	
    
    
	
	//Accepted:  45%
	private List<Integer> lstOverall = new ArrayList<Integer>();
	private int nIdx = 0;
	
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        if ((v1 == null || v1.isEmpty()) && (v2 == null || v2.isEmpty())) return;
        if (v1 == null || v1.isEmpty()) {
        	lstOverall = new ArrayList<Integer>(v2);
        	return;
        } else if (v2 == null || v2.isEmpty()) {
        	lstOverall = new ArrayList<Integer>(v1);
        	return;
        }
        
        int i;
        int nMinLen = Math.min(v1.size(), v2.size());
        
        for (i=0; i<nMinLen; i++) {
        	lstOverall.add(v1.get(i));
        	lstOverall.add(v2.get(i));
        }
        
        if (v1.size() > v2.size()) {
        	for (i=nMinLen; i<v1.size(); i++) lstOverall.add(v1.get(i));
        } else {
        	for (i=nMinLen; i<v2.size(); i++) lstOverall.add(v2.get(i));
        }
    }

    public int next() {
        return lstOverall.get(nIdx++);
    }

    public boolean hasNext() {
        return nIdx < lstOverall.size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */