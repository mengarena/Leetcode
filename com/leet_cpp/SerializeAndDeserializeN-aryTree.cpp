/*

428. Serialize and Deserialize N-ary Tree

https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/

Serialization is the process of converting a data structure or object into a sequence of bits so that 
it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. 
An N-ary tree is a rooted tree in which each node has no more than N children. 
There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that an N-ary tree can be serialized to a string and 
this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree

 as [1 [3[5 6] 2 4]]. You do not necessarily need to follow this format, 
 so please be creative and come up with different approaches yourself.


Note:
N is in the range of [1, 1000]
Do not use class member/global/static variables to store states. 
Your serialize and deserialize algorithms should be stateless.

Uber, Amazon, Google, Ebay, Microsoft
*/


/*
// Definition for a Node.
class Node {
public:
    int val = NULL;
    vector<Node*> children;

    Node() {}

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Codec {
public:

    // 96%
    // Encodes a tree to a single string.
    string serialize(Node* root) {
        if (!root) return "";
        if (root->children.size() == 0) {
            return to_string(root->val);
        }
        
        string ret = to_string(root->val);
        
        string stringChild = "";
        for (int i = 0; i<root->children.size(); ++i) {
            stringChild += serialize(root->children[i]);
            if (i != root->children.size()-1) stringChild += " ";
        }
        
        ret += "[" + stringChild + "]";
        return ret;
    }
    
    
    // Decodes your encoded data to tree.
    Node* deserialize(string data) {
        stack<Node*> stk;
        Node* root = NULL;
        int i = 0, n = data.length();
        
        while (i < n) {
            if (data[i] == '[') {
                i++;
                continue;
            }
            
            if (data[i] == ']' || data[i] == ' ') {
                if (!stk.empty()) stk.pop();
                i++;
                continue;
            }
            
            int num = 0;
            while (i < n && isdigit(data[i])) {
                num = num*10 + data[i] - '0';
                i++;
            }
            
            Node* curNode = new Node(num);
            
            if (stk.empty()) {
                root = curNode;
            } else {
                stk.top()->children.push_back(curNode);
            }
            
            stk.push(curNode);
        }
        
        return root;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.deserialize(codec.serialize(root));
