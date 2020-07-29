package simple_fun.data_structure.linear.stack.monoton_stack.app;

import java.util.Stack;

/**
 * Description:环形山中可见山峰的对数
 * 如[5,2,3,2,6,5,7],其中某个数代表山峰高度,故
 * 1.相邻必可见
 * 2.任意数向左或向右找第一个大于它的数,则他们俩个可见，且后面的不可见
 * 3.若数的值都不等，则对数为2*n-3(n>=2)
 *
 * @author: NULL
 * @date: 2018/10/28 18:03
 */
public class MountainsAndFlame {

    //  构造循环数组遍历,size为数组的长度
    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    //   计算从n个不同元素任选两个的挑选方法种数
    public static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    //   单调栈里的元素类
    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.times = 1;//出现次数
            this.value = value;//数组元素的值
        }
    }

    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;//找到数组里最大元素的索引
        }
        int value = arr[maxIndex];
        int index = nextIndex(size, maxIndex);//从最大值开始遍历
        long res = 0L;
        Stack<Pair> stack = new Stack<>();//构造栈底到栈顶Pair.value由大到小的栈
        stack.push(new Pair(value));//把最高山压入作为第一个元素即栈底,保证栈不为空

        while (index != maxIndex) {//遍历一次整个数组
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {//1.入栈的山更高,开始弹出元素
                int times = stack.pop().times;
                res += getInternalSum(times) + 2 * times;//结算弹出元素产生的对数=相同元素(getInternalSum(times))+该元素和左右第一个更高山组队(2 * times)
            }
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;//2.入栈的山和栈顶等高,合并入栈顶,次数加一
            } else {
                stack.push(new Pair(value));//3.入栈的山小于栈顶的山,压入栈顶,初始化次数值为一
            }
            index = nextIndex(size, index);
        }

//         遍历完一次数组,开始弹出数组中的所有元素
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getInternalSum(times);//重复元素的次数
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;//最后俩元素上面元素弹出时
                } else {
                    res += stack.peek().times > 1 ? times : 0;//若栈底元素(最高峰)只出现一次,栈底上面的元素和最高峰配对数只有times次；否则,有2 * times次
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
       /* Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(communications(arr));
        }
        in.close();*/
       int[] mountains = {5,4,4,5,2};
        System.out.println(communications(mountains));

    }

}
