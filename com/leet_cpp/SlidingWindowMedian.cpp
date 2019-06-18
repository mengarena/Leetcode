/*

480. Sliding Window Median

Median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. 
Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
Therefore, return the median sliding window as [1,-1,-1,3,5,6].

Note: 
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.

Hard

Google,Amazon,Facebook,Snapchat
*/


class Solution {
public:

    //98%
    vector<double> medianSlidingWindow(vector<int>& nums, int k) {
        vector<double> ans;
        multiset<double> m(nums.begin(), nums.begin()+k);  // Sorted ascending
        
        auto mid = next(m.begin(), k/2);
        
        for (int i=k; ;++i) {
            if (k % 2 == 0) {
                ans.push_back((*mid + *prev(mid,1))/2);
            } else {
                ans.push_back(*mid);
            }
            
            if (i == nums.size()) break;
            
            m.insert(nums[i]);
            if (nums[i] < *mid) mid--;  // The new inserted value is smaller than mid, so it will lower the median, 
                                        // so move back 1 step
            if (nums[i-k] <= *mid) mid++;  // nums[i-k] is the one to be removed
            auto it = m.lower_bound(nums[i-k]);
            m.erase(it);   // Cannot directly erase value, because multiple could have same value
        }
        
        return ans;
    }
};
