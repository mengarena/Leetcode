package com.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

//Given a non-empty array of integers, return the k most frequent elements.
//
//For example,
//Given [1,1,1,2,2,3] and k = 2, return [1,2].
//
//Note: 
//You may assume k is always valid, 1 ¡Ü k ¡Ü number of unique elements.
//Your algorithm's time complexity must be better than O(n log n), where n is the array's size.


//Medium
//Yelp, Pocket Gems
public class TopKFrequentElements {

	public TopKFrequentElements() {
		// TODO Auto-generated constructor stub
	}
	
	
    class ElemFreq {
        int num;
        int count;
        
        public ElemFreq (int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public List<Integer> topKFrequentA(int[] nums, int k) {
        List<Integer> lstTopK = new ArrayList<Integer>();
        if (nums == null || nums.length == 0) return lstTopK;
        int n = nums.length;
        int i;
        Queue<ElemFreq> pqTopK = new PriorityQueue<ElemFreq>(k, new Comparator<ElemFreq>() {
            public int compare(ElemFreq a, ElemFreq b) {
                return b.count - a.count;
            }
        });
        
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (i=0; i<n; i++) {
            if (!hm.containsKey(nums[i])) {
                hm.put(nums[i], 1);
            } else {
                hm.put(nums[i], hm.get(nums[i]) + 1);
            }
        }
        
        Set<Integer> keys = hm.keySet();
        for (Integer key:keys) {
            pqTopK.offer(new ElemFreq(key, hm.get(key)));
        }
        
        for (i=1; i<=k; i++) {
            lstTopK.add(pqTopK.poll().num);
        }
        
        return lstTopK;
    }	
	
	
	
	//ACC
	class FreqStat {
		int num;
		int cnt;
		
		public FreqStat(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> lstTopK = new ArrayList<Integer>();
        if (nums == null || nums.length == 0 || k <= 0) return lstTopK;
                
        Queue<FreqStat> pq = new PriorityQueue<FreqStat>(new Comparator<FreqStat>() {
        	public int compare(FreqStat a, FreqStat b) {
        		return b.cnt - a.cnt;
        	}
        });
        
        int n = nums.length;
        int i;
        int cnt = 0;
        int tmp = 0;
        
        Arrays.sort(nums);   //This might be nlogn (The problem requires better than nlogn)

        tmp = nums[0];
        cnt = 1;
        
        for (i=1; i<n; i++) {
        	if (nums[i] == tmp) {
        		cnt++;
        	} else {
        		pq.offer(new FreqStat(tmp, cnt));
        		
        		tmp = nums[i];
        		cnt = 1;
        	}
        }
        
        pq.offer(new FreqStat(tmp, cnt));
        
        for (i=1; i<=k; i++) lstTopK.add(pq.remove().num);

        return lstTopK;
        
    }

}
