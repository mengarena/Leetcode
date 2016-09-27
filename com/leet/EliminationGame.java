package com.leet;

import java.util.ArrayList;
import java.util.List;

//There is a list of sorted integers from 1 to n. 
//Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.
//
//Repeat the previous step again, but this time from right to left, 
//remove the right most number and every other number from the remaining numbers.
//
//We keep repeating the steps again, alternating left to right and right to left, until a single number remains.
//
//Find the last number that remains starting with a list of length n.
//
//Example:
//
//Input:
//n = 9,
//1 2 3 4 5 6 7 8 9
//2 4 6 8
//2 6
//6
//
//Output:
//6

public class EliminationGame {

	public EliminationGame() {
		// TODO Auto-generated constructor stub
	}

	
	//ACC:  31%
	//After each round, half number of elements will remain
	//
    public int lastRemaining(int n) {
        int head = 1;   
        int step = 1;  //Step or gap will double every round
        int remained = n;
        boolean bLeft = true;
        
        //Although should start from left and then right; it convert to the operation every time from left
        //The only problem is to decide which will be the first left number
        while (remained > 1) {
            if (bLeft || remained % 2 == 1) { //Decide the position of next head position
            	                             //If this round from left, next time, the left most number will be head + step
            	                             //If this round is from right and % 2 == 1, next time the left most number will be head + step
            	                             //For example, numbers are 2 4 6 8 10 and from right, (step = 2, current head = 2), next head = 4
            	                             //Otherwise, head does not change
                head += step;
            }
            
            remained = remained/2;
            step = step * 2;
            bLeft = !bLeft;
        }
        
        return head;
     }
	
	
	//Time Limit Exceeded
    public int lastRemainingA(int n) {
        List<Integer> lstNum = new ArrayList<>();
        int i;
        boolean fromLeft = true;
        int startPos = 0;
        int size = 0;
        
        for (i=1; i<=n; i++) lstNum.add(i);
        
        while (lstNum.size() > 1) {
            size = lstNum.size();
            
            if (fromLeft) {
                if (size % 2 == 1) {
                    startPos = size - 1;
                } else {
                    startPos = size - 2;
                }
                
            } else {
                startPos = size - 1;
            }
            
            for (int j = startPos; j>=0; j=j-2) lstNum.remove(j);
            
            fromLeft = !fromLeft;    
        }
        
        return lstNum.get(0);
    }

}
