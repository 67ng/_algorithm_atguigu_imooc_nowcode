package _courses.imooc.algorithmic_interview.LinkedList.Others._92;

/**
 * Description:
 *
 * @date: 2019/2/19 17:03
 */
public class Solution2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        for (int i = 0; i < m - 1; i++, pre = pre.next) ;

        ListNode tail = pre.next;
        ListNode left = tail;
        pre.next = reverse(pre.next, n - m, left);
        if (left != tail) tail.next = left;

        ListNode ret = dummyHead.next;

        return ret;
    }

    private ListNode reverse(ListNode head, int n, ListNode left) {

        if (head == null || head.next == null || n == 0)
            return head;

        ListNode pre = head;
        ListNode cur = head.next;
        while (n-- != 0) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        left = cur;
        return pre;
    }
}
