package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//You are playing the following Flip Game with your friend: 
//	Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
//	The game ends when a person can no longer make a move and therefore the other person will be the winner.
//
//Write a function to determine if the starting player can guarantee a win.
//
//For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
//
//Follow up:
//Derive your algorithm's runtime complexity.

//Google
public class FlipGameII {

	public FlipGameII() {
		// TODO Auto-generated constructor stub
	}


    public void run() {
        String s = "+++++++++";
        
        boolean bret = canWin(s);
        
        System.out.println(bret);
    }

    
    //AC:  92%
    //Improve from the solution below:
    //Use a hashmap to remember the string has been judged before
    //The same string (stmp) will occur again, for example, original we have a string "-++-+-+++"
    //In the for loop, if i = 1,  we have ----+-+++, and next recursion, we have ----+---+
    //In the for loop, if i = 6,  we have -++-+---+, and next recursion, we have ----+---+  (which occurred before)
    //
    //Strategy: try to replace "++" with "--", and then to see whether the opponent could win
    //If the opponent could not win (means I will win), then I could take this solution, i.e. I first flip "++" at this position
    public boolean canWin(String s) {
        if (s == null || s.length() < 2) return false;
        Map<String, Boolean> hm = new HashMap<String, Boolean>();
        
        return canWinHelper(s, hm);
    }
    
    private boolean canWinHelper(String s, Map<String, Boolean> hm) {
    	if (hm.containsKey(s)) return hm.get(s);
    	
        int n = s.length();     
        
        String stmp = "";
        
        for (int i=1; i<n; i++) {
        	if (s.charAt(i-1) == '+' && s.charAt(i) == '+') {
        		stmp = s.substring(0, i-1) + "--" + s.substring(i+1);  //Now opponent will have this string to process
        		
        		if (!canWinHelper(stmp, hm)) {   //The opponent can't win, so I win
        			hm.put(s, true);
        			return true;
        		}
        	}
        }
        
        hm.put(s, false);
        return false;  
    } 
    
    
    //AC:  40%
    //Strategy: try to replace "++" with "--", and then to see whether the opponent could win
    //If the opponent could not win (means I will win), then I could take this solution, i.e. I first flip "++" at this position
    public boolean canWinA(String s) {
        if (s == null || s.length() < 2) return false;
        int n = s.length();     
        
        String stmp = "";
        
        for (int i=1; i<n; i++) {
        	if (s.charAt(i-1) == '+' && s.charAt(i) == '+') {
        		stmp = s.substring(0, i-1) + "--" + s.substring(i+1);
        		
        		if (!canWinA(stmp)) {   //The opponent can't win, so I win
        			return true;
        		}
        	}
        }
        
        return false;  
    } 

    
}
