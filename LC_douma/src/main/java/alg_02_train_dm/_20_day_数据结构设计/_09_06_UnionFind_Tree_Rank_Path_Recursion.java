package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 22:56
 * @Version 1.0
 */
public class _09_06_UnionFind_Tree_Rank_Path_Recursion implements _09_UnionFind {

    // parent[i] 表示的是节点 i 所指向的父亲节点
    int[] parent;
    // rank[i] 表示以 i 为根的集合所表示的树的深度。
    int[] rank;

    public _09_06_UnionFind_Tree_Rank_Path_Recursion(int capacity) {
        parent = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找元素 p 所属的集合
    // 时间复杂度：O(h) h 表示树的深度
    // find 递归查找 p 的父节点
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        // 递归边界 => 找到 p 父节点，返回 parent[p]
        if (p == parent[p]) return parent[p];
        // 路径压缩
        // p 父节点  =  p 父节点的父节点
        // parent[p] = find(parent[p])
        parent[p] = find(parent[p]);
        // 将 p 父节点返回
        return parent[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
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
            // rank[pRoot] == rank[qRoot]
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    @Override
    public int size() {
        return parent.length;
    }
}
