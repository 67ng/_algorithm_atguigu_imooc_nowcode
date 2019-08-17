package nowcode.zuoshenjinjie.kmp;

/**
 * Description:
 *
 * @author: NULL
 * @date: 2018/10/24 16:26
 */
public class KMP {
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;//长字符串指针
        int i2 = 0;//短字符串指针
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {//匹配相等就都往后移
                i1++;
                i2++;
            } else if (next[i2] == -1) {//第一个就不匹配,长串指针后移
                i1++;
            } else {
                i2 = next[i2];//遇到第一个不相等的元素时，短串指针移动当前元素的最大前后缀匹配的数组值
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }
//      最大前缀重复数组
    public static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;//若该元素等于最大匹配前缀的后一个元素，它的值比前一个加一，并跳到下一个
            } else if (cn > 0) {
                cn = next[cn];//跳到子串的最大匹配前缀的后一个元素
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));

    }
}
