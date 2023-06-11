package alg_02_体系班_zcy.class08;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-09-19 18:14
 * @Version 1.0
 */

// 该程序完全正确
public class Code02_TrieTree {

    // 前缀树
    // 前缀树是一种用于快速检索的多叉树结构,利用字符串的公共前缀来降低查询时间
    // 核心思想是空间换时间,经常被搜索引擎用于文本词频统计
    // 要求:需要能手写,还是非常重要

    // 实现
    // 1)字符在路径上,每次根据字符串建立路径都是从头节点出发
    //   没有路径直接建立,相同的路径则可以复用
    // 2)每个节点上封装两个值pass和end
    //   pass表示该节点经过几次,end是多少个字符串结尾节点
    //   计算pass时,单个节点向下看为pass值(root节点除外,root表示pass表示有多少个字符串)
    // 3)在建立好前缀树之后
    //    a.查询某个字符串"abc"出现几次,看结尾字符'c'的end值
    //    b.查询以"ab"为前缀,看结尾字符'b'的pass值
    // 4)时间复杂度分析
    //    a.字符串中的每个字符挂载树上,一个字符跳一下,建立前缀树时间复杂度 O(M),M为总的字符数
    //    b.查询一个字符串出现几次,同样,还是一个字符跳一下,时间复杂度O(K),K为字符串长度
    //    c.查询前缀,还是一个字符跳一下,时间复杂度O(K),K为字符串长度
    //  总结:性能上和HashMap一样,还能实现HashMap不能的查询(前缀)

    // 实现一(字串种类不多的情况)
    // 前缀树节点类型 Node1
    public static class Node1 {
        public int pass;
        public int end;
        // 使用数组的形式表是有多少路径
        public Node1[] nexts;

        // char tmp = 'b',tmp - 'a' 值为对应的路径
        public Node1() {
            pass = 0;
            end = 0;

            // 0    a
            // 1    b
            // 2    c
            // ..   ..
            // 25   z

            // nexts[i] == null i方向的路不存在
            // nexts[i] != null i方向的路存在

            // 假定字符串都是小写字母,则一个节点最多26条路
            // 在字符节点很多情况,数据就比较长了,使用实现二:HashMap结构
            nexts = new Node1[26];
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        // 将给定的字符串加入前缀树
        public void insert(String word) {
            if (word == null) {
                return;
            }
            // "abc"-> 'a','b','c'
            char[] str = word.toCharArray();
            // 从root出发
            Node1 node = root;
            // 插入需要++
            node.pass++;

            int path = 0;
            // 从左往右遍历字符数组str('a','b','c')
            for (int i = 0; i < str.length; i++) {
                // 由字符,对应成走向哪条路
                path = str[i] - 'a';
                // 遍历字符,建立相应路径
                if (node.nexts[path] == null) {
                    // 建立节点
                    node.nexts[path] = new Node1();
                }
                // node下移之前,判断是否为null
                // node下移,到新建立的节点上
                node = node.nexts[path];
                node.pass++;
            }
            // 底层最后一个节点,node.end加1
            node.end++;
        }

        // 将给定的字符串从前缀树删除
        // 删除时避免内存泄漏,将p=0和e=0的节点都删除,只让前缀树包含必须有的信息
        public void delete(String word) {
            // 在真的有该字符串情况下删除
            // 不能在修改节后pass值后,发现删除的word不存在,就尴尬了
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node1 node = root;
                // 删除需要--
                node.pass--;
                int path = 0;
                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';
                    // 下方节点的pass-1后为0,则往后的路径都不要了,直接赋值为null
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        // 可以直接结束delete方法了
                        return;
                    }
                    node = node.nexts[path];
                }
                // 有两条重复的"abc",但是只删除一条"abc",调用delete方法也是需要保证是正确的
                // 节点走到最后,中途没有节点pass值为0
                // 执行到最后一个节点,end减1
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        // 顺着字符往下找,若在找的过程中,提前没有路径了,则出现0次,直接return
        // 若能走到最后,将end值返回,则是加入几次
        public int search(String word) {
            if (word == null) {
                // 一次也没有
                return 0;
            }
            char[] chs = word.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串中,有几个是以pre这个字符串作为前缀的
        // 顺着字符往下找,若在找的过程中,提前没有路径了,则出现0次
        // 若能走到前缀的最后一个字符,将pass值返回,则是前缀数量
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node1 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

    // 实现二(字符种类很多)
    public static class Node2 {
        public int pass;
        public int end;
        // 字符种类很多,使用HashMap表示下级的路径
        // Integer,字符转成整型之后的ASCII值
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                // 使用HashMap之后,不需要chs[i]-'a',不再是依靠索引来定位的
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        // HashMap的删除方式,区别于索引
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        // 所有加入的字符串中,有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }


    // for test
    public static class Right {
        private HashMap<String, Integer> box;
        public Right() {
            box = new HashMap<>();
        }

        public void insert(String word) {
            if (!box.containsKey(word)) {
                box.put(word, 1);
            } else {
                box.put(word, box.get(word) + 1);
            }
        }

        public void delete(String word) {
            if (box.containsKey(word)) {
                if (box.get(word) == 1) {
                    box.remove(word);
                } else {
                    box.put(word, box.get(word) - 1);
                }
            }
        }

        public int search(String word) {
            if (!box.containsKey(word)) {
                return 0;
            } else {
                return box.get(word);
            }
        }

        public int prefixNumber(String pre) {
            int count = 0;
            for (String cur : box.keySet()) {
                if (cur.startsWith(pre)) {
                    count += box.get(cur);
                }
            }
            return count;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                    right.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
