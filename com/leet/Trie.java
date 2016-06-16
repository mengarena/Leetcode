package com.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Implement Trie (Prefix Tree)

//Implement a trie with insert, search, and startsWith methods.
//
//Note:
//You may assume that all inputs are consist of lowercase letters a-z.


//Google, Facebook, Uber, Microsoft, Bloomberg

public class Trie {
    class TrieNode {
        boolean bWord;
        TrieNode[] children = new TrieNode[26];
        
        public TrieNode() {
            bWord = false;
            for (int i=0; i<26; i++) {
                children[i] = null;
            }
        }
    }

    private TrieNode root = null;
    
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        TrieNode tmp = root;
        int idx = 0;
        for (int i=0; i<word.length(); i++) {
            idx = word.charAt(i)-'a';
            if (tmp.children[idx] == null) tmp.children[idx] = new TrieNode();
            tmp = tmp.children[idx];
        }
        
        tmp.bWord = true;
    }

    
    // Returns if the word is in the trie.
    public boolean search(String word) {
        if (word == null || word.length() == 0) return true;
        TrieNode tmp = root;
        int idx = 0;
        
        for (int i=0; i<word.length(); i++) {
            idx = word.charAt(i)-'a';
            if (tmp.children[idx] == null) return false;
            tmp = tmp.children[idx];
        }
        
        return tmp.bWord;
    }

    
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) return true;
        TrieNode tmp = root;
        int idx = 0;
        
        for (int i=0; i<prefix.length(); i++) {
            idx = prefix.charAt(i)-'a';
            if (tmp.children[idx] == null) return false;
            tmp = tmp.children[idx];
        }
        
        return true;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");












//ACC
class TrieNodeA {
    // Initialize your data structure here.
	char cNode;
	boolean bWord;
	TrieNodeA[] children = new TrieNodeA[26];
	
    public TrieNodeA() {
    	bWord = false;   //Default is false, this line is not needed
    	for (int i=0; i<26; i++) children[i] = null;   //Default is null, this line is not needed
    }
   
}

 class TrieA {
    private TrieNodeA root;

    public TrieA() {
        root = new TrieNodeA();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	if (word == null || word.length() == 0) return;
    	TrieNodeA rootTmp = root;
    	int i, n = word.length();
    	
    	for (i=0; i<n; i++) {
    		if (rootTmp.children[word.charAt(i)-'a'] == null) {
    			rootTmp.children[word.charAt(i)-'a'] = new TrieNodeA();
    			rootTmp.children[word.charAt(i)-'a'].cNode = word.charAt(i);    			
    		}
    		
    		rootTmp = rootTmp.children[word.charAt(i)-'a'];
    	}
    	
    	rootTmp.bWord = true;
    }

    
    // Returns if the word is in the trie.
    public boolean search(String word) {
    	if (root == null) return false;
    	
        if (word == null || word.length() == 0) return true;
        TrieNodeA rootTmp = root;
        int i, n = word.length();
        
        for (i=0; i<n; i++) {
        	if (rootTmp != null) {
        		rootTmp = rootTmp.children[word.charAt(i)-'a'];
        	} else {
        		break;
        	}
        }
        
        if (rootTmp == null) {
        	return false;
        } else {
        	return rootTmp.bWord;
        }
    }

    
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	if (root == null) return false;
    	
        if (prefix == null || prefix.length() == 0) return true;
        TrieNodeA rootTmp = root;
        int i, n = prefix.length();
        
        for (i=0; i<n; i++) {
        	if (rootTmp != null) {
        		rootTmp = rootTmp.children[prefix.charAt(i)-'a'];
        	} else {
        		break;
        	}
        }
        
        if (rootTmp == null) {
        	return false;
        } else {
        	return true;
        }
    }
}







//NOTE:  Actually, every node only needs to store one char




/* The following solution works.  But not a good implementation
 
class TrieNode {
    // Initialize your data structure here.
	String sNode;
	boolean bWord;
	List<TrieNode> children = null;
	
    public TrieNode() {
    	sNode = "";
    	bWord = false;
    }
   
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	if (word == null || word.length() == 0) return;
    	TrieNode rootTmp = root;
    	
    	while (rootTmp != null) {
    		List<TrieNode> children = rootTmp.children;
        	
        	if (children == null) break;

        	boolean bFound = false;

        	for (TrieNode child:children) {
        		String sChild = child.sNode;
        		        		
        		if (sChild.compareTo(word.substring(0, sChild.length())) == 0) {
        			
        			if (sChild.length() == word.length()) {
        				child.bWord = true;
        				return; 
        			}
        			
        			bFound = true;
        			rootTmp = child;
        			break;
        		}
        	}
        	
        	if (bFound == false) break;
    	}
    	
    	int nInitPos = rootTmp.sNode.length()+1;
    	for (int i = nInitPos; i <= word.length(); i++) {
    		if (rootTmp.children == null) rootTmp.children = new ArrayList<TrieNode>();
    		TrieNode tmpRoot = new TrieNode();
    		tmpRoot.sNode = word.substring(0, i);
    		rootTmp.children.add(tmpRoot);
    		rootTmp = tmpRoot;
    	}
    	
    	rootTmp.bWord = true;
    }

    
    // Returns if the word is in the trie.
    public boolean search(String word) {
    	if (root == null) return false;
    	
        if (word == null || word.length() == 0) return true;
        TrieNode rootTmp = root;
        
        while (rootTmp != null) {
        	List<TrieNode> children = rootTmp.children;
        	
        	if (children == null) return false;

        	boolean bFound = false;
        	
        	for (TrieNode child:children) {
        		String sChild = child.sNode;
        		        		
        		if (sChild.compareTo(word.substring(0, sChild.length())) == 0) {   
        			
        			if (sChild.length() == word.length()) {
        				return child.bWord;
        			}
        			
        			bFound = true;
        			rootTmp = child;
        			break;
        		}
        	}
        	
        	if (bFound == false) break;
        }
    	
    	return false;
    }

    
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	if (root == null) return false;
    	
        if (prefix == null || prefix.length() == 0) return true;
        TrieNode rootTmp = root;
        
        while (rootTmp != null) {
        	List<TrieNode> children = rootTmp.children;
        	
        	if (children == null) return false;

        	boolean bFound = false;
        	
        	for (TrieNode child:children) {
        		String sChild = child.sNode;
        		        		
        		if (sChild.compareTo(prefix.substring(0, sChild.length())) == 0) {
        			
        			if (sChild.length() == prefix.length()) return true;
        			
        			bFound = true;
        			rootTmp = child;
        			break;
        		}
        	}
        	
        	if (bFound == false) break;
        }
    	
    	return false;
    }
}

*/

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");