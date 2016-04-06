package com.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Given two words (beginWord and endWord), and a dictionary's word list, 
//find the length of shortest transformation sequence from beginWord to endWord, such that:
//
//Only one letter can be changed at a time
//Each intermediate word must exist in the word list
//For example,
//
//Given:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//return its length 5.
//
//Note:
//Return 0 if there is no such transformation sequence.
//All words have the same length.
//All words contain only lowercase alphabetic characters.

//Linkedin
public class WordLadder {

	public WordLadder() {
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		String beginWord = "cet";
		String endWord = "ism";
		//String beginWord = "hit";
		//String endWord = "cog";

		Set<String> wordList = new HashSet<String>();
		
		//wordList.add("hot");
		//wordList.add("dot");
		//wordList.add("dog");
		//wordList.add("lot");
		//wordList.add("log");
		
		wordList.add("kid");
		wordList.add("tag");
		wordList.add("pup");
		wordList.add("ail");
		wordList.add("tun");
		wordList.add("woo");
		wordList.add("erg");
		wordList.add("luz");
		wordList.add("brr");
		wordList.add("gay");
		wordList.add("sip");
		wordList.add("kay"); 
	    wordList.add("per"); 
	    wordList.add("val"); 
	    wordList.add("mes"); 
	    wordList.add("ohs"); 
	    wordList.add("now"); 
	    wordList.add("boa"); 
	    wordList.add("cet"); 
	    wordList.add("pal"); 
	    wordList.add("bar"); 
	    wordList.add("die"); 
	    wordList.add("war"); 
	    wordList.add("hay"); wordList.add("eco"); wordList.add("pub"); wordList.add("lob"); 
	    wordList.add("rue"); wordList.add("fry"); wordList.add("lit"); wordList.add("rex"); 
	    wordList.add("jan"); wordList.add("cot"); wordList.add("bid"); wordList.add("ali"); 
	    wordList.add("pay"); wordList.add("col"); wordList.add("gum"); wordList.add("ger"); 
	    wordList.add("row"); wordList.add("won"); wordList.add("dan"); wordList.add("rum"); 
	    wordList.add("fad"); wordList.add("tut"); wordList.add("sag"); wordList.add("yip"); wordList.add("sui"); wordList.add("ark"); wordList.add("has"); 
	    wordList.add("zip"); wordList.add("fez"); wordList.add("own"); wordList.add("ump"); wordList.add("dis"); wordList.add("ads"); wordList.add("max"); 
	    wordList.add("jaw"); wordList.add("out"); wordList.add("btu"); wordList.add("ana"); wordList.add("gap"); wordList.add("cry"); wordList.add("led"); 
	    wordList.add("abe"); wordList.add("box"); wordList.add("ore"); wordList.add("pig"); wordList.add("fie"); wordList.add("toy"); wordList.add("fat"); 
	    wordList.add("cal"); wordList.add("lie"); wordList.add("noh"); wordList.add("sew"); wordList.add("ono"); wordList.add("tam"); wordList.add("flu"); 
	    wordList.add("mgm"); wordList.add("ply"); wordList.add("awe"); wordList.add("pry"); wordList.add("tit"); wordList.add("tie"); wordList.add("yet"); 
	    wordList.add("too"); wordList.add("tax"); wordList.add("jim"); wordList.add("san"); wordList.add("pan"); wordList.add("map"); wordList.add("ski"); 
	    wordList.add("ova"); wordList.add("wed"); wordList.add("non"); wordList.add("wac"); wordList.add("nut"); wordList.add("why"); wordList.add("bye"); 
	    wordList.add("lye"); wordList.add("oct"); wordList.add("old"); wordList.add("fin"); wordList.add("feb"); wordList.add("chi"); wordList.add("sap"); 
	    wordList.add("owl"); wordList.add("log"); wordList.add("tod"); wordList.add("dot"); wordList.add("bow"); wordList.add("fob"); wordList.add("for"); 
	    wordList.add("joe"); wordList.add("ivy"); wordList.add("fan"); wordList.add("age"); wordList.add("fax"); wordList.add("hip"); wordList.add("jib"); 
	    wordList.add("mel"); wordList.add("hus"); wordList.add("sob"); wordList.add("ifs"); wordList.add("tab"); wordList.add("ara"); wordList.add("dab"); 
	    wordList.add("jag"); wordList.add("jar"); wordList.add("arm"); wordList.add("lot"); wordList.add("tom"); wordList.add("sax"); wordList.add("tex"); 
	    wordList.add("yum"); wordList.add("pei"); wordList.add("wen"); wordList.add("wry"); wordList.add("ire"); wordList.add("irk"); wordList.add("far"); 
	    wordList.add("mew"); wordList.add("wit"); wordList.add("doe"); wordList.add("gas"); wordList.add("rte"); wordList.add("ian"); wordList.add("pot"); 
	    wordList.add("ask"); wordList.add("wag"); wordList.add("hag"); wordList.add("amy"); wordList.add("nag"); wordList.add("ron"); wordList.add("soy"); 
	    wordList.add("gin"); wordList.add("don"); wordList.add("tug"); wordList.add("fay"); wordList.add("vic"); wordList.add("boo"); wordList.add("nam"); 
	    wordList.add("ave"); wordList.add("buy"); wordList.add("sop"); wordList.add("but"); wordList.add("orb"); wordList.add("fen"); wordList.add("paw"); 
	    wordList.add("his"); wordList.add("sub"); wordList.add("bob"); wordList.add("yea"); wordList.add("oft"); wordList.add("inn"); wordList.add("rod"); 
	    wordList.add("yam"); wordList.add("pew"); wordList.add("web"); wordList.add("hod"); wordList.add("hun"); wordList.add("gyp"); wordList.add("wei"); 
	    wordList.add("wis"); wordList.add("rob"); wordList.add("gad"); wordList.add("pie"); wordList.add("mon"); wordList.add("dog"); wordList.add("bib"); 
	    wordList.add("rub"); wordList.add("ere"); wordList.add("dig"); wordList.add("era"); wordList.add("cat"); wordList.add("fox"); wordList.add("bee"); 
	    wordList.add("mod"); wordList.add("day"); wordList.add("apr"); wordList.add("vie"); wordList.add("nev"); wordList.add("jam"); wordList.add("pam"); 
	    wordList.add("new"); wordList.add("aye"); wordList.add("ani"); wordList.add("and"); wordList.add("ibm"); wordList.add("yap"); wordList.add("can"); 
	    wordList.add("pyx"); wordList.add("tar"); wordList.add("kin"); wordList.add("fog"); wordList.add("hum"); wordList.add("pip"); wordList.add("cup"); wordList.add("dye"); wordList.add("lyx"); wordList.add("jog"); wordList.add("nun"); wordList.add("par"); wordList.add("wan"); wordList.add("fey"); wordList.add("bus"); wordList.add("oak"); wordList.add("bad"); wordList.add("ats"); wordList.add("set"); wordList.add("qom"); wordList.add("vat"); wordList.add("eat"); wordList.add("pus"); wordList.add("rev"); wordList.add("axe"); wordList.add("ion"); wordList.add("six"); 
	    wordList.add("ila"); wordList.add("lao"); wordList.add("mom"); wordList.add("mas"); wordList.add("pro"); wordList.add("few"); wordList.add("opt"); wordList.add("poe"); wordList.add("art"); wordList.add("ash"); wordList.add("oar"); wordList.add("cap"); wordList.add("lop"); wordList.add("may"); wordList.add("shy"); wordList.add("rid"); wordList.add("bat"); wordList.add("sum"); wordList.add("rim"); wordList.add("fee"); wordList.add("bmw"); wordList.add("sky"); wordList.add("maj"); wordList.add("hue"); wordList.add("thy"); wordList.add("ava"); wordList.add("rap"); 
	    wordList.add("den"); wordList.add("fla"); wordList.add("auk"); wordList.add("cox"); wordList.add("ibo"); wordList.add("hey"); wordList.add("saw"); wordList.add("vim"); wordList.add("sec"); wordList.add("ltd"); wordList.add("you"); wordList.add("its"); wordList.add("tat"); wordList.add("dew"); wordList.add("eva"); wordList.add("tog"); wordList.add("ram"); wordList.add("let"); wordList.add("see"); wordList.add("zit"); wordList.add("maw"); 
	    wordList.add("nix"); wordList.add("ate"); wordList.add("gig"); wordList.add("rep"); wordList.add("owe"); wordList.add("ind"); wordList.add("hog"); wordList.add("eve"); wordList.add("sam"); wordList.add("zoo"); wordList.add("any"); wordList.add("dow"); wordList.add("cod"); wordList.add("bed"); wordList.add("vet"); wordList.add("ham"); wordList.add("sis"); wordList.add("hex"); wordList.add("via"); wordList.add("fir"); wordList.add("nod"); wordList.add("mao"); wordList.add("aug"); wordList.add("mum"); wordList.add("hoe"); wordList.add("bah"); wordList.add("hal"); 
	    wordList.add("keg"); wordList.add("hew"); wordList.add("zed"); wordList.add("tow"); wordList.add("gog"); wordList.add("ass"); wordList.add("dem"); wordList.add("who"); wordList.add("bet"); wordList.add("gos"); wordList.add("son"); wordList.add("ear"); wordList.add("spy"); wordList.add("kit"); wordList.add("boy"); wordList.add("due"); wordList.add("sen"); wordList.add("oaf"); wordList.add("mix"); wordList.add("hep"); wordList.add("fur"); wordList.add("ada"); wordList.add("bin"); wordList.add("nil"); wordList.add("mia"); wordList.add("ewe"); wordList.add("hit"); 
	    wordList.add("fix"); wordList.add("sad"); wordList.add("rib"); wordList.add("eye"); wordList.add("hop"); wordList.add("haw"); wordList.add("wax"); wordList.add("mid"); wordList.add("tad"); wordList.add("ken"); wordList.add("wad"); wordList.add("rye"); wordList.add("pap"); wordList.add("bog"); wordList.add("gut"); wordList.add("ito"); wordList.add("woe"); wordList.add("our"); wordList.add("ado"); wordList.add("sin"); wordList.add("mad"); wordList.add("ray"); wordList.add("hon"); wordList.add("roy"); wordList.add("dip"); wordList.add("hen"); wordList.add("iva"); 
	    wordList.add("lug"); wordList.add("asp"); wordList.add("hui"); wordList.add("yak"); wordList.add("bay"); wordList.add("poi"); wordList.add("yep"); wordList.add("bun"); wordList.add("try"); wordList.add("lad"); wordList.add("elm"); wordList.add("nat"); wordList.add("wyo"); wordList.add("gym"); wordList.add("dug"); wordList.add("toe"); wordList.add("dee"); wordList.add("wig"); wordList.add("sly"); wordList.add("rip"); wordList.add("geo"); wordList.add("cog"); wordList.add("pas"); wordList.add("zen"); wordList.add("odd"); wordList.add("nan"); wordList.add("lay"); 
	    wordList.add("pod"); wordList.add("fit"); wordList.add("hem"); wordList.add("joy"); wordList.add("bum"); wordList.add("rio"); wordList.add("yon"); wordList.add("dec"); wordList.add("leg"); wordList.add("put"); wordList.add("sue"); wordList.add("dim"); wordList.add("pet"); wordList.add("yaw"); wordList.add("nub"); wordList.add("bit"); wordList.add("bur"); wordList.add("sid"); wordList.add("sun"); wordList.add("oil"); wordList.add("red"); 
	    wordList.add("doc"); wordList.add("moe"); wordList.add("caw"); wordList.add("eel"); wordList.add("dix"); wordList.add("cub"); wordList.add("end"); wordList.add("gem"); wordList.add("off"); wordList.add("yew"); wordList.add("hug"); wordList.add("pop"); wordList.add("tub"); wordList.add("sgt"); wordList.add("lid"); wordList.add("pun"); wordList.add("ton"); wordList.add("sol"); wordList.add("din"); wordList.add("yup"); wordList.add("jab"); wordList.add("pea"); wordList.add("bug"); wordList.add("gag"); wordList.add("mil"); wordList.add("jig"); wordList.add("hub"); 
	    wordList.add("low"); wordList.add("did"); wordList.add("tin"); wordList.add("get"); wordList.add("gte"); wordList.add("sox"); wordList.add("lei"); wordList.add("mig"); wordList.add("fig"); wordList.add("lon"); wordList.add("use"); wordList.add("ban"); wordList.add("flo"); wordList.add("nov"); wordList.add("jut"); wordList.add("bag"); wordList.add("mir"); wordList.add("sty"); wordList.add("lap"); wordList.add("two"); wordList.add("ins"); wordList.add("con"); wordList.add("ant"); wordList.add("net"); wordList.add("tux"); wordList.add("ode"); wordList.add("stu"); 
	    wordList.add("mug"); wordList.add("cad"); wordList.add("nap"); wordList.add("gun"); wordList.add("fop"); wordList.add("tot"); wordList.add("sow"); wordList.add("sal"); wordList.add("sic"); wordList.add("ted"); wordList.add("wot"); wordList.add("del"); wordList.add("imp"); wordList.add("cob"); wordList.add("way"); wordList.add("ann"); wordList.add("tan"); wordList.add("mci"); wordList.add("job"); wordList.add("wet"); wordList.add("ism"); 
	    wordList.add("err"); wordList.add("him"); wordList.add("all"); wordList.add("pad"); wordList.add("hah"); wordList.add("hie"); wordList.add("aim"); wordList.add("ike"); wordList.add("jed"); wordList.add("ego"); wordList.add("mac"); wordList.add("baa"); wordList.add("min"); wordList.add("com"); wordList.add("ill"); wordList.add("was"); wordList.add("cab"); wordList.add("ago"); wordList.add("ina"); wordList.add("big"); wordList.add("ilk"); wordList.add("gal"); wordList.add("tap"); wordList.add("duh"); wordList.add("ola"); wordList.add("ran"); wordList.add("lab"); 
	    wordList.add("top"); wordList.add("gob"); wordList.add("hot"); wordList.add("ora"); wordList.add("tia"); wordList.add("kip"); wordList.add("han"); wordList.add("met"); wordList.add("hut"); wordList.add("she"); wordList.add("sac"); wordList.add("fed"); wordList.add("goo"); wordList.add("tee"); wordList.add("ell"); wordList.add("not"); wordList.add("act"); wordList.add("gil"); wordList.add("rut"); wordList.add("ala"); wordList.add("ape"); wordList.add("rig"); wordList.add("cid"); wordList.add("god"); wordList.add("duo"); wordList.add("lin"); wordList.add("aid"); 
	    wordList.add("gel"); wordList.add("awl"); wordList.add("lag"); wordList.add("elf"); wordList.add("liz"); wordList.add("ref"); wordList.add("aha"); wordList.add("fib"); wordList.add("oho"); wordList.add("tho"); wordList.add("her"); wordList.add("nor"); wordList.add("ace"); wordList.add("adz"); wordList.add("fun"); wordList.add("ned"); wordList.add("coo"); wordList.add("win"); wordList.add("tao"); wordList.add("coy"); wordList.add("van"); 
	    wordList.add("man"); wordList.add("pit"); wordList.add("guy"); wordList.add("foe"); wordList.add("hid"); wordList.add("mai"); wordList.add("sup"); wordList.add("jay"); wordList.add("hob"); wordList.add("mow"); wordList.add("jot"); wordList.add("are"); wordList.add("pol"); wordList.add("arc"); wordList.add("lax"); wordList.add("aft"); wordList.add("alb"); wordList.add("len"); wordList.add("air"); wordList.add("pug"); wordList.add("pox"); wordList.add("vow"); wordList.add("got"); wordList.add("meg"); wordList.add("zoe"); wordList.add("amp"); wordList.add("ale"); 
	    wordList.add("bud"); wordList.add("gee"); wordList.add("pin"); wordList.add("dun"); wordList.add("pat"); wordList.add("ten"); wordList.add("mob");	

		System.out.println("Dict Size: " + wordList.size());

		
		long aa = System.currentTimeMillis();
	
		int nLen = findLadders(beginWord, endWord, wordList);
		
		System.out.println("Time = " + (System.currentTimeMillis() - aa));
		
		System.out.println("Len = " + nLen);
		
	}
	
	
	//Accepted.
	//Strategy:  Process level by level. all the path/branch forms a tree.  The root of the tree is the beginWord.
	//From the leaves at each level, to find the words which could be derived by chaning one letter from these leaves.
	//These derived words forms the new level (i.e. the new leaves of the tree at one level below)
	//Once on one level, there occurs the endWord, the process stops.
	//
	//Trick:  Once one candidate word (i.e. the word made by changing one letter from a previous valid word) exists in the dictionary
	//The candidate word is added to the end of the branch
	//AND, the candidate word could be removed from the dictionary, because if don't remove it, it might be met at other branches,
	//but the final length of the path of those branches could not be shorter than current one
	//
	//Different from WordLadder II:
	//Here at each level, once a word is met, could remove it from the dictionary immediately. One word could only occur at each level once.
	//If one word occurs at each level many times, the situation below that word is the same for different branches
	//
    public int findLadders(String beginWord, String endWord, Set<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
                               
    	int n = beginWord.length();
    	int nDiffCnt = 0;
    	
    	for (int i=0; i<n; i++) {
    		if (beginWord.charAt(i) != endWord.charAt(i)) nDiffCnt++;
    		
    		if (nDiffCnt > 1) break;
    	}

    	if (nDiffCnt == 1) return 2; 
        
        wordList.add(endWord);
        wordList.remove(beginWord);
                               
        List<String> lstParent = new ArrayList<String>();
        lstParent.add(beginWord);
        List<List<String>> lstlstParent = new ArrayList<List<String>>();
        lstlstParent.add(lstParent);

        int nLen = findLadderHelper(wordList, n, endWord, lstlstParent);
               
        return nLen;
    }
    
    
    //BFS
    private int findLadderHelper(Set<String> wordList, int n, String sEndWord, List<List<String>> lstlstParent) {    	   	
    	int i;
    	
    	int nSize = wordList.size();
    	
    	while (!lstlstParent.isEmpty() && lstlstParent.get(0).size() < nSize + 2) {
    		
    		int nParentCnt = lstlstParent.size();
    		
    		List<List<String>> lstlstParentNew = new ArrayList<List<String>>();
    		//One a word is found in this level, it could be removed. 
    		//Because, if we don't remove it, if the words is met in other level at other branches, their final path could not be shorter than this one
    		
    		for (i=nParentCnt-1; i>=0; i--) {
    			List<String> lstParent = lstlstParent.get(i);
    			String sParent = lstParent.get(lstParent.size()-1);
    			
    	    	List<String> lstLevel = new ArrayList<String>();
    	    	
    			boolean bRet = findLadderHelperBFS(wordList, n, sParent, sEndWord, lstLevel);
    			if (bRet == true) {
    				return lstParent.size()+1;
    			} else {
    				
    				for (String sLevelWord:lstLevel) {
    					List<String> lstNewParent = new ArrayList<String>(lstParent);
    					lstNewParent.add(sLevelWord);
    					lstlstParentNew.add(lstNewParent);
    				}
    			}
    		}
    		
    		lstlstParent = lstlstParentNew;
    	}
    	    	
    	return 0;
    }
    
