package algorithm.design.magic_pointer;

/**
 * @Description: 快慢指针，通常应用与链表相关的问题
 * @Author: matreeix
 * @Date: 2020/9/15
 */

public class FastSlowPointer {
    //链表中的环路检测
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;//快慢指针相遇
        }
        if (fast == null || fast.next == null) {//无环
            return null;
        }

        // slow从head开始，fast从相遇点开始，一步一步走再次相遇即为环入口
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //检测是否回文链表
    public boolean isPalindrome(ListNode head) {
        // 快慢指针找中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转后半部分
        ListNode pre = null;
        while (slow != null) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        // 前后两段比较是否一致
        ListNode node = head;
        while (pre != null) {
            if (pre.val != node.val) {
                return false;
            }
            pre = pre.next;
            node = node.next;
        }
        return true;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
