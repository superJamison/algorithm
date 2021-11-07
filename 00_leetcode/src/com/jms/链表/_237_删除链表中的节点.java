package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/11/2 17:00
 */
public class _237_删除链表中的节点 {

    public void deleteNode(ListNode node) {
        ListNode next = node.next;
        int val = node.val;
        node.val = next.val;
        next.val = val;
        node.next = next.next;
    }
}
