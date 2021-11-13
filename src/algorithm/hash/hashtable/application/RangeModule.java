package algorithm.hash.hashtable.application;

import java.util.TreeMap;

/**
 * @Description: 715. Range 模块
 *  Range 模块是跟踪数字范围的模块。你的任务是以一种有效的方式设计和实现以下接口。
 *  1.addRange(int left, int right) 添加半开区间 [left, right)，跟踪该区间中的每个实数。添加与当前跟踪的数字部分重叠的区间时，
 *  应当添加在区间 [left, right) 中尚未跟踪的任何数字到该区间中。
 *  2.queryRange(int left, int right) 只有在当前正在跟踪区间 [left, right) 中的每一个实数时，才返回 true。
 *  3.removeRange(int left, int right) 停止跟踪区间 [left, right) 中当前正在跟踪的每个实数。
 * @Date: 2021/11/13
 */

public class RangeModule {
    TreeMap<Integer, Integer> intervals;

    public RangeModule() {
        this.intervals = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);

        if (start != null && intervals.get(start) >= left) left = start;
        if (end != null && intervals.get(end) > right) right = intervals.get(end);

        intervals.put(left, right);
        intervals.subMap(left, false, right, true).clear();
    }

    public boolean queryRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        if (start == null) return false;
        return intervals.get(start) >= right;
    }

    public void removeRange(int left, int right) {
        Integer start = intervals.floorKey(left);
        Integer end = intervals.floorKey(right);

        if (end != null && intervals.get(end) > right) intervals.put(right, intervals.get(end));
        if (start != null && intervals.get(start) > left) intervals.put(start, left);

        intervals.subMap(left, true, right, false).clear();
    }

}
