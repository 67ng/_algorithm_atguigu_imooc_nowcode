package _courses.imooc.Graph_Algorithms.Graph_Basics.AdjacencyMatrix.src;

import java.io.*;
import java.util.Scanner;

public class AdjMatrix {

    private int V;//顶点数
    private int E;//边数
    private int[][] adj;//邻接矩阵

    public AdjMatrix(String filename) {

        File file = new File(filename);

        try (Scanner scanner = new Scanner(file)) {//try的withresource语法，会自动关闭资源

            V = scanner.nextInt();
            adj = new int[V][V];

            E = scanner.nextInt();
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        AdjMatrix adjMatrix = new AdjMatrix("C:\\Users\\mzh\\IdeaProject\\_justforfun\\src\\_courses.imooc\\Graph_Algorithms\\Graph_Basics\\AdjacencyMatrix\\g.txt");
        System.out.print(adjMatrix);
    }
}
