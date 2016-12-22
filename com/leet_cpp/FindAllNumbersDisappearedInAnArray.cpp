/*
448. Find All Numbers Disappeared in an Array 

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]
*/

//Google
//Easy

class Solution {
public:
    vector<int> findDisappearedNumbers(vector<int>& nums) {
        vector<int> ret;
        
        if (nums.size() == 0) return ret;

        for (auto &i : nums) {
            int tmpPos = abs(i) - 1;
            if (nums[tmpPos] > 0) nums[tmpPos] = -nums[tmpPos]; 
        }
        
        for (int i=0; i<nums.size(); i++) {
            if (nums[i] > 0) ret.push_back(i+1);
        }
        
        return ret;
    }
};

