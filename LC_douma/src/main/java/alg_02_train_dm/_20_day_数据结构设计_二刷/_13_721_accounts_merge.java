package alg_02_train_dm._20_day_数据结构设计_二刷;

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
        现在我们想合并这些账户。

        如果两个账户都 有一些共同的邮箱地址，则两个账户必定属于同一个人。
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

        // KeyPoint 核心思维
        // 1.现在草稿纸上梳理逻辑，再去代码实现
        // 2.先在时间复杂度限制内完成，再去优化成最优解 => 先完成，再完美

        // KeyPoint 经验积累
        // 1.Map 映射定义：nameToMail 和 mailToName
        // 2.区别 List 和 数组，获取元素的方式
        //   list account 通过 account.get(index) 获取 value
        //   数组 account 通过 account[index] 获取 value

        /*
           原始数据
           accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                       ["John", "johnnybravo@mail.com"],
                       ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                       ["Mary", "mary@mail.com"]]
       */

        // 根据邮箱合并账户 => 两个不同账户，使用的邮箱相同，两个账户属于同一个人
        // 关键：将邮箱唯一化，每个邮箱使用 id 表示，作为并查集顶点
        // KeyPoint 不能将用户名作为唯一化标识，因为不同邮箱，可能存在相同用户名，应该作为不同集合，而不是相同集合
        // 使用 Map 做映射，emailToIndex
        // key => email
        // value => id
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();

        // 后续需要通过邮箱获取对应人名，故这里也做个映射 emailToName
        Map<String, String> emailToName = new HashMap<String, String>();

        int count = 0;
        // KeyPoint 1.建立 Map 映射
        for (List<String> account : accounts) {
            String name = account.get(0);
            // 从第 2 元素开始，才是邮箱字符串
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                // KeyPoint 通过 Map 的 Key 去重
                if (!emailToIndex.containsKey(email)) {
                    // count 计数作为 id，保证 id 不重复
                    emailToIndex.put(email, count++);
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

        // KeyPoint 2.使用 UnionFind 合并同一个账户下的邮箱 id
        UnionFind uf = new UnionFind(count);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();

            /*
            accounts = [["John", "johnsmith@mail.com", "john00@mail.com"],
                                         0                     1
                       ["John", "johnnybravo@mail.com"],
                                         2
                       ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
                                         0                    3
                       ["Mary", "mary@mail.com"]]
                                         4
             */

            // 遍历同一个账户下多个邮箱
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                // 将同一个人的邮箱对应的 index 进行合并 => [0, 1, 3]、[2]、[4] => 邮箱属于 3 人的
                uf.union(firstIndex, nextIndex);
                // 注意：第 3个 John -> 对应 "johnsmith@mail.com", "john_newyork@mail.com"
                // 其中 "johnsmith@mail.com" 并没有缺少，emailToIndex 和 emailToName 为了避免重复 key
                // 没有将 "johnsmith@mail.com" 加入，但是 account 本身还是有 2 个邮箱的
            }
        }

        /*
            rootIndexToEmails = {1 -> ["johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                             2 - > ["johnnybravo@mail.com"],
                             4 -> ["mary@mail.com"]]}
        */

        //  KeyPoint 3. 建立 root 和邮件集合的 Map 映射：rootIndexToEmails
        // 根据不同的 rootIndex，创建不同的 account，用来装邮箱信息
        // 整体映射成 Map 结构
        Map<Integer, List<String>> rootIndexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = emailToIndex.get(email);
            // KeyPoint 找到根节点
            // 1.同一个集合中，find 最后都是相同的 rootIndex
            // 2.通过 rootIndex 作为一个集合的标识，将相同的集合元素存在一起
            int rootIndex = uf.find(index);
            // 以每个 rootIndex 节点为 key，创建一个关于邮箱集合 (value)，整体作为 Map 映射
            List<String> emails = rootIndexToEmails.getOrDefault(rootIndex, new ArrayList<>());
            emails.add(email);
            rootIndexToEmails.put(rootIndex, emails);
        }

        // KeyPoint 4.将 rootIndexToEmails 中 rootIndex 替换成 name
        // 将 rootIndex 替换成人名
        List<List<String>> res = new ArrayList<>();
        for (List<String> emails : rootIndexToEmails.values()) {
            // 排序(字符串，默认是 ASCII 进行排序 )
            Collections.sort(emails);
            // 通过 email 获取 name
            String name = emailToName.get(emails.get(0));
            List<String> accountAndEmails = new ArrayList<>();
            // name 和 emails 整体加入 accountAndEmails 中
            accountAndEmails.add(name);
            // 加入集合，需要使用 addAll API
            accountAndEmails.addAll(emails);
            // accountAndEmails 整体加入 res 集合中，作为最后的结果
            res.add(accountAndEmails);
            /*
            res = [["John", "johnsmith@mail.com", "john00@mail.com", "john_newyork@mail.com"],
                      ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
            */
        }
        return res;
    }

    // KeyPoint 并查集
    // 核心方法：isConnected，UnionFind，附加 find，其内部构造是无法获取的
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
        public void union(int p, int q) {
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
