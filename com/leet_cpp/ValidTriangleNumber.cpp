/*
611. Valid Triangle Number

Given an array consists of non-negative integers, your task is to count the number of triplets chosen from the array 
that can make triangles if we take them as side lengths of a triangle.
Example 1:
Input: [2,2,3,4]
Output: 3
Explanation:
Valid combinations are: 
2,3,4 (using the first 2)
2,3,4 (using the second 2)
2,2,3
Note:
The length of the given array won't exceed 1000.
The integers in the given array are in the range of [0, 1000].

Medium:
Uber
*/

// To be a triangle, conditions:  a+b > c,  a+c>b, b+c>a

class Solution {
public:
    
    // 99%
    // Complixity: O(n^2)
    int triangleNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end(), less<int>());   // to make elements satisfy condition: a+c > b, b+c > a
        int n = nums.size();
        int res = 0;
        
        for (int i=n-1; i>=2; i--) {
            int left = 0, right = i-1;
            
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {  // This means: when we fix i and right,
                                                           // all elements from "left" to the elment right before "right"
                                                           // satisify the condition a + b > c
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        
        return res;
    }


    // 36%
    // Complixity: O(n^2logn)
    // after sorting, a+c>b and b+c>a are already satisified, so only need to check a+b>c
    int triangleNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end(), less<int>());
        int n = nums.size();
        int res = 0;
        
        for (int i=0; i<n-2; ++i) {
            for (int j=i+1; j < n-1; ++j) {
                int sum = nums[i] + nums[j];
                int left = j+1, right = n;   // Here right should be n, not n-1,  to allow check last number in nums
                
                while (left < right) {
                    int mid = left + (right-left)/2;
                    if (nums[mid] < sum) left = mid + 1;
                    else right = mid;
                }
                
                res += left - 1 - j;
            }
        }
        
        return res;
    }
    
    
    
    // 23%
    int triangleNumber(vector<int>& nums) {
        sort(nums.begin(), nums.end(), less<int>());
        int n = nums.size();
        int res = 0;
        
        for (int i=0; i<n-2; ++i) {
            for (int j=i+1; j < n-1; ++j) {
                int sum = nums[i] + nums[j];
                int left = j+1;
                
                while (left < n && nums[left] < sum) left++;
                res += left-1-j;
            }
        }
        
        return res;
    }
};
