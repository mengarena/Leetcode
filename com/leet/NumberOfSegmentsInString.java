/*
434. Number of Segments in a String

Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5
*/

//Easy

public class Solution {
    public int countSegments(String s) {
        s = s.trim();
        if (s.isEmpty()) return 0;
        String[] str = s.split("\\s+");
        
        return str.length;
    }
}
