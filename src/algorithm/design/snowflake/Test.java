package algorithm.design.snowflake;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static int getRepeatNumber(long[] arr) {//得到重复数字的个数
        int len = arr.length;
        Map<Long, Long> map = new HashMap<>();
        for (long num : arr) {
            if (map.get(num) == null )
                map.put(num, 1L);
            else
                map.put(num, 1L + map.get(num));
        }
        return len - map.size();
    }

    public static void main(String[] args) {
        SnowFlakeUtils snowFlake = new SnowFlakeUtils(9, 9);
        long[] arr = new long[1_048_576];
        for (int i = 0; i < (1 << 20); i++) {
            arr[i] = snowFlake.nextId();
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(getRepeatNumber(arr));
    }
}
