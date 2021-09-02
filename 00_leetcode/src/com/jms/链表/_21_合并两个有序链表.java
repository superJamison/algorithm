package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/26 21:02
 */
public class _21_合并两个有序链表 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode listNode = mergeTwoLists(l1, l2);

        while (listNode != null) {
            System.out.print(listNode.val + "  ");
            listNode = listNode.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode root = new ListNode(-1);
        ListNode node = root;

        ListNode node1 = l1;
        ListNode node2 = l2;
        while (node1 != null) {
            while ((node2 != null && node1.val >= node2.val)) {
                node.next = new ListNode(node2.val);
                node = node.next;
                node2 = node2.next;
            }
            node.next = new ListNode(node1.val);

            node = node.next;
            node1 = node1.next;
        }

        node.next = node2;

        return root.next;
    }

    static class ListNode {
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
