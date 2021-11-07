package com.jms.链表;

import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/8 10:35
 */
public class _剑指_Offer_18_删除链表的节点 {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;

        ListNode node = head;
        ListNode newHead = new ListNode(-1);
        ListNode prev = newHead;
        prev.next = head;

        while (node != null){
            if (node.val == val){
                prev.next = node.next;
            }

            prev = node;
            node = node.next;
        }

        return newHead.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
