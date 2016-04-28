package com.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Given a 2D board and a list of words from the dictionary, find all words in the board.
//
//Each word must be constructed from letters of sequentially adjacent cell, 
//where "adjacent" cells are those horizontally or vertically neighboring. 
//The same letter cell may not be used more than once in a word.
//
//For example,
//Given words = ["oath","pea","eat","rain"] and board =
//
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//		
//Return ["eat","oath"].
//Note:
//You may assume that all inputs are consist of lowercase letters a-z.
//
//click to show hint.
//
//You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
//
//If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
//What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? 
//If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

//Airbnb, Microsoft
public class WordSearchII {
	public WordSearchII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
//	   char[][] board =  {{'o','a','a','n'},
//			              {'e','t','a','e'},
//			              {'i','h','k','r'},
//			              {'i','f','l','v'}};
	   
	   char[][] board =  {{'b','b','a','a','b','a'},
			   {'b','b','a','b','a','a'},
			   {'b','b','b','b','b','b'},
			   {'a','a','a','b','a','a'},
			   {'a','b','a','a','b','b'}};
	  // char[][] board = {{'a'},{'a'}};
	   
	  // String[] words = {"oath","pea","eat","rain"};
	   String[] words = {"abbbababaa"};
	   //String[] words = {"aaa"};
	   List<String> lstWords = findWords(board, words);
	   
	   if (!lstWords.isEmpty()) {
		   for (String sword:lstWords) System.out.println(sword);
	   }

	}
	
	
	//Strategy: Construct Trie for the words
	//Search around the board[][], once meet a valid word, save it
    class TrieNode {
    	String sWord;
    	TrieNode[] children = new TrieNode[26];
    }
    
		
	public TrieNode buildTrie(String[] words) {
	    TrieNode root = new TrieNode();
	    
	    for (String sword:words) {
	    	TrieNode p = root;
	    	char[] carr = sword.toCharArray();
	    	for (int i=0; i<carr.length; i++) {
	    		int nIdx = carr[i]-'a';
	    		if (p.children[nIdx] == null) p.children[nIdx] = new TrieNode();
	    		p = p.children[nIdx];
	    	}
	    	
	    	p.sWord = sword;
	    }
	    
	    return root;
	}
	
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> lstWords = new ArrayList<String>();
	    if (board == null || board.length == 0 || board[0].length == 0) return lstWords;
	    if (words == null || words.length == 0) return lstWords;
	    TrieNode root = buildTrie(words);
	    	    
	    int m = board.length;
	    int n = board[0].length;
	    
	    for(int i=0; i<m; i++) {
	    	for (int j=0; j<n; j++) {
	    		searchWords(board, i, j,  root, lstWords);
	    	}
	    }
	    
	    return lstWords;
	}	
	
	private void searchWords(char[][] board, int i, int j, TrieNode tr, List<String> lstWords) {
	    int m = board.length;
	    int n = board[0].length;

	    if (i >= m || i < 0 || j >= n || j < 0 || board[i][j] == '#' || tr.children[board[i][j]-'a'] == null) return;
	    
	    char c = board[i][j];
	    
	    tr = tr.children[c-'a'];
	    	    
	    if (tr.sWord != null && !tr.sWord.isEmpty()) {
	    	lstWords.add(tr.sWord);
	    	tr.sWord = null;
	    }
	    
	    board[i][j] = '#';
			
		if (j < n-1) searchWords(board, i, j+1, tr, lstWords);
		if (j > 0) searchWords(board, i, j-1, tr, lstWords);
		if (i < m-1) searchWords(board, i+1, j, tr, lstWords);
		if (i > 0) searchWords(board, i-1, j, tr, lstWords);
		
		board[i][j] = c;
	}
	
	
	
