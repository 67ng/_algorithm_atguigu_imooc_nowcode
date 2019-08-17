package nowcode.zuoshenjinjie.kmp;


import static nowcode.zuoshenjinjie.kmp.KMP.getIndexOf;

/**
 * Description:树的匹配问题
 *
 * @author: NULL
 * @date: 2018/10/24 16:37
 */
public class KMP_T1SubtreeEqualsT2 {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }
//    树的前序遍历序列化
    public static String serialByPre(Node head){
        if (head == null){return "#!";}
        String res = head.value + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }
//   用KMP算法判定两字符串是否匹配，匹配则树也匹配
    public static boolean isSubtree(Node t1,Node t2){
        String t1Str = serialByPre(t1);
        String t2Str = serialByPre(t2);
        return getIndexOf(t1Str,t2Str) != -1;
    }

    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.right = new Node(8);
        t1.left.right.left = new Node(9);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.right = new Node(8);
        t2.right = new Node(5);
        t2.right.left = new Node(9);

        System.out.println(isSubtree(t1, t2));

    }


}
