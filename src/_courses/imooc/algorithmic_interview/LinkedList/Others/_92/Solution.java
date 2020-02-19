package _courses.imooc.algorithmic_interview.LinkedList.Others._92;

/**
 * Description:Recursive
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 *
 * @date: 2019/2/19 13:42
 */
public class Solution {
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
        ListNode left = null;
        pre.next = reverse(pre.next, n - m, left);
        tail.next = left;

        ListNode ret = dummyHead.next;

        return ret;
    }

    private ListNode reverse(ListNode head, int index, ListNode left) {//迭代的方式翻转

        if (index == 0) {
            left = head.next;
            return head;
        }

        ListNode tail = head.next;
        ListNode ret = reverse(head.next, index - 1, left);
        tail.next = head;
        return ret;
    }

}
