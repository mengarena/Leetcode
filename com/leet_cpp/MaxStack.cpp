/*

716. Max Stack

Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. 
If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.

Easy-Medium
*/

// 99 %
class MaxStack {
public:
    /** initialize your data structure here. */
    MaxStack() {
        
    }
    
    void push(int x) {
        normals.push(x);
        if (maxs.empty() || x >= maxs.top()) maxs.push(x);
    }
    
    int pop() {
        int val = normals.top();
        normals.pop();
        if (val == maxs.top()) maxs.pop();
        return val;
    }
    
    int top() {
        return normals.top();
    }
    
    int peekMax() {
        return maxs.top();
    }
    
    int popMax() {
        int val = maxs.top();
        maxs.pop();
        
        if (normals.top() == val) {
            normals.pop();
            return val;
        }
        
        stack<int> tmp;
        while (normals.top() != val) {
            int tmpval = normals.top();
            normals.pop();
            tmp.push(tmpval);
        }
        
        normals.pop();
        while (!tmp.empty()) {
            push(tmp.top());
            tmp.pop();
        }
        return val;
    }

private:
    stack<int> normals;
    stack<int> maxs;
};




// 86%
// This method use map to record the positions of each value
class MaxStack {
public:
    /** initialize your data structure here. */
    MaxStack() {
        
    }
    
    void push(int x) {
        normals.insert(normals.begin(), x);
        maxs[x].push_back(normals.begin());
    }
    
    int pop() {
        int val = normals.front();
        normals.erase(normals.begin());
        maxs[val].pop_back();
        if (maxs[val].empty()) maxs.erase(val);
        return val;
    }
    
    int top() {
        return normals.front();
    }
    
    int peekMax() {
        return maxs.rbegin()->first;
    }
    
    int popMax() {
        int val = maxs.rbegin()->first;
        auto it = maxs[val].back();
        maxs[val].pop_back();
        if (maxs[val].empty()) maxs.erase(val);
        normals.erase(it);
        return val;
    }

private:
    list<int> normals;
    map<int, vector<list<int>::iterator>> maxs;  // Ascending order by key
};

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack* obj = new MaxStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->top();
 * int param_4 = obj->peekMax();
 * int param_5 = obj->popMax();
 */
