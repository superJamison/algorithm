package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/21 19:30
 */
public class _206_反转链表1 {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode node = root;
        for (int i = 2; i < 6; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        ListNode result = reverseList(root);

        while (result != null) {
            System.out.print(result.val + "  ");
            result = result.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode temp = null;
        ListNode newHead = null;

        while (head != null){
            temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }

        return newHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
