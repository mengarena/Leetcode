/*

433. Minimum Genetic Mutation

A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), 
where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. 
A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end".
If there is no such a mutation, return -1.

Note:
Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
 
Example 1:
start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]
return: 1
 
Example 2:
start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
return: 2
 
Example 3:
start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
return: 3

Medium
*/

class Solution:
    def getNewStart(self, start: str):
        startlist = list(start)
        newStarts = set()
        for i in range(len(startlist)):
            newStart = startlist.copy()
            newStart[i] = 'A'
            newStarts.add("".join(newStart))
            newStart[i] = 'C'
            newStarts.add("".join(newStart))
            newStart[i] = 'G'
            newStarts.add("".join(newStart))
            newStart[i] = 'T'
            newStarts.add("".join(newStart))
        newStarts.discard(start)
        return newStarts
        
               
    def calPath(self, startList: List[str], end: str, banks: Set[str], stepCnt: int)->int:
        for start in startList:
            if start == end:
                return stepCnt
            
        if banks == None or len(banks) == 0:
            return -1
        
        newStartList = []
        for start in startList:
            newStarts = self.getNewStart(start)
            for newStart in newStarts:
                if newStart in banks or newStart == end:
                    newStartList.append(newStart)
                    banks.discard(newStart)
        
        if len(newStartList) == 0:
            return -1
        
        steps = self.calPath(newStartList, end, banks, stepCnt+1)
        if steps == -1:
            return -1
        
        return steps
    
    
    def minMutation(self, start: str, end: str, bank: List[str]) -> int:
        banks = set(bank)
        
        startList = [start]
        if end not in banks:
            return -1
        
        stepCnt = self.calPath(startList, end, banks, 0)
        return stepCnt
