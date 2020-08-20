package algorithm.math.probability;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description: Knuth洗牌算法
 * @Author: matreeix
 * @Date: 2020/8/20
 */

public class KnuthShuffle {

    //Knuth-shuffle算法
    public static int[] shuffle(int[] arr) {
        Random rnd = new Random();
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            swap(arr, i, j);
        }
        return arr;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //JDK源码的shuffle算法
    public static List<Integer> shuffleByJDK(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        return list;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }
}
