/*

895. Maximum Frequency Stack

Implement FreqStack, a class which simulates the operation of a stack-like data structure.

FreqStack has two functions:

push(int x), which pushes an integer x onto the stack.
pop(), which removes and returns the most frequent element in the stack.
If there is a tie for most frequent element, the element closest to the top of the stack is removed and returned.
 

Example 1:
Input: 
["FreqStack","push","push","push","push","push","push","pop","pop","pop","pop"],
[[],[5],[7],[5],[7],[4],[5],[],[],[],[]]
Output: [null,null,null,null,null,null,null,5,7,5,4]
Explanation:
After making six .push operations, the stack is [5,7,5,7,4,5] from bottom to top.  Then:

pop() -> returns 5, as 5 is the most frequent.
The stack becomes [5,7,5,7,4].

pop() -> returns 7, as 5 and 7 is the most frequent, but 7 is closest to the top.
The stack becomes [5,7,5,4].

pop() -> returns 5.
The stack becomes [5,7,4].

pop() -> returns 4.
The stack becomes [5,7].
 

Note:
Calls to FreqStack.push(int x) will be such that 0 <= x <= 10^9.
It is guaranteed that FreqStack.pop() won't be called if the stack has zero elements.
The total number of FreqStack.push calls will not exceed 10000 in a single test case.
The total number of FreqStack.pop calls will not exceed 10000 in a single test case.
The total number of FreqStack.push and FreqStack.pop calls will not exceed 150000 across all test cases.

Hard

Amazon, Uber
*/


class FreqStack {

public:

    // 30%
    FreqStack() {
        maxFreq = 0;
    }
    
    void push(int x) {
        valFreq[x]++;
        int freq = valFreq[x];
        freqValues[freq].push(x);
        if (freq > maxFreq) maxFreq = freq;
    }
    
    int pop() {
        int val = freqValues[maxFreq].top();
        freqValues[maxFreq].pop();
        valFreq[val]--;
        if (freqValues[maxFreq].empty()) {
            maxFreq--;
        }
        return val;
    }
    
private:    
    unordered_map<int, int> valFreq;
    map<int, stack<int>> freqValues;   // It guarantees the order of the values to reach the each frequency
                                       // e.g. for all the numbers occur 5 times, the latest one will be on top
    int maxFreq;
};




class FreqStack {
public:

    // 5%
    FreqStack() {
        globalIdx = 0;
    }
    
    void push(int x) {
        globalIdx++;
        if (normal.count(x) == 0) {
            normal[x].push_back(globalIdx);
            deque<int> indexes;
            indexes.push_back(globalIdx);
            ordered[indexes] = x;
        } else {
            deque<int> indexes = normal[x];
            normal[x].push_back(globalIdx);
            ordered.erase(indexes);
            indexes.push_back(globalIdx);
            ordered[indexes] = x;
        }
    }
    
    int pop() {
        auto it = ordered.begin();
        int ret = it->second;
        deque<int> indexes = it->first;
        if (indexes.size() == 1) {
            ordered.erase(indexes);
            normal.erase(ret);
        } else {
            ordered.erase(indexes);
            indexes.pop_back();
            ordered[indexes] = ret;
            normal[ret].pop_back();
        }
        
        return ret;
    }
    
private:    
    // Through this sorting, most freqent will be the first in ordered;
    // If tie, the one with larger last index will be the first
    struct NodeCmp {
        bool operator()(const deque<int>& A, const deque<int>& B) const {
            if (A.size() != B.size()) {
                return A.size() > B.size();   // Compare frequency
            } else {
                return A.back() > B.back();   // Compare last index
            }
        }
    };
    
    map<int, deque<int>> normal;     // Val, index list
    map<deque<int>, int, NodeCmp> ordered;    // index list, val
    int globalIdx;
};




/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack* obj = new FreqStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 */
