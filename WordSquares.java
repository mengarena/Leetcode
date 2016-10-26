package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Given a set of words (without duplicates), find all word squares you can build from them.
//
//A sequence of words forms a valid word square if the kth row and column read the exact same string, 
//where 0 ¡Ü k < max(numRows, numColumns).
//
//For example, the word sequence ["ball","area","lead","lady"] forms a word square 
//because each word reads the same both horizontally and vertically.
//
//b a l l
//a r e a
//l e a d
//l a d y
//
//Note:
//There are at least 1 and at most 1000 words.
//All words will have the exact same length.
//Word length is at least 1 and at most 5.
//Each word contains only lowercase English alphabet a-z.
//Example 1:
//
//Input:
//["area","lead","wall","lady","ball"]
//
//Output:
//[
//  [ "wall",
//    "area",
//    "lead",
//    "lady"
//  ],
//  [ "ball",
//    "area",
//    "lead",
//    "lady"
//  ]
//]
//
//Explanation:
//The output consists of two word squares. 
//The order of output does not matter (just the order of words in each word square matters).
//
//Example 2:
//
//Input:
//["abat","baba","atan","atal"]
//
//Output:
//[
//  [ "baba",
//    "abat",
//    "baba",
//    "atan"
//  ],
//  [ "baba",
//    "abat",
//    "baba",
//    "atal"
//  ]
//]
//
//Explanation:
//	
//The output consists of two word squares. 
//The order of output does not matter (just the order of words in each word square matters).
//Hide Company Tags


//Google
public class WordSquares {

	public WordSquares() {
		// TODO Auto-generated constructor stub
	}
	
	//Another solution and explaination: (similar idea, but use Trie to remember the words which have same prefix
	//https://discuss.leetcode.com/topic/63516/explained-my-java-solution-using-trie-126ms-16-16/2

	//ACC
	public List<List<String>> wordSquares(String[] words) {
	    int n = words[0].length();
	    Map<String, Set<String>> map = new HashMap<>();  //(prefix, words have the prefix)
	                                                     //To remember the word set which has the same prefix
	    
	    for (String w : words) {
	        for (int i = 0; i < n; ++i) {
	            String key = w.substring(0, i);
	            Set<String> set = map.get(key);
	            if (set == null) {
	                set = new HashSet<>();
	                map.put(key, set);
	            }
	            set.add(w);
	        }
	    }
	    
	    List<List<String>> ans = new LinkedList<>();
	    dfs(0, n, new char[n][n], map, ans);
	    return ans;
	}
	
	//char[][] m is the matrix of the words (characters)
	private void dfs(int i, int n, char[][] m, Map<String, Set<String>> map, List<List<String>> ans) {
	    if (i == n) {
	        List<String> list = new ArrayList<>(n);
	        for (int j = 0; j < n; ++j) list.add(new String(m[j]));
	        ans.add(list);
	        return;
	    }
	    
	    for (String w : map.get(new String(m[i], 0, i))) {   //Check each of the words which have same prefix
	        m[i][i] = w.charAt(i);
	        int j = i + 1;   //To right and bottom
	        
	        for (; j < n; ++j) {
	            m[i][j] = w.charAt(j);   //horizontal
	            m[j][i] = w.charAt(j);   //vertical
	            
	            if (!map.containsKey(new String(m[j], 0, i+1))) break;  //if not valid, break
	        }
	        
	        if (j == n) dfs(i + 1, n, m, map, ans);   //If the whole is valid, try next i position
	    }
	}
}
