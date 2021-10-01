package com.jms.链表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/9/29 22:40
 */
public class _剑指_Offer_35_复杂链表的复制 {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.random = head.next;
        head.next.random = head.next;

        Node node = copyRandomList(head);

        System.out.println(node.val);
        System.out.println(node.next.val);

    }

    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        Node node = head;

        while (node != null){
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
            node = node.next.next;
        }

        node = head;
        while (node != null){
            node.next.random = node.random == null ? null : node.random.next;
            node = node.next.next;
        }

        Node newHead = head.next;
        node = head;
        while (node != null){
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
            node = node.next;
        }

        return newHead;
    }

    public Node copyRandomList2(Node head) {
        Map<Node, Node> map = new HashMap<>();

        Node reslult = copyRandom(head, map);

        return reslult;
    }

    private Node copyRandom(Node node, Map<Node, Node> map) {
        if (node == null) return null;

        if (!map.containsKey(node)){
            Node newNode = new Node(node.val);
            map.put(node, newNode);
            newNode.next = copyRandom(node.next, map);
            newNode.random = copyRandom(node.random, map);
        }

        return map.get(node);
    }

    public Node copyRandomList1(Node head) {
        List<Node> list = new ArrayList<>();

        while (head != null) {
            list.add(head);
            head = head.next;
        }

        Node node = new Node(-1);
        Node newHead = node;
        List<Node> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            node.next = new Node(list.get(i).val);
            newList.add(node.next);
            node = node.next;
        }

        for (int i = 0; i < newList.size(); i++) {
            int index = list.indexOf(list.get(i).random);
            if (index < 0 || index >= list.size()) {
                newList.get(i).random = null;
            } else {
                newList.get(i).random = newList.get(index);
            }
        }

        return newHead.next;
    }

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
