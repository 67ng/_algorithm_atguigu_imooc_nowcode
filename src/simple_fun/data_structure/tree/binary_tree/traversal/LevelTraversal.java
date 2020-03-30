package simple_fun.data_structure.tree.binary_tree.traversal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    private List<List<Integer>> levels = new ArrayList<>();

    public void helper(Node node, int level) {
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        levels.get(level).add(node.value);

        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    //递归
    public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return
                levels;
        helper(root, 0);
        return levels;
    }


    //非递归
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
