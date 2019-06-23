/*

1095. Find in Mountain Array

(This problem is an interactive problem.)

You may recall that an array A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  
If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  
Also, any solutions that attempt to circumvent the judge will result in disqualification.

Example 1:
Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.

Example 2:
Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.
 
Constraints:
3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9

Hard
*/


/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * class MountainArray {
 *   public:
 *     int get(int index);
 *     int length();
 * };
 */
class Solution {
public:

    // 3 binary searches
    // First search to find the peak
    // Then search through the first slope, and then search through the 2nd slope
    int findInMountainArray(int target, MountainArray &mountainArr) {
        int n = mountainArr.length();
        int i = 0;
        int j = n-1;
        int mid;
        int peakIdx = -1;
        int curVal;
        int prevVal, nextVal;
                
        while (i <= j) {
            int mid = i + (j-i)/2;
            
            if (mid == 0) {
                mid = 1;
            }
            
            if (mid == n-1) {
                mid = n-2;
            }
            
            curVal = mountainArr.get(mid);
            prevVal = mountainArr.get(mid-1);
            nextVal = mountainArr.get(mid+1);
            if (curVal > nextVal && curVal > prevVal) {
                peakIdx = mid;
                break;
            } else if (curVal > nextVal) {
                peakIdx = mid;
                j = mid-1;
            } else if (curVal > prevVal) {
                peakIdx = mid;
                i = mid+1;
            }
        }
        
        if (peakIdx == -1) return -1;
        if (target == mountainArr.get(peakIdx)) return peakIdx;
        
        i = 0;
        j = peakIdx-1;

        while (i <= j) {
            mid = i + (j-i)/2;
            curVal = mountainArr.get(mid);
            if (curVal == target) return mid;
            if (curVal > target) {
                j = mid-1;
            } else {
                i = mid+1;
            }
        }
        
        i = peakIdx+1;
        j = n-1;
        while (i <= j) {
            mid = i + (j-i)/2;
            curVal = mountainArr.get(mid);
            if (curVal == target) return mid;
            if (curVal > target) {
                i = mid+1;
            } else {
                j = mid-1;
            }
        }
        
        return -1;
    }
};
