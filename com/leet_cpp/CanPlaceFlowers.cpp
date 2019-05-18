/*

605. Can Place Flowers

Suppose you have a long flowerbed in which some of the plots are planted and some are not. 
However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), 
and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.

Easy
*/

class Solution {
public:

    // 86%
    // The rule for the #plantable flowers are different for heading/trailing empty entries and middle empty entries
    bool canPlaceFlowers(vector<int>& flowerbed, int n) {
        int len = flowerbed.size();
        if (2*len < n) return false;
        int count = 0;
        int tmp = 0;
        int i = 0;
        
        while (i < len && flowerbed[i] == 0) {
            tmp++;
            i++;
        }
        
        if (i == len) {
            count += (tmp+1)/2;
            return count >= n;
        } else {
            count += tmp/2;
        }
        
        int j = len-1;
        tmp = 0;
        while (j >= 0 && flowerbed[j] == 0) {
            tmp++;
            j--;
        }
        
        count += tmp/2;
        
        tmp = 0;
        for (; i<=j; ++i) {
            if (flowerbed[i] == 0) {
                tmp++;
            } else {
                count += (tmp-1)/2;
                tmp = 0;
            }
        }
        
        count += (tmp-1)/2;
        
        return count >= n;
    }
};
