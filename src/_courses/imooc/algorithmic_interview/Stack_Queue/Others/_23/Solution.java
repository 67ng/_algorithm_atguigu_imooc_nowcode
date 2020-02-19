package _courses.imooc.algorithmic_interview.Stack_Queue.Others._23;

/**
 * Description:Linear Compare each ListNode
 * Time Complexity: O(k*n) where k is len(lists) and n is the nodes number
 * Space Complexity: O(1)
 *
 * @date: 2019/2/20 11:56
 */
public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        ListNode dummyHead = new ListNode(-1);
        ListNode curNode = dummyHead;

        int finished = 0;//统计已经排序的数组元素
        for (ListNode node : lists)
            if (node == null)
                finished++;

        while (finished < lists.length) {//妙啊！

            ListNode nextNode = null;
            int nextIndex = -1;
            for (int i = 0; i < lists.length; i++)
                if (lists[i] != null) {
                    if (nextIndex == -1 || lists[i].val < nextNode.val) {
                        nextNode = lists[i];
                        nextIndex = i;
                    }
                }
            assert (nextIndex != -1 && nextNode != null);

            lists[nextIndex] = lists[nextIndex].next;
            if (lists[nextIndex] == null)
                finished++;

            curNode.next = nextNode;
            nextNode.next = null;
            curNode = curNode.next;
        }

        ListNode ret = dummyHead.next;
        return ret;
    }
}
