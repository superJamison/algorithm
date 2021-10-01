package com.jms.链表;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/29 20:55
 */
public class _剑指_Offer_06_从尾到头打印链表 {
    public static void main(String[] args) {

    }

    public int[] reversePrint(ListNode head) {
        ListNode node = head;
        List<Integer> list = new ArrayList<>();

        while (node != null){
            list.add(node.val);
            node = node.next;
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[list.size() - 1 - i] = list.get(i);
        }

        return result;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
