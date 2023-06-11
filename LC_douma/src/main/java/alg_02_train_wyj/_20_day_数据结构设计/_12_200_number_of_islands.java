package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 16:24
 * @Version 1.0
 */
public class _12_200_number_of_islands {
    public int numIslands(char[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        int rows = grid.length;
        int cols = grid[0].length;

        UnionFind unionFind = new UnionFind(grid);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    for (int[] dir : dirs) {
                        int nexti = i + dir[0];
                        int nextj = j + dir[1];
                        if (nexti >= 0 && nexti < rows
                                && nextj >= 0 && nextj < cols
                                && grid[nexti][nextj] == '1') {
                            unionFind.unionElement(i * cols + j, nexti * cols + nextj);
                        }
                    }
                }
            }
        }
        return unionFind.count;
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        int count = 0;

        public UnionFind(char[][] grid) {
            int rows = grid.length;
            int cols = grid[0].length;
            parent = new int[rows * cols];
            rank = new int[rows * cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        parent[i * cols + j] = i * cols + j;
                        rank[i * cols + j] = 1;
                        count++;
                    }
                }
            }
        }

        private int find(int p) {
            if (p < 0 || p >= parent.length) {
                throw new IllegalArgumentException("p 超出了范围");
            }
            while (p != parent[p]) {
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
                parent[qRoot] = pRoot;
                rank[pRoot] += 1;
            }
            count--;
        }
    }
}
