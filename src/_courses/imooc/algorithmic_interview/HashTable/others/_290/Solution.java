package _courses.imooc.algorithmic_interview.HashTable.others._290;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 *
 * @date: 2019/2/5 12:05
 */
public class Solution {
    public static boolean wordPattern(String pattern, String str) {
        String[] words = split(str);
        if (pattern.length() != words.length)
            return false;

        Map<Character, String> map1 = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++)
            if (!map1.containsKey(pattern.charAt(i))) {
                if (map2.containsKey(words[i]))
                    return false;
                map1.put(pattern.charAt(i), words[i]);
                map2.put(words[i], pattern.charAt(i));
            } else {
                String s = map1.get(pattern.charAt(i));
                if (!s.equals(words[i]))
                    return false;
            }
        return true;
    }

    private static String[] split(String s) {//将str划分成单个字符串
        ArrayList<String> res = new ArrayList<>();
        int start = 0;
        for (int i = start + 1; i <= s.length(); )
            if (i == s.length() || s.charAt(i) == ' ') {
                res.add(s.substring(start, i));//substring前闭后开
                start = i + 1;
                i = start + 1;
            } else
                i++;
        return res.toArray(new String[res.size()]);
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String pattern2 = "aaaa";
        String str = "dog cat cat dog";
        System.out.println(Solution.wordPattern(pattern, str));
    }
}
