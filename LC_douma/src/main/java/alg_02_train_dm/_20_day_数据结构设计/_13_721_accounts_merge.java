package alg_02_train_dm._20_day_数据结构设计;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-28 23:21
 * @Version 1.0
 */
public class _13_721_accounts_merge {
     /* 
        721. 账户合并
        给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表
        其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
        现在，我们想合并这些账户。

        如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。
        请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。
        一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
    
        合并账户后，按以下格式返回账户：
        每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。
        账户本身可以以任意顺序返回。
    
        输入：
        accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                    ["John", "johnnybravo@mail.com"],
                    ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                    ["Mary", "mary@mail.com"]]
        输出：
        [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],
         ["John", "johnnybravo@mail.com"],
         ["Mary", "mary@mail.com"]]

        解释：
        第一个和第三个 John 是同一个人，因为他们有共同的邮箱地址 "johnsmith@mail.com"。
        第二个 John 和 Mary 是不同的人，因为他们的邮箱地址没有被其他帐户使用。
        可以以任何顺序返回这些列表，例如答案
        [['Mary'，'mary@mail.com']，['John'，'johnnybravo@mail.com']，
        ['John'，'john00@mail.com'，'john_newyork@mail.com'，'johnsmith@mail.com']] 也是正确的。

        提示：
        1 <= accounts.length <= 1000
        2 <= accounts[i].length <= 10
        1 <= accounts[i][j].length <= 30
        accounts[i][0] 由英文字母组成
        accounts[i][j] (for j > 0) 是有效的邮箱地址
     */

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        /*
           原始数据
           accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                       ["John", "johnnybravo@mail.com"],
                       ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                       ["Mary", "mary@mail.com"]]
       */

        // 根据邮箱合并账户 => 两个不同账户，使用的邮箱相同，两个账户属于同一个人
        // 将邮箱唯一化，每个邮箱使用 id 表示，作为并查集顶点，使用 Map 做映射，emailToIndex
        // 其中：key => email；value => id
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();

        // 后续需要通过邮箱获取对应人名，故这里也做个映射 emailToName
        Map<String, String> emailToName = new HashMap<String, String>();

        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            // 从第 2 元素开始，才是邮箱字符串
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailsCount++);
                     /*
                        emailToIndex = {"johnsmith@mail.com" -> 0, "john00@mail.com" -> 1,
                                        "johnnybravo@mail.com" -> 2,
                                        "john_newyork@mail.com" -> 3,
                                        "mary@mail.com" -> 4}
                    */

                    emailToName.put(email, name);
                    /*
                        emailToName = {"johnsmith@mail.com" -> "John", "john00@mail.com" -> "John",
                                       "johnnybravo@mail.com" -> "John",
                                       "john_newyork@mail.com" -> "John",
                                       "mary@mail.com" -> "Mary"}
                    */
                }
            }
        }

        /*
            将同一个人的邮箱对应的 index 进行合并 => [0, 1, 3]、[2]、[4]
        */
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                // 将同一个人账号的多个邮箱进行合并
                uf.unionElement(firstIndex, nextIndex);
                // 注意：第 3个 John -> 对应 "johnsmith@mail.com", "john_newyork@mail.com"
                // 其中 "johnsmith@mail.com" 并没有缺少，emailToIndex 和 emailToName 为了避免重复 key
                // 没有将 "johnsmith@mail.com" 加入，但是 account 本身还是有 2 个邮箱的
            }
        }

        /*
            indexToEmails = {1 -> ["johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                             2 - > ["johnnybravo@mail.com"],
                             4 -> ["mary@mail.com"]]}
        */
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = emailToIndex.get(email);
            int indexRoot = uf.find(index);
            // 以每个 root 节点，创建一个关于邮箱集合，整体作为 Map 映射
            List<String> account = indexToEmails.getOrDefault(indexRoot, new ArrayList<>());
            account.add(email);
            // 根据不同的 root，创建不同的 account，用来装邮箱信息
            // 整体映射成 Map 结构
            indexToEmails.put(indexRoot, account);
        }

        // 将 root 替换成人名
        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails : indexToEmails.values()) {
            // 排序(字符串，默认是 ASCII 进行排序 )
            Collections.sort(emails);
            // 通过 email 获取 name
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            // name 和 emails 整体加入 account 中
            account.add(name);
            // 加入集合，需要使用 addAll API
            account.addAll(emails);
            // account 整体加入 merged 集合中，作为最后的结果
            merged.add(account);
            /*
            merged = [["John", "johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                      ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
            */
        }
        return merged;
    }

    // 并查集
    class UnionFind {
        // parent[i] 表示的是节点 i 所指向的父亲节点
        private int[] parent;
        private int[] rank;

        // 正常情况，通过外部传入 capacity，从而定义并查集容量
        public UnionFind(int capacity) {
            parent = new int[capacity];
            rank = new int[capacity];

            // 一开始的时候，每个元素都属于不同的集合
            for (int i = 0; i < capacity; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // 查找元素 p 所属的集合
        public int find(int p) {
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

        // 查看 p 和 q 是否属于同一个集合
        public boolean isConnected(int p, int q) {
            return find(p) == find(q);
        }

        // 合并 p 和 q 所属的集合
        public void unionElement(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot == qRoot) return;

            if (rank[pRoot] < rank[qRoot]) {
                parent[pRoot] = qRoot;
            } else if (rank[pRoot] > rank[qRoot]) {
                parent[qRoot] = pRoot;
            } else {
                parent[qRoot] = pRoot;
                rank[pRoot] += 1;
            }
        }
    }
}
