/*

866. Prime Palindrome

Find the smallest prime palindrome greater than or equal to N.

Recall that a number is prime if it's only divisors are 1 and itself, and it is greater than 1. 

For example, 2,3,5,7,11 and 13 are primes.

Recall that a number is a palindrome if it reads the same from left to right as it does from right to left. 

For example, 12321 is a palindrome.

Example 1:
Input: 6
Output: 7

Example 2:
Input: 8
Output: 11

Example 3:
Input: 13
Output: 101
 
Note:

1 <= N <= 10^8
The answer is guaranteed to exist and be less than 2 * 10^8.

Medium
*/


class Solution {
public:
    bool isPrime(int num) {
        if (num < 2) return false;
        
        for (int i=2; i*i <= num; ++i) {
            if (num % i == 0) return false;
        }
        
        return true;
    }
    
    // Get the next palindrom number >= num
    int nextPalindrome(int num) {
        string s = to_string(num);
        int n = s.length();
        if (n == 1) return num;
        
        string half = s.substr(0, n/2);
        string tmpHalf = half;
        string result = half;
        if (n % 2 ==1) result += s[n/2];
        reverse(half.begin(), half.end());
        result += half;
        
        if (result.compare(s) >= 0) return stoi(result);
        
        if (n % 2 == 1) {
            tmpHalf = s.substr(0, n/2+1);
        }
        
        int base = stoi(tmpHalf);
        base++;
        
        string sbase = to_string(base);
        
        if (sbase.length() == tmpHalf.length()) {
            if (n % 2 == 1) {
                string ss = sbase.substr(0, sbase.length()-1);
                reverse(ss.begin(), ss.end());
                result = sbase + ss;
            } else {
                result = sbase;
                reverse(sbase.begin(), sbase.end());
                result += sbase;
            }
        } else {
            string ss = sbase.substr(0, n/2);
            reverse(ss.begin(), ss.end());
            result = sbase + ss;
        }
        
        return stoi(result);
    }
    
    // 77%
    int primePalindrome(int N) {
        int nextNum = nextPalindrome(N);
        
        while (!isPrime(nextNum)) {
            nextNum = nextPalindrome(nextNum+1);
        }
        
        return nextNum;
    }
};
