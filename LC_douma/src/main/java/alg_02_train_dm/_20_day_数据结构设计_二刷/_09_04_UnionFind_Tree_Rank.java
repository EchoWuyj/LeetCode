package alg_02_train_dm._20_day_数据结构设计_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 21:09
 * @Version 1.0
 */

public class _09_04_UnionFind_Tree_Rank implements _09_UnionFind {

    // KeyPoint 基于 rank 优化
    // 在 UnionFind_Tree_Size 的基础上，将 size 替换成 rank
    // 其中 rank 表示 树的深度(高度)，使用 rank 避免树高度变高的效果很好

    // parent[i] 表示的是节点 i 所指向的父亲节点
     int[] parent;
    // rank[i] 表示以 i 为根的集合所表示的树的深度(高度)
     int[] rank;

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
    // 这样保证合并两个顶点的性能很好
    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (rank[pRoot] < rank[qRoot]) {
            // KeyPoint 注意事项
            // 1.只改变 root，节点数不用改变
            // 2.最好情况 rank[pRoot] = rank[qRoot]-1，即使 pRoot 接入到 qRoot，rank[qRoot] 没有变化
            // 代码书写方向 => 调整方向：rank 从小到大
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // 因为 pRoot 和 qRoot 树的深度相等，故此时合并操作，父节点 rank 需要 +1
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    @Override
    public int size() {
        return parent.length;
    }
}
