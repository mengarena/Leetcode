/*

969. Pancake Sorting

Given an array A, we can perform a pancake flip: 
We choose some positive integer k <= A.length, then reverse the order of the first k elements of A.  
We want to perform zero or more pancake flips (doing them one after another in succession) to sort the array A.

Return the k-values corresponding to a sequence of pancake flips that sort A.
Any valid answer that sorts the array within 10 * A.length flips will be judged as correct.

Example 1:
Input: [3,2,4,1]
Output: [4,2,4,3]
Explanation: 
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k=4): A = [1, 4, 2, 3]
After 2nd flip (k=2): A = [4, 1, 2, 3]
After 3rd flip (k=4): A = [3, 2, 1, 4]
After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted. 

Example 2:
Input: [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.
 
Note:

1 <= A.length <= 100
A[i] is a permutation of [1, 2, ..., A.length]

Medium

Uber, Square
*/


class Solution {
public:
    
    // 98%
    // Strategy:  Find the max value, at index i, then move this max value to index 0 by using reverse,
    // then reverse the whole thing (*) to put the max value to last position
    // Here "whole thing(*)" means currently unprocessed subarray
    vector<int> pancakeSort(vector<int>& A) {
        vector<int> ans;
        if (A.size() <= 1) return ans;
        int n = A.size();

        while (n > 1) {
            int maxVal = INT_MIN;
            int maxIdx = -1;
            
            for (int i=0; i<n; ++i) {
                if (maxVal < A[i]) {
                    maxVal = A[i];
                    maxIdx = i;
                }
            }
            
            ans.push_back(maxIdx+1);   // For Flipping the max to the head
            ans.push_back(n);          // For Flipping the whole currently unprocessed subarray
            
            // pay attention to the reverse() API, the range of the two parameters is [ )
            reverse(A.begin(), A.begin()+maxIdx+1); 
            reverse(A.begin(), A.begin()+n);
            
            n--;
        }
        
        return ans;
    }

    
    
    // Strategy:  Find the max value, say at index i,
    // flip from 0 ~ index i to move the max value to the beginning,
    // then flip the whole thing to move the max to the end (from this, the largest one has in correct position)
    // next, find the max value betwen 0~n-1 
    vector<int> pancakeSort(vector<int>& A) {
        vector<int> ans;
        if (A.size() <= 1) return ans;
        int n = A.size();

        vector<int> indexArray(n, 0);  // Index+1 for the elements in A
        for (int i=0; i<n; ++i) indexArray[i] = i+1;
        
        // Sort the indexes based on the values in A
        // i.e. we need to flip base on largest element, then 2nd largest element...
        sort(indexArray.begin(), indexArray.end(), [=](const int i, const int j){ return A[i-1] > A[j-1]; } );        
        
        for (int curMaxvalIdx:indexArray) {
            
            // After previous flipping, the original curMaxvalIdx has been moved
            // Here to get its current value derived all previous flipping
            for (int f:ans) {
                if (curMaxvalIdx <= f) {
                    curMaxvalIdx = f+1-curMaxvalIdx;
                }
            }
            
            ans.push_back(curMaxvalIdx);
            ans.push_back(n--);
        }
        
        return ans;
    }
};
