package com.leet;

import java.util.ArrayList;
import java.util.List;

//Given an integer n, return 1 - n in lexicographical order.
//
//For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
//
//Please optimize your algorithm to use less time and space. 
//The input size may be as large as 5,000,000.


//Bloomberg
public class LexicographicalNumbers {

	public LexicographicalNumbers() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		int n = 199;
		List<Integer> lstNums = lexicalOrder(n);
		
		for (int num:lstNums) System.out.print(num + ",");
		System.out.println();
		System.out.println("==>" + lstNums.size());
	}
	
	

	//ACC:  Quickest
	//Idea is actually same as lexicalOrderM
    public List<Integer> lexicalOrder(int n) {
        List<Integer> lstNums = new ArrayList<>();
        if (n <= 0) return lstNums;
        int base = 1;
        
        for (int i=1; i<=n; i++) {
            lstNums.add(base);
            
            if (base <= n/10) {
                base = base * 10;   //Process 10, 100, 1000
            } else if (base <= n-1 && (base % 10) != 9) {
                base++;             //Process 1001, 1002 .... 1009
            } else {                //Process 198 --> 19 --> 2
                base = base/10;
                base++;
                while (base % 10 == 0) base = base/10;
            }
        }
        
        return lstNums;
    }
	
	
	//ACC
	//The idea is pretty simple. If we look at the order we can find out we just keep adding digit from 0 to 9 to every digit and make it a tree.
	//Then we visit every node in pre-order. 
	//       1        2        3    ...
	//      /\        /\       /\
	//   10 ...19  20...29  30...39   ....
    public List<Integer> lexicalOrderK(int n) {
        List<Integer> lstNums = new ArrayList<>();
        if (n <= 0) return lstNums;

        for (int i=1; i<=9; i++) {
            dfs(i, n, lstNums);
        }
        
        return lstNums;
    }
    
    private void dfs(int base, int n, List<Integer> lstNums) {
        if (base > n) return;

        lstNums.add(base);
        
        for (int i=0; i<=9; i++) {
            if (base > (n-i)/10) return;
            
            dfs(base*10+i, n, lstNums);
        }
        
    }
	
	
	
	//ACC
	//Rule:
	//1, 10, 100, 1000..
	//From the last part, say 1000, to do
	// +1, +1 ..... until  +1 mod 10 == 0 (e.g. 1009 + 1)
	//Get the new base (1009+1)/10...  = 101
	//Loop to the begining in the main while, to start from 101, 1010, 
	//.....
	//Get back to 2, 20, 200
	//(If using a count, instead of lstNums.size(), will slight quicker)
    public List<Integer> lexicalOrderM(int n) {
        List<Integer> lstNums = new ArrayList<>();
        if (n <= 0) return lstNums;
        int base = 1;

        lstNums.add(base);
        
        while (lstNums.size() < n) {
            while (base <= n/10) {   //10, 100, 1000, 10000
                lstNums.add(base*10);
                base *= 10;
            }
            
            while (base <= n-1 && (((base+1) % 10) != 0)) {
                lstNums.add(base+1);
                base++;
            }
            
            if (lstNums.size() == n) break;
            
            if (base <= n-1 && (((base+1) % 10) == 0)) {
                base++;
                while (base % 10 == 0) base = base/10;
                
                if (base <= n) lstNums.add(base);
            } else {
            	base = base / 10;
            	base++;
            	while (base % 10 == 0) base = base/10;
            	if (base <= n) lstNums.add(base);
            }
        }
        
        return lstNums;
    }
	
	
	//Exceed Time Limit
    public List<Integer> lexicalOrderA(int n) {
        List<Integer> lstNums = new ArrayList<>();

        for (int i=1; i <= 9; i++) {
            if (i <= n) {
                List<Integer> lstNumsTmp = lexicalOrderHelper(i, n);
                if (lstNumsTmp != null) lstNums.addAll(lstNumsTmp);
            } else {
                break;
            }
        }
        
        return lstNums;
    }
    
    private List<Integer> lexicalOrderHelper(int num, int n) {
        List<Integer> lstNums = new ArrayList<>();
        if (num > n) return null;
        
        lstNums.add(num);
        
        List<Integer> lstNumsTmp = lexicalOrderHelper(num*10, n);
        
        if (lstNumsTmp != null) lstNums.addAll(lstNumsTmp);
        
        int modTmp = num % 10;
        if ((num < 10) || (num >= 10 && modTmp != 0)) return lstNums;
        
        int endNum = (num/10 * 10) + 10;
        
        for (int i=num+1; i<endNum; i++) {
            List<Integer> lstNumsTmpTmp = lexicalOrderHelper(i, n);
            if (lstNumsTmpTmp != null) {
                lstNums.addAll(lstNumsTmpTmp);
            } else {
                break;
            }
        }
        
        return lstNums;
     }


}
