package imooc.algorithmic_interview.LinkedList.Others._2;

/**
 * Description: Create new LinkedList for result
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @date: 2019/2/10 12:28
 */
public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1, p2 = l2;
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        int carried = 0;
        while (p1 != null || p2 != null) {
            int a = p1 != null ? p1.val : 0;
            int b = p2 != null ? p2.val : 0;
            cur.next = new ListNode((a + b + carried) % 10);
            carried = (a + b + carried) / 10;//进位

            cur = cur.next;
            p1 = p1 != null ? p1.next : null;
            p2 = p2 != null ? p2.next : null;
        }

        cur.next = carried == 1 ? new ListNode(1) : null;//确定加和后的最高位
        ListNode ret = dummyHead.next;
        return ret;
    }

    public static void main(String[] args) {

    }
}
