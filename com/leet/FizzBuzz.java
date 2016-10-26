package com.leet;

import java.util.ArrayList;
import java.util.List;

//Write a program that outputs the string representation of numbers from 1 to n.
//
//But for multiples of three it should output ¡°Fizz¡± instead of the number and for the multiples of five output ¡°Buzz¡±. 
//For numbers which are multiples of both three and five output ¡°FizzBuzz¡±.
//
//Example:
//
//n = 15,
//
//Return:
//[
//    "1",
//    "2",
//    "Fizz",
//    "4",
//    "Buzz",
//    "Fizz",
//    "7",
//    "8",
//    "Fizz",
//    "Buzz",
//    "11",
//    "Fizz",
//    "13",
//    "14",
//    "FizzBuzz"
//]


public class FizzBuzz {

	public FizzBuzz() {
		// TODO Auto-generated constructor stub
	}

	//ACC
    public List<String> fizzBuzz(int n) {
        List<String> lstRet = new ArrayList<String>();
        if (n < 1) return lstRet;
        
        for (int i=1; i<=n; i++) {
            if (i % 15 == 0) {
                lstRet.add("FizzBuzz");
            } else if (i % 3 == 0) {
                lstRet.add("Fizz");
            } else if (i % 5 == 0) {
                lstRet.add("Buzz");
            } else {
                lstRet.add(i + "");
            }
        }
        
        return lstRet;
    }

}
