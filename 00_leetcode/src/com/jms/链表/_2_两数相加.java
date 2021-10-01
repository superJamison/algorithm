package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/27 19:43
 */
public class _2_两数相加 {
    public static void main(String[] args) {

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode resut = new ListNode(-1);
        ListNode node = resut;
        int temp = 0, value;

        while (l1 != null){
            if (l2 != null){
                value = l1.val + l2.val + temp;
                node.next = new ListNode(value % 10);
                temp = value / 10;
            }else {
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
            node = node.next;
        }

        if (l2 != null) {
            l1 = l2;
        }

        while (l1 != null){
            value = temp + l1.val;
            node.next = new ListNode(value % 10);
            temp = value / 10;
            node = node.next;
            l1 = l1.next;
        }

        if (temp != 0){
            node.next = new ListNode(temp);
        }

        return resut.next;
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode resut = new ListNode(-1);
        ListNode node = resut;
        int temp = 0, value;

        while (l1 != null){
            if (l2 != null){
                value = l1.val + l2.val + temp;
                node.next = new ListNode(value % 10);
                temp = value / 10;
            }else {
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
            node = node.next;
        }

        while (l1 != null){
            value = temp + l1.val;
            node.next = new ListNode(value % 10);
            temp = value / 10;
            node = node.next;
            l1 = l1.next;
        }

        while (l2 != null){
            value = temp + l2.val;
            node.next = new ListNode(value % 10);
            temp = value / 10;
            node = node.next;
            l2 = l2.next;
        }

        if (temp != 0){
            node.next = new ListNode(temp);
        }

        return resut.next;
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
