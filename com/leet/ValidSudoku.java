package com.leet;

import java.util.HashMap;
import java.util.Map;

//Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
//
//The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
//
//
//A partially filled sudoku which is valid.
//
//Note:
//A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

//Uber
public class ValidSudoku {

	public ValidSudoku() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
    public boolean isValidSudoku(char[][] board) {
        int i, j, m, n;
        Map<Integer, Integer> unitMap = new HashMap<Integer, Integer>();
        int nVal;
        
        //Check whether every row is valid
        for (i = 0; i < 9; i++) {
        	unitMap.clear();
        	for (j=0; j < 9; j++) {
        		if (board[i][j] != '.') {
        			nVal = board[i][j] - '1';
        			if (unitMap.containsKey(nVal)) {
        				return false;
        			} else {
        				unitMap.put(nVal, nVal);
        			}
        		}
        	}
        }

        //Check whether every column is valid
        for (i = 0; i < 9; i++) {
        	unitMap.clear();
        	for (j=0; j < 9; j++) {
        		if (board[j][i] != '.') {
        			nVal = board[j][i] - '1';
        			if (unitMap.containsKey(nVal)) {
        				return false;
        			} else {
        				unitMap.put(nVal, nVal);
        			}
        		}
        	}
        }

        //Check whether every 3x3 unit is valid
        for (i=0; i<3; i++) {
        	for (j=0; j<3; j++) {
        		unitMap.clear();
        		for (m=0; m<3; m++) {
        			for (n=0; n<3; n++) {
        				if (board[j*3+m][i*3+n] != '.') {
        					nVal = board[j*3+m][i*3+n] - '1';
        					if (unitMap.containsKey(nVal)) {
        						return false;
        					} else {
        						unitMap.put(nVal, nVal);
        					}
        				}
        			}
        		}
        	}
        }
        
        return true;
    }
	
}
