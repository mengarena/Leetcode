/*

552. Student Attendance Record II

Given a positive integer n, return the number of all possible attendance records with length n, 
which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8 
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times. 

Note: The value of n won't exceed 100,000.

Hard

Google, Uber
*/


class Solution {
public:

    // 82%
    // Strategy: First only consider strings containing P and L, then try to insert one 'A' in those strings
    int checkRecord(int n) {
        int NN = 1e9 + 7;
        vector<long> endingPorL(n+1, 0);   // #string ending with P or L
        vector<long> endingP(n+1, 0);      // #string only ending with P
        
        endingPorL[0] = 1;    // # of "" string 1
        endingP[0] = 1;       
        
        endingPorL[1] = 2;    // "L" or "P"
        endingP[1] = 1;       // "P"
        
        for (int i=2; i<=n; ++i) {
            endingP[i] = endingPorL[i-1];    // 'P' could be added to all strings (with len i-1) ending with P or L
            endingPorL[i] = (endingP[i] + endingP[i-1] + endingP[i-2]) % NN;  // ending with P or L means ending P + ending L
                                                                              // so ending with P is endingP[i]
                                                                              // ending with L could be "...PL" or "...PLL"
                                                                              // so endingP[i-1] + endingP[i-2]
        }

        int res = endingPorL[n];  // without 'A'
        // Here below try to insert 'A' at different positions (i.e. 0~n-1)
        // After inserting 'A', the string is divided into two parts
        for (int i=0; i<n; ++i) {
            int tmp = (endingPorL[i]*endingPorL[n-1-i]) % NN;
            res = (res + tmp) % NN;
        }
        
        return res;
    }
};
