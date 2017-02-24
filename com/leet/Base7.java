/*
Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"

Example 2:
Input: -7
Output: "-10"

Note: The input will be in range of [-1e7, 1e7].
*/

//Easy

public class Solution {
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        boolean bNeg = false;
        long tmpNum = num;
        if (num < 0) {
            bNeg = true;
            tmpNum = (long)Math.abs(num);
        }
        
        while (tmpNum > 0) {
            sb.insert(0, tmpNum % 7);
            tmpNum = tmpNum/7;
        }
        
        if (bNeg) sb.insert(0, '-');
        
        return sb.toString();
    }
}
