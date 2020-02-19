package _courses.imooc.algorithmic_interview.Stack_Queue.Others._23;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Description: Using Priority Queue to compare each ListNode
 * Time Complexity: O(nlogk) where k is len(lists) and n is the nodes number
 * Space Complexity: O(1)
 *
 * @date: 2019/2/20 12:31
 */
public class Solution2 {
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

        PriorityQueue<ListNode> q = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode node1, ListNode node2) {
                return node1.val - node2.val;
            }
        });
        for (ListNode node : lists)
            if (node != null)
                q.add(node);

        while (!q.isEmpty()) {

            ListNode nextNode = q.peek();
            q.poll();

            curNode.next = nextNode;
            if (nextNode.next != null)
                q.add(nextNode.next);

            nextNode.next = null;
            curNode = curNode.next;
        }

        ListNode ret = dummyHead.next;
        return ret;

    }
}
