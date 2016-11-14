package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Given a string, sort it in decreasing order based on the frequency of characters.
//
//Example 1:
//
//Input:
//"tree"
//
//Output:
//"eert"
//
//Explanation:
//'e' appears twice while 'r' and 't' both appear once.
//So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
//
//Example 2:
//
//Input:
//"cccaaa"
//
//Output:
//"cccaaa"
//
//Explanation:
//Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
//Note that "cacaca" is incorrect, as the same characters must be together.
//
//Example 3:
//
//Input:
//"Aabb"
//
//Output:
//"bbAa"
//
//Explanation:
//"bbaA" is also a valid answer, but "Aabb" is incorrect.
//Note that 'A' and 'a' are treated as two different characters.
//Show Company Tags
//Show Tags
//Show Similar Problems

//Google, Amazon
//Medium
public class Draft_SortCharactersByFrequency {

	public Draft_SortCharactersByFrequency() {
		// TODO Auto-generated constructor stub
	}

	//simple O(n) Bucket Sort
	public String frequencySortA(String s) {
        if(s.length() < 3)
            return s;
        int max = 0;
        int[] map = new int[256];
        for(char ch : s.toCharArray()) {
            map[ch]++;
            max = Math.max(max,map[ch]);
        }
        String[] buckets = new String[max + 1]; // create max buckets
        for(int i = 0 ; i < 256; i++) { // join chars in the same bucket
            String str = buckets[map[i]];
            if(map[i] > 0)
                buckets[map[i]] = (str == null) ? "" + (char)i : (str + (char) i);
        }
        StringBuilder strb = new StringBuilder();
        for(int i = max; i >= 0; i--) { // create string for each bucket.
            if(buckets[i] != null)
                for(char ch : buckets[i].toCharArray())
                    for(int j = 0; j < i; j++)
                        strb.append(ch);
        }
        return strb.toString();
    }

	
	//O(n)
	public String frequencySortB(String s) {
	    char[] arr = s.toCharArray();
	    
	    // bucket sort
	    int[] count = new int[256];
	    for(char c : arr) count[c]++;
	    
	    // count values and their corresponding letters
	    Map<Integer, List<Character>> map = new HashMap<>();
	    for(int i = 0; i < 256; i++){
	        if(count[i] == 0) continue;
	        int cnt = count[i];
	        if(!map.containsKey(cnt)){
	            map.put(cnt, new ArrayList<Character>());
	        }
	        map.get(cnt).add((char)i);
	    }

	    // loop throught possible count values
	    StringBuilder sb = new StringBuilder();
	    for(int cnt = arr.length; cnt > 0; cnt--){ 
	        if(!map.containsKey(cnt)) continue;
	        List<Character> list = map.get(cnt);
	        for(Character c: list){
	            for(int i = 0; i < cnt; i++){
	                sb.append(c);
	            }
	        }
	    }
	    return sb.toString();
	}
}
