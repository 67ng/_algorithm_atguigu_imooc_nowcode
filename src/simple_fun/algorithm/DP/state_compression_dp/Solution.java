package simple_fun.algorithm.DP.state_compression_dp;

/**
 * @Description: 状态压缩动态规划的问题汇总
 * @Author: matreeix
 * @Date: 2020/4/11
 */
public class Solution {

//    /**
//     * @Name: 464.我能赢吗
//     * @Description: 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和达到 100 的玩家，即为胜者。
//     * 如果我们将游戏规则改为 “玩家不能重复使用整数” 呢？
//     * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
//     * 给定一个整数 maxChoosableInteger （整数池中可选择的最大数）和另一个整数 desiredTotal（累计和），判断先出手的玩家是否能稳赢（假设两位玩家游戏时都表现最佳）？
//     * 你可以假设 maxChoosableInteger 不会大于 20， desiredTotal 不会大于 300。
//     *
//     * @Linked: https://leetcode-cn.com/problems/can-i-win/
//     * */
//
//    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
//
//        return false;
//    }
    /**
     * @Name: 526.优美的排列
     * @Description: 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，
     * 使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
     * 1.第 i 位的数字能被 i 整除
     * 2.i 能被第 i 位上的数字整除
     * 现在给定一个整数 N，请问可以构造多少个优美的排列？
     * @Linked: https://leetcode-cn.com/problems/beautiful-arrangement/
     */

    //回溯法
    //时间复杂度：O(k)。k 是有效排列的数目。
    //空间复杂度：O(n)。使用了大小为 n 的数组 visited。递归树的深度最多为 n，这里 n 是给定的整数 n。
    int count = 0;
    public int countArrangement(int N) {
        boolean[] visited = new boolean[N + 1];
        calculate(N, 1, visited);
        return count;
    }

    public void calculate(int N, int pos, boolean[] visited) {
        if (pos > N)
            count++;
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                calculate(N, pos + 1, visited);
                visited[i] = false;
            }
        }
    }

    //暴力法
    class Solution2 {
        int count = 0;
        public int countArrangement(int N) {
            int[] nums = new int[N];
            for (int i = 1; i <= N; i++)
                nums[i - 1] = i;
            permute(nums, 0);
            return count;
        }
        public void permute(int[] nums, int l) {
            if (l == nums.length) {
                count++;
            }
            for (int i = l; i < nums.length; i++) {
                swap(nums, i, l);
                if (nums[l] % (l + 1) == 0 || (l + 1) % nums[l] == 0)
                    permute(nums, l + 1);
                swap(nums, i, l);
            }
        }
        public void swap(int[] nums, int x, int y) {
            int temp = nums[x];
            nums[x] = nums[y];
            nums[y] = temp;
        }
    }

}
