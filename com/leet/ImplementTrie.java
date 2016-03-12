package com.leet;

public class ImplementTrie {

	public ImplementTrie() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		Trie trie = new Trie();
		
		trie.insert("app");
		trie.insert("apple");
		trie.insert("beer");
		trie.insert("add");
		trie.insert("jam");
		trie.insert("rental");		
		
		
		boolean bRet = trie.search("apps");
		
		System.out.println("Search = " + bRet);
		
		bRet = trie.search("app");
		
				
		

		
		//[true,false,false,false]
				
		//GT: [true,false,true,true]
		
		//insert("abc"),search("abc"),search("ab"),    insert("ab"), search("ab"),     insert("ab"), search("ab")
	}
}
