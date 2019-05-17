/*

658. Find K Closest Elements

Given a sorted array, two integers k and x, find the k closest elements to x in the array. 
The result should also be sorted in ascending order. 
If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104

UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). 
Please reload the code definition to get the latest changes.


Medium:
Uber
*/


class Solution {
public:
    
    // 70%
    // Time complixity: O(logn) --- n is the number of elements in arr (actually should be log(n-k))
    // This method direct searchs for the left and right boundary of the k-element section within arr,
    // which contains the K closest elements
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        int left = 0, right = arr.size()-k;
        
        // The 'mid' is the beginning of the candidate section (i.e. the section is mid ~ mid+k
        // i.e. arr[mid] and arr[mid+k] and two boundary elements
        // push the K-element section window until arr[mid] is closer to x (at that time, left = right)
        while (left < right) {
            int mid = left + (right-left)/2;
            if (x-arr[mid] > arr[mid+k] - x) left = mid+1;
            else right = mid;
        }

        return vector<int>(arr.begin()+left, arr.begin()+left+k);
    }

    // 5%
    // Prune from begin and end
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        vector<int> ans = arr;
 
        while (ans.size() > k) {
            if (abs(ans.front() - x) > abs(ans.back() -x)) {
                ans.erase(ans.begin());
            } else {
                ans.pop_back();
            }
        }
        
        return ans;
    }
    
    
    // 10%
    // Strategy:  Find the insert position in arr for x
    // From the insert position, check both directions
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        vector<int> ans;
        int i = 0;
        int j = arr.size()-1;
        int insertPos = j;
        
        // Search for the insert position for x in arr
        while (i < j) {
            int mid = i + (j-i)/2;
            if (arr[mid] > x) {
                insertPos = mid;
                if (arr[mid] == x) break;
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        
        j = insertPos;
        i = j-1;
        
        while (ans.size() < k && i >= 0 && j < arr.size()) {            
            if (abs(arr[i] - x) <= abs(arr[j] - x)) {
                ans.insert(ans.begin(), arr[i--]);
            } else {
                ans.push_back(arr[j++]);
            }
        }
        
        while (ans.size() < k) {
            if (i >= 0) {
                ans.insert(ans.begin(), arr[i--]);
            }
            
            if (j < arr.size()) {
                ans.push_back(arr[j++]);
            }
        }
        
        return ans;
    }    
    
    
    
    
    // 12%
    // Strategy: First check whether x is beyond the range of arr, if that's the case, it becomes easy,
    // just get the elements continusously from the begin or end
    // Otherwise,
    // Use binary search to find the position where x should be insert in order in arr,
    // from that position, check both directions to find the clostest elements one by one
    vector<int> findClosestElements(vector<int>& arr, int k, int x) {
        vector<int> ans;
        int i = 0;
        int j = arr.size()-1;
        bool found = false;
        int max = *max_element(arr.begin(), arr.end());
        
        if (x >= max) {
            for (auto it=arr.rbegin(); it != arr.rend(); ++it) {
                ans.insert(ans.begin(), *it);
                if (ans.size() == k) return ans;
            }
        }
        
        int min = *min_element(arr.begin(), arr.end());
        if (x <= min) {
            for (auto it=arr.begin(); it != arr.end(); ++it) {
                ans.push_back(*it);
                if (ans.size() == k) return ans;
            }            
        }
        
        while (i < j) {
            int mid = i + (j-i)/2;
            if (arr[mid] == x) {
                ans.push_back(arr[mid]);
                found = true;
                j = mid + 1;
                i = mid - 1;
                break;
            } else if (arr[mid] > x){
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        
        if (!found) i = j-1;
        
        while (ans.size() < k && i >= 0 && j < arr.size()) {            
            if (abs(arr[i] - x) <= abs(arr[j] - x)) {
                ans.insert(ans.begin(), arr[i--]);
            } else {
                ans.push_back(arr[j++]);
            }
        }
        
        while (ans.size() < k) {
            if (i >= 0) {
                ans.insert(ans.begin(), arr[i--]);
            }
            
            if (j < arr.size()) {
                ans.push_back(arr[j++]);
            }
        }
        
        return ans;
    }
};
