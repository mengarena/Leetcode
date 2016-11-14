package com.leet;

import java.util.HashMap;
import java.util.Stack;

//You are given two linked lists representing two non-negative numbers. 
//The most significant digit comes first and each of their nodes contain a single digit. 
//Add the two numbers and return it as a linked list.
//
//You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
//Follow up:
//What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
//
//Example:
//
//Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 8 -> 0 -> 7

//Microsoft, Bloomberg
//Medium
public class Draft_AddTwoNumbersII {

	public Draft_AddTwoNumbersII() {
		// TODO Auto-generated constructor stub
	}

	   public ListNode addTwoNumbersA(ListNode l1, ListNode l2) {
	        Stack<Integer> s1 = new Stack<Integer>();
	        Stack<Integer> s2 = new Stack<Integer>();
	        
	        while(l1 != null) {
	            s1.push(l1.val);
	            l1 = l1.next;
	        };
	        while(l2 != null) {
	            s2.push(l2.val);
	            l2 = l2.next;
	        }
	        
	        int sum = 0;
	        ListNode list = new ListNode(0);
	        
	        while (!s1.empty() || !s2.empty()) {
	            if (!s1.empty()) sum += s1.pop();
	            if (!s2.empty()) sum += s2.pop();
	            list.val = sum % 10;
	            ListNode head = new ListNode(sum / 10);
	            head.next = list;
	            list = head;
	            sum /= 10;
	        }
	        
	        return list.val == 0 ? list.next : list;
	    }

	   
	    public ListNode addTwoNumbersB(ListNode l1, ListNode l2) {
	        
	        HashMap<Integer, Integer> hm1 = new HashMap<>(); //Store the 'index' and the value of List1
	        HashMap<Integer, Integer> hm2 = new HashMap<>(); //Store the 'index' and the value of List2
	        int i = 1, j = 1;
	        
	        while(l1 != null){
	            hm1.put(i, l1.val);
	            l1 = l1.next;
	            i++;
	        }
	        while(l2 != null){
	            hm2.put(j, l2.val);
	            l2 = l2.next;
	            j++;
	        }
	        
	        int carry = 0;
	        i--; j--;
	        ListNode head = null;
	        
	      //Create new nodes to the front of a new LinkedList
	        while(i > 0 || j > 0 || carry > 0){

	            int a = i > 0 ? hm1.get(i) : 0;
	            int b = j > 0 ? hm2.get(j) : 0;
	            int res = (a + b + carry) % 10;
	            
	            ListNode newNode = new ListNode(res);
	            newNode.next = head;
	            head = newNode;
	            
	            carry = (a + b + carry) / 10;
	            i--; j--;
	        }
	        return head;
	    }	   
}
