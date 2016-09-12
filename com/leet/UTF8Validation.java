package com.leet;

//A character in UTF8 can be from 1 to 4 bytes long, subjected to the following rules:
//
//For 1-byte character, the first bit is a 0, followed by its unicode code.
//For n-bytes character, the first n-bits are all one's, the n+1 bit is 0, followed by n-1 bytes with most significant 2 bits being 10.
//
//This is how the UTF-8 encoding would work:
//
//   Char. number range  |        UTF-8 octet sequence
//      (hexadecimal)    |              (binary)
//   --------------------+---------------------------------------------
//   0000 0000-0000 007F | 0xxxxxxx
//   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
//   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
//   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
//
//   Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
//
//Note:
//The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data. 
//This means each integer represents only 1 byte of data.
//
//Example 1:
//
//data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
//
//Return true.
//It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
//
//Example 2:
//
//data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
//
//Return false.
//
//The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
//The next byte is a continuation byte which starts with 10 and that's correct.
//But the second continuation byte does not start with 10, so it is invalid.

//Google
public class UTF8Validation {

	public UTF8Validation() {
		// TODO Auto-generated constructor stub
	}

	//ACC:  8ms
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) return true;
        int n = data.length;
        int i = 0;
        int nByteCnt = 0;
        
        while (i < n) {
            nByteCnt = getByteCnt(data[i]);
            if (nByteCnt == 0) return false;
            
            if (nByteCnt+i > n) return false;
            
            for (int j=1; j<nByteCnt; j++) {
                if (!isFollowByte(data[i+j])) return false;
            }
            
            i = i + nByteCnt;
        }
        
        return true;
    }
    
    //Check whether it is the valid starting byte of UTF-8, and how many bytes UTF-8 it is
    private int getByteCnt(int num) {
        if (((num >> 3) & 0x1E) == 0x1E) return 4;
        if (((num >> 4) & 0xE) == 0xE) return 3;
        if (((num >> 5) & 0x6) == 0x6) return 2;
        if ((num & 0x80) == 0) return 1;
        
        return 0;
    }
    
    //Check whether it is the valid byte following the leading UTF-8 byte
    private boolean isFollowByte(int num) {
        if ((num & 0x80) == 0x80) return true;
        return false;
    }

}