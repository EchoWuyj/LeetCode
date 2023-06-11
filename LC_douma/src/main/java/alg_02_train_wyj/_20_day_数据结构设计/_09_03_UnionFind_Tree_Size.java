package alg_02_train_wyj._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 14:55
 * @Version 1.0
 */
public class _09_03_UnionFind_Tree_Size implements _09_UnionFind {

    int[] parent;
    int[] size;

    public _09_03_UnionFind_Tree_Size(int capacity) {
        parent = new int[capacity];
        size = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

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
    public void unionElement(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
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
