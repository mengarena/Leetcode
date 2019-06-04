/*

708. Insert into a Cyclic Sorted List

https://leetcode.com/problems/insert-into-a-cyclic-sorted-list/

Given a node from a cyclic linked list which is sorted in ascending order, 
write a function to insert a value into the list such that it remains a cyclic sorted list. 
The given node can be a reference to any single node in the list, 
and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. 
After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), 
you should create a new single cyclic list and return the reference to that single node. 
Otherwise, you should return the original given node.

The following example may help you understand the problem better:

 
In the figure above, there is a cyclic sorted list of three elements. 
You are given a reference to the node with value 3, and we need to insert 2 into the list.

 
The new node should insert between node 1 and node 3. 
After the insertion, the list should look like this, and we should still return node 3.

Medium
*/


/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;

    Node() {}

    Node(int _val, Node* _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
public:

    // 90%
    Node* insert(Node* head, int insertVal) {
        if (!head) {
            Node* myHead = new Node(insertVal, NULL);
            myHead->next = myHead;
            return myHead;
        }
        
        Node* current = head;
        Node* insertAfterNode;
        
        // Scenario to insert (existing node head->3->4->1 (1->3):
        //value  positions
        // 0:    <4, 1>:    condition:  < current, < next, next < current
        // 2:    <1, 3>:    condition:  > current, < next
        // 5:    <4, 1>:    condition:  > current, next < current
        while (current) {
            if (insertVal == current->val) {
                break;
            }
            
            if (insertVal < current->val && insertVal < current->next->val && current->next->val < current->val) {
                break;
            }
            
            if (insertVal > current->val && insertVal < current->next->val) {
                break;
            }
            
            if (insertVal > current->val && current->next->val < current->val) {
                break;
            }
            
            current = current->next;
            if (current == head) break;
        }
        
        Node* next = current->next;
        current->next = new Node(insertVal, next);
        
        return head;
    }
};
