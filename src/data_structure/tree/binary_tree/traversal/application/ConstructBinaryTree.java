package data_structure.tree.binary_tree.traversal.application;

/**
 * @Description: 已知中序遍历序列和另外一个序列可以求第三个序列
 * 1. 前序的第一个是 root，后序的最后一个是 root。
 * 2. 先确定根节点，然后根据中序遍历，在根左边的为左子树，根右边的为右子树。
 * 3. 对于每一个子树可以看成一个全新的树，仍然遵循上面的规律。
 * @Date: 2021/12/10
 */

public class ConstructBinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
     * 注意:你可以假设树中没有重复的元素。
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return helper1(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper1(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper1(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper1(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }


    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * 根据一棵树的中序遍历与后序遍历构造二叉树。
     * 注意:你可以假设树中没有重复的元素。
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        return helper2(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode helper2(int[] inorder, int inS, int inE, int[] postorder, int posS, int posE) {
        if (inS > inE || posS > posE) return null;
        TreeNode root = new TreeNode(postorder[posE]);
        int rootI = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                rootI = i;
                break;
            }
        }
        root.left = helper2(inorder, inS, rootI - 1, postorder, posS, posS + rootI - inS - 1);
        root.right = helper2(inorder, rootI + 1, inE, postorder, posS + rootI - inS, posE - 1);

        return root;
    }
}
