package algorithm.hash.hashtable;

import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @Description: TreeMap的API使用
 * @Date: 2021/11/13
 */

public class TreeMapDemo {

    public static void main(String[] args) {
        // creating maps
        TreeMap<Integer, String> treemap = new TreeMap<Integer, String>();
        NavigableMap<Integer, String> treemapincl = new TreeMap<Integer, String>();

        // populating tree map
        treemap.put(2, "two");
        treemap.put(1, "one");
        treemap.put(3, "three");
        treemap.put(6, "six");
        treemap.put(5, "five");

        // 获取给定key的上界、下界值(包含key)
        int floor = treemap.floorKey(4);
        int ceiling = treemap.ceilingKey(4);
        System.out.println(floor);
        System.out.println(ceiling);

        System.out.println("Getting a portion of the map");
        // 获取给定范围有序的子Map,true表示包含边界
        treemapincl = treemap.subMap(1, true, 3, false);
        System.out.println("Sub map values: " + treemapincl);
    }


}
