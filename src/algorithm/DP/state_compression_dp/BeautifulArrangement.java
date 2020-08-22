package algorithm.DP.state_compression_dp;

/**
 * @Name: 526.优美的排列
 * @Description: 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，
 * 使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 * 1.第 i 位的数字能被 i 整除
 * 2.i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * @Linked: https://leetcode-cn.com/problems/beautiful-arrangement/
 */

public class BeautifulArrangement {
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
    public int countArrangement2(int N) {
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
