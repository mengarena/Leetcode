/*
248. Strobogrammatic Number III

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.
*/

//Hard

class Solution {
public:
    char pairs[5][2] = {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
    
    int strobogrammaticInRangeHelper(string low, string high, string p, int nLen, int left, int right) {
        if (left > right) {
            if ((nLen == low.size() && p.compare(low) < 0) || (nLen == high.size() && p.compare(high) > 0)) return 0;
            
            return 1;
        }
        
        int countTmp = 0;
        
        for (int i=0; i<5; i++) {
            if (pairs[i][0] == '0' && left == 0 && nLen > 1) continue;
            p[left] = pairs[i][0];
            p[right] = pairs[i][1];
            if ((left < right) || (left == right && pairs[i][0] == pairs[i][1])) {
                countTmp += strobogrammaticInRangeHelper(low, high, p, nLen, left+1, right-1);
            }
        }
        
        return countTmp;
    }

    int strobogrammaticInRange(string low, string high) {
        int count = 0;
        
        for (int i=low.size(); i<=high.size(); i++) {
            string s(i, '0');
            count += strobogrammaticInRangeHelper(low, high, s, i, 0, i-1);
        }
        
        return count;
    }
};

