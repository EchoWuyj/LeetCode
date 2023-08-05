package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 14:55
 * @Version 1.0
 */
public class _09_06_UnionFind_Tree_Rank_Path_Recursion implements _09_UnionFind {

    int[] parent;
    int[] rank;

    public _09_06_UnionFind_Tree_Rank_Path_Recursion(int capacity) {
        parent = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public int find(int p) {
        if (p >= parent.length || p < 0) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        if (parent[p] == p) return p;
        parent[p] = find(parent[p]);
        return parent[p];
    }

    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int size() {
        return parent.length;
    }
}
