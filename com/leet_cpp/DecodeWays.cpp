//91. Decode Ways
//
//A message containing letters from A-Z is being encoded to numbers using the following mapping:
//
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
//
//Given an encoded message containing digits, determine the total number of ways to decode it.
//
//For example,
//Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
//
//The number of ways decoding "12" is 2.
//
//Microsoft, Uber, Facebook

class Solution {
public:
    int numDecodings(string s) {
        if ((s.empty() == true) || (s.at(0) == '0')) return 0;  //Can't use s == NULL  (NULL could only be used for pointer, s is not a pointer)
        int n = s.length();
        int *dp = new int[n+1];
        
        fill_n(dp, n+1, 0);
        
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i=2; i<=n; i++) {
            int twoDigitNum = stoi(s.substr(i-2,2));
            int oneDigitNum = stoi(s.substr(i-1,1));
            
            if (twoDigitNum >= 10 && twoDigitNum <= 26) {
                dp[i] = dp[i-2];
            }
            
            if (oneDigitNum >= 1 && oneDigitNum <= 9) {
                dp[i] += dp[i-1];
            }
        }
        
        return dp[n];
    }
};
