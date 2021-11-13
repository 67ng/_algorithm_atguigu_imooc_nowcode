package algorithm.hash.hashtable;

import java.util.*;
import java.util.Map.Entry;

/**
 * Description: HashMap的API使用
 * @Linked: https://www.runoob.com/java/java-hashmap.html
 *
 * @author: matreeix
 * @date: 2018/10/17 22:48
 */
public class HashMapDemo {

    /**
     * merge() 方法会先判断指定的 key 是否存在，如果不存在，则添加键值对到 hashMap 中。
     * 如果 key 对应的 value 不存在，则返回该 value 值，如果存在，则返回通过 remappingFunction 重新计算后的值。
     * */
    private static void merge(){
        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        int returnedValue = prices.merge("Shirt", 100, Integer::sum);
        System.out.println("Price of Shirt: " + returnedValue);
        // 输出更新后的 HashMap
        System.out.println("Updated HashMap: " + prices);
    }
    /**
     * putIfAbsent() 方法会先判断指定的键（key）是否存在，不存在则将键/值对插入到 HashMap 中。
     * 如果所指定的 key 已经在 HashMap 中存在，返回和这个 key 值对应的 value, 如果所指定的 key 不在 HashMap 中存在，则返回 null。
     *
     * */
    private static void putIfAbsent(){
        HashMap<Integer, String> sites = new HashMap<>();
        sites.put(1, "Google");
        sites.put(2, "Runoob");
        sites.put(3, "Taobao");
        System.out.println("sites HashMap: " + sites);

        // HashMap 不存在该key
        sites.putIfAbsent(4, "Weibo");
        // HashMap 中存在 Key
        sites.putIfAbsent(2, "Wiki");
        System.out.println("Updated Languages: " + sites);
    }

    /**
     * compute() 方法对 hashMap 中指定 key 的值进行重新计算。
     * 如果 key 对应的 value 不存在，则返回该 null，如果存在，则返回通过 remappingFunction 重新计算后的值。
     *
     * */
    private static void compute(){
        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 重新计算鞋子打了10%折扣后的值
        int newPrice = prices.compute("Shoes", (key, value) -> value - value * 10/100);
        System.out.println("Discounted Price of Shoes: " + newPrice);
        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
    }

    /**
     * computeIfAbsent() 方法对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中。
     * 如果 key 对应的 value 不存在，则使用获取 remappingFunction 重新计算后的值，并保存为该 key 的 value，否则返回 value。
     *
     * */
    private static void computeIfAbsent(){
        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 计算 Shirt 的值
        int shirtPrice = prices.computeIfAbsent("Shirt", key -> 280);
        System.out.println("Price of Shirt: " + shirtPrice);
        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
    }

    /**
     * computeIfPresent() 方法对 hashMap 中指定 key 的值进行重新计算，前提是该 key 存在于 hashMap 中。
     * 如果 key 对应的 value 不存在，则返回该 null，如果存在，则返回通过 remappingFunction 重新计算后的值。
     * */
    private static void computeIfPresent(){
        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);

        // 重新计算鞋加上10%的增值税后的价值
        int shoesPrice = prices.computeIfPresent("Shoes", (key, value) -> value + value * 10/100);
        System.out.println("Price of Shoes after VAT: " + shoesPrice);
        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
    }

}
