"""

1556. Thousand Separator

Given an integer n, add a dot (".") as the thousands separator and return it in string format.

Example 1:
Input: n = 987
Output: "987"

Example 2:
Input: n = 1234
Output: "1.234"

Example 3:
Input: n = 123456789
Output: "123.456.789"

Example 4:
Input: n = 0
Output: "0"
 
Constraints:
0 <= n < 2^31

Easy
"""

class Solution:
    def thousandSeparator(self, n: int) -> str:
        strN = str(n)
        strLen = len(strN)
        ret = ""
        cnt = strLen//3
        remained = strLen%3
        if remained > 0:
            ret = strN[0:remained]
        
        for i in range(cnt):
            if len(ret) > 0:
                ret = ret + "."
            ret = ret + strN[remained+3*i:remained+3*(i+1)]
            
        return ret
        
