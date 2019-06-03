/*

493. Reverse Pairs

Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:
Input: [1,3,2,3,1]
Output: 2

Example2:
Input: [2,4,3,5,1]
Output: 3

Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

Hard

Google, Microsoft, Apple
*/


class Solution {
public:
    
    // Stratgy: sort few numbers first (e.g. sort two consecutive numbers, and then 3..)
    // The sorted part will not affect the number of pairs when the first number is in front of this subarray
    // e.g.  5 3 7 *2 3 4* 9 8  (i.e. the sorted 2 3 4 does not affect the result 
    // when checking 5 or 3 or 7) 
    void mergeSort(vector<int>& nums, int s, int e, int& cnt) {
        if (s >= e) return;
        
        int mid = s + (e-s)/2;
        
        mergeSort(nums, s, mid, cnt);
        mergeSort(nums, mid+1, e, cnt);
        
        int i = s, j = mid+1;
        
        // Until now, the numbers between [s, mid] and [mid+1, e] are sorted
        // The order in those sorting are ascending
        while (i <= mid && j <= e) {
            if (nums[i] > 2L*nums[j]) {
                cnt += mid-i+1;  // Since [s, mid] are sorted ascendingly, 
                                 //so all numbers between [i, mid] satisified > 2*nums[i]
                j++;
            } else {
                i++;
            }
        }
        
        // Sort the elements between [s, e]
        sort(nums.begin() + s, nums.begin() + e + 1);
    }
    
    // 21%
    int reversePairs(vector<int>& nums) {
        if (nums.size() <= 1) return 0;
        int cnt = 0;
        mergeSort(nums, 0, nums.size()-1, cnt);
        return cnt;
    }
};
