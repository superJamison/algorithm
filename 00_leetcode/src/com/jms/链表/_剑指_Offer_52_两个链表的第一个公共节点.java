package com.jms.链表;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/10/9 16:51
 */
public class _剑指_Offer_52_两个链表的第一个公共节点 {

    public static void main(String[] args) {
        //   4 1 8 4 5
        // 5 0 1 8 4 5

        // 5 0 1 8 4 5  4 1 8 4 5
        // 4 1 8 4 5  5 0 1 8 4 5
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int countA = 0, countB = 0;

        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != null) {
            countA++;
            nodeA = nodeA.next;
        }
        while (nodeB != null) {
            countB++;
            nodeB = nodeB.next;
        }

        nodeA = headA;
        nodeB = headB;
        if (countA > countB){
            int count = countA - countB;
            while (count > 0){
                nodeA = nodeA.next;
                count--;
            }
        }else {
            int count = countB - countA;
            while (count > 0){
                nodeB = nodeB.next;
                count--;
            }
        }

        while (nodeA != null){
            if (nodeA == nodeB){
                return nodeA;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }

        return null;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
