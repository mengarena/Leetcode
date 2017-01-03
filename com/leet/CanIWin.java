/*
464. Can I Win 

In the "100 game," two players take turns adding, to a running total, any integer from 1..10. 
The player who first causes the running total to reach or exceed 100 wins.

What if we change the game so that players cannot re-use integers?

For example, two players might take turns drawing from a common pool of numbers of 1..15 without replacement until they reach a total >= 100.

Given an integer maxChoosableInteger and another integer desiredTotal, determine if the first player to move can force a win, assuming both players play optimally.

You can always assume that maxChoosableInteger will not be larger than 20 and desiredTotal will not be larger than 300.

Example

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false

Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win. 
  
 */

//LinkedIn
//Medium
public class Solution {
	
	//ACC:  78%
	//Complexity: O(2^n)   (here n = maxChoosableInteger)
	//(2^n comes from the combination of state, which has maxChoosableInteger bits, each has 2 states, so 2^n)
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        
        if (sum < desiredTotal) return false;
        
        return canIWinHelper(maxChoosableInteger, desiredTotal, 0, new HashMap<>());
    }
    
    private boolean canIWinHelper(int maxChoosableInteger, int desiredTotal, int state, HashMap<Integer, Boolean> hm) {
        if (desiredTotal <= 0) return false;
        if (hm.containsKey(state)) return hm.get(state);
      
        for (int i=0; i<maxChoosableInteger; i++) {
            if (((1 << i) & state) == 0) {
                if (!canIWinHelper(maxChoosableInteger, desiredTotal-(i+1), (1 << i) | state, hm)) {
                    hm.put(state, true);
                    return true;
                }
            }            
        }  

        hm.put(state, false);
        
        return false;
    }
	
	
	
	
	//ACC:  11%
	//Complexity: O(2^n)
    public boolean canIWinA(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= 0) return true;
        
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        
        if (sum < desiredTotal) return false;
        
        HashMap<String, Boolean> hm = new HashMap<>();   //Or make it global
        
        int[] narrUsed = new int[maxChoosableInteger+1];

        return canIWinHelper(desiredTotal, narrUsed, hm);
    }
    
    private boolean canIWinHelper(int desiredTotal, int[] narrUsed, HashMap<String, Boolean> hm) {
        if (desiredTotal <= 0) return false;
        String sKey = Arrays.toString(narrUsed);
        
        if (!hm.containsKey(sKey)) {
            for (int i=1; i<narrUsed.length; i++) {
                if (narrUsed[i] == 0) {
                    narrUsed[i] = 1;
                    
                    if (!canIWinHelper(desiredTotal-i, narrUsed, hm)) {
                        hm.put(sKey, true);
                        narrUsed[i] = 0;
                        return true;
                    }
                    
                    narrUsed[i] = 0;
                }
            }
            
            hm.put(sKey, false);
        }
        
        return hm.get(sKey);
    }
    
}

