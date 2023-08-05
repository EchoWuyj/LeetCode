package alg_02_train_wyj._20_day_数据结构设计;

import javax.sql.rowset.FilteredRowSet;
import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 16:25
 * @Version 1.0
 */
public class _13_721_accounts_merge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
      
        // 输出
        //[["John","john00@mail.com","john_newyork@mail.com","johnnybravo@mail.com","johnsmith@mail.com"],["Mary",
        // "mary@mail.com"]]
        //预期结果
        //[["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John",
        // "johnnybravo@mail.com"]]
    }

    class UnionFind {

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
