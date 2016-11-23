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
public class Draft_PoorPigs {

	public Draft_PoorPigs() {
		// TODO Auto-generated constructor stub
	}

	public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
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


    /*
     int nAttempt = minutesToTest/minutesToDie;  //drink everytime
     int base = nAttempt + 1;  //1: one round not drink
     //every pig has base status, how many pigs to represent buckets
     int ans = (int) Math.ceil(Math.log(buckets)/Math.log(base));
     */

}