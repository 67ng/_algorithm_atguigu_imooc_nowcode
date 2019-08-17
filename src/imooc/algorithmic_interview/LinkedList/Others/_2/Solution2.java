package imooc.algorithmic_interview.LinkedList.Others._2;

/**
 * Description: Using l1 as the result list
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @date: 2019/2/10 13:35
 */
public class Solution2 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode pre = null;
        int carried = 0;

        while (p1 != null || p2 != null) {
            int a = p1 != null ? p1.val : 0;
            int b = p2 != null ? p2.val : 0;
            if (p1 != null)
                p1.val = (a + b + carried) % 10;
            else {
                pre.next = new ListNode((a + b + carried) % 10);
                p1 = pre.next;
            }
            carried = (a + b + carried) / 10;

            pre = p1;
            p1 = p1.next;
            if (p2 != null) p2 = p2.next;
        }

        pre.next = carried == 1 ? new ListNode(1) : null;
        return l1;
    }
}
