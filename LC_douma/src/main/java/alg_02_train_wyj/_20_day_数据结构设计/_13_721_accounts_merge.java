package alg_02_train_wyj._20_day_数据结构设计;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-29 16:25
 * @Version 1.0
 */
public class _13_721_accounts_merge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        int count = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, count++);
                    emailToName.put(email, name);
                }
            }
        }

        UnionFind unionFind = new UnionFind(count);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                unionFind.union(firstIndex, nextIndex);
            }
        }

        Map<Integer, List<String>> rootIndexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = emailToIndex.get(email);
            int rootIndex = unionFind.find(index);
            List<String> emails = rootIndexToEmails.getOrDefault(rootIndex, new ArrayList<>());
            emails.add(email);
            rootIndexToEmails.put(rootIndex, emails);
        }

        List<List<String>> res = new ArrayList<>();
        for (List<String> emails : rootIndexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> accountsAndEmails = new ArrayList<>();
            accountsAndEmails.add(name);
            accountsAndEmails.addAll(emails);
            res.add(accountsAndEmails);
        }
        return res;
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
