package simple_fun.algorithm.string.kmp;

/**
 * Description:京东笔试题
 * 在一个字符串后面添加字符，使新字符串让原字符串可以重复两次且最短
 *
 * @author: NULL
 * @date: 2018/10/24 16:36
 */
public class KMP_ShortestHaveTwice {
    public static String answer(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] chas = str.toCharArray();
        if (chas.length == 1) {
            return str + str;
        }
        if (chas.length == 2) {
            return chas[0] == chas[1] ? (str + String.valueOf(chas[0])) : (str + str);
        }
        int endNext = endNextLength(chas);
        return str + str.substring(endNext);//从endNext开始包括它直到最后
    }

    public static int endNextLength(char[] chas) {
        int[] next = new int[chas.length + 1];//length加一是因为原字符串要从后面添加
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (chas[i - 1] == chas[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next[next.length - 1];
    }

    public static void main(String[] args) {
        String test1 = "a";
        System.out.println(answer(test1));

        String test2 = "aa";
        System.out.println(answer(test2));

        String test3 = "ab";
        System.out.println(answer(test3));

        String test4 = "abcdabcd";
        System.out.println(answer(test4));

        String test5 = "abracadabra";
        System.out.println(answer(test5));

    }
}
