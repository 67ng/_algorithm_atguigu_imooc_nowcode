package imooc.algorithmic_interview.Array.Others._438;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @date: 2019/1/27 19:25
 */
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        char[] chs = s.toCharArray();
        char[] chp = p.toCharArray();
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length())
            return res;

        assert (p.length() > 0);

        int[] freq_p = new int[26];//p里的字符频数统计数组
        for (char c : chp)
            freq_p[c - 'a'] += 1;

        int[] freq_s = new int[26];//滑动窗口里字符频数统计数组
        int l = 0, r = -1; // Sliding window: s[l, r]
        while (r + 1 < s.length()) {
            r++;
            freq_s[chs[r] - 'a']++;
            if (r - l + 1 > p.length())
                freq_s[chs[l++] - 'a']--;

            if (r - l + 1 == p.length() && same(freq_s,freq_p))
                res.add(l);
        }
        return res;
    }

    private boolean same(int[] freq_s, int[] freq_p) {//判断频数是否匹配
        for (int i = 0; i < 26; i++)
            if (freq_s[i] != freq_p[i])
                return false;
        return true;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(new Solution().findAnagrams(s,p).toString());
    }
}