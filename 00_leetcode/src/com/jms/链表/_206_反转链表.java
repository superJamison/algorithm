package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/3/21 19:30
 */
public class _206_反转链表 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = null;
        ListNode tmp;
        while (head != null){
            tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }





}
