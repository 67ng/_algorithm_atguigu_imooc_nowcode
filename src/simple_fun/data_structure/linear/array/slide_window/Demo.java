package simple_fun.data_structure.linear.array.slide_window;

/**
 * @Description: 滑动窗口的标准模板
 * @Author: matreeix
 * @Date: 2020/6/28
 */

import java.util.Map;

/**
 * 作者：labuladong
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/hua-dong-chuang-kou-tong-yong-si-xiang-jie-jue-zi-/
 * 来源：力扣（LeetCode）
 */
public class Demo {
    /* 滑动窗口算法框架 */
    public void slidingWindow(String s, String t) {//假设s、t只含有小写字母
        int[] need = new int[26];
        int[] window = new int[26];
        for (char c : t.toCharArray())
            need[c - 'a']++;

        int left = 0, right = 0;
        int valid = 0;
        while (right < s.length()) {
            // c 是将移入窗口的字符
            char c = s.charAt(right);
            // 右移窗口
            right++;
            // 进行窗口内数据的一系列更新
            //...

            /*** debug 输出的位置 ***/
//            System.out.println(%d\n, %d)\n", left, right);
            /********************/

            // 判断左侧窗口是否要收缩,举个栗子
            boolean window_needs_shrink = true;
            while (window_needs_shrink) {
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                //...
            }
        }
    }
}
