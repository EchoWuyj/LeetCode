package alg_02_train_dm._20_day_数据结构设计;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 23:17
 * @Version 1.0
 */
public class _11_547_number_of_provinces {
    /*
       547. 省份数量
       有 n 个城市，其中一些彼此相连，另一些没有相连。
       如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，
       那么城市 a 与城市 c 间接相连。
       省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

       给你一个 n x n 的矩阵 isConnected ，
       其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，
       而 isConnected[i][j] = 0 表示二者不直接相连。
       返回矩阵中 省份 的数量。

       示例 1：
       输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
       输出：2

       提示：
       1 <= n <= 200
       n == isConnected.length
       n == isConnected[i].length
       isConnected[i][j] 为 1 或 0
       isConnected[i][i] == 1
       isConnected[i][j] == isConnected[j][i]
    */

    // 图解法：
    // 抽象成图结构，每个城市是顶点，城市与城市之间相邻是边
    // 通过 DFS 或者 BFS 来求解图结构中有多少个连通分量

    // 并查集
    // 理解并查集原理，掌握并查集代码，关键：将该问题抽象成并查集的模型
    public int findCircleNum(int[][] isConnected) {

        int rows = isConnected.length;
        int cols = isConnected[0].length;

        // 使用并查集求解：抽象出并查集有多少个顶点
        UnionFind uf = new UnionFind(rows);
        for (int i = 0; i < rows; i++) {
            // j 严格大于 i，两个城市一定不同，i != j
            // 只要遍历矩阵的右上半部分即可，因为 0 1 连通 和 1 0 连通是重复的
            for (int j = i + 1; j < cols; j++) {
                // [i][j] 位置为 1，才使用 uf 进行合并
                if (isConnected[i][j] == 1) {
                    uf.unionElement(i, j);
                }
            }
        }
        return uf.getCircles();
    }

    class UnionFind {
        // parent[i] 表示的是节点 i 所指向的父亲节点
        private int[] parent;
        // rank[i] 表示以 i 为根的集合所表示的树的深度。
        private int[] rank;

        // 省份数量
        private int circles = 0;

        public UnionFind(int capacity) {
            parent = new int[capacity];
            rank = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                parent[i] = i;
                rank[i] = 1;
                // 刚开始城市与城市之间没有连接，有多个城市就有多少个省份
                circles++;
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
            // 若相等，则提前退出，不要遗漏
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
            // 连接一个城市，减去一个省份
            circles--;
        }

        public int getCircles() {
            return circles;
        }
    }
}
