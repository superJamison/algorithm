package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/20 9:45
 */
public class _19_删除链表的倒数第N个结点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        ListNode node = head;

        for (int i = 2; i < 3; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }

        ListNode result = removeNthFromEnd(head, 2);

        while (result != null){
            System.out.println(result.val);
            result = result.next;
        }

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head != null && head.next == null) return null;

        ListNode node = head;
        // 要删除节点的前一个节点
        ListNode prev = new ListNode(-1);
        prev.next = head;
        int size = 1;

        while ((node = node.next) != null){
            size++;
            if (size > n){
                prev = prev.next;
            }
        }

        // 删除节点
        prev.next = prev.next.next;
        if (prev.val == -1){
            head = prev.next;
        }

        return head;
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
