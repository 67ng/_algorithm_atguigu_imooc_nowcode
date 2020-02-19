package _courses.imooc.Graph_Algorithms.AI_Search_and_BFS.States.src;/// Leetcode 752

import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
/**
 *
 * 密码锁从"0000"开始到达target，经过的最少步数。
 * 其中deadends中的数不能出现
 *
 * */
class Solution {
    public int openLock(String[] deadends, String target) {

        HashSet<String> deadset = new HashSet<>();
        for (String s : deadends)
            deadset.add(s);

        if (deadset.contains(target)) return -1;
        if (deadset.contains("0000")) return -1;
        if (target.equals("0000")) return 0;

        // BFS
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();//string->步数
        queue.add("0000");
        visited.put("0000", 0);
        while (!queue.isEmpty()) {
            String curs = queue.remove();
            char[] curarray = curs.toCharArray();

            ArrayList<String> nexts = new ArrayList<>();//后续的8种可能状态
            for (int i = 0; i < 4; i++) {
                char o = curarray[i];
                curarray[i] = Character.forDigit(
                        (curarray[i] - '0' + 1) % 10, 10);//妙啊！
                nexts.add(new String(curarray));
                curarray[i] = o;

                curarray[i] = Character.forDigit(
                        (curarray[i] - '0' + 9) % 10, 10);
                nexts.add(new String(curarray));
                curarray[i] = o;
            }

            for (String next : nexts)
                if (!deadset.contains(next) &&
                        !visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(curs) + 1);
                    if (next.equals(target)) return visited.get(next);
                }
        }

        return -1;
    }
}
