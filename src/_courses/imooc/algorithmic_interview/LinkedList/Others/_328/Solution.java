package _courses.imooc.algorithmic_interview.LinkedList.Others._328;

/**
 * Description: Split the Linked List into two and then merge
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 *
 * @date: 2019/2/19 12:35
 */
public class Solution {
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

        ListNode dummyHead1 = new ListNode(-1);
        ListNode dummyHead2 = new ListNode(-1);
        ListNode p1 = dummyHead1;
        ListNode p2 = dummyHead2;
        ListNode p = head;
        for (int i = 0; p != null; i++)//for循环为奇偶节点重排序
            if (i % 2 == 0) {//偶数节点链接在一起(索引从0开始)
                p1.next = p;
                p = p.next;
                p1 = p1.next;
                p1.next = null;//设置为null为后面链接准备
            } else {//奇数节点
                p2.next = p;
                p = p.next;
                p2 = p2.next;
                p2.next = null;
            }

        p1.next = dummyHead2.next;//链接奇偶节点
        ListNode ret = dummyHead1.next;

        return ret;
    }
}

