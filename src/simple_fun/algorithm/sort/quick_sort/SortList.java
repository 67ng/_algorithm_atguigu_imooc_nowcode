package simple_fun.algorithm.sort.quick_sort;

/**
 * @Description: 链表快排
 *
 * 注：链表的快排是稳定的
 *
 * @Author: caffebaby
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
        ListNode left = new ListNode(-1);
        ListNode mid = new ListNode(-1);
        ListNode right = new ListNode(-1);
        ListNode ltail = left;
        ListNode mtail = mid;
        ListNode rtail = right;
        int val = head.val;
        for (ListNode p = head; p != null; p = p.next) {
            if (p.val < val) {
                ltail.next = p;
                ltail = ltail.next;
            } else if (p.val == val) {
                mtail.next = p;
                mtail = mtail.next;
            } else {
                rtail.next = p;
                rtail = rtail.next;
            }
        }
        ltail.next = mtail.next = rtail.next = null;//注意清空尾节点
        //递归执行
        left.next = sortList(left.next);
        right.next = sortList(right.next);

        //拼接三个链表
        getTail(left).next = mid.next;
        getTail(left).next = right.next;
        return left.next;
    }

    private ListNode getTail(ListNode head) {
        while (head.next != null)
            head = head.next;
        return head;
    }

}
