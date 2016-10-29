package com.leet;

//Given  an  arbitrary  ransom  note  string  and  another  string  containing  letters from  all  the  magazines,  write  a  function  that  will  return  true  if  the  ransom   note  can  be  constructed  from  the  magazines ; 
// otherwise,  it  will  return  false.   
//
//Each  letter  in  the  magazine  string  can  only  be  used  once  in  your  ransom  note.
//
//Note:
//You may assume that both strings contain only lowercase letters.
//
//canConstruct("a", "b") -> false
//canConstruct("aa", "ab") -> false


//Apple
//Easy
public class RansomNote {

	public RansomNote() {
		// TODO Auto-generated constructor stub
	}

    public boolean canConstruct(String ransomNote, String magazine) {
        if (magazine == null || magazine.isEmpty()) {
            if (ransomNote == null || ransomNote.isEmpty()) return true;
            return false;
        }
        
        if (ransomNote == null || ransomNote.isEmpty()) return true;
        if (ransomNote.length() > magazine.length()) return false;
        
        int[] mag = new int[256];
        int n = magazine.length();
        int i;
        
        for (i=0; i<n; i++) mag[magazine.charAt(i)-0]++;
        
        for (i=0; i<ransomNote.length(); i++) {
            mag[ransomNote.charAt(i)-0]--;
            if (mag[ransomNote.charAt(i)-0] < 0) return false;
        }
        
        return true;
    }
}
