package _cola.algorithm.hashtable;

import java.util.BitSet;

/**
 * Description:认识布隆过滤器
 *
 * @author: matreeix
 * @date: 2018/10/18 0:17
 */
/*布隆过滤器实际上是由一个很长的二进制向量和一系列随机映射函数组成,布隆过滤器用于检索一个元素是否在一个集合中
        优点
        布隆过滤器具有空间和时间优势。布隆过滤器存储空间和插入/查询时间都是常数。另外,
        Hash 函数相互之间没有关系，方便由硬件并行实现。布隆过滤器不需要存储元素本身，
        在某些对保密要求非常严格的场合有优势

        缺点
        存在一定误算率，即Bloom Filter报告某一元素存在于某集合中，
        但是实际上该元素并不在集合中，但如果某个元素确实没有在该集合中，
        那么Bloom Filter是不会报告该元素存在于集合中的，不会漏报;另外从布隆过滤器中删除元素困难.
        具体实现
        先建立一个16亿二进制常量，然后将这16亿个二进制位全部置0。对于每个字符串，
        用8个不同的随机产生器（F1,F2,.....,F8）产生8个信息指纹(f1,f2,....,f8).
        再用一个随机数产生器G把这八个信息指纹映射到1到16亿中的8个自然数g1,g2,...,g8。
        现在把这8个位置的二进制位全部变为1。这样一个布隆过滤器就建好了 
             */

public class BloomFilter {
    private static final int DEFAULT_SIZE = 2 << 24;//布隆过滤器的比特长度    
    private static final int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};//这里要选取质数，能很好的降低错误率    
    private static BitSet bits = new BitSet(DEFAULT_SIZE);
    private static SimpleHash[] func = new SimpleHash[seeds.length];

    public static void addValue(String value) {
        for (SimpleHash f : func)//将字符串value哈希为8个或多个整数，然后在这些整数的bit上变为1    
            bits.set(f.hash(value), true);
    }

    public static void add(String value) {
        if (value != null) addValue(value);
    }

    public static boolean contains(String value) {
        if (value == null) return false;
        boolean ret = true;
        for (SimpleHash f : func)//这里其实没必要全部跑完，只要一次ret==false那么就不包含这个字符串    
            ret = ret && bits.get(f.hash(value));
        return ret;
    }

    public static void main(String[] args) {
        String value = "xkeyideal@gmail.com";
        for (int i = 0; i < seeds.length; i++) {
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
        add(value);
        System.out.println(contains(value));
    }
}

class SimpleHash {//这玩意相当于C++中的结构体    

    private int cap;
    private int seed;

    public SimpleHash(int cap, int seed) {
        this.cap = cap;
        this.seed = seed;
    }

    public int hash(String value) {//字符串哈希，选取好的哈希函数很重要    
        int result = 0;
        int len = value.length();
        for (int i = 0; i < len; i++) {
            result = seed * result + value.charAt(i);
        }
        return (cap - 1) & result;
    }

}
