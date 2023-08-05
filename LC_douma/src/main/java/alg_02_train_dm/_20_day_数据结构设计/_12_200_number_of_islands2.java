package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-08-05 14:34
 * @Version 1.0
 */
public class _12_200_number_of_islands2 {

    // KeyPoint 自己解法
    public static int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int capacity = rows * cols;
        UnionFind unionFind = new UnionFind(capacity);
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    // 将其设置为 '2'，避免走回头路，同时避免和 '0' 相混淆
                    // 因为 grid[i][j] == '0'，还要执行 size -- 操作
                    grid[i][j] = '2';
                    for (int[] dir : dirs) {
                        int nexti = i + dir[0];
                        int nextj = j + dir[1];
                        if (nexti >= 0 && nexti < rows
                                && nextj >= 0 && nextj < cols
                                && grid[nexti][nextj] == '1') {
                            // KeyPoint 二维坐标 转 一维坐标
                            // 并查集处理都是一维数组坐标 parent[capacity] 和 rank[capacity]
                            // 二维坐标 [i,j] 和 [nexti,nextj] 无法处理，故需要
                            unionFind.union(i * cols + j, nexti * cols + nextj);
                        }
                    }
                    // else if 明确判断条件
                    // grid[i][j] == '0'，执行 size--;
                } else if (grid[i][j] == '0') {
                    unionFind.size--;
                }
            }
        }
        return unionFind.size;
    }

    static class UnionFind {

        int[] parent;
        int[] rank;
        int size;

        public UnionFind(int capacity) {
            parent = new int[capacity];
            rank = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
            size = capacity;
        }

        private int find(int p) {
            while (parent[p] != p) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;

            if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
            } else if (rank[pRoot] > rank[qRoot]) {
                parent[qRoot] = pRoot;
            } else {
                parent[pRoot] = qRoot;
                rank[qRoot]++;
            }
            size--;
        }
    }
}
