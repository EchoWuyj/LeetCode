package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 21:09
 * @Version 1.0
 */

// KeyPoint 基于 rank 优化 => rank 本质：树的深度
public class _09_04_UnionFind_Tree_Rank implements _09_UnionFind {

    // parent[i] 表示的是节点 i 所指向的父亲节点
    private int[] parent;
    // rank[i] 表示以 i 为根的集合所表示的树的深度
    private int[] rank;

    public _09_04_UnionFind_Tree_Rank(int capacity) {
        parent = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找元素 p 所属的集合
    // 时间复杂度：O(h) h 表示树的深度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 基于 rank 合并，将 rank 值小合并到 rank 大树，从而保证树是最小的高度
    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            // 只改变 root，节点数不用改变
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            // 父节点 rank 发生变化
            rank[pRoot] += 1;
        }
    }

    @Override
    public int size() {
        return parent.length;
    }
}
