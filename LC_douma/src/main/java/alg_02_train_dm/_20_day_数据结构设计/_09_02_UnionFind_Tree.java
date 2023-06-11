package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 20:11
 * @Version 1.0
 */
public class _09_02_UnionFind_Tree implements _09_UnionFind {

    // 将数组替换成 Tree 结构
    // parent[i] 表示的是节点 i 所指向的父亲节点
    private int[] parent;

    public _09_02_UnionFind_Tree(int capacity) {
        parent = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            // 初始化，父亲节点自己本身
            parent[i] = i;
        }
    }

    // 查找元素 p 所属的集合
    // 时间复杂度：O(h)，h 表示树的深度，一般比线性结构好 n
    // 注意：这里 h 和节点数量没有什么关系，这里树不是平衡树
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p 超出了范围");
        }
        // p = parent[p] => 此时 p 指向 root 节点 => 结束循环
        while (p != parent[p]) {
            // p 指向父节点
            p = parent[p];
        }
        // 返回 root 节点
        return p;
    }

    // 时间复杂度 O(h)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 时间复杂度 O(1)
    @Override
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        // 两个 root 相同，两者是连接的，直接返回
        if (pRoot == qRoot) return;
        // 将 pRoot 父节点 设置为 qRoot
        parent[pRoot] = qRoot;
    }

    @Override
    public int size() {
        return parent.length;
    }
}
