/*
445. Add Two Numbers II

You are given two linked lists representing two non-negative numbers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7
*/

//Microsoft, Bloomberg
//Medium

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
	
	//Strategy:
	//Form two List; the first list is the sum of l1, l2, and on each position, the carry of the sum on each position is not counted
	//the second list only record the carry of the sum on each position; and the second list needs to be moved left by one step to make the carries valid
	//Next round, add the sum list and carry list, until all carries are 0
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = 0;
        int n2 = 0;
        ListNode tmpNode1 = l1;
        ListNode tmpNode2 = l2;
        
        while (tmpNode1 != null) {
            n1++;
            tmpNode1 = tmpNode1.next;
        }
        
        while (tmpNode2 != null) {
            n2++;
            tmpNode2 = tmpNode2.next;
        }
        
        ListNode ret = null;
        ListNode retHead = null;
        ListNode carry = null;
        ListNode carryHead = null;
        ListNode other = null;
        int i = 0;
        
        if (n1  >= n2) {
            ret = l1;
            retHead = l1;
            other = l2;

            while (i < n1-n2) {
                ret = ret.next;
                i++;
            }
        } else {
            ret = l2;
            retHead = l2;
            other = l1;
            
            while (i < n2-n1) {
                ret = ret.next;
                i++;
            }            
        }
            
        boolean bCarried = false;
        int tmpSum = 0;
            
        while (ret != null) {
            tmpSum = ret.val + other.val;
            ret.val = tmpSum % 10;
            if (bCarried == false) {
                if (tmpSum >= 10) bCarried = true;
            }
            
            if (carryHead == null) {
                carry = new ListNode(tmpSum / 10);
                carryHead = carry;
            } else {
                carry.next = new ListNode(tmpSum / 10);
                carry = carry.next;
            }
            
            ret = ret.next;
            other = other.next;
            
        }

        if (bCarried) {
            carry.next = new ListNode(0);
            carry.next.next = null;
            
            return addTwoNumbers(retHead, carryHead);
        } else {
            if (retHead == null || retHead.next == null) return retHead;
            
            while (retHead != null && retHead.val == 0) retHead = retHead.next;
            
            return retHead;
        }

        
    }
}

