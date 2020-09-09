package algorithm.math.permutation_combination;

/**
 * @Description: 杨辉三角
 * @Author: matreeix
 * @Date: 2020/9/1
 */

public class YanghuiTriangle {

    public static void main(String[] args) {
        /**
         * 8行8列的杨辉三角
         */
        int row = 6;//行数
        int[][] yanghui = new int[row][row];//6行6列数组

        for (int i = 0; i < row; i++) {//行
            for (int j = 0; j <= i; j++) {//列
                if (j == 0 || j == i) {
                    yanghui[i][j] = 1;

                } else {
                    yanghui[i][j] = yanghui[i - 1][j - 1] + yanghui[i - 1][j];
                }
//              System.out.print(yanghui[i][j]+" ");
            }
//          System.out.println();
        }
//等腰输出处理
        for (int i = 0; i < row; i++) {
            int num = row - i;
            for (int j = 0; j <= num; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k <= i; k++) {
                System.out.print(yanghui[i][k] + " ");
            }
            System.out.println();
        }

    }
}
