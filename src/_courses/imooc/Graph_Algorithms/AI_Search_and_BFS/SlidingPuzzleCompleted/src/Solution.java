package _courses.imooc.Graph_Algorithms.AI_Search_and_BFS.SlidingPuzzleCompleted.src;/// Leetcode 773

import java.util.*;

/**
 * leetcode773滑动谜题
 */
public class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    //返回移动的步骤
    public ArrayList<String> slidingPuzzle(int[][] board) {
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();//字符串->步数
        ArrayList<String> pre = new ArrayList<String>();//存储前置状态
        String initalState = boardToString(board);
        if ("123450".equals(initalState)) {
            pre.add("123450");
            return pre;
        }
        queue.add(initalState);
        visited.put(initalState, 0);
        pre.add(0, initalState);
        while (!queue.isEmpty()) {
            String cur = queue.remove();

            ArrayList<String> nexts = getNexts(cur);

            for (String next : nexts)
                if (!visited.containsKey(next)) {
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);
                    pre.add(visited.get(cur) + 1, cur);
                    if (next.equals("123450")) {
                        pre.add(visited.get(cur) + 2, "123450");
                        return pre;
                    }
                }
        }
        return null;
    }

    //得到下一个状态
    private ArrayList<String> getNexts(String s) {
        int[][] cur = stringToBoard(s);
        int zero;
        for (zero = 0; zero < 6; zero++)//找到0的位置
            if (cur[zero / 3][zero % 3] == 0)
                break;

        ArrayList<String> res = new ArrayList<>();
        int zx = zero / 3, zy = zero % 3;
        for (int d = 0; d < 4; d++) {
            int nextx = zx + dirs[d][0], nexty = zy + dirs[d][1];
            if (inArea(nextx, nexty)) {
                swap(cur, zx, zy, nextx, nexty);
                res.add(boardToString(cur));
                swap(cur, zx, zy, nextx, nexty);// 不要忘记交换回来
            }
        }
        return res;
    }

    private boolean inArea(int x, int y) {

        return x >= 0 && x < 2 && y >= 0 && y < 3;
    }

    private void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int t = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = t;
    }

    //将字符数组转成字符串
    private String boardToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 3; j++)
                sb.append(board[i][j]);
        return sb.toString();
    }

    //将字符串转成字符数组
    private int[][] stringToBoard(String s) {
        int[][] board = new int[2][3];
        for (int i = 0; i < 6; i++)
            board[i / 3][i % 3] = s.charAt(i) - '0';
        return board;
    }

    public String step(int[][] board) {
        ArrayList<String> pre = (new Solution()).slidingPuzzle(board);
//        System.out.println(pre);
        String end = "123450";
        String start = boardToString(board);
        ArrayList<String> res = new ArrayList<>();
        if (pre == null) return "无法实现移动";

        String cur = end;
        while (!cur.equals(start)) {
            res.add(cur);
            cur = pre.get(pre.indexOf(cur) - 1);
        }
        res.add(start);
        Collections.reverse(res);
        return res.toString();
    }

    public static void main(String[] args) {
        int[][] board1 = {{4, 1, 2}, {5, 0, 3}};
        int[][] board2 = {{3,2,4},{1,5,0}};
        int[][] board3 = {{1,2,3},{4,5,0}};
        int[][] board4 = {{1,2,3},{5,4,0}};
        System.out.println((new Solution()).step(board1));
        System.out.println((new Solution()).step(board2));
        System.out.println((new Solution()).step(board3));
        System.out.println((new Solution()).step(board4));
    }
}
