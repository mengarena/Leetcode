/*
481. Magical String

A magical string S consists of only '1' and '2' and obeys the following rules:

The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.

The first few elements of string S is the following: S = "1221121221221121122……"

If we group the consecutive '1's and '2's in S, it will be:

1 22 11 2 1 22 1 22 11 2 11 22 ......

and the occurrences of '1's or '2's in each group are:

1 2	2 1 1 2 1 2 2 1 2 2 ......

You can see that the occurrence sequence above is the S itself.

Given an integer N as input, return the number of '1's in the first N number in the magical string S.

Note: N will not exceed 100,000.

Example 1:
Input: 6
Output: 3

Explanation: The first 6 elements of magical string S is "12211" and it contains three 1's, so return 3.
*/

//Medium
//Google

public class Solution {
	
	//Strategy: Use two indexes
	//One index points to the (starting) number to be checked
	//Another index points to the number which is treated as count for the number(s) pointed by the first index
    public int magicalString(int n) {
        if (n <= 0) return 0;
        int oneCount = 0;
        List<Integer> lstNum = new ArrayList<>();
        int countIdx = 0;
        int numIdx = 0;
        int count;
        
        lstNum.add(1);

        while (countIdx < n) {
            count = lstNum.get(countIdx);
            
            if (count == 1) {
                oneCount++;
                
                if (lstNum.get(numIdx) == 1) {
                    if (lstNum.size() == numIdx+1) {
                        lstNum.add(2);
                    }
                } else {
                    if (lstNum.size() == numIdx+1) {
                        lstNum.add(1);
                    }
                }
                
                numIdx++;
            } else {
                if (lstNum.get(numIdx) == 1) {
                    if (lstNum.size() == numIdx+1) {
                        lstNum.add(1);
                        lstNum.add(2);
                    } else if (lstNum.size() == numIdx+2) {
                        lstNum.add(2);
                    }
                } else {
                    if (lstNum.size() == numIdx+1) {
                        lstNum.add(2);
                        lstNum.add(1);
                    } else if (lstNum.size() == numIdx+2) {
                        lstNum.add(1);
                    }
                }
                
                numIdx += 2;
            }
            
            countIdx++;
        }

        return oneCount;
    }
}

