package simple_fun.data_structure.tree.multiway_tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 前序遍历
 * @Author: caffebaby
 * @Date: 2020/3/28
 */
public class PreOrder {

    // Definition for a Node.
    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    private List<Integer> list = new ArrayList<>();

    //递归
    public List<Integer> preorder(Node root) {
        if (root == null)
            return new ArrayList<>();
        list.add(root.val);
        if (root.children != null)
            for (Node node : root.children)
                preorder(node);

        return list;
    }
    //非递归，迭代版本
    public List<Integer> preorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.empty()) {
            root = stack.pop();
            list.add(root.val);
            for (int i = root.children.size() - 1; i >= 0; i--)//注意要从后往前遍历
                stack.add(root.children.get(i));
        }

        return list;
    }
}
