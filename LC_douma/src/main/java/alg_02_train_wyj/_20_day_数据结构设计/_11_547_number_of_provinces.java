package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 16:24
 * @Version 1.0
 */
public class _11_547_number_of_provinces {

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null) return 0;
        int rows = isConnected.length;
        int cols = isConnected[0].length;

        UnionFind unionFind = new UnionFind(rows);
        for (int i = 0; i < rows; i++) {
            for (int j = i + 1; j < cols; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSize();
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        int size = 0;

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

        public int getSize() {
            return size;
        }
    }
}