    //Find next level
    private boolean findLadderHelperBFS(Set<String> wordList, int n, String sBeginWord, String sEndWord,List<String> lstLevel) {
    	
    	char[] carrElement = sBeginWord.toCharArray();
    	
    	for (int i=0; i<n; i++) {
    		char cElement = carrElement[i];
    		
    		for (char j='a'; j<='z'; j++) {
    			if (cElement != j) {
    				carrElement[i] = j;
    				String sNewBeginWord = new String(carrElement);
    				
    				if (wordList.contains(sNewBeginWord)) {
    					
    					if (sNewBeginWord.equals(sEndWord)) return true;
    					
    					lstLevel.add(sNewBeginWord);
    					
    					//Reason to remove the word for next level:
    					//If don't remove, when meets this word at other (higher) level, the final path could not be shorter than this one,
    					//those will not be the result path, so could remove it  
    					wordList.remove(sNewBeginWord);
    				}
    				
    				carrElement[i] = cElement;
    			}
    		}
    	}
    	
    	return false;
    }
    
    
    private boolean IsLadderable(String sWord1, String sWord2) {
    	int n = sWord1.length();
    	int nDiffCnt = 0;
    	
    	for (int i=0; i<n; i++) {
    		if (sWord1.charAt(i) != sWord2.charAt(i)) nDiffCnt++;
    		
    		if (nDiffCnt > 1) return false;
    	}
    	
    	if (nDiffCnt == 1) return true;
    	
    	return false;
    }

    
/*
     private boolean findLadderHelperBFS(Set<String> wordList, String sBeginWord, String sEndWord, List<String> lstPrev, List<String> lstLevel, List<Integer> lstFoundIdx) {
    	int n = sBeginWord.length();
    	int i;
    	int nIdx = -1;
    	boolean bRet = false;
    	
    	StringBuffer sbBeginWord = new StringBuffer(sBeginWord);
    	for (i=0; i<n; i++) {
    		char cElement = sBeginWord.charAt(i);
    		
    		for (char j='a'; j<='z'; j++) {
    			if (cElement != j) {
    				//String sNewBeginWord = sBeginWord.substring(0, i) + j + sBeginWord.substring(i+1);
    				sbBeginWord.setCharAt(i, j);
    				String sNewBeginWord = sbBeginWord.toString();
    				
    				if (wordList.contains(sNewBeginWord) && !lstPrev.contains(sNewBeginWord)) {
    					nIdx++;
    					lstLevel.add(sNewBeginWord);
    					if (IsLadderable(sNewBeginWord, sEndWord)) {
    						bRet = true;
    						lstFoundIdx.add(nIdx);
    					}
    				}
    				
    				sbBeginWord.setCharAt(i, cElement);
    			}
    		}
    	}
    	
    	return bRet;
    }

 * */    
    
}
