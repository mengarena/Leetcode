package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Design and implement a data structure for Least Recently Used (LRU) cache. 
//It should support the following operations: get and set.
//
//get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//set(key, value) - Set or insert the value if the key is not already present. 
//When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

//Google, Uber, Zenefits, Amazon, Microsoft, Bloomberg
public class LRUCache {

	
	//ACC: 22%
	//Formal implement:  HashMap + double link list
    class MyEntry {
        MyEntry before;
        MyEntry after;
        int key;
        int val;
    }
    
	private Map<Integer, MyEntry> hm = null;
	private MyEntry first = null;
	private MyEntry last = null;
	private int capacity = 0;
	
    public LRUCache(int capacity) {
    	this.capacity = capacity;
        hm = new HashMap<Integer, MyEntry>(capacity);
    }

    public int get(int key) {
        if (hm.containsKey(key)) {
            MyEntry me = hm.get(key);
            int value = me.val;
            
            if (first == last || first == me) return value;
        
            if (me == last) {
                first = me;
                last = me.before;
                return value;
            }
            
            MyEntry before = me.before;
            MyEntry after = me.after;
            after.before = before;
            before.after = after;
            
            me.before = last;
            me.after = first;
            first.before = me;
            last.after = me;
            first = me;
            
            hm.put(key, me);
        	return value;        	
        } else {
        	return -1;
        }
    }
    
    public void set(int key, int value) {
        if (!hm.containsKey(key)) {
            MyEntry me = new MyEntry();
            me.key = key;
            me.val = value;

            if (hm.size() < this.capacity) {
                
                if (first == null) {
                    me.before = me;
                    me.after = me;
                    first = me;
                    last = me;
                } else {
                    me.before = last;
                    me.after = first;
                    first.before = me;
                    last.after = me;
                    first = me;
                }
                
                hm.put(key, me);
            } else {
                me.before = last;
                me.after = first;
                first.before = me;
                last.after = me;
                MyEntry prevLast = last.before;
                
                prevLast.after = last.after;
                last.after.before = prevLast;
                
                hm.remove(last.key);
                
                first = me;
                last = prevLast;
                
                hm.put(key, me);
            }
            
        } else {
            MyEntry me = hm.get(key);
            me.val = value;
            
            if (first == last) {
                hm.put(key, me);
            } else {
                if (me == first) {
                    hm.put(key, me);
                } else if (me == last) {
                    first = me;
                    last = last.before;
                    hm.put(key, me);
                } else {
                    me.before.after = me.after;
                    me.after.before = me.before;
                
                    me.after = first;
                    me.before = last;
                    first.before = me;
                    last.after = me;
                    first = me;
                    hm.put(key, me);
                }
            }
        }
    }

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//ACC: 10%, but this method is somehow cheating, it uses LinkedHashMap
	private Map<Integer, Integer> hmA = null;
	private int nSize = 0;
	
    public void LRUCacheA(int capacity) {   //Original should be public LRUCacheA(int capacity) 
    	nSize = capacity;
    	hmA = new java.util.LinkedHashMap<Integer, Integer>(capacity);   //In insert order, when read will also follow this order
    }
    
    public int getA(int key) {
        if (hmA.containsKey(key)) {
            int value = hmA.get(key);
            //These two steps put the key-value to the beginning
            hmA.remove(key);
            hmA.put(key, value);
        	return value;        	
        } else {
        	return -1;
        }
    }
    
    public void setA(int key, int value) {
        if (!hmA.containsKey(key)) {
        	if (hmA.size() < nSize) {
        		hmA.put(key, value);
        	} else {
        		Set<Integer> keySet = hmA.keySet();
        		hmA.remove(keySet.iterator().next());  //Remove the oldest one
        		hmA.put(key, value);
        	}
        } else {
        	//Remove from its original position and put it to the head
        	hmA.remove(key);
        	hmA.put(key, value);
        }
    }
    
}
