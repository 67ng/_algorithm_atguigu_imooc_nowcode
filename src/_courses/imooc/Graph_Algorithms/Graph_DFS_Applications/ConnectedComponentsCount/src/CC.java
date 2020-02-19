package _courses.imooc.Graph_Algorithms.Graph_DFS_Applications.ConnectedComponentsCount.src;

/**
 * 计算联通分量个数
 * */
public class CC {

    private Graph G;
    private boolean[] visited;
    private int cccount = 0;

    public CC(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v]){
                dfs(v);//每次深度优先遍历都会遍历完该联通分量的所有顶点
                cccount ++;
            }
    }

    private void dfs(int v){

        visited[v] = true;
        for(int w: G.adj(v))
            if(!visited[w])
                dfs(w);
    }

    public int count(){
        return cccount;
    }

    public static void main(String[] args){

        Graph g = new Graph("C:\\Users\\mzh\\IdeaProject\\_justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Graph_DFS_Applications\\ConnectedComponentsCount\\g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
    }
}
