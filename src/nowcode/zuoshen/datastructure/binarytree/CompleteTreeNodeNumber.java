package nowcode.zuoshen.datastructure.binarytree;

/**
 * Description:已知一棵完全二叉树，求其节点的个数
 *
 * @author: 黑山老妖
 * @date: 2018/10/17 12:25
 */
             //时间复杂度:O((logN)^2)
public class CompleteTreeNodeNumber {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){this.value = data;}
    }
//求节点总数
    public static int nodeNum(Node head){
        if (head == null){return 0;}
        return bs(head, 1, mostLeftLevel(head, 1));
    }
//每一层向下递归，一次求约一半的节点数量
    public static int bs(Node node, int level, int h){
        if (level == h){return 1;}
//       右节点的层数比上一个节点大一，故(level + 1)再mostLeftLevel遍历后得的值为h-1(左可能不满)或者h(左满)，
//       若和总高h相等说明左子树是满的，就进入右子树再迭代遍历，否则进入左子树迭代(右子树必满)
        if (mostLeftLevel(node.right, level + 1) == h){
//            1 << (h - level)为左满子树的节点数量和
            return 1 << (h - level) + bs(node.right, level + 1, h);
        }else {
//            1 << (h - level -1)为右满子树的节点数量和
            return 1 << (h - level -1) + bs(node.left, level + 1, h);
        }
    }

//    到达最左子节点的层数
    public static int mostLeftLevel(Node node, int level){
        while (node != null){
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));
    }
}
