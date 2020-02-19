package _courses.imooc.algorithmic_interview.LinkedList.Others._82;

/**
 * Description:
 *
 * @date: 2019/2/19 13:17
 */
public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        ListNode cur = prev.next;//存储不重复的节点
        while (cur != null) {
            int num = 0;
            ListNode p = cur;
            while (p != null && p.val == cur.val) {
                num++;
                p = p.next;
            }

            if (num > 1)
                prev.next = p;
            else
                prev = cur;

            cur = p;
        }
        return dummyHead.next;
    }
}
