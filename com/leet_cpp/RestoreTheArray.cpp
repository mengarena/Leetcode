/*

1416. Restore The Array

A program was supposed to print an array of integers. 
The program forgot to print whitespaces and the array is printed as a string of digits and 
all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.

Given the string s and the integer k. There can be multiple ways to restore the array.

Return the number of possible array that can be printed as a string s using the mentioned program.
The number of ways could be very large so return it modulo 10^9 + 7

Example 1:
Input: s = "1000", k = 10000
Output: 1
Explanation: The only possible array is [1000]

Example 2:
Input: s = "1000", k = 10
Output: 0
Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.

Example 3:
Input: s = "1317", k = 2000
Output: 8
Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]

Example 4:
Input: s = "2020", k = 30
Output: 1
Explanation: The only possible array is [20,20]. [2020] is invalid because 2020 > 30. [2,020] is ivalid because 020 contains leading zeros.

Example 5:
Input: s = "1234567890", k = 90
Output: 34
 
Constraints:
1 <= s.length <= 10^5.
s consists of only digits and doesn't contain leading zeros.
1 <= k <= 10^9.

Hard
*/

class Solution {
public:

    // 20%
    int numberOfArrays(string s, int k) {
        long mod = 1e9+7;
        string strK = to_string(k);
        int lenK = strK.length();
        int n = s.length();
        vector<long> dp(n, 0);
        
        if (s[0] == '0') return 0;
        
        dp[0] = 1;
        
        for (int i=1; i<n; ++i) {
            int start = max(0, i-lenK+1);
            
            if (start-1 >= 0 && dp[start-1] == 0) return 0;
            
            for (int j=start; j<=i; ++j) {
                string tmp = s.substr(j, i-j+1);
                if (tmp[0] == '0' || k < stol(tmp)) continue;       // Don't use: strK.compare(tmp) < 0,   because "10000" < "1234"
                if (j-1 >= 0) {
                    dp[i] += dp[j-1];
                } else {
                    dp[i] += 1;
                }
                dp[i] = dp[i] % mod;
            }
        }
        
        return dp[n-1];
    }
};
