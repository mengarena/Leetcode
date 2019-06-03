/*

556. Next Greater Element III

Given a positive 32-bit integer n, you need to find the smallest 32-bit integer 
which has exactly the same digits existing in the integer n and is greater in value than n. 
If no such positive 32-bit integer exists, you need to return -1.

Example 1:
Input: 12
Output: 21
 
Example 2:
Input: 21
Output: -1

Medium
*/


class Solution {
public:
    
    // 100%
    // Strategy:  Find the last place where s[i] > s[i-1], then the digit at [i-1] should be exchanged with a digit behind
    // Search for the first digit after i-1, which is the smallest larger digit then s[i-1]
    // After swap these two digits, all the digits after position [i-1] should be in ascending order to make sure the result
    // number is smallest
    int nextGreaterElement(int n) {
        string s = to_string(n);
        int i = s.length()-1;
        bool changed = false;
        int position = -1;
        
        while (i > 0) {
            if (s[i] > s[i-1]) {
                position = i;
                break;
            }
            i--;
        }
        
        if (position == -1) return -1;
        
        int minMaxPos = position;
        char minMaxVal = s[position];
        
        for (i=position+1; i<s.length(); ++i) {
            if (s[i] > s[position-1] && s[i] < minMaxVal) {
                minMaxPos = i;
                minMaxVal = s[i];
            }
        }
        
        swap(s[minMaxPos], s[position-1]);
        
        sort(s.begin() + position, s.end());  // Sorrt remaining part to be in ascending order
        
        string ss = to_string(INT_MAX);
        
        // Compare with largest INT value
        if (s.length() == ss.length() && s.compare(ss) > 0) return -1;
        
        return stoi(s);
    }
};
