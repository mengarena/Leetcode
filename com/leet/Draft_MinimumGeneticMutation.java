package com.leet;

import java.util.HashSet;
import java.util.Set;

//A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
//
//Suppose we need to investigate about a mutation (mutation from "start" to "end"), 
//where ONE mutation is defined as ONE single character changed in the gene string.
//
//For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
//
//Also, there is a given gene "bank", which records all the valid gene mutations. 
//A gene must be in the bank to make it a valid gene string.
//
//Now, given 3 things - start, end, bank, your task is to determine 
//what is the minimum number of mutations needed to mutate from "start" to "end". 
//If there is no such a mutation, return -1.
//
//Note:
//
//Starting point is assumed to be valid, so it might not be included in the bank.
//If multiple mutations are needed, all mutations during in the sequence must be valid.
//You may assume start and end string is not the same.
//
//Example 1:
//
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//return: 1
//
//Example 2:
//
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//return: 2
//
//Example 3:
//
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//return: 3
//
//Twitter
//Medium
public class Draft_MinimumGeneticMutation {

	public Draft_MinimumGeneticMutation() {
		// TODO Auto-generated constructor stub
	}

	//ACC
	public int minMutation(String start, String end, String[] bank) {
        if (start == null || end == null || bank == null || bank.length == 0 || start.length() != end.length())
            return -1;
        if (start.equals(end)) return 0;
        
        return minMutation(start, end, bank, new HashSet<String>(), 0);
    }

    private int minMutation(String current, String end, String[] bank, Set<String> path, int depth) {
        int min = -1;
        if (current.equals(end)) return 0;
        if (depth >= end.length()) return min;
        
        for (String gene : bank) {
            if (!path.contains(gene) && isClose(current, gene)) {
                path.add(gene);
                int num = minMutation(gene, end, bank, path, depth++);
                if (num != -1) min =  Math.min(Integer.MAX_VALUE, num + 1);
                
                path.remove(gene);
            }
        }
        
        return min;
    }

    //check dist is ONE
    private boolean isClose(String a, String b) {
        if (a.length() != b.length()) return false;
        
        for (int i = 0, diffs=0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i) && ++diffs > 1) return false;
        }
        
        return true;
    }

}
