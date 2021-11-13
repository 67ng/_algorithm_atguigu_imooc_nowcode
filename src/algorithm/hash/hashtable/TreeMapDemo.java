package algorithm.hash.hashtable;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * @Description: TreeMap的API使用
 * @Date: 2021/11/13
 */

public class TreeMapDemo {
    private static TreeMap<Integer, String> treeMap;

    public TreeMapDemo() {
        treeMap = new TreeMap<>();
        treeMap.put(2, "two");
        treeMap.put(1, "one");
        treeMap.put(3, "three");
        treeMap.put(6, "six");
        treeMap.put(5, "five");
    }

    // floorKey <= key
    private static Integer floorKey(int key) {
        if (treeMap.firstKey() > key) return null;
        return treeMap.floorKey(key);
    }

    // lowerKey < key
    private static Integer lowerKey(int key) {
        if (treeMap.firstKey() >= key) return null;
        return treeMap.lowerKey(key);
    }

    // ceilingKey >= key
    private static Integer ceilingKey(int key) {
        if (treeMap.lastKey() < key) return null;
        return treeMap.ceilingKey(key);
    }

    // higherKey > key
    private static Integer higherKey(int key) {
        if (treeMap.lastKey() <= key) return null;
        return treeMap.higherKey(key);
    }

    // true 包含边界
    private static Map<Integer, String> subMap(int l, int r) {
        return treeMap.subMap(l, true, r, false);
    }

    public static void main(String[] args) {
        TreeMapDemo t = new TreeMapDemo();
        System.out.println(treeMap.floorKey(0));
        System.out.println(treeMap.lowerKey(1));
    }


}
