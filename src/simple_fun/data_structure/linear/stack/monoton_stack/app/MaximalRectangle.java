package simple_fun.data_structure.linear.stack.monoton_stack.app;

import java.util.Stack;

/**
 * Description:求最大子矩阵的大小
 * 矩阵map只包含0和1两种值，求其值全为1的最大的矩形区域中1的数量
 *
 * @author: NULL
 * @date: 2018/10/28 17:57
 */
public class MaximalRectangle {
    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0 || map[0].length == 0) {
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];//矩阵从上到下每一行看成一个数组
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;//矩阵每一行往上叠加,得到可表示成柱形图的数组
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }
//  数组表示成柱形图求可以框出的最大矩形面积(美团笔试题)
    public static int maxRecFromBottom(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();//构造栈底到栈顶由小到大的单调栈(压入的是数组的索引)
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {//入栈元素若不大于栈顶元素,就从栈顶开始弹出元素
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];//得到弹出元素的矩形区域值
                maxArea = Math.max(maxArea, curArea);//更新最大矩形区域值
            }
            stack.push(i);//直到遇到某个元素小于入栈元素时,把入栈元素压入栈
        }
        //数组中的所有元素都压栈完毕以后,开始从栈顶依次弹出元素
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - k - 1) * height[j];//得到弹出元素的矩形区域值
            maxArea = Math.max(maxArea, curArea);//更新最大矩形区域值
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 0}};
        System.out.println(maxRecSize(map));
    }

}
