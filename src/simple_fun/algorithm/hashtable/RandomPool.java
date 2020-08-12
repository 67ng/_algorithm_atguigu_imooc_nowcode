package simple_fun.algorithm.hashtable;

import java.util.HashMap;

/**
 * Description:设计RandomPool结构
 *
 * @author: matreeix
 * @date: 2018/10/17 23:26
 */
       /*【题目】 设计一种结构，在该结构中有如下三个功能：
        insert(key)：将某个key加入到该结构，做到不重复加入。
        delete(key)：将原本在结构中的某个key移除。
        getRandom()：等概率随机返回结构中的任何一个key。
        【要求】 Insert、delete和getRandom方法的时间复杂度都是O(1)*/

public class RandomPool {
    public static class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;//原map
        private HashMap<Integer, K> indexKeyMap;//复制map
        private int size;

        public Pool() {
            this.indexKeyMap = new HashMap<>();
            this.keyIndexMap = new HashMap<>();
            this.size = 0;
        }

        //          插入
        public void insert(K key) {
            if (!keyIndexMap.containsKey(key)) {
                this.keyIndexMap.put(key, this.size);
                this.indexKeyMap.put(this.size++, key);
            }
        }

        //          取随机K
        public K getRandom() {
            if (this.size == 0) {
                return null;
            }
            int randomIndex = (int) (Math.random() * this.size);//0~size - 1
            return this.indexKeyMap.get(randomIndex);//通过复制的map来取随机的K
        }

        //        删除
        public void delete(K key) {
            if (keyIndexMap.containsKey(key)) {
                int deleteIndex = this.keyIndexMap.get(key);
                int lastIndex = --this.size;
                K lasrKey = this.indexKeyMap.get(lastIndex);
                keyIndexMap.put(lasrKey, deleteIndex);//这四行保证两map一一对应
                indexKeyMap.put(deleteIndex, lasrKey);
                keyIndexMap.remove(key);
                indexKeyMap.remove(lastIndex);//保证index的有序性，方便取随机K
            }
        }
    }

    //test
    public static void main(String[] args) {
        Pool<String> pool = new Pool<String>();
        pool.insert("0");
        pool.insert("1");
        pool.insert("2");
        pool.insert("3");
        pool.insert("4");
        pool.insert("5");
        pool.insert("6");
        pool.insert("7");
        pool.insert("8");
        pool.insert("9");
        pool.delete("5");
        pool.delete("6");
        pool.delete("7");
        pool.delete("8");
        pool.delete("9");
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());
        System.out.println(pool.getRandom());

    }

}