/*  Accepted: Version 1
 * 
     class MyTrie {
    	public TrieNode root = null;
    	
    	public MyTrie() {
    		root = new TrieNode();
    	}
    	public void insert(String s) {
    		if (s == null || s.length() == 0) return;
    		TrieNode curRoot = root;
    		char[] carr = s.toCharArray();
    		
    		for (int i=0; i<carr.length; i++) {
    			if (curRoot.children[carr[i]-'a'] == null) {
    				curRoot.children[carr[i]-'a'] = new TrieNode();
    			}

    			curRoot = curRoot.children[carr[i]-'a'];
    		}
    		
    		curRoot.bWord = true;
    	}
    	
    	public boolean search(String s) {
    		if (root == null) return false;
    		if (s == null || s.length() == 0) return true;
    		TrieNode curRoot = root;
    		char[] carr = s.toCharArray();
    		
    		for (int i=0; i<carr.length; i++) {
    			if (curRoot.children[carr[i]-'a'] != null) {
    				curRoot = curRoot.children[carr[i]-'a'];
    			} else {
    				return false;
    			}
    		}
    		
    		return curRoot.bWord;
    	}
    	
    	public boolean startWith(String s) {
    		if (root == null) return false;
    		if (s == null || s.length() == 0) return true;
    		TrieNode curRoot = root;
    		char[] carr = s.toCharArray();
    		
    		for (int i=0; i<carr.length; i++) {
    			if (curRoot.children[carr[i]-'a'] != null) {
    				curRoot = curRoot.children[carr[i]-'a'];
    			} else {
    				return false;
    			}
    		}
    		
    		return true;    		
    	}
    }

  
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> lstWords = new ArrayList<String>();
	    Set<String> hsWords = new HashSet<String>();
	    if (board == null || board.length == 0 || board[0].length == 0) return lstWords;
	    if (words == null || words.length == 0) return lstWords;
	    int wordCnt = words.length;
	    MyTrie tr = new MyTrie();
	    
	    for (int i = 0; i<wordCnt; i++) {
	    	tr.insert(words[i]);
	    }
	    
	    int m = board.length;
	    int n = board[0].length;
	    
	    for(int i=0; i<m; i++) {
	    	for (int j=0; j<n; j++) {
	    		searchWords(board, i, j,  tr, "", hsWords);
	    	}
	    }
	    
	    lstWords = new ArrayList<String>(hsWords);
	    return lstWords;
	}	
	
	private void searchWords(char[][] board, int i, int j, MyTrie tr, String sCur, Set<String> hsWords) {
	    int m = board.length;
	    int n = board[0].length;
	    char c = board[i][j];

	    if (i >= m || i < 0 || j >= n || j < 0 || c == '#') return;
	    
	    sCur += "" + c;
	    
	    if (tr.startWith(sCur) == false) return;
	    if (tr.search(sCur)) hsWords.add(sCur);
	    
	    board[i][j] = '#';
			
		if (j < n-1) searchWords(board, i, j+1, tr, sCur, hsWords);
		if (j > 0) searchWords(board, i, j-1, tr, sCur, hsWords);
		if (i < m-1) searchWords(board, i+1, j, tr, sCur, hsWords);
		if (i > 0) searchWords(board, i-1, j, tr, sCur, hsWords);
		
		board[i][j] = c;
	}
 
 * */	
	
	
/* Accepted: Verion 0
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> lstWords = new ArrayList<String>();
	    Set<String> hsWords = new HashSet<String>();
	    if (board == null || board.length == 0 || board[0].length == 0) return lstWords;
	    if (words == null || words.length == 0) return lstWords;
	    int wordCnt = words.length;
	    MyTrie tr = new MyTrie();
	    
	    for (int i = 0; i<wordCnt; i++) {
	    	tr.insert(words[i]);
	    }
	    
	    int m = board.length;
	    int n = board[0].length;
	    boolean[][] visited = new boolean[m][n];
	    
	    for(int i=0; i<m; i++) {
	    	for (int j=0; j<n; j++) {
	    		searchWords(board, visited, i, j,  tr, "", hsWords);
	    	}
	    }
	    
	    lstWords = new ArrayList<String>(hsWords);
	    return lstWords;
	}	
	
	private void searchWords(char[][] board, boolean[][] visited, int i, int j, MyTrie tr, String sCur, Set<String> hsWords) {
	    int m = board.length;
	    int n = board[0].length;

	    if (i >= m || i < 0 || j >= n || j < 0) return;
	    
	    if (visited[i][j] == true) return;
	    
	    sCur += "" + board[i][j];
	    
	    if (tr.startWith(sCur) == false) return;
	    if (tr.search(sCur)) hsWords.add(sCur);
	    
		visited[i][j] = true;
			
		if (j < n-1) searchWords(board, visited, i, j+1, tr, sCur, hsWords);
		if (j > 0) searchWords(board, visited, i, j-1, tr, sCur, hsWords);
		if (i < m-1) searchWords(board, visited, i+1, j, tr, sCur, hsWords);
		if (i > 0) searchWords(board, visited, i-1, j, tr, sCur, hsWords);
		
		visited[i][j] = false;
	}
	 
 * */
	
}
