package nowcode.zuoshenjinjie.dp;

/**
 * Description:
 *
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走
 * 每张纸牌，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右
 * 的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 *
 *https://blog.csdn.net/qwe_lingkun/article/details/47305035
 * @author: NULL
 * @date: 2018/11/1 17:09
 */
public class CardsInLine {
    //暴力解法
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
    }

    public static int f(int[] arr, int i, int j) {//F(arr, l , r)表示对于数组arr，元素从l到r，先拿可以达到的最大分数
        if (i == j) {//只有一个数的情况
            return arr[i];
        }
        return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
    }

    public static int s(int[] arr, int i, int j) {//S(arr, l, r)表示对于数组arr, 元素从l到r，后拿可以达到的最大分数
        if (i == j) {
            return 0;
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));//后拿让先拿在接下来的递推中取较差情况就可以了
    }

    //动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];//先拿的解空间矩阵
        int[][] s = new int[arr.length][arr.length];//后拿的解空间矩阵
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {//i <= j,因为i在前，j在后
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 4, 3, 1};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
