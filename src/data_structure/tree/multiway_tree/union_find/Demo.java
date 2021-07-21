package data_structure.tree.multiway_tree.union_find;

/**
 * @Description: 朴素并查集模板
 * @Date: 2021/7/21
 */

public class Demo {

    private int[] p;
    private int[] size;

    public Demo(int n) {
        p = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            size[i] = 1;
        }
    }

    private int find(int x) {
        if (x != p[x])
            p[x] = find(p[x]);
        return p[x];
    }

    private void union(int x, int y) {
        p[find(y)] = find(x);
        size[x] += size[y];
    }

}
