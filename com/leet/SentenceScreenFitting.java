package com.leet;

//Given a rows x cols screen and a sentence represented by a list of words, 
//find how many times the given sentence can be fitted on the screen.
//
//Note:
//
//A word cannot be split into two lines.
//The order of words in the sentence must remain unchanged.
//Two consecutive words in a line must be separated by a single space.
//Total words in the sentence won't exceed 100.
//Length of each word won't exceed 10.
//1 ¡Ü rows, cols ¡Ü 20,000.
//Example 1:
//
//Input:
//rows = 2, cols = 8, sentence = ["hello", "world"]
//
//Output: 
//1
//
//Explanation:
//hello---
//world---
//
//The character '-' signifies an empty space on the screen.
//Example 2:
//
//Input:
//rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
//
//Output: 
//2
//
//Explanation:
//a-bcd- 
//e-a---
//bcd-e-
//
//The character '-' signifies an empty space on the screen.
//Example 3:
//
//Input:
//rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
//
//Output: 
//1
//
//Explanation:
//I-had
//apple
//pie-I
//had--
//
//The character '-' signifies an empty space on the screen.
//
//Google
public class SentenceScreenFitting {

	public SentenceScreenFitting() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		String[] sentence = {"f", "p", "a"};
		int rows = 8;
		int cols = 7;
		
		int count = wordsTyping(sentence, rows, cols);
		
		System.out.println(count);
	}
	
	
	//ACC: 580 ms
	//Strategy:  Get the length of the words, put in an array
	//Based on the current column position and next word, to calculate next start position if the next word is put
	//
	//The case might repeat, for example, after put the sentences, it might comes to a row's head, and at the same time, the sentence comes to the head too, 
	//Then we could use this as a repeat pattern to fill the screen quickly
    public int wordsTyping(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length == 0) return 0;
        if (rows <= 0 || cols <= 0) return 0;
        int n = sentence.length;
        int[] narrLen = new int[n];
        int i;
        int idx = 0;
        int r = 0, c = 0;
        int nextEndPos;
        int maxLen = Integer.MIN_VALUE;
        int count = 0;
        int baseRowSeg = 0;
        
        for (i=0; i<n; i++) {
            narrLen[i] = sentence[i].length();
            maxLen = Math.max(maxLen, narrLen[i]);
        }
        
        if (maxLen > cols) return 0;
        
        while (r < rows) {
            nextEndPos = c + narrLen[idx] - 1;  //After putting the next word, where the cursor will be
            
            if (nextEndPos < cols-1) {   //Case one: after putting the word, has not reached the end of this column
                c = nextEndPos + 2;   //Add a space, so next start column position will be nextEndPos+2
                if (c >= cols) {
                    r++;
                    c = 0;
                }
                if (idx == n-1) {    //Reached sentence end
                    count++;
                    idx = 0;
                } else {
                    idx++;
                }
            } else if (nextEndPos == cols-1) {   //Case two: after putting the word, reached the end of this column
                c = 0;
                r++;
                if (idx == n-1) {   //Reached sentence end
                    count++;
                    idx = 0;
                } else {
                    idx++;
                }
            } else {   //If put the next word, it will exceed the end of the column(i.e. the word could not be put here, need to start from next row)
                r++;
                c = 0;
            }
            
            //If sentence come to head and column comes to head too, could use repeat pattern
            if (idx == 0 && c == 0 && count > 0 && rows - r >= baseRowSeg) {
                count += (rows-r)/r * count;
                baseRowSeg = r;
                r = rows/baseRowSeg*baseRowSeg;    //Directly jump over the repeated rows and next r start from the first row of the remained rows
            }
        }
        
        return count;
    }

}
