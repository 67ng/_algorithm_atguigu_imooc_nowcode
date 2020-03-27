package simple_fun.data_structure.tree.binary_tree.traversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description:
 * @Author: 67ng
 * @Date: 2020/3/27
 */
public class LevelTraversal {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void levelTraversal(Node head) {
        if (head == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (queue.size() != 0) {
            head = queue.poll();
            System.out.print(head.value + " ");

            if (head.left != null)
                queue.offer(head.left);
            if (head.right != null)
                queue.offer(head.right);
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
//        head.left.left = new Node(4);
        head.left.right = new Node(5);
//        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTraversal(head);
    }
}
