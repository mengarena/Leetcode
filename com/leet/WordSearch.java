package com.leet;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Given a 2D board and a word, find if the word exists in the grid.
//
//The word can be constructed from letters of sequentially adjacent cell, 
//where "adjacent" cells are those horizontally or vertically neighboring. 
//The same letter cell may not be used more than once.
//
//For example,
//Given board =
//
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//word = "ABCCED", -> returns true,
//word = "SEE", -> returns true,
//word = "ABCB", -> returns false.

//Facebook, Microsoft, Bloomberg
public class WordSearch {

	public WordSearch() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		//char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
		char[][] board = {{'A','A','A','A'}, {'A','A','A','A'}, {'A','A','A','B'}};
		//String word = "ABCCED";
		//String word = "SEE";
		//String word = "ABCB";
		//String word = "FDECSECB";
		String word = "AAAAAAAAAAAA";
		//String word = "KSA";
		
		System.out.println(word + "  Exist ? " + exist(board, word));
	}
	
	
    public boolean exist(char[][] board, String word) {
    	if (word == null || word.length() == 0) return true;
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        int nRowCnt = board.length;
        int nColCnt = board[0].length;
        int n = word.length();

        if (nRowCnt*nColCnt < n) return false;
        
        char[] carr = word.toCharArray();
        boolean[][] checked = new boolean[nRowCnt][nColCnt];
        
        for (int i=0; i<nRowCnt; i++) {
            for (int j=0; j<nColCnt; j++) {
        	boolean bRet = existHelper(board, checked, i, j, nRowCnt, nColCnt, carr, 0);
        	if (bRet) return true;
        	checked[i][j] = false;
            }
        }
        
        return false;
    }	
    
    //DFS
    private boolean existHelper(char[][] board, boolean[][] checked, int nRowIdx, int nColIdx, int nRowCnt, int nColCnt, 
				char[] carr, int nStartPos) {
    	if (board[nRowIdx][nColIdx] != carr[nStartPos]) {
    	    return false;
    	} else {
    	    if (nStartPos == carr.length-1) return true;
    	    checked[nRowIdx][nColIdx] = true;
    	}
    	
    	//Check four neighbors for next character
    	if (nRowIdx+1 < nRowCnt && !checked[nRowIdx+1][nColIdx] && nStartPos+1 <= carr.length-1) {
    	    boolean bRet = existHelper(board, checked, nRowIdx+1, nColIdx, nRowCnt, nColCnt, carr, nStartPos+1);
    	    if (bRet) return true;
    	    checked[nRowIdx+1][nColIdx] = false;
    	}
    	
    	if (nRowIdx-1 >= 0 && !checked[nRowIdx-1][nColIdx] && nStartPos+1 <= carr.length-1) {
    	    boolean bRet = existHelper(board, checked, nRowIdx-1, nColIdx, nRowCnt, nColCnt, carr, nStartPos+1);
    	    if (bRet) return true;
    	    checked[nRowIdx-1][nColIdx] = false;
    	}

    	if (nColIdx+1 < nColCnt && !checked[nRowIdx][nColIdx+1] && nStartPos+1 <= carr.length-1) {
    	    boolean bRet = existHelper(board, checked, nRowIdx, nColIdx+1, nRowCnt, nColCnt, carr, nStartPos+1);
    	    if (bRet) return true;
    	    checked[nRowIdx][nColIdx+1] = false;
    	}

    	if (nColIdx-1 >= 0 && !checked[nRowIdx][nColIdx-1] && nStartPos+1 <= carr.length-1) {
    	    boolean bRet = existHelper(board, checked, nRowIdx, nColIdx-1, nRowCnt, nColCnt, carr, nStartPos+1);
    	    if (bRet) return true;
    	    checked[nRowIdx][nColIdx-1] = false;
    	}

    	return false;
    }
	
