package com.leet;

//Given a non negative integer number num. 
//For every numbers i in the range 0 ¡Ü i ¡Ü num calculate the number of 1's in their binary representation and return them as an array.
//
//Example:
//For num = 5 you should return [0,1,1,2,1,2].
//
//Follow up:
//
//It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
//But can you do it in linear time O(n) /possibly in a single pass?
//Space complexity should be O(n).
//Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

public class CountingBits {

	public CountingBits() {
		// TODO Auto-generated constructor stub
	}


	public void run() {
		int num = 2;
		int[] bits = countBits(num);
		
		for (int i=0; i<bits.length; i++) {
			System.out.print(bits[i] + ",");
		}
		
		System.out.println();
	}
	

    public int[] countBits(int num) {
        int[] narrBits = new int[num+1];
        int i = 1;
        int j = 0;
        
        narrBits[0] = 0;
        if (num == 0) return narrBits;
        
        narrBits[1] = 1;
        i = i*2;
        
        while (i*2-1 <= num) {
            for (j=i; j<=i*2-1; j++) {
                narrBits[j] = narrBits[j-i] + 1;
            }

            i=i*2; 
        }
        
        for (j=i; j<=num; j++) {
            narrBits[j] = narrBits[j-i] + 1;
        }
        
        return narrBits;
    }	
	
	
	//Every 2^n---2^(n+1)-1 is a segment, the 1s of the numbers in 2^n--2^(n+1)-1 segment will repeat the numbers of 1# in 2^(n-1)--2^n-1, plus One 
	//(because the new segment added a 1 at the beginning compared with previous segment)
    public int[] countBitsA(int num) {
        int[] bits = new int[num+1];
        int tmp = 0;
        int j = 0;
        
        bits[0] = 0;
        
        if (num == 0) return bits;
        
        bits[1] = 1;
        
        for (int i=2; i<=num; i++) {
        	tmp = 1;   //Get the 2^n
        	j = i;
        	while (j>1) {
        		j = j>>1;
			    tmp = tmp << 1;
        	}
        	
        	bits[i] = 1 + bits[i-tmp];   //1 is the # of 1 corresponding to the prefix 1 for this segment
        }
        
        return bits;
    }
}
