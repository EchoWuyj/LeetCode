package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 23:18
 * @Version 1.0
 */
public class _12_200_number_of_islands {
     /* 
        200. 岛屿数量
        给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
        岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
        此外，你可以假设该网格的四条边均被水包围。

        示例 1：
        输入：grid = [
          ["1","1","1","1","0"],
          ["1","1","0","1","0"],
          ["1","1","0","0","0"],
          ["0","0","0","0","0"]
        ]
        输出： 1

        示例 2：
        输入：grid = [
          ["1","1","0","0","0"],
          ["1","1","0","0","0"],
          ["0","0","1","0","0"],
          ["0","0","0","1","1"]
        ]
        输出： 3

        提示：
        m == grid.length
        n == grid[i].length
        1 <= m, n <= 300
        grid[i][j] 的值为 '0' 或 '1'

     */

    // 并查集
    public int numIslands(char[][] grid) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int rows = grid.length;
        int cols = grid[0].length;

        UnionFind uf = new UnionFind(grid);
        // 矩阵中每个元素，对应为并查集的顶点
        // 只处理 [i][j] = 1 的顶点，[i][j] = 0 的顶点跳过
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == '1') {
                    // 将其设置为 0，避免走回头路
                    grid[i][j] = '0';
                    for (int[] dir : dirs) {
                        int nextRow = i + dir[0];
                        int nextCol = j + dir[1];
                        // 顶点下左右相邻顶点是否为 1，若是 1，则需要将顶点合并
                        // 边界取等，与不取等，需要特别小心
                        if (nextRow >= 0 && nextRow < rows
                                && nextCol >= 0 && nextCol < cols
                                // KeyPoint 字符类型，使用字符 '1'，而不是数字 1
                                && grid[nextRow][nextCol] == '1') {
                            // 唯一确定二维数组每个顶点，将二维数组转成一维数组
                            // 一维数组中每个索引都是唯一的，使用一维数组索引表示顶点的 id
                            // index = i * n + j，i 和 j 表示的是二维数组中的行索引和列索引，n 表示的是二维数组的列数
                            uf.unionElement(i * cols + j, nextRow * cols + nextCol);
                        }
                    }
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind {
        // parent[i] 表示的是节点 i 所指向的父亲节点
        private int[] parent;
        // rank[i] 表示以 i 为根的集合所表示的树的深度
        private int[] rank;
        // 记录岛屿数量
        private int count = 0;

        // 传入 char 二维字符数组
        public UnionFind(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            // 矩阵中每个元素，对应为并查集的顶点
            // 需要进行坐标转换，二维 (i,j) => 一维 i
            parent = new int[rows * cols];
            rank = new int[rows * cols];
            // 遍历矩阵中每个顶点
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    // 只处理 [i][j] = 1 的顶点，[i][j] = 0 的顶点跳过
                    if (grid[i][j] == '1') {
                        // id => 二维(i,j) 转 一维 i
                        parent[i * cols + j] = i * cols + j;
                        rank[i * cols + j] = 1;
                        count++;
                    }
                }
            }
        }

        // 查找元素 p 所属的集合
        // 时间复杂度：O(h) h 表示树的深度
        // O(h) -> O(1) < O(log*n) < O(logn)
        private int find(int p) {
            if (p < 0 || p >= parent.length) {
                throw new IllegalArgumentException("p 超出了范围");
            }
            while (p != parent[p]) {
                // 路径压缩
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        public void unionElement(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;

            if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
            } else if (rank[pRoot] > rank[qRoot]) {
                parent[qRoot] = pRoot;
            } else {
                // rank[pRoot] == rank[qRoot]
                parent[qRoot] = pRoot;
                rank[pRoot] += 1;
            }
            count--;
        }

        public int getCount() {
            return count;
        }
    }
}


