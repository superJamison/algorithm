package com.jms.双指针;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/2 20:13
 * ***************************************
 */
public class _82_删除排序链表中的重复元素_II {

    public static void main(String[] args) {
        int[] ints = new int[]{2,3,3,4,4,5};
        ListNode head = new ListNode(1);
        ListNode node = head;
        for (int i = 0; i < ints.length; i++) {
            node.next = new ListNode(ints[i]);
            node = node.next;
        }

        ListNode result = deleteDuplicates(head);

        while (result != null) {
            System.out.print(result.val + "  ");
            result = result.next;
        }
    }

    /**
     * 递归
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;

        return deleteNextRepeatNode(head);
    }

    private static ListNode deleteNextRepeatNode(ListNode node) {
        if (node == null || node.next == null) return node;

        if (node.next.val != node.val){
            node.next = deleteNextRepeatNode(node.next);
            return node;
        }else {
            int value = node.val;

            while (node != null && node.val == value){
                node = node.next;
            }
            return deleteNextRepeatNode(node);
        }
    }

    /**
     * 非递归
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null) return head;

        ListNode node = new ListNode(101, head);
        ListNode fast = node;

        while (fast.next != null && fast.next.next != null) {
            if (fast.next.val == fast.next.next.val){
                int temp = fast.next.val;
                while (fast.next != null && fast.next.val == temp){
                    fast.next = fast.next.next;
                }
            }else {
                fast = fast.next;
            }

        }

        return node.next;
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
