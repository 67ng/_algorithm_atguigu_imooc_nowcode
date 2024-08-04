package algorithm.DP.linear_dp;

/**
 * @Name: 72.编辑距离
 * @Description: 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * 你可以对一个单词进行如下三种操作：
 * 1.插入一个字符
 * 2.删除一个字符
 * 3.替换一个字符
 * @Linked: https://leetcode-cn.com/problems/edit-distance/
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        // 有一个字符串为空串
        if (n * m == 0)
            return n + m;
        // DP 数组，用 D[i][j] 表示 A 的前 i 个字母和 B 的前 j 个字母之间的编辑距离。
        int[][] D = new int[n + 1][m + 1];
        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) //字符串 A 为空，如从空转换到 ro，显然编辑距离为字符串 B 的长度，这里是 2；
            D[i][0] = i;
        for (int j = 0; j < m + 1; j++) //同上理
            D[0][j] = j;

//        状态转移方程：
//        若 A 和 B 的最后一个字母相同：
//        D[i][j] =min(D[i][j−1]+1,D[i−1][j]+1,D[i−1][j−1])
//        若 A 和 B 的最后一个字母不同：
//        D[i][j]=1+min(D[i][j−1],D[i−1][j],D[i−1][j−1])

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1))
                    left_down += 1;
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

}
