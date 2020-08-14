package algorithm.graph;
/**
 *
 * 并查集
 *
 * */
public class UF {

    private int[] parent;

    public UF(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int find(int p) {//找到根节点
        if (p != parent[p])
            parent[p] = find(parent[p]);//递归
        return parent[p];
    }

    public boolean isConnected(int p, int q) { return find(p) == find(q);}

    public void unionElements(int p, int q) {//将p、q合并

        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot)
            return;

        parent[pRoot] = qRoot;
    }
}