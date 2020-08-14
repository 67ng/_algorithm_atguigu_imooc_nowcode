package algorithm.sort.merge_sort;

/**
 * @Description: 对链表进行归并排序
 * @Author: matreeix
 * @Date: 2020/5/24
 */
public class SortList {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;
        head = sortList(head);
        head2 = sortList(head2);
        return merge(head, head2);
    }

    private ListNode merge(ListNode a, ListNode b) {

        ListNode dummyHead = new ListNode(-1);
        ListNode p1 = a, p2 = b, p = dummyHead;
        while (p1 != null && p2 != null)
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
                p.next = null;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
                p.next = null;
            }
        if (p1 != null) p.next = p1;
        if (p2 != null) p.next = p2;

        ListNode ret = dummyHead.next;
        return ret;
    }
}
