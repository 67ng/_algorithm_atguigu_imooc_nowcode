package simple_fun.data_structure.tree.multiway_tree.traversal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 后序遍历
 * @Author: 67ng
 * @Date: 2020/3/28
 */
public class PostOrder {
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

    List<Integer> list = new ArrayList<>();

    //递归
    public List<Integer> postorder(Node root) {
        if (root == null)
            return list;

        for (Node node : root.children)
            postorder(node);

        list.add(root.val);

        return list;
    }

    //    非递归
    public List<Integer> postorder2(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;

        Stack<Node> stack = new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            root = stack.pop();
            list.add(root.val);
            for (Node node : root.children)//注意和前序遍历的区别
                stack.add(node);
        }
        Collections.reverse(list);//
        return list;
    }


}
