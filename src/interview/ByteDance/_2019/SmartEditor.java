package interview.ByteDance._2019;

/**
 * @Description: 万万没想到之聪明的编辑
 * <p>
 * 我叫王大锤，是一家出版社的编辑。
 * <p>
 * 我负责校对投稿来的英文稿件，这份工作非常烦人，因为每天都要去修正无数的拼写错误。
 * <p>
 * 但是，优秀的人总能在平凡的工作中发现真理。
 * <p>
 * 我发现了一个发现拼写错误的捷径：
 * <p>
 * 1.三个同样的字母连在一起，一定是拼写错误，去掉一个的就好啦：比如 helllo -> hello
 * <p>
 * 2.两对一样的字母（AABB型）连在一起，一定是拼写错误，去掉第二对的一个字母就好啦：比如 helloo -> hello
 * <p>
 * 3.上面的规则优先“从左到右”匹配，即如果是AABBCC，虽然AABB和BBCC都是错误拼写，应该优先考虑修复AABB，结果为AABCC
 * <p>
 * 我特喵是个天才！
 * <p>
 * 我在蓝翔学过挖掘机和程序设计，按照这个原理写了一个自动校对器，工作效率从此起飞。
 * <p>
 * 用不了多久，我就会出任CEO，当上董事长，迎娶白富美，走上人生巅峰，想想都有点小激动呢！
 * <p>
 * ......
 * <p>
 * 万万没想到，我被开除了，临走时老板对我说：“做人做事要兢兢业业、勤勤恳恳、本本分分，人要是行，干一行行一行。一行行行行行；要是不行，干一行不行一行，一行不行行行不行。”
 * <p>
 * 我现在整个人红红火火恍恍惚惚的......
 * <p>
 * 请听题：请实现大锤的自动校对程序
 * @Author: matreeix
 * @Date: 2020/6/6
 */
public class SmartEditor {
    public String smartEditor(String s) {
        char[] chs = new char[s.length()];
        int k = 0;
        for (int i = 0; i < s.length(); i++) {
            chs[k++] = s.charAt(i);
            if (k >= 3 && chs[k - 3] == chs[k - 2] && chs[k - 2] == chs[k - 1]) k--;
            if (k >= 4 && chs[k - 4] == chs[k - 3] && chs[k - 2] == chs[k - 1]) k--;
        }
        return new String(chs);
    }

}
