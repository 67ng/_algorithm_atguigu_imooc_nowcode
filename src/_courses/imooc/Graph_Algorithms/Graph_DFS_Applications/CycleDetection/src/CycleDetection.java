package _courses.imooc.Graph_Algorithms.Graph_DFS_Applications.CycleDetection.src;


/**
 * 检测图中否有环
 * 核心思想：当路径回到某个已经遍历过的顶点时，如果他的前置顶点不是该顶点，就说明有环
 *
 * */
public class CycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G) {

        this.G = G;
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                if (dfs(v, v)) {
                    hasCycle = true;
                    break;
                }
    }

    // 从顶点v开始，判断图中是否有环
    private boolean dfs(int v, int parent) {//注:添加了返回Boolean，就不用完全DFS遍历，有环就直接返回
        visited[v] = true;
        for (int w : G.adj(v))
            if (!visited[w]) {
                if (dfs(w, v)) return true;
            } else if (w != parent)
                return true;
        return false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {

        Graph g = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Graph_DFS_Applications\\CycleDetection\\g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());

        Graph g2 = new Graph("C:\\Users\\daito\\ideaproject\\justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Graph_DFS_Applications\\CycleDetection\\g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}