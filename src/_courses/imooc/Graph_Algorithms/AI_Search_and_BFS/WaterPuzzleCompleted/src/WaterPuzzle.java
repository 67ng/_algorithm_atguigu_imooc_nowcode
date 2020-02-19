package _courses.imooc.Graph_Algorithms.AI_Search_and_BFS.WaterPuzzleCompleted.src;

import java.util.*;
import java.util.ArrayList;
/**
 *
 * 用容积为5,3的两个桶子，量出4的水
 * */
public class WaterPuzzle {

    private int[] pre;
    private int end = -1;

    public WaterPuzzle(){

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[100];
        pre = new int[100];//记录倒水的步骤

        queue.add(0);
        visited[0] = true;
        while (!queue.isEmpty()){
            int cur = queue.remove();
            int a = cur / 10, b = cur % 10;
            // max a = 5, max b = 3,状态压缩：用一个两位数表示两个桶的一个状态,妙啊！

            ArrayList<Integer> nexts = new ArrayList<>();
            nexts.add(5 * 10 + b);//给a桶灌满水
            nexts.add(a * 10 + 3);//给b桶灌满水
            nexts.add(a * 10 + 0);//b桶倒空
            nexts.add(0 * 10 + b);//a桶倒空

            int x = Math.min(a, 3 - b);//最多能倒的水
            nexts.add((a - x) * 10 + (b + x));//a桶中的水倒进b桶

            int y = Math.min(b, 5 - a);
            nexts.add((a + y) * 10 + (b - y));//b桶中的水倒进a桶

            for(int next: nexts)
                if(!visited[next]){
                    queue.add(next);
                    visited[next] = true;
                    pre[next] = cur;

                    if(next / 10 == 4 || next % 10 == 4) {//后一种情况其实永远达不到
                        end = next;
                        return;
                    }
                }
        }
    }

    public Iterable<Integer> result(){

        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);
        Collections.reverse(res);
        System.out.println(Arrays.toString(pre));
        return res;
    }

    public static void main(String[] args){

        System.out.println((new WaterPuzzle()).result());
    }
}
