package com.leet;

//We are playing the Guess Game. The game is as follows:
//
//I pick a number from 1 to n. You have to guess which number I picked.
//
//Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
//
//However, when you guess a particular number x, and you guess wrong, you pay $x. 
//You win the game when you guess the number I picked.
//
//Example:
//
//n = 10, I pick 8.
//
//First round:  You guess 5, I tell you that it's higher. You pay $5.
//Second round: You guess 7, I tell you that it's higher. You pay $7.
//Third round:  You guess 9, I tell you that it's lower. You pay $9.
//
//Game over. 8 is the number I picked.
//
//You end up paying $5 + $7 + $9 = $21.
//Given a particular n ¡Ý 1, find out how much money you need to have to guarantee a win.
//
//Hint:
//
//1. The best strategy to play the game is to minimize the maximum loss you could possibly face. 
//Another strategy is to minimize the expected loss. Here, we are interested in the first scenario.
//2. Take a small example (n = 3). What do you end up paying in the worst case?
//3. Check out this article if you're still stuck.
//4. The purely recursive implementation of minimax would be worthless for even a small n. You MUST use dynamic programming.
//5. As a follow-up, how would you modify your code to solve the problem of minimizing the expected loss, 
//instead of the worst-case loss?


//Google		
public class GuessNumberHigherLowerII {

	public GuessNumberHigherLowerII() {
		// TODO Auto-generated constructor stub
	}

	
    //ACC:  17ms
    public int getMoneyAmount(int n) {
        int[][] pay = new int[n+1][n+1];    //for range[i~j], need to pay pay[i][j]
        
        return DP(pay, 1, n);    //To get pay[1][n]
    }
    
    private int DP(int[][] pay, int s, int e) {
        if (s >= e) return 0;
        if (pay[s][e] != 0) return pay[s][e];
        
        int tmpPay = Integer.MAX_VALUE;
        
        //Between [s, e], could guess different numbers (i),
        //Here assume the situation is every time, once I make a guess (each guess will divide the range into two), 
	//the guess is wrong and pay most (Math.max)
        //The purpose is to get the minimal max (i.e. if every time guessed wrong, 
	//the minimal money I need to pay (Math.min, i.e. the optimal guessing point)
        for (int i=s; i<=e; i++) {
            int tmp = i + Math.max(DP(pay, s, i-1), DP(pay, i+1, e));    
		                                 //i == penalty of guessing wrong, PLUS the cost in the two sub ranges
            tmpPay = Math.min(tmpPay, tmp);
        }
        
        pay[s][e] = tmpPay;
        
        return tmpPay;
    }

    
    
    //ACC:  22ms
    public int getMoneyAmountB(int n) {
        if (n <= 1) return 0;
        
        int[][] pay = new int[n+1][n+1];
        
        for (int gap = 1; gap < n; gap++) {    //If gap = 0, cost = 0, so start from 1
            for (int i = 0; i+gap <= n; i++) {
                int j = i + gap;
                
                pay[i][j] = Integer.MAX_VALUE;
                
                for (int k = i; k <= j; k++) {
                    int guessRet = k + Math.max(k-1 >= i ? pay[i][k-1] : 0, j >= k+1? pay[k+1][j] : 0);
                    pay[i][j] = Math.min(pay[i][j], guessRet);
                }
            }
        }
        
        return pay[1][n];
    }
}
