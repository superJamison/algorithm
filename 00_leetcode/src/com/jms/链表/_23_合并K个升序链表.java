package com.jms.链表;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/30 22:49
 */
public class _23_合并K个升序链表 {

    /**
     * 分治
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];

        if (l > r) return null;

        int mid = (l + r) >> 1;

        return merge2List(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    /**
     * 合并两个升序链表
     */
    private ListNode merge2List(ListNode head1, ListNode head2) {
        if (head1 == null) return head2;
        if (head2 == null) return head1;

        ListNode result = new ListNode();
        ListNode node1 = head1, node2 = head2, prev = result;

        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                prev.next = node1;
                node1 = node1.next;
            }else {
                prev.next = node2;
                node2 = node2.next;
            }
            prev = prev.next;
        }

        if (node1 != null) prev.next = node1;
        else prev.next = node2;

        return result.next;
    }

    /**
     * 小顶堆
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];

            while (node != null){
                ListNode temp = node.next;
                node.next = null;
                priorityQueue.add(node);
                node = temp;
            }
        }

        ListNode result = priorityQueue.poll();
        ListNode node = result;
        while (!priorityQueue.isEmpty()){
            node.next = priorityQueue.poll();
            node = node.next;
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
