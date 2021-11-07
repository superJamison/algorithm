package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/9 16:11
 */
public class _剑指_Offer_25_合并两个排序的链表 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head = new ListNode(-1);
        ListNode node = head;

        while (l1 != null && l2 != null){
            if (l1.val > l2.val) {
                node.next = l2;
                l2 = l2.next;
            }
            else{
                node.next = l1;
                l1 = l1.next;
            }
            node = node.next;
        }

        if (l1 != null) node.next = l1;
        if (l2 != null) node.next = l2;

        return head.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
