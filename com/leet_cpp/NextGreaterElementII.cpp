/*
Given a circular array (the next element of the last element is the first element of the array), 
print the Next Greater Number for every element. 
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
which means you could search circularly to find its next greater number. 
If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]

Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number; 
The second 1's next greater number needs to search circularly, which is also 2.

Note: The length of given array won't exceed 10000.
*/

//Medium
//Google

class Solution {
public:
    //Strategy: consider an array: 6,5,3,2,1,4
    //For any descending order, here 3,2,1, smaller one woulcd not be the next of previous one
    //So 4 will be the next for 3,2,1
    //Save nums in stack (here use index), when get a new num, compare the nums on top,
    //If the top numbers are smaller than the new num, 
    //the new number becomes the next element of the top (smaller) elements on the stack
    vector<int> nextGreaterElements(vector<int>& nums) {
        vector<int> ans(nums.size(), -1);
        int n = nums.size();
        stack<int> s;  //Record the index
        
        //Here use 2*n, based on %, could circular traversing
        for (int i=0; i<2*n; i++) {
            int num = nums[i % n];
            
            while (!s.empty() && nums[s.top()] < num) {
                ans[s.top()] = num;
                s.pop();
            }
            
            if (i < n) s.push(i);
        }
        
        return ans;
    }
};

