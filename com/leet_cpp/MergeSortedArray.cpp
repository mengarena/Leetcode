/*
88. Merge Sorted Array

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2. 
The number of elements initialized in nums1 and nums2 are m and n respectively.
*/

//Microsoft, Bloomberg, Facebook
//Easy

class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        if (nums2.size() == 0) return;
        if (m == 0) {
            nums1 = nums2;
            return;
        }
        
        int i=m-1;
        int j=n-1;
        int totalIdx = m+n-1;
        
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[totalIdx] = nums1[i];
                i--;
            } else {
                nums1[totalIdx] = nums2[j];
                j--;
            }
            
            totalIdx--;
        }
        
        if (j < 0) return;
        
        while (j >= 0) {
            nums1[totalIdx--] = nums2[j--];
        }
    }
};
