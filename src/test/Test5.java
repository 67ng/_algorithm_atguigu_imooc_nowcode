package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:2019年今日头条春招一次笔试第二题
 *自动识别程序：1.连续三个或以上相同的字符，自取两个，即AAA→AA；
 * 2.AABB→AAB；
 * 3.AABBDDCC→AABDDC；
 *
 *
 * @date: 2019/3/16 11:02
 */
public class Test5 {
    public String proofreaderDemo(String s) {
        char[] chs = s.toCharArray();
        int len = chs.length;
        List<Character> list = new ArrayList<>();
        int l = 0;
        int r = 1;
        while (r < len) {
            if (chs[r] != chs[l] && r == l + 1) {//AB
                list.add(chs[l++]);
                r++;
            } else if (chs[r] == chs[l] && r == l + 1) {//AA
                r++;
            } else if (chs[r] == chs[l] && r == l + 2) {//AAAAAA
                l++;
                r++;
            } else if (chs[r] != chs[l] && r == l + 2) {//AAB
                r++;
            } else if (chs[r] == chs[r - 1] && r == l + 3) {//AABB
                list.add(chs[l]);
                list.add(chs[l + 1]);
                l = r;
                r++;
            } else if (chs[r] != chs[r - 1] && r == l + 3) {//AABD
                list.add(chs[l]);
                list.add(chs[l + 1]);
                list.add(chs[l + 2]);
                list.add(chs[l + 3]);
                l = r;
                r++;
            }
        }
        while (l < len) {
            list.add(chs[l]);
            l++;
        }
        char[] chars = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            chars[i] = list.get(i);
        }
        return Arrays.toString(chars);
    }

    public static void main(String[] args) {
        String s1 = "wooooooaaddcc";//wooaddc
        String s2 = "woooo";//woo
        String s3 = "hhee";//hhe
        System.out.println(new Test5().proofreaderDemo(s3));
        System.out.println(new Test5().proofreaderDemo(s2));
        System.out.println(new Test5().proofreaderDemo(s1));
    }
}
