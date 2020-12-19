package com.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//An abbreviation of a word follows the form <first letter><number><last letter>. 
//Below are some examples of word abbreviations:
//
//a) it                      --> it    (no abbreviation)
//
//     1
//b) d|o|g                   --> d1g
//
//              1    1  1
//     1---5----0----5--8
//c) i|nternationalizatio|n  --> i18n
//
//              1
//     1---5----0
//d) l|ocalizatio|n          --> l10n
//
//Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. 
//A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
//
//Example: 
//Given dictionary = [ "deer", "door", "cake", "card" ]
//
//isUnique("dear") -> false
//isUnique("cart") -> true
//isUnique("cane") -> false
//isUnique("make") -> true

//Google
//Easy
public class UniqueWordAbbreviation {

    public UniqueWordAbbreviation() {
		// TODO Auto-generated constructor stub
    }
	
    //Pay attention to the description of the question
    //***A word's abbreviation is unique if no other word from the dictionary has the same abbreviation***
    //If the dictionary has a word "hello",  if we use isUnique to test a word "hello", 
    //the two "hello" will be considered the same, so isUnique("hello") = true (if no other h2o words exist)
    //While in the example given in the question, "cane" has same abbr as "cake", but they are different words
	
    private Map<String, List<String>> hmDict = new HashMap<String, List<String>>();   //Key (abbr),  
	                                                                              //the list of dict words which has the abbr

    public void ValidWordAbbr(String[] dictionary) {   //Original version for the question should be (constructor): 
	                                               //public ValidWordAbbr(String[] dictionary)
        if (dictionary == null || dictionary.length == 0) return;
        
        for (int i=0; i<dictionary.length; i++) {
        	String sAbbr = getAbbr(dictionary[i]);
        	
            if (hmDict.containsKey(sAbbr)) {
                if (!hmDict.get(sAbbr).contains(dictionary[i])) hmDict.get(sAbbr).add(dictionary[i]);
            } else {
                List<String> lstValue = new ArrayList<String>();
                lstValue.add(dictionary[i]);
                hmDict.put(sAbbr, lstValue);
            }	
        }
    }

    private String getAbbr(String s) {
    	int n = s.length();
    	if (n <= 2) return s;
    	
    	return s.charAt(0) + "" + (n-2) + s.charAt(n-1);
    }
    
    public boolean isUnique(String word) {
        if (word == null) return false;
        if (hmDict.size() == 0) return true;
        
        String sAbbr = getAbbr(word);
        
        if (!hmDict.containsKey(sAbbr)) return true;
        
    	if (hmDict.get(sAbbr).size() > 2) return false;
    	
    	if (hmDict.get(sAbbr).size() == 1 && word.equals(hmDict.get(sAbbr).get(0))) return true;
    	
    	return false;
    }
	
}


// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
