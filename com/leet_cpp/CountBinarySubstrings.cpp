/*

696. Count Binary Substrings

Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's, 
and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

Example 1:
Input: "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: 
"0011", "01", "1100", "10", "0011", and "01".

Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:
Input: "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.

Note:
s.length will be between 1 and 50,000.
s will only consist of "0" or "1" characters.

Easy
*/


class Solution {
public:
    
    // 90%
    // Strategy:  Convert "000111000" into an array like:  2, -2, 2...
    //            For each 000111,  it has min(#0, #1) substrings
    int countBinarySubstrings(string s) {
        if (s.length() <= 1) return 0;

        int n = s.length();
        int counts[n];
        int idx = 0;
        int prev = s[0];
        int count = 1;
        
        for (int i = 1; i<s.length(); ++i) {
            if (s[i] == prev) {
                count++;
            } else {
                if (prev == 0) {
                    counts[idx++] = count;
                } else {
                    counts[idx++] = -1*count;
                }
                prev = s[i];
                count = 1;
            }
        }
        
        if (prev == 0) {
            counts[idx++] = count;
        } else {
            counts[idx++] = -1*count;
        }
        
        int sum = 0;
        
        for (int i=1; i<idx; i++) {
            sum += min(abs(counts[i-1]), abs(counts[i]));
        }
        
        return sum;
    }
};
