/*

460. LFU Cache

Design and implement a data structure for Least Frequently Used (LFU) cache. 
It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. 

When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. 
For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), 
the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Hard

Google, Amazon, Linkedin, Uber, Bloomberg
*/

class LFUCache {
public:

    // 87%
    LFUCache(int capacity) {
        cap = capacity;
        minFreq = 0;
    }
    
    int get(int key) {
        if (keyIter.count(key) == 0) return -1;
        
        auto it = keyIter[key];
        Node tmp = *it;
        int freq = tmp.freq;
        tmp.freq++;
        freqNodes[freq+1].push_front(tmp);
        freqNodes[freq].erase(it);
        if (freqNodes[freq].size() == 0) {
            freqNodes.erase(freq);
            if (freq == minFreq) minFreq++;
        }
    
        keyIter[key] = freqNodes[freq+1].begin();
        return tmp.val;
    }
    
    void put(int key, int value) {
        if (!cap) return;
        
        if (keyIter.count(key)) {
            auto it = keyIter[key];
            Node tmp = *it;
            int freq = tmp.freq;
            tmp.freq++;
            tmp.val = value;
            freqNodes[freq+1].push_front(tmp);
            freqNodes[freq].erase(it);
            if (freqNodes[freq].size() == 0) {
                freqNodes.erase(freq);
                if (minFreq == freq) minFreq++;
            }
            keyIter[key] = freqNodes[freq+1].begin();
        } else {
            if (keyIter.size() == cap) {  // Full, need to remove one
                Node tmp = freqNodes[minFreq].back();
                freqNodes[minFreq].pop_back();
                if (freqNodes[minFreq].size() == 0) freqNodes.erase(minFreq);
                keyIter.erase(tmp.key);
            }
            
            freqNodes[1].push_front(Node(key, value, 1));
            keyIter[key] = freqNodes[1].begin();
            minFreq = 1;
        }
    }
    
private:
    struct Node {
        int key;
        int val;
        int freq;
        Node(int k, int v, int f) {key = k; val = v; freq = f;}
    };
    
    int cap;
    int minFreq;
    
    unordered_map<int, list<Node>> freqNodes;  // Freqency, <Node with same frequencies>
    unordered_map<int, list<Node>::iterator> keyIter;  // Key, iterator pointing to freqNodes's list
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache* obj = new LFUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
