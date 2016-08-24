package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//There are following like contact information;
//One person could have more than one email address;
//If two emails are the same, then the corresponding person should be the same person 
//(although the person might have different name)
//
//{
//    { "John", {"john@gmail.com"} },
//    { "Mary", {"mary@gmail.com"} },
//    { "John", {"john@yahoo.com"} },
//    { "John", {"john@gmail.com", "john@yahoo.com", "john@hotmail.com"} },
//    { "Bob",  {"bob@gmail.com"} }
//}
//
//Group the contacts for each person


public class GroupContacts {

	public class Contact {
		public String name;
		List<String> emails;
	}
	
	public class UnionFind {
		int[] roots = null;
		int[] ranks = null;
		
		public UnionFind(int nodeCnt) {
			roots = new int[nodeCnt];
			ranks = new int[nodeCnt];
			
			for (int i=0; i<nodeCnt; i++) {
				roots[i] = i;
			}
		}
		
		public int findRoot(int x) {
			while (x != roots[x]) {
				roots[x] = roots[roots[x]];
				x = roots[x];
			}
			
			return x;
		}
		
		public void do_union(int x, int y) {
		    int rootx = findRoot(x);
			int rooty = findRoot(y);
			
			if (rootx == rooty) return;
			
			if (ranks[rootx] < ranks[rooty]) {  //Higher rank becomes the final root
				roots[rootx] = rooty;
			} else {
				roots[rooty] = rootx;
				if (ranks[rootx] == ranks[rooty]) ranks[rootx]++;
			}
		}
	}
	
	
	public List<List<Contact>> group_contacts(List<Contact> input) {
		List<List<Contact>> lstlstGroup = new ArrayList<>();
		
		int n = input.size();
		int i;
		Map<String, List<Integer>> hm = new HashMap<String, List<Integer>>();   //Email,  Contact index
		
		//Based on Email, get the Contact index for each unique email
		for (i=0; i<n; i++) {
			List<String> emails = input.get(i).emails;
			
			for (String em:emails) {
				hm.get(em).add(i);
			}
		}
		
		
		UnionFind uf = new UnionFind(n);
		
		Set<String> keySets = hm.keySet();
		
		//Set the roots for the Contact indexes of each Email
		for (String em:keySets) {
			List<Integer> lstIds = hm.get(em);
			
			for (i=0; i<lstIds.size()-1; i++) {
				uf.do_union(lstIds.get(i), lstIds.get(i+1));
			}
		}
		
		//Group the Contact index based on Roots (By this step, the groups are decided)
		Map<Integer, List<Integer>> hmGroup = new HashMap<Integer, List<Integer>>();   //Root,  Contact indexes
		
		for (i=0; i<n; i++) {
			int root = uf.findRoot(i);
			if (!hmGroup.containsKey(root)) {
				List<Integer> lstIdx = new ArrayList<>();
				lstIdx.add(i);
				hmGroup.put(root, lstIdx);
			} else {
				hmGroup.get(root).add(i);
			}
		}
		
		//Form contact groups
		Set<Integer> roots = hmGroup.keySet();
		
		for (Integer root:roots) {
			List<Contact> lstContact = new ArrayList<>();
			
			List<Integer> lstContactIdxes = hmGroup.get(root);
			for (Integer idx:lstContactIdxes) lstContact.add(input.get(idx));
			lstlstGroup.add(lstContact);
		}
		
		return lstlstGroup;
	}
	
	
	
	public GroupContacts() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
