/*
169. Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ? n/2 ? times.

You may assume that the array is non-empty and the majority element always exist in the array.


Adobe, Zenefits
*/

class Solution {
public:
    int majorityElement(vector<int>& nums) {
        int ret = 0;
        
        for (int i=0; i<32; i++) {
            int ones = 0;
            int zeros = 0;
            
            for (int j = 0; j<nums.size(); j++) {
                if ((nums[j] & (1 << i)) != 0) {
                    ones++;
                } else {
                    zeros++;
                }
            }
            
            if (ones > zeros) ret |= (1 << i);
        }
        
        return ret;
    }
};
