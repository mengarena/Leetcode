package com.leet;

import java.util.ArrayList;
import java.util.List;

//You are playing the following Flip Game with your friend: 
//	Given a string that contains only these two characters: + and -, 
//	you and your friend take turns to flip two consecutive "++" into "--". 
//	The game ends when a person can no longer make a move and therefore the other person will be the winner.
//
//Write a function to compute all possible states of the string after one valid move.
//
//For example, given s = "++++", after one move, it may become one of the following states:
//
//[
//  "--++",
//  "+--+",
//  "++--"
//]
//If there is no valid move, return an empty list [].

//Google
public class FlipGame {

	public FlipGame() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
    public List<String> generatePossibleNextMoves(String s) {
        List<String> lstStates = new ArrayList<String>();
        if (s == null || s.length() < 2) return lstStates;
        int n = s.length();
        int i;
        
        for (i=1; i<n; i++) {
            if (s.charAt(i-1) == '+' && s.charAt(i) == '+') {
                lstStates.add(s.substring(0, i-1) + "--" + s.substring(i+1));
            }
        }
        
        return lstStates;        
    }	
}