package imooc.algorithmic_interview.HashTable.others._49;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @date: 2019/2/3 23:04
 */
public class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String s : strs) {
            String key = getKey(s);
            if (!map.containsKey(key)) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(s);
                map.put(key, arrayList);
            } else {
                map.get(key).add(s);
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String key : map.keySet())
            res.add(map.get(key));
        return res;
    }

    private String getKey(String s) {//存储字符串的字符频率
        int[] freq = new int[26];
        for (char c : s.toCharArray())
            freq[c - 'a']++;
        String key = "";
        for (int num : freq)
            key += num + "#";
        return key;
    }

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(new Solution2().groupAnagrams(strs).toString());
    }

}
