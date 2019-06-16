/*

1089. Duplicate Zeros

Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array in place, do not return anything from your function.

 
Example 1:
Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]

Example 2:
Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]
 

Note:
1 <= arr.length <= 10000
0 <= arr[i] <= 9

Easy
*/



class Solution {
public:

    // Better
    void duplicateZeros(vector<int>& arr) {
        if (arr.size() == 0) return;
        int n = arr.size();
        int j = n + count(arr.begin(), arr.end(), 0);
        
        for (int i = n-1; i>=0; --i) {
            if (--j < n) {
                arr[j] = arr[i];
            }
            
            if (arr[i] == 0 && --j < n) arr[j] = 0;   // in a for loop, if arr[i] is not 0, both i and j move one step
                                                      // if arr[i] is 0, j moves 2 steps (both 2 steps should set value 0)
        }
    }
    

    void duplicateZeros(vector<int>& arr) {
        if (arr.size() == 0) return;
        int n = arr.size();
        int cnt = 0;
        for (auto num:arr) {
            if (num == 0) cnt++;
        }

        if (cnt == 0 || cnt == n || (cnt == 1 && arr[n-1] == 0)) return;
        
        for (int i=n-1; i>=0; --i) {
            if (cnt > 0) {
                if (i+cnt > n-1) {
                    if (arr[i] == 0) {
                        if (i+cnt-1 <= n-1) arr[i+cnt-1] = 0;
                        cnt--;
                    }
                    continue;
                }
                arr[i+cnt] = arr[i];
                if (arr[i] == 0) {
                    arr[i+cnt-1] = 0;
                    cnt--;
                }  
            } 
        }
    }
};
