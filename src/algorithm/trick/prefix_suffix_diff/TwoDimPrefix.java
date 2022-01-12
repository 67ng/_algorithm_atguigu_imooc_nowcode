package algorithm.trick.prefix_suffix_diff;

/**
 * @Description: 二维前缀和
 * S[i, j] = 第i行j列格子左上部分所有元素的和
 * 以(x1, y1)为左上角，(x2, y2)为右下角的子矩阵的和为：
 * S[x2, y2] - S[x1 - 1, y2] - S[x2, y1 - 1] + S[x1 - 1, y1 - 1]
 * @Date: 2022/1/12
 */

public class TwoDimPrefix {
    private int[][] S;

    public TwoDimPrefix(int[][] nums) {
        S = new int[nums.length][nums[0].length];
        // 单独求左边界和上边界的值
        S[0][0] = nums[0][0];
        for (int i = 1; i < nums.length; i++) S[i][0] = S[i - 1][0] + nums[i][0];
        for (int i = 1; i < nums[0].length; i++) S[0][i] = S[0][i - 1] + nums[0][i];

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < nums[0].length; j++) {
                S[i][j] = S[i - 1][j] + S[i][j - 1] - S[i - 1][j - 1] + nums[i][j];
            }
        }
    }

    public int getAreaVal(int x1, int y1, int x2, int y2) {
        return S[x2][y2] - S[x1 - 1][y2] - S[x2][y1 - 1] + S[x1 - 1][y1 - 1];
    }

}
