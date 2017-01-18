package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//For example,
//[1,1,2] have the following unique permutations:
//[1,1,2], [1,2,1], and [2,1,1].

//Linkedin, Microsoft
public class PermutationsII {

	public PermutationsII() {
		// TODO Auto-generated constructor stub
	}

	public void run() {
		int[] nums = {0,0,3,5};
		List<List<Integer>> lstlstPerm = permuteUnique(nums);
		
		for (List<Integer> lstPerm:lstlstPerm) {
			System.out.print("[");
			for (Integer nVal:lstPerm) System.out.print(nVal + ",");
			System.out.println("]");
		}
	}

	//AC: 78%
	//Complexity: O(n!)
    public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> lstlstPerm = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) return lstlstPerm;
		
		Arrays.sort(nums);
		boolean[] used = new boolean[nums.length];
		
		List<Integer> lstPerm = new ArrayList<Integer>();
		
		permuterHelper(nums, 0, lstPerm, lstlstPerm, used);
		
		return lstlstPerm;
	}
	
	
	public void permuterHelper(int[] nums, int nCnt, List<Integer> lstPerm, List<List<Integer>> lstlstPerm, boolean[] used) {
		int nLen = nums.length;
		if (nCnt == nLen) {
			lstlstPerm.add(new ArrayList<Integer>(lstPerm));
			return;
		}
		
		//Every element could be the leading element in a permutation, and for the duplicate elements, only the first of them could be the leading element in a permutation
		//And so on, for the remaining positions in the permutation 
		for (int i=0; i<nLen; i++) {   			
			if (!used[i]) {			
				used[i] = true;
				lstPerm.add(nums[i]);
				permuterHelper(nums, nCnt+1, lstPerm, lstlstPerm, used);
				lstPerm.remove(lstPerm.size()-1);
				used[i] = false;
				
				while (i<nLen-1 && nums[i+1] == nums[i]) i++;  //Skip the remaining same-value element, because these can't be the leading element any more
			}
		}
    }	
	
	
/* Following works!	
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> lstlstPerm = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) return lstlstPerm;
		
		Arrays.sort(nums);
		boolean[] used = new boolean[nums.length];
		for (int i = 0; i<nums.length; i++) used[i] = false;
		
		List<Integer> lstPerm = new ArrayList<Integer>();
		
		permuterHelper(nums, 0, lstPerm, lstlstPerm, used);
		
		return lstlstPerm;
	}
	
	
	public void permuterHelper(int[] nums, int nStartPos, List<Integer> lstPerm, List<List<Integer>> lstlstPerm, boolean[] used) {
		int nLen = nums.length;
		if (nStartPos == nLen) {
			lstlstPerm.add(new ArrayList<Integer>(lstPerm));
			return;
		}
		
		//Every element could be the leading element in a permutation, and for the duplicate elments, only the first of them could be the leading element in a permutation
		//And so on, for the remaining positions in the permutation 
		for (int i=0; i<nLen; i++) {   			
			if (!used[i]) {			
				used[i] = true;
				lstPerm.add(nums[i]);
				permuterHelper(nums, nStartPos+1, lstPerm, lstlstPerm, used);
				lstPerm.remove(lstPerm.size()-1);
				used[i] = false;
				
				while (i<nLen-1 && nums[i+1] == nums[i]) i++;  //Skip the remaining same-value element, because these can't be the leading element any more
			}
		}
	}
*/	
	
	
	
/*	Works
	public void permuterHelper(int[] nums, int nStartPos, List<Integer> lstPerm, List<List<Integer>> lstlstPerm, boolean[] used) {
		int nLen = nums.length;
		if (nStartPos == nLen) {
			lstlstPerm.add(new ArrayList<Integer>(lstPerm));
			return;
		}
		
		for (int i=0; i<nLen; i++) {
			if (!used[i]) {
				if (i > 0 && !used[i-1] && nums[i] == nums[i-1]) continue;   //If previous (might be more than one) same-value element has not been used, don't use current one, this could avoid duplicate result
			
				used[i] = true;
				lstPerm.add(nums[i]);
				permuterHelper(nums, nStartPos+1, lstPerm, lstlstPerm, used);
				lstPerm.remove(lstPerm.size()-1);
				used[i] = false;
			}
		}
	}
	
*/
	
	
}
