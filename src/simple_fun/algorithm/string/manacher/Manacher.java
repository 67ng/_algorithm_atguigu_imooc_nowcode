package simple_fun.algorithm.string.manacher;

/**
 * Description:查找一个字符串的最长回文子串的线性算法
 * 时间复杂度O(N)
 *
 * @author: NULL
 * @date: 2018/10/26 19:06
 */
public class Manacher {
    //添加虚轴，排除奇偶差异
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];//i % 2 == 0 ? '#' : charArr[index++]
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];//最大回文半径数组
        int C = -1;//回文中心指针
        int R = -1;//回文右边界指针
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;//i在某个回文里遍历时生成回文数组
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {//当i的回文半径超过R时，更新回文半径指针和中心指针
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }
}
