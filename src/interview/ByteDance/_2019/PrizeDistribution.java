package interview.ByteDance._2019;

import java.util.Arrays;

/**
 * @Description: 奖品分配(类似leetcode135)
 * <p>
 * 有n个人参加编程比赛，比赛结束后每个人都得到一个分数；现在所有人排成一圈（第一个和第n个相邻）领取奖品，要求：
 * <p>
 * 1、如果某个人的分数比左右的人高，那么奖品数量也要比左右的人多；
 * <p>
 * 2、每个人至少得到一个奖品；
 * <p>
 * 问最少应该准备多少个奖品。
 * @Author: caffebaby
 * @Date: 2020/6/6
 */
public class PrizeDistribution {

    public int prizeDistribution(int[] score) {
        int len = score.length;
        int[] candies = new int[len];
        Arrays.fill(candies, 1);
        for (int i = 1; i < len + 1; i++) {//从左往右扫
            if (score[i % len] > score[(i - 1) % len]) {
                candies[i % len] = candies[(i - 1) % len] + 1;
            }
        }
        int sum = candies[len - 1];
        for (int i = len - 2; i >= 0; i--) {//从右往左扫
            if (score[i % len] > score[(i + 1) % len]) {
                candies[i % len] = Math.max(candies[i % len], candies[(i + 1) % len]+1);//扫过一次了，可能不需要更新
            }
            sum += candies[i % len];
        }
        return sum;

    }
}
