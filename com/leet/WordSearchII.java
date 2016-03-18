package com.leet;

import java.util.ArrayList;
import java.util.List;

public class WordSearchII {

	public WordSearchII() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
	
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> lstWords = new ArrayList<String>();
	    if (board == null || board.length == 0 || board[0].length == 0) return lstWords;
	    if (words == null || words.length == 0) return lstWords;
	    int wordCnt = words.length;
	     
	    for (int i = 0; i<wordCnt; i++) {
	    	if (isSearchable(board, words[i])) {
	    		lstWords.add(words[i]);
	    	}
	    }
	     
	    return lstWords;
	}	
	
	
	private isSearchable(char[][] board, String word) {
		
		
		return true;
	}
}
