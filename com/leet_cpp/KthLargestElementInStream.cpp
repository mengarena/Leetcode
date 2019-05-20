/*

703. Kth Largest Element in a Stream

Design a class to find the kth largest element in a stream. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, 
which contains initial elements from the stream. 
For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8

Note: 
You may assume that nums' length ≥ k-1 and k ≥ 1.

Easy
*/

// 99%
// only keep the K largest numbers

class KthLargest {
public:
    KthLargest(int k, vector<int>& nums) {
        K = k;        
        int i = 0;
        while (i < k && i < nums.size()) {
            pqLarge.push(nums[i]);
            i++;
        }
        
        while (i < nums.size()) {
            add(nums[i++]);
        }
    }
    
    int add(int val) {
        if (pqLarge.size() < K || pqLarge.top() < val) {
            pqLarge.push(val);
        }
        
        if (pqLarge.size() > K) pqLarge.pop();
        return pqLarge.top();
    }
    
private:
    int K;
    priority_queue<int, vector<int>, greater<int>> pqLarge; // Smallest on top
};

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest* obj = new KthLargest(k, nums);
 * int param_1 = obj->add(val);
 */
