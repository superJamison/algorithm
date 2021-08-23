package com.jms.双指针;

import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/8/20 9:10
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 */
public class _876_链表的中间结点 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);

        ListNode node = head;

        for (int i = 2; i < 7; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }

        System.out.println(middleNode(head).val);
    }

    /**
     * 2.0版本
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    /**
     * 1.0版本
     * @param head
     * @return
     */
    public static ListNode middleNode1(ListNode head) {
        ListNode node = head, result = head;
        int size = -1;

        while ((node = node.next) != null){

            if (size % 2 == 0){
                result = result.next;
            }

            size++;
        }

        if (size % 2 == 0){
            result = result.next;
        }

        return result;
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
