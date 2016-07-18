package com.leet;

//We are playing the Guess Game. The game is as follows:
//
//I pick a number from 1 to n. You have to guess which number I picked.
//
//Every time you guess wrong, I'll tell you whether the number is higher or lower.
//
//You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
//
//-1 : My number is lower
// 1 : My number is higher
// 0 : Congrats! You got it!
//Example:
//n = 10, I pick 6.
//
//Return 6.


//Easy
//Google
public class GuessNumberHigherLower {

	public GuessNumberHigherLower() {
		// TODO Auto-generated constructor stub
	}

	/* The guess API is defined in the parent class GuessGame.
	   @param num, your guess
	   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
	      int guess(int num); */
	//// Here "my number" means the number to be guessed (i.e. the correct number)
	public int guess(int guessedNum) {
		return 0;
	}
	
	//ACC
    public int guessNumber(int n) {
        int i = 1;
        int j = n;
        int mid = 0;
        int guessedRet = 0;
        
        while (i <= j) {
            mid = i + (j-i)/2;
            guessedRet = guess(mid);
            
            if (guessedRet == 0) {
                break;
            } else if (guessedRet == 1) {
                i = mid + 1;
            } else {
                j = mid - 1;
            } 
        }
        
        return mid;
    }

}
