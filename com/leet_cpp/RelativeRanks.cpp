/*
Given scores of N athletes, find their relative ranks and the people with the top three highest scores, 
who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:

Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
For the left two athletes, you just need to output their relative ranks according to their scores.

Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
*/

//Easy
//Google

class Solution {
public:
    vector<string> findRelativeRanks(vector<int>& nums) {
        vector<string> ans(nums.size(),"");
        
        priority_queue<pair<int, int>> pq;  //Element Value, Original index
                                            //The priority_queue will be maxHeap based on the firse element (i.e. values of elements)
        int i;
        
        for (i=0; i<nums.size(); i++) {
            pq.push(make_pair(nums[i], i));
        }        

        int order = 0;
        
        while (!pq.empty()) {
            order++;
            if (order == 1) {
                ans[pq.top().second] = "Gold Medal";
            } else if (order == 2) {
                ans[pq.top().second] = "Silver Medal";
            } else if (order == 3) {
                ans[pq.top().second] = "Bronze Medal";
            } else {
                ans[pq.top().second] = to_string(order);
            }

            pq.pop();
        }
        
        return ans;
    }
};

