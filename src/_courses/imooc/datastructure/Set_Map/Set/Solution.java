package _courses.imooc.datastructure.Set_Map.Set;

/**
 * Description:Leetcode 804. Unique Morse Code Words
 *
 *  https://leetcode.com/problems/unique-morse-code-words/description/
 *
 * @date: 2018/11/27 10:57
 */


import java.util.TreeSet;

public class Solution {

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        TreeSet<String> set = new TreeSet<>();
        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++)
                res.append(codes[word.charAt(i) - 'a']);

            set.add(res.toString());
        }

        return set.size();
    }
}
