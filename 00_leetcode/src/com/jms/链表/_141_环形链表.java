package com.jms.链表;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 给定一个链表，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 思路：利用快慢指针
 *
 * @author Jamison
 * @version 1.0
 * @date 2021/3/21 19:59
 */
public class _141_环形链表 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null){
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            if (slow == fast){
                return true;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return false;
    }

}
