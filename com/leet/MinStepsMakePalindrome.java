package com.leet;


//Given a string, every step you can add/delete/change one character at any 
//position. Minimize the step number to make it a palindrome.


public class MinStepsMakePalindrome {

	public MinStepsMakePalindrome() {
		// TODO Auto-generated constructor stub
	}

	public int minStep(String input) {
	    int inputSize = input.length();
	    int[][] dp = new int[inputSize][inputSize];  //Steps for substring between i, j (both inclusive) to be palindrome
	    for (int i = 0; i < inputSize - 1; i++) {
	        if (input.charAt(i) != input.charAt(i + 1)) {
	            dp[i][i + 1] = 1;
	        }
	    }
	 
	    for (int len = 3; len <= inputSize; len++) {
	        for (int i = 0; i + len - 1 < inputSize; i++) {  //Starting index
	            int endIdx = i + len - 1;
	            //In this "if", input[i] is going to match with input[endIdx] for forming palindrome
	            //Corresponding to "change"
	            if (input.charAt(i) == input.charAt(endIdx)) {
	                dp[i][endIdx] = dp[i + 1][endIdx - 1];
	            } else {
	                dp[i][endIdx] = dp[i + 1][endIdx - 1] + 1;
	            }
	            
	            //Following case: input[i] is not matched with input[endIdx]
	            dp[i][endIdx] = Math.min(dp[i][endIdx], dp[i + 1][endIdx] + 1);  //Corresponds to add or delete   
	            dp[i][endIdx] = Math.min(dp[i][endIdx], dp[i][endIdx - 1] + 1);  //Corresponds to add or delete
	        }
	    }
	    return dp[0][inputSize - 1];
	}

}
