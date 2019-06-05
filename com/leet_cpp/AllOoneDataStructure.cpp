/*

432. All O`one Data Structure

Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. 
Key is guaranteed to be a non-empty string.

Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. 
If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.

GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".

Challenge: Perform all these in O(1) time complexity.

Hard

Linkedin, Facebook, Amazon
*/

class AllOne {
public:

    /* Describes the main ideas, implementation is not complete  */
    
    // Maintain three hashmap
    //   1) <keystring, node>
    //   2) <val, head node>
    //   3) <val, tail node>
    // 
    // Create a node structure, which is to be used to form a double link list
    // The double link list is sorted in ascending order by val
    
    /** Initialize your data structure here. */
    AllOne() {
        head = NULL:
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    void inc(string key) {
        Node* newNode;
        
        // Following situaton:
        // 1) No node exist, this is the first node, need to update head point
        // 2) The key is a header node of a same value segment, need to update header node hashmap of that segment
        // 3) The key is a tail node of a same value segment, need to update tail node hashmap of that segment
        // 4) To insert the increased node:
        //    a) If segment with same increated value segment exist, insert as header
        //    b) If same value segment does not exist, insert right behind the tail
        //       of the segment of its previous value
        if (nodePos.count(key) == 0) {
            newNode = new Node(key);
            nodePos[key] = newNode;
        
            if (head == NULL) {
                head = newNode;
                newNode->prev = newNode;
                newNode->next = newNode;
                valHeader[1] = newNode;
                valTail[1] = newNode;
                return;
            }
            
            head->prev->next = newNode;
            newNode->prev = head->prev;
            newNode->next = head;
            head->prev = newNode;
            head = newNode;
            valHeader[1] = head;
            valTail[1] = head;
            return;
        } 
        
        newNode = nodePos[key];
        int val = newNode->val;
        Node* curValHeader = valHeader[val];
        Node* curValTail = valTail[val];
        Node* lastNode;
        
        newNode->val++;
        if (curValHeader == newNode) {
            if (curValHeader->next->val != val) {
                valHeader.erase(val);
                valTail.erase(val);
                lastNode = curValHeader->prev;
            } else {
                valHeader[val] = curValHeader->next;
                lastNode = valTail[val];
            }
        } else if (curValTail == newNode) {
            valTail[val] = curValTail->prev;
            lastNode = valTail[val];
        }
        
        if (newNode == head) {
            head = head->next;
        }
        
        newNode->prev->next = newNode->next;
        newNode->next->prev = newNode->prev;
        
        if (valHeader.count(newNode->val)) {
            Node *nextValHead = valHead[newNode->val];
            newNode->prev = nextValHead->prev;
            newNode->next = nextValHead;
            nextValHead->prev = newNode;
            valHeader[newnode->val] = newNode;
        } else {
            newNode->next = lastNode->next;
            lastNode->next->prev=newNode;
            newNode->prev = lastNode;
            lastNode->next = newNode;
            valHeader[newNode->val] = newNode;
            if (newNode->next->val != newNode->val) {
                valTail[newNode->val] = newNode;
            }
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    void dec(string key) {
        if (nodePos.count(key) == 0) return;
        Node* curNode = nodePos[key];
        
        int curVal = curNode->val;
        
        //Similary, based on different situations to update the hashmaps and linked list
        // For example, following situations:
        // 1) key is the head node of the linked list and current value is 1 (to delete)
        // 2) Key is a header node a same value segment (need to update header node hashmap of that segment)
        // 3) Key is the tail node of a same value segment (need to update tail node hashmap of that segment)
        // 4) Insert decreased node as the header node of the new same value segment
        //    (possibly also update the tail node)
        // 5) might even update header node if decreased to 1
    }
    
    /** Returns one of the keys with maximal value. */
    string getMaxKey() {
        if (!head) return "";
        return head->prev->key;
    }
    
    /** Returns one of the keys with Minimal value. */
    string getMinKey() {
        if (!head) return "";
        return head->key;
    }
    
private:
    struct Node {
        Node* prev;
        Node* next;
        int val;
        string key;
        Node(string k) { key = k; val = 1; prev = NULL; next = NULL; }
    };
    
    Node* head;
    unordered_map<string, Node*> nodePos;  // Key and its node
    unordered_map<int, Node*> valHeader; // for a same value segment, the header node
    unordered_map<int, Node*> valTail;  // for a same value segment, the tail node
};

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne* obj = new AllOne();
 * obj->inc(key);
 * obj->dec(key);
 * string param_3 = obj->getMaxKey();
 * string param_4 = obj->getMinKey();
 */

