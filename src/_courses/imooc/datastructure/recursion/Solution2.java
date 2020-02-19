package _courses.imooc.datastructure.recursion;

/**
 * Description:使用虚拟头节点
 *
 * @author: NULL
 * @date: 2018/11/25 21:36
 */
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {//不考虑垃圾回收
            if (prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution2()).removeElements(head, 6);
        System.out.println(res);
    }
}
