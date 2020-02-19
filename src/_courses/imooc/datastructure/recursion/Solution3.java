package _courses.imooc.datastructure.recursion;

/**
 * Description:递归解法
 *
 * @author: NULL
 * @date: 2018/11/25 23:13
 */
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {

        if (head == null)
            return head;

        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 6);
        System.out.println(res);
    }
}
