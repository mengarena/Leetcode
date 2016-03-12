package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//For example,
//[1,1,2] have the following unique permutations:
//[1,1,2], [1,2,1], and [2,1,1].

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

	//This solution has problem.  The solutions below it work well.
	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> lstlstPerm = new ArrayList<List<Integer>>();
		if (nums == null || nums.length == 0) return lstlstPerm;
		List<Integer> lstNums = new ArrayList<Integer>();
			
		Arrays.sort(nums);

		for (int i=0; i<nums.length; i++) lstNums.add(nums[i]);
		
		permuterHelper(lstNums, 0, lstlstPerm);
		
		return lstlstPerm;
	}
	
	
	//All elements are already in the list, only need to swap them
	public void permuterHelper(List<Integer> lstNums, int nStartPos, List<List<Integer>> lstlstPerm) {
		if (nStartPos >= lstNums.size()) {
			lstlstPerm.add(new ArrayList<Integer>(lstNums));
			return;
		}
		
		//Every element has a chance to be the leading element in a permutation
		for (int i = nStartPos; i<lstNums.size(); i++) {
			//If current element is same as previous one, don't do following 
			//(i.e. this element should not be the leading, which could avoid duplicate permutation)
			if (i != nStartPos && lstNums.get(i) == lstNums.get(i-1)) continue;   
			
			//Swap elements at position nStartPos vs. i
//			if (lstNums.get(i) != lstNums.get(nStartPos)) {
				int nTmp = lstNums.get(i);
				lstNums.set(i,  lstNums.get(nStartPos));
				lstNums.set(nStartPos, nTmp);
//			}
			
			permuterHelper(lstNums, nStartPos+1, lstlstPerm);   //Permute remaining elements

//			if (lstNums.get(i) != lstNums.get(nStartPos)) {				
				nTmp = lstNums.get(i);
				lstNums.set(i,  lstNums.get(nStartPos));
				lstNums.set(nStartPos, nTmp);
//			}
				
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
