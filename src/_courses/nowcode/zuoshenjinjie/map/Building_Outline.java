package _courses.nowcode.zuoshenjinjie.map;

import java.util.*;

/**
 * Description:大楼轮廓
 * 给定一个N行3列二维数组，每一行表示有一座大楼，一共有N座
 * 大楼。 所有大楼的底部都坐落在X轴上，每一行的三个值
 * (a,b,c)代表每座大楼的从(a,0)点开始，到 (b,0)点结束，高
 * 度为c。 输入的数据可以保证a<b,且a，b，c均为正数。大楼之
 * 间可以有重合。 请输出整体的轮廓线。
 * 例子：给定一个二维数组 [ [1, 3, 3], [2, 4, 4], [5, 6,1] ]
 * 输出为轮廓线 [ [1, 2, 3], [2, 4, 4], [5, 6, 1] ]
 *
 * @author: NULL
 * @date: 2018/10/29 22:33
 */
public class Building_Outline {
    public static class Node {
        public boolean isUp;//当前数是上还是下
        public int posi;//当前数的值
        public int h;//上升的高度

        public Node(boolean bORe, int position, int height) {
            isUp = bORe;
            posi = position;
            h = height;
        }
    }
//   定义比较器
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            if (o1.posi != o2.posi) {//把数值按从小到大排列
                return o1.posi - o2.posi;
            }
            if (o1.isUp != o2.isUp) {//同一个数有上有下时,先上后下
                return o1.isUp ? -1 : 1;
            }
            return 0;
        }
    }

    public static List<List<Integer>> buildingOutline(int[][] buildings) {
        Node[] nodes = new Node[buildings.length * 2];//把传入的每个数组分成上下两部分存为节点数组
        for (int i = 0; i < buildings.length; i++) {
            nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);//偶数索引存向上的值
            nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);//奇数索引存向下的值
        }
        Arrays.sort(nodes, new NodeComparator());
        TreeMap<Integer, Integer> htMap = new TreeMap<>();//遍历整个数组
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();//存储需要的数值

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].isUp) {//该数值是向上的情况
                if (!htMap.containsKey(nodes[i].h)) {//没有该高度值,存该值计数为一
                    htMap.put(nodes[i].h, 1);
                } else {
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);//有该高度值,计数加一
                }
            } else {//该数值是向下的情况
                if (htMap.containsKey(nodes[i].h)) {
                    if (htMap.get(nodes[i].h) == 1) {
                        htMap.remove(nodes[i].h);
                    } else {
                        htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1);
                    }
                }
            }
            //把htMap中的数值posi存入pmMap
            if (htMap.isEmpty()) {
                pmMap.put(nodes[i].posi, 0);
            } else {
                pmMap.put(nodes[i].posi, htMap.lastKey());//？
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;
        for (Map.Entry<Integer, Integer> entry : pmMap.entrySet()) {
            int curPosition = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (height != curMaxHeight) {
                if (height != 0) {
                    List<Integer> newRecord = new ArrayList<Integer>();
                    newRecord.add(start);
                    newRecord.add(curPosition);
                    newRecord.add(height);
                    res.add(newRecord);
                }
                start = curPosition;
                height = curMaxHeight;
            }
        }
        return res;
    }

}
