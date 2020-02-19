package _courses.imooc.algorithmic_interview.LinkedList.Others._2;

/**
 * Description: Using the longest list in l1 and l2 as the result list
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * @date: 2019/2/10 13:41
 */
public class Solution3 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int len1 = getLen(l1), len2 = getLen(l2);
        ListNode p1 = len1 > len2 ? l1 : l2;//长链表
        ListNode p2 = len1 > len2 ? l2 : l1;//短链表

        ListNode pre = null;
        int carried = 0;
        while (p1 != null) {
            int a = p1.val;
            int b = p2 != null ? p2.val : 0;
            p1.val = (a + b + carried) % 10;
            carried = (a + b + carried) / 10;

            pre = p1;
            p1 = p1.next;
            p2 = p2 != null ? p2.next : null;
        }

        pre.next = carried == 1 ? new ListNode(1) : null;
        return len1 > len2 ? l1 : l2;
    }

    private int getLen(ListNode l) {
        int res = 0;
        while (l != null) {
            res++;
            l = l.next;
        }
        return res;
    }
}
