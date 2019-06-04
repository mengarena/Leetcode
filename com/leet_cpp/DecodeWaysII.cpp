/*

639. Decode Ways II

A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', 
which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".

Example 2:
Input: "1*"
Output: 9 + 9 = 18

Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.

Hard

Facebook
*/


class Solution {
public:
    
    // 93%
    int numDecodings(string s) {
        int NN = 1000000007;
        int n = s.length();
        if (n == 0) return 0;
        
        long dp[n+1] = {0};  // #decoding way by position i-1 in s
        
        dp[0] = 1;
        
        if (s[0] == '0') {
            dp[1] = 0;
        } else if (s[0] == '*') {
            dp[1] = 9;
        } else {
            dp[1] = 1;
        }
        
        // c0c1c2 
        // dp[i-2] is the #decoding way by 'c0' (i.e s[i-3])
        for (int i = 2; i<=n; ++i) {
            char c1 = s[i-2];
            char c2 = s[i-1];
            long sum = 0;
            
            // Situation 1: c1c2   (i.e. try to decode using two digits c1c2)
            if (c1 == '1') {
                if (c2 == '*') {
                    sum = 9 * dp[i-2] % NN;
                } else {
                    sum = dp[i-2];
                }
            } else if (c1 == '2') {
                if (c2 >= '0' && c2 <= '6') {
                    sum = dp[i-2];
                } else if (c2 == '*') {
                    sum = 6*dp[i-2] % NN;   // * could be 1~6
                }
            } else if (c1 == '*') {
                if (c2 == '*') {
                    sum = 15*dp[i-2] % NN;   // 11~19, 21~26 so total 15
                } else if (c2 <= '6') {
                    sum = 2*dp[i-2] % NN;    // 1(c2) or 2(c2) so 2
                } else {
                    sum = dp[i-2];   // c1 could only be '1' to decode with c2
                }
            }
            
            // Situation 2: c2  (i.e. try to decode using only one digit c2)
            if (c2 == '*') {
                sum = (sum + 9*dp[i-1]) % NN;
            } else if (c2 != '0') {
                sum = (sum + dp[i-1]) % NN;
            }   
            
            dp[i] = sum % NN;   
                
        }

        return dp[n];
    }
};


