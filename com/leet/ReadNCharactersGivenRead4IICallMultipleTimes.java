package com.leet;

import java.util.LinkedList;
import java.util.Queue;

//The API: int read4(char *buf) reads 4 characters at a time from a file.
//
//The return value is the actual number of characters read. 
//		For example, it returns 3 if there is only 3 characters left in the file.
//
//By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//
//Note:
//The read function may be called multiple times.

//Google, Facebook, Bloomberg
//Hard
public class ReadNCharactersGivenRead4IICallMultipleTimes {

	public ReadNCharactersGivenRead4IICallMultipleTimes() {
		// TODO Auto-generated constructor stub
	}

	
	/* The read4 API is defined in the parent class Reader4.
    int read4(char[] buf); */
	
	/**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
	
	
	//AC: Performance is the same as below solution
	private int buffPtr = 0;  //Reading pointer in buff
	private int buffCnt = 0;  //Number of character in buff
	private char[] buff = new char[4];
	
	public int read(char[] buf, int n) {
        if (n == 0) return 0;
	    int buffIdx = 0;
	    
	    while (buffIdx < n) {
	    	if (buffPtr == 0) {   //Equals 0 means buff is empty; otherwise, there are remaining characters in buff
	    		//buffCnt = read4(buff);
	    	}
	    	
	    	if (buffCnt == 0) break;   //No characters in buff
	    	
	    	while (buffIdx < n && buffPtr < buffCnt) buf[buffIdx++] = buff[buffPtr++];
	    	
	    	if (buffPtr >= buffCnt) buffPtr = 0;  //If all the buff has been used, reset buffPtr to 0
	    }
	    
	    return buffIdx;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//AC:  
	//What this question is asking is that:  if last time, read4 get some characters, but has not been used all, 
	//the remaining part needs to be stored for next call
	//Next call might not used the remaining all, then the remaining remaining should be for third call....
	
    Queue<Character> qu = new LinkedList<Character>();    //To store read but unused characters
    
    public int readA(char[] buf, int n) {
        if (n == 0) return 0;
        int buffIdx = 0;
        int i = 0;
        int actualCnt = 0;
        boolean fileEnd = false;
        char carr[] = new char[4];
        
        while (!qu.isEmpty() && buffIdx < n) {    //First use last time used character
            buf[buffIdx++] = qu.poll();
        }
        
        if (!qu.isEmpty()) return buffIdx;
        
        n = n - buffIdx;
        
        while (i + 4 <= n) {
        	i = i + 4;
        	
        	//actualCnt = read4(carr);
        	
        	for (int j=0; j<actualCnt; j++) buf[buffIdx++] = carr[j];
        	
        	if (actualCnt < 4) {
        		fileEnd = true;
        		break;
        	}
        }
        
        if (fileEnd == false) {
        	//actualCnt = read4(carr);
        	
        	for (int j=0; j<Math.min(n-i, actualCnt); j++) buf[buffIdx++] = carr[j];
        	
        	if (n-i < actualCnt) {
        	    for (int j=n-i; j<actualCnt; j++) qu.offer(carr[j]);   //Remained characters are stored in queue
        	}
        }
        
        return buffIdx;        
    }

}
