package test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Description:字节跳动第六次笔试
 *
 * @author: NULL
 * @date: 2018/11/5 19:09
 */
public class Test1 {
    //定义比较器
    public static class Mycompare implements Comparable<Mycompare> {
        int num;

        public Mycompare(int a) {
            this.num = a;
        }

        @Override
        public int compareTo(Mycompare b) {
            return num - b.num;
        }
    }

    public static void main(String[] args) {
        int N = 7;
        int a = 10;
        int b = 4;
        int sum = 0;
        int[] arr = {20, 30, 40, 69, 17, 28, 10};
        PriorityQueue<Mycompare> life = new PriorityQueue<>();
        PriorityQueue<Mycompare> life2 = new PriorityQueue<>();
        for (int i = 0; i < N / 2; i++) {
            life.add(new Mycompare(arr[2 * i]));
            life2.add(new Mycompare(arr[2 * i + 1]));
        }
        while (life.peek().num > 0 && life2.peek().num > 0) {
            if (life.peek().num > life2.peek().num) {
                for (Mycompare m : life) {
                    life2.add(new Mycompare(life.peek().num - b));
                    life.poll();
                }
                life2.add(new Mycompare(life2.poll().num - a + b));
                sum++;
            } else if (life2.peek().num > life.peek().num) {
                for (Mycompare m : life2) {//ConcurrentModificationException并发修改异常
                    life.add(new Mycompare(life2.peek().num - b));
                    life2.poll();
                }
                life.add(new Mycompare(life.poll().num - a + b));
                sum++;
            }
        }
        System.out.println(sum);

    }
}
