package _courses.imooc.Graph_Algorithms.Bridges_and_Cut_Points.CutPointsAlgorithm.src;

import java.util.HashSet;

public class FindCutPoints {

    private Graph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;

    private HashSet<Integer> res;

    public FindCutPoints(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];

        res = new HashSet<>();
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;

        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent) {

        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt++;
        int child = 0;//统计DFS的遍历树的孩子节点数

        for (int w : G.adj(v))
            if (!visited[w]) {
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);//更新low
                if (v != parent //v不能是根节点，否则下一个判断一定成立
                        && low[w] >= ord[v])//对于点v有一个孩子节点w，满足low[w]>=ord[v]，则v是割点
                    res.add(v);//大于时，根据桥很容易判断；
                               // 等于时，整个环的low值都会等于环的初始链接点的那个顶点的low值，该点low值等于ord值，是桥的一边也是割点
                               //对于孤点桥，孤点没有除了桥接的点没有其他孩子节点，所以必不满足if判断里的条件就不是割点
                child++;
                if (v == parent && child > 1)
                    res.add(v);
            } else if (w != parent)
                low[v] = Math.min(low[v], low[w]);
    }

    public HashSet<Integer> result() {
        return res;
    }

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Bridges_and_Cut_Points\\CutPointsAlgorithm\\g.txt");
        FindCutPoints fc = new FindCutPoints(g);
        System.out.println("Cut Points in g : " + fc.result());

        Graph g2 = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Bridges_and_Cut_Points\\CutPointsAlgorithm\\g2.txt");
        FindCutPoints fc2 = new FindCutPoints(g2);
        System.out.println("Cut Points in g2 : " + fc2.result());

        Graph tree = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Bridges_and_Cut_Points\\CutPointsAlgorithm\\tree.txt");
        FindCutPoints fc3 = new FindCutPoints(tree);
        System.out.println("Cut Points in tree : " + fc3.result());
    }
}
