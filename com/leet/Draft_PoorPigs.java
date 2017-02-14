package com.leet;

//There are 1000 buckets, one and only one of them contains poison, the rest are filled with water. 
//They all look the same. 
//If a pig drinks that poison it will die within 15 minutes. 
//What is the minimum amount of pigs you need to figure out which bucket contains the poison within one hour.
//
//Answer this question, and write an algorithm for the follow-up general case.
//
//Follow-up:
//
//If there are n buckets and a pig drinking poison will die within m minutes, 
//how many pigs (x) you need to figure out the "poison" bucket within p minutes? 
//There is exact one bucket with poison.

//Easy
public class PoorPigs {

	public PoorPigs() {
		// TODO Auto-generated constructor stub
	}

    /*
    With 2 pigs, poison killing in 15 minutes, and having 60 minutes, 
	we can find the poison in up to 25 buckets in the following way. 
	Arrange the buckets in a 5×5 square:

	 1  2  3  4  5
	 6  7  8  9 10
	11 12 13 14 15
	16 17 18 19 20
	21 22 23 24 25

	Now use one pig to find the row (make it drink from buckets 1, 2, 3, 4, 5, wait 15 minutes, 
	make it drink from buckets 6, 7, 8, 9, 10, wait 15 minutes, etc). 
	Use the second pig to find the column (make it drink 1, 6, 11, 16, 21, then 2, 7, 12, 17, 22, etc).

	Having 60 minutes and tests taking 15 minutes means we can run four tests. 
	If the row pig dies in the third test, the poison is in the third row. 
	If the column pig doesn't die at all, the poison is in the fifth column 
	(this is why we can cover five rows/columns even though we can only run four tests).

    (If first pig died at 3rd row, second pig didn't die, the poisoned bucket is on row 3, column 5)

	With 3 pigs, we can similarly use a 5×5×5 cube instead of a 5×5 square and 
	again use one pig to determine the coordinate of one dimension. 
	So 3 pigs can solve up to 125 buckets.

	In general, we can solve up to (⌊minutesToTest / minutesToDie⌋ + 1)^pigs buckets this way, 

	so just find the smallest sufficient number of pigs for example like this:

    
     int nAttempt = minutesToTest/minutesToDie;  //drink everytime
     int base = nAttempt + 1;  //1: one round not drink
     //every pig has base status, how many pigs to represent buckets
     int ans = (int) Math.ceil(Math.log(buckets)/Math.log(base));
     */

    //ACC
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int base = minutesToTest/minutesToDie + 1;
        
        return (int) Math.ceil(Math.log(buckets) / Math.log(base));
    }


	public int poorPigsA(int buckets, int minutesToDie, int minutesToTest) {
		if (buckets-- == 1) {
			return 0;
		}
		
		int base = minutesToTest / minutesToDie + 1;
		int count = 0;
		
		while (buckets > 0) {
			buckets /= base;
			count++;
		}
		
		return count;
	}




}
