package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given a collection of distinct numbers, return all possible permutations.
//
//For example,
//[1,2,3] have the following permutations:
//[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

//Linkedin, Microsoft
public class Permutations {

	public Permutations() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int[] nums = {1,2,3, 4};
		
		List<List<Integer>> lstlstPerm = permute(nums);
		
		for (List<Integer> lstPerm:lstlstPerm) {
			for (Integer nPerm:lstPerm) System.out.print(nPerm + ",");
			System.out.println();
		}
	}
	
    // ACC:  99.75%
    // Time Complexity:  O(n!)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lstlstRet = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return lstlstRet;
        
        int n = nums.length;
        boolean[] used = new boolean[n];
        List<Integer> lstOne = new ArrayList<>();
        
        permuteHelper(nums, 0, used, lstOne, lstlstRet);
        return lstlstRet;
    }
    
    public void permuteHelper(int[] nums, int count, boolean[] used, List<Integer> lstOne, List<List<Integer>> lstlstRet) {
        if (count == nums.length) {
            lstlstRet.add(new ArrayList<Integer>(lstOne));
            return;
        }
        
        for (int i=0; i<nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                lstOne.add(nums[i]);

                permuteHelper(nums, count+1, used, lstOne, lstlstRet);

                lstOne.remove(lstOne.size()-1);
                used[i] = false;
            }
        }
    }	
	
	
    //ACC: 68%
    //Time Complexity:  O(n!)
    public List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> lstlstPerm = new ArrayList<List<Integer>>();
    	
    	if (nums == null || nums.length == 0) return lstlstPerm;
    	
    	int n = nums.length;
    	
    	lstlstPerm = permute(nums, n);
     	
    	return lstlstPerm;
    }
    
    
    //if previous is <a, b>, <b, a>;  next if three elements, the 3rd element could be added at 0th,1th,2th positions
    public List<List<Integer>> permute(int[] nums, int nValidCnt) {
    	List<List<Integer>> lstlstPerm = new ArrayList<List<Integer>>();
    	List<List<Integer>> lstlstPermPrev = new ArrayList<List<Integer>>();
    	
    	if (nValidCnt == 1) {
    		List<Integer> lstOnePerm = new ArrayList<Integer>();
    		lstOnePerm.add(nums[0]);
    		lstlstPerm.add(lstOnePerm);
    		return lstlstPerm;
    	}
    	
    	lstlstPermPrev = permute(nums, nValidCnt-1);  //Leave the last one out

    	int nTmpLen = lstlstPermPrev.get(0).size();
    	
    	for (List<Integer> lstOnePerm:lstlstPermPrev) {
    		for (int i=0; i<=nTmpLen; i++) {
    			List<Integer> lstOnePermTmp = new ArrayList<Integer>(lstOnePerm);
    			lstOnePermTmp.add(i, nums[nValidCnt-1]);
    			lstlstPerm.add(lstOnePermTmp);
    		}
    	}
    	
    	return lstlstPerm;
    	
    }
	
}
