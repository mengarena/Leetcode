package com.leet;

//The API: int read4(char *buf) reads 4 characters at a time from a file.
//
//The return value is the actual number of characters read. 
//For example, it returns 3 if there is only 3 characters left in the file.
//
//By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//
//Note:
//The read function will only be called once for each test case.

public class ReadNCharactersGivenRead4 {

	public ReadNCharactersGivenRead4() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		
	}
	
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int i=0, j=0;
        int nReadCnt = 0;
        int nBufIdx = 0;
        boolean bFileEnd = false;
        char[] carr = new char[4];
        
        while (i+4 <= n) {
        	i = i+4;
        	//nReadCnt = read4(carr);   //read4 is API
        	
        	for (j=0; j<nReadCnt; j++) buf[nBufIdx++] = carr[j];
        	
        	if (nReadCnt < 4) {
        		bFileEnd = true;
        		break;
        	}
        }
        
        if (bFileEnd == false) {
        	//nReadCnt = read4(carr);   //read4 is API
        	
        	for (j=0; j<Math.min(nReadCnt, n-i); j++) buf[nBufIdx++] = carr[j];
        	
        }
        
        return nBufIdx;
    }
	
}
