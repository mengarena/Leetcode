package com.leet;


//Add and Search Word - Data structure design

//
//Design a data structure that supports the following two operations:
//
//void addWord(word)
//bool search(word)
//
//search(word) can search a literal word or a regular expression string containing only letters a-z or .. 
//A . means it can represent any one letter.
//
//For example:
//
//addWord("bad")
//addWord("dad")
//addWord("mad")
//search("pad") -> false
//search("bad") -> true
//search(".ad") -> true
//search("b..") -> true
//Note:
//You may assume that all words are consist of lowercase letters a-z.
//
//click to show hint.
//
//You should be familiar with how a Trie works. If not, please work on this problem: Implement Trie (Prefix Tree) first.


//Facebook
public class WordDictionary {
	
	
	//ACC: 65%
	class TrieNode {
		char cNode;
		boolean bWord;
		TrieNode[] children = new TrieNode[26];
		
		public TrieNode() {
			bWord = false;
			for (int i=0; i<26; i++) children[i] = null;
		}
	}

	private TrieNode root = new TrieNode();
	
    // Adds a word into the data structure.
    public void addWord(String word) {
    	if (word == null || word.length() == 0) return;
    	TrieNode rootTmp = root;
    	int i, n = word.length();
    	
    	for (i=0; i<n; i++) {
    		if (rootTmp.children[word.charAt(i) - 'a'] == null) {
    			rootTmp.children[word.charAt(i) - 'a'] = new TrieNode();
    			rootTmp.children[word.charAt(i) - 'a'].cNode = word.charAt(i);
    		}
    		
    		rootTmp = rootTmp.children[word.charAt(i) - 'a'];
    	}
    	
    	rootTmp.bWord = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if (word == null || word.length() == 0) return true;
            	
    	return searchHelper(word, 0, root);
    }
    
    private boolean searchHelper(String word, int nStartPos, TrieNode root) {
    	int n = word.length();
    	if (nStartPos >= n) return true;
    	int i;
    	char cNodeTmp;
    	boolean bRet = false;
    	
		cNodeTmp = word.charAt(nStartPos);
		
		if (cNodeTmp != '.') {
			
			if (root.children[cNodeTmp - 'a'] == null) {
				return false;
			} else {
				root = root.children[cNodeTmp - 'a'];
				if (nStartPos == n-1) {
					return root.bWord;
				} else {
				    return searchHelper(word, nStartPos+1, root);
				}
			}
			
		} else {
			
			for (i=0; i<26; i++) {
				if (root.children[i] != null) {
					if (nStartPos == n-1) {
					    if (root.children[i].bWord == true) {
					    	bRet = true;
					    	break;
					    }
					} else {					
						if (searchHelper(word, nStartPos+1, root.children[i])) {
							bRet = true;
							break;
						}
					}
				}
			}
			
			return bRet;
		}

    	    	
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");