/*    
	//Accepted, but not efficient
	//Strategy:  Convert board into hashmap, which records the coordinates for each character
	//Then check every character in word, if the neighboring characters could also be neighboring elements in board
    public boolean exist(char[][] board, String word) {
    	if (word == null || word.length() == 0) return true;
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        int nRow = board.length;
        int nCol = board[0].length;
        int n = word.length();
        
        if (nRow*nCol < n) return false;
        
        //Form the hashmap for character - coordinates
        HashMap<Character, List<Point>> hmBoard = new HashMap<Character, List<Point>>();
        for (int i=0; i<nRow; i++) {
        	for (int j=0; j<nCol; j++) {
        		if (!hmBoard.containsKey(board[i][j])) {
        			List<Point> lstPnt = new ArrayList<Point>();
        			lstPnt.add(new Point(i, j));
        			hmBoard.put(board[i][j], lstPnt);
        		} else {
        			List<Point> lstPnt = hmBoard.get(board[i][j]);
        			lstPnt.add(new Point(i,j));
        			hmBoard.replace(board[i][j], lstPnt);
        		}
        	}
        }
        
        char carr[] = word.toCharArray();
        
        boolean bRet = checkExist(hmBoard, carr, 0, null);
        
        return bRet;
    }
	
    
    private boolean checkExist(HashMap<Character, List<Point>> hmBoard, char[] carr, int nPos, Point pnPrev) {
    	int n = carr.length;
    	int i;
    	
		List<Point> lstPnTmp = hmBoard.get(carr[nPos]);
		if (lstPnTmp == null) return false;
		
		int nCnt = lstPnTmp.size();

		for (i=0; i<nCnt; i++) {		
			Point pnCur = lstPnTmp.get(i);
			
			if (!IsNeighbor(pnPrev, pnCur)) {
				continue;
			} else {
				if (nPos == n-1) return true;
				HashMap<Character, List<Point>> hmBoardBack = new HashMap<Character, List<Point>>(hmBoard);
				List<Point> lstPn = new ArrayList<Point>(lstPnTmp);
				lstPn.remove(i);
				hmBoard.replace(carr[nPos], lstPn);
				
				if (lstPn.isEmpty()) hmBoard.remove(carr[nPos]);
				
				boolean bRet = checkExist(hmBoard, carr, nPos+1, pnCur);
				if (bRet) return true;

				hmBoard = new HashMap<Character, List<Point>>(hmBoardBack);
			}
			
		}
    	
    	return false;
    }
    
    
    
    private boolean IsNeighbor(Point a, Point b) {
    	if (a == null || b == null) return true;
    	if ((a.x == b.x) && (Math.abs(a.y - b.y) == 1)) return true;
    	if ((a.y == b.y) && (Math.abs(a.x - b.x) == 1)) return true;
    	return false;
    }
*/    
    
 /* Exceed time limit  
    private boolean checkExist(HashMap<Character, List<Point>> hmBoard, char[] carr, int nPos, Point pnPrev) {
    	int n = carr.length;
    	int i;
    	
		List<Point> lstPnTmp = hmBoard.get(carr[nPos]);
		if (lstPnTmp == null) return false;
		
		int nCnt = lstPnTmp.size();
		
		for (i=0; i<nCnt; i++) {
			HashMap<Character, List<Point>> hmBoardBack = new HashMap<Character, List<Point>>(hmBoard);
			List<Point> lstPn = new ArrayList<Point>(hmBoardBack.get(carr[nPos]));
		
			Point pnCur = lstPn.remove(i);
			hmBoard.replace(carr[nPos], lstPn);
			
			if (lstPn.isEmpty()) hmBoard.remove(carr[nPos]);
			
			if (IsNeighbor(pnPrev, pnCur)) {
				if (nPos == n-1) return true;
				boolean bRet = checkExist(hmBoard, carr, nPos+1, pnCur);
				if (bRet) return true;
			}
			
			hmBoard = new HashMap<Character, List<Point>>(hmBoardBack);
		}
    	
    	return false;
    }
 */  
    
    
 /* Exceed time limit    
    private boolean checkExist(HashMap<Character, List<Point>> hmBoard, char[] carr, int nPos, Point pnPrev) {
    	int n = carr.length;
    	int i;
    	
		List<Point> lstPnTmp = hmBoard.get(carr[nPos]);
		if (lstPnTmp == null) return false;
		
		int nCnt = lstPnTmp.size();

		HashMap<Character, List<Point>> hmBoardBack = new HashMap<Character, List<Point>>(hmBoard);
		
		for (i=0; i<nCnt; i++) {
			//HashMap<Character, List<Point>> hmBoardBack = new HashMap<Character, List<Point>>(hmBoard);
			List<Point> lstPn = new ArrayList<Point>(hmBoardBack.get(carr[nPos]));
		
			Point pnCur = lstPn.get(i);
			
			if (!IsNeighbor(pnPrev, pnCur)) {
				continue;
			} else {
				if (nPos == n-1) return true;
				
				lstPn.remove(i);
				hmBoard.replace(carr[nPos], lstPn);
				
				if (lstPn.isEmpty()) hmBoard.remove(carr[nPos]);
				
				boolean bRet = checkExist(hmBoard, carr, nPos+1, pnCur);
				if (bRet) return true;

				hmBoard = new HashMap<Character, List<Point>>(hmBoardBack);
			}
			
		}
    	
    	return false;
    }
 */   
    
}
