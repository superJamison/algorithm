package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/8 10:52
 */
public class _剑指_Offer_22_链表中倒数第k个节点 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode l = head, r = head;

        while (r != null) {
            if (k <= 0) {
                l = l.next;
            }
            r = r.next;
            k--;
        }

        return l;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
