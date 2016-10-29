package com.leet;

//Given an integer, write an algorithm to convert it to hexadecimal. 
//For negative integer, two¡¯s complement method is used.
//
//Note:
//
//1. All letters in hexadecimal (a-f) must be in lowercase.
//2. The hexadecimal string must not contain extra leading 0s. 
//If the number is zero, it is represented by a single zero character '0'; 
//otherwise, the first character in the hexadecimal string will not be the zero character.
//3. The given number is guaranteed to fit within the range of a 32-bit signed integer.
//4. You must not use any method provided by the library which converts/formats the number to hex directly.
//
//Example 1:
//
//Input:
//26
//
//Output:
//"1a"
//Example 2:
//
//Input:
//-1
//
//Output:
//"ffffffff"

//Easy
public class ConvertNumberToHexadecimal {

	public ConvertNumberToHexadecimal() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int num = -1;
		
		String sNum = toHex(num);
		
		System.out.println(sNum);
	}
	
	
	//ACC:  8ms
    public String toHex(int num) {
        char[] cHex = {'a', 'b', 'c', 'd', 'e', 'f'};
        if (num == 0) return "0";
        String sNum = "";
        int digit = 0;
        int times = 0;
        
        while (num != 0 && times < 8) {
            digit = (int)(num & 0xf);
            if (digit < 10) {
                sNum = digit + sNum;
            } else {
                sNum = cHex[digit-10] + sNum;
            }
            
            num = num >> 4;
            times++;
        }
        
        return sNum;
    }
}
