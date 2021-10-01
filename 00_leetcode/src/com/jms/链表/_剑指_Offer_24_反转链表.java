package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/29 21:21
 */
public class _剑指_Offer_24_反转链表 {

    public static void main(String[] args) {

    }

    public ListNode reverseList(ListNode head) {
        ListNode newHead = null;
        ListNode node = head;
        ListNode temp = null;

        while (node != null){
            temp = node.next;
            node.next = newHead;
            newHead = node;
            node = temp;
        }

        return newHead;
    }

    public ListNode reverseList1(ListNode head) {
        ListNode node = head;

        return reverse(node);
    }

    private ListNode reverse(ListNode node) {
        if (node == null || node.next == null) return node;

        ListNode tail = node.next;
        ListNode reverse = reverse(tail);
        tail.next = node;
        node.next = null;
        return reverse;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
