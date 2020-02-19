package _courses.imooc.algorithmic_interview.LinkedList.Others._328;

/**
 * Description:Keep one in original Linked List
 *
 * @date: 2019/2/19 12:58
 */
public class Solution2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;

        ListNode dummyHead2 = new ListNode(-1);
        ListNode p2 = dummyHead2;
        ListNode p = head;
        while (p.next != null) {
            p2.next = p.next;
            if (p.next.next == null) {
                p.next = null;
                break;
            }
            p.next = p.next.next;
            p = p.next;
            p2 = p2.next;
            p2.next = null;
        }

        p.next = dummyHead2.next;

        return head;
    }
}
