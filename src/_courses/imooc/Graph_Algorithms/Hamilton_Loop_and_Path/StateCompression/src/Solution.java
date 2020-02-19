package _courses.imooc.Graph_Algorithms.Hamilton_Loop_and_Path.StateCompression.src;

/// Leetcode 980 通过状态压缩优化
class Solution {

    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int R, C;
    private int[][] grid;
    private int start, end;

    public int uniquePathsIII(int[][] grid) {

        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        int left = R * C;

        for(int i = 0; i < R; i ++)
            for(int j = 0; j < C; j ++)
                if(grid[i][j] == 1){
                    start = i * C + j;
                    grid[i][j] = 0;
                }
                else if(grid[i][j] == 2){
                    end = i * C + j;
                    grid[i][j] = 0;
                }
                else if(grid[i][j] == -1)
                    left --;

        int visited = 0;//二进制的第i位表示第i个顶点的状态，1为已访问，0为未访问
        return dfs(visited, start, left);
    }

    private int dfs(int visited, int v, int left){

        visited += (1 << v);//将顶点v设为已访问
        left --;
        if(v == end && left == 0){
            visited -= (1 << v);
            return 1;
        }

        int x = v / C, y = v % C;
        int res = 0;
        for(int d = 0; d < 4; d ++){
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            int next = nextx * C + nexty;
            if(inArea(nextx, nexty) && grid[nextx][nexty] == 0
                    && (visited & (1 << next)) == 0//判断顶点next是否已经访问，等于0表示没有访问
            )
                res += dfs(visited, next, left);
        }

        visited -= (1 << v);//将顶点v设为未访问
        return res;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}