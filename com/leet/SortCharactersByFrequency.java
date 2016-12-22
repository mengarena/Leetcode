/*
451. Sort Characters By Frequency

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
*/

//Google, Amazon
//Medium

public class Solution {
    public String frequencySort(String s) {
        if (s.isEmpty() || s.length() <= 1) return s;
        int n = s.length();
        int i;
        int maxCount = 0;
        
        Map<Character, Integer> hm = new HashMap<>();
        
        //Get the count for each character
        for (i=0; i<n; i++) {
            hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i), 0) + 1);
            
            maxCount = Math.max(maxCount, hm.get(s.charAt(i)));
        }
        
        //Save same-count characters in the same list
        List<Character>[] lstarr = new List[maxCount+1];
        
        Set<Character> keys = hm.keySet();
        for (Character c:keys) {
            int count = hm.get(c);
            if (lstarr[count] == null) lstarr[count] = new ArrayList<Character>();
            
            lstarr[count].add(c);
        }
        
        //Get the result string
        StringBuilder sb = new StringBuilder();
        for (i=maxCount; i>=1; i--) {
            if (lstarr[i] != null) {
                for (Character c:lstarr[i]) {
                    for (int j=1; j<=i; j++) sb.append(c);   //This character has i times
                }
            }
        }
        
        return sb.toString();
    }
}


