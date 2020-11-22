/*

528. Random Pick with Weight

Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:
1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.

Example 1:
Input: 
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]

Example 2:
Input: 
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. 
Solution's constructor has one argument, the array w. pickIndex has no arguments. 
Arguments are always wrapped with a list, even if there aren't any.

Medium:
Uber
*/

class Solution {
public:

    // 97%
    // Explaination:
    // Say a list:  1,3,2
    // The probability of each value get picked is 1/6, 3/6, 2/6
    // So use 'sum' to accumulate sums (i.e. 1, 4, 6)
    // The gap between sum[i] and sum[i-1] corresponds to the weight of w[i]
    // Then in pickIndex(),  rannum could be one of these values: 0, 1,2,3,  4,5
    // If rannum = 0, should pick index 0
    // If rannum = 1,2,3, should pick index 1
    // if rannum = 4,5,  should pick index 2
    // So for each randum, the while loop tries to figure out which section it falls in (sum[])
    // In the while loop, the right tells the upbound of the rannum within sum[]
    // e.g if upbound is 4, then the rannum should be 1 or 2 or 3,
    // then the picked index should be index 1
    // i.e. if upbound for the rannum is ith value in sum[],  the number to pick is ith number in w
    
    vector<int> sum;
    
    Solution(vector<int>& w) {
        sum = w;
        for (int i = 1; i<w.size(); i++) {
            sum[i] = sum[i-1] + w[i]; 
        }
    }
    
    int pickIndex() {
        int rannum = rand() % sum.back();
        int left = 0, right = sum.size()-1;
        while (left < right) {
            int mid = left + (right-left)/2;
            if (rannum >= sum[mid]) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return right;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(w);
 * int param_1 = obj->pickIndex();
 */
