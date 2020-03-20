package simple_fun.algorithm.math.bit_operation;

/**
 * Description:给定一个数组，求子数组的最大异或和。
 * 一个数组的异或和为，数组中所有的数异或起来的结果。
 *
 * @author: NULL
 * @date: 2018/11/1 10:05
 */
public class Max_EOR {
    //暴力算法，时间复杂度O(N^2)
    public static int getMaxEOR1(int[] arr) {
        int max = Integer.MIN_VALUE;
        int[] dp = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];//0....i的异或
            max = Math.max(eor, max);
            for (int start = 1; start <= i; start++) {
                int startToI = eor ^ dp[start - 1];//start.....i的异或
                max = Math.max(startToI, max);
            }
            dp[i] = eor;
        }
        return max;
    }

    //  定义前缀树
    public static class Node {
        public Node[] nexts = new Node[2];
    }

    public static class NumTrie {
        public Node head = new Node();

        public void add(int num) {//num是0....i的异或结果
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = ((num >> move) & 1);//取出二进制表示从高位到低位的每一位数
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        public int maxXor(int num) {//返回0......i中异或最好的结果值
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;
                int best = move == 31 ? path : (path ^ 1);//期待想选的路
                best = cur.nexts[best] != null ? best : (best ^ 1);//实际选择的路
                res |= (path ^ best) << move;//设置答案的每一位（若A^B = C，则A = C^B）
                cur = cur.nexts[best];
            }
            return res;
        }
    }

    //    时间复杂度O(N)
    public static int maxXorSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(max, numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }

}
