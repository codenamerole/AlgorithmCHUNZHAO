//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 
// 👍 965 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/* 1. DFS
* 是1-> 岛屿数量+1， 把所有和这个1相连的1全部置为0，直到所有地图都是0
* */
/*class Solution {
    void dfs(char[][] grid, int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        if (row < 0 || col < 0 || row >= m || col >= n || grid[row][col] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, row - 1, col); //上
        dfs(grid, row + 1, col); //下
        dfs(grid, row, col - 1); //左
        dfs(grid, row, col + 1); //右
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (grid[r][c] == '1') {
                    ++res;
                    dfs(grid, r, c);
                }
            }
        }
        return res;
    }
}*/

//Round1 BFS
/* 判断队列首部节点 (i, j) 是否未越界且为 1:
* 若是则置为0（删除岛屿节点），并将此节点上下左右节点 (i+1,j),(i-1,j),(i,j+1),(i,j-1) 加入队列；
* 若不是则跳过此节点；
* 循环 pop 队列首节点，直到整个队列为空，此时已经遍历完此岛屿
* */
/*
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1'){
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void bfs(char[][] grid, int i, int j){
        Queue<int[]> list = new LinkedList<>();
        list.add(new int[] { i, j });
        while(!list.isEmpty()){
            int[] cur = list.remove();
            i = cur[0]; j = cur[1];
            if(0 <= i && i < grid.length && 0 <= j && j < grid[0].length && grid[i][j] == '1') {
                grid[i][j] = '0';
                list.add(new int[] { i + 1, j });
                list.add(new int[] { i - 1, j });
                list.add(new int[] { i, j + 1 });
                list.add(new int[] { i, j - 1 });
            }
        }
    }
}
*/

//Round1 并查集
class UnionFind {
    private int count = 0;
    private int[] parent;
    public UnionFind(char[][] grid) {
        count = 0;
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    parent[i * n + j] = i * n + j;
                    ++count;
                }
            }
        }
    }
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
    public int getCount() {
        return count;
    }
}
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * n + c, (r-1) * n + c);
                    }
                    if (r + 1 < m && grid[r+1][c] == '1') {
                        uf.union(r * n + c, (r+1) * n + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * n + c, r * n + c - 1);
                    }
                    if (c + 1 < n && grid[r][c+1] == '1') {
                        uf.union(r * n + c, r * n + c + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }
}


//leetcode submit region end(Prohibit modification and deletion)
//Round2 DFS
class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        //超过横竖边界 或 不是岛屿
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == '0') return;
        grid[row][col] = '0';
        dfs(grid, row + 1, col);
        dfs(grid, row, j + col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col - 1);
    }
}

//Round2 并查集
class UnionFind {
    private int count = 0;
    private int[] parent;
    public UnionFind(char[][] grid) {
        count = 0;
        int m = grid.length;
        int n = grid[0].length;
        parent = new int[m * n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    parent[i * n + j] = i * n + j;
                    ++count;
                }
            }
        }
    }
    public int find(int p) {
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ;
        count--;
    }
    public int getCount() {
        return count;
    }
}
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < m; ++r) {
            for (int c = 0; c < n; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * n + c, (r-1) * n + c);
                    }
                    if (r + 1 < m && grid[r+1][c] == '1') {
                        uf.union(r * n + c, (r+1) * n + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * n + c, r * n + c - 1);
                    }
                    if (c + 1 < n && grid[r][c+1] == '1') {
                        uf.union(r * n + c, r * n + c + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }
}