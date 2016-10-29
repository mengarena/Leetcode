package com.leet;

//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
//Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
//
//For example,
//Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
//
//Note:
//Because the range might be a large number, the low and high numbers are represented as string.

//Hard
public class StrobogrammaticNumberIII {

	public StrobogrammaticNumberIII() {
		// TODO Auto-generated constructor stub
	}

	
	//AC: 73%
    char[][] pairs = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
    int count = 0;
    
    public int strobogrammaticInRange(String low, String high) {
        for (int len=low.length(); len<=high.length(); len++) {
            formStrobo(low, high, new char[len], 0, len-1);
        }   
        
        return count;
    }
    
    private void formStrobo(String low, String high, char[] carr, int left, int right) {
        if (left > right) {
            String s = new String(carr);
            if ((s.length() == low.length() && s.compareTo(low) < 0) || (s.length() == high.length() && s.compareTo(high) > 0)) {
                return;
            }
            count++;
            return;
        }
        
        for (int i = 0; i < pairs.length; i++) {
            if (left == 0 && carr.length > 1 && pairs[i][0] == '0') continue;
            carr[left] = pairs[i][0];
            carr[right] = pairs[i][1];
            if (left < right || (left == right && pairs[i][0] == pairs[i][1])) formStrobo(low, high, carr, left+1, right-1);
        }
    }
 

}
