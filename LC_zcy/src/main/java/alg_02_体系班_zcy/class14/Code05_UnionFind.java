package alg_02_体系班_zcy.class14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code05_UnionFind {

    // 并查集(支持集合合并和查询的结构)
    // 样本 a,b,c,d,e,f
    // {a},{b},{c},{d},{e},{f} 并查集是维持一堆集合的结构
    // 并查集提供2个操作:
    // 1) boolean isSameSet(a,e) 查询两个样本是否在一个集合
    // 2) void union(a,e) 将两个样本(a,e)所在集合的全体变成一个集合
    // 关键:在N个样本的情况下,调用isSameSet和union很频繁,能不能做到均摊下来,单次的时间复杂度O(1)

    // 并查集结构设计
    // 1)给样本类V型包了一层Node,且只有一条指针指向自己
    // 2)集合代表节点:一个节点往上找,找到不能再往上的节点
    // 3)判断两个样本(a,e)是否是一个集合?
    //    a往上到不能再往上的节点,a节点
    //    e往上到不能再往上的节点,e节点
    //   => 两者的代表节点不同,则a所在集合和e所在集合不在同一个集合中
    // 4)将a,e所在集合合并?
    //    找a所在集合的代表节点,a节点,同时该集合大小1
    //    找e所在集合的代表节点,e节点,同时该集合大小1
    //   => 修改e指针,指向a节点(这样方便判断两个节点是否同一个集合)
    //    修改原则:
    //      a.只是修改代表节点的指针
    //      b.小节点挂大节点
    //      c.沿途节点扁平化
    // 精髓:使用代表节点确定时那个集合

    // 结论:
    // 在N个样本的情况下,findFather方法调用次数到达O(N)以上,则单次查询代价为O(1)

    // 给样本类V型包了一层Node,避免HashMap的值传递
    public static class Node<V> {
        V value;

        // 定义Node泛型,即能传入的数据类型,类比Integer
        public Node(V v) {
            value = v;
        }
    }

    public static class UnionFind<V> {
        // 样本类V和节点Node的映射关系
        public HashMap<V, Node<V>> nodes;
        // key子节点,value父节点,使用map代替了指针
        public HashMap<Node<V>, Node<V>> parents;
        // 只有集合的代表节点才会在sizeMap中留下记录
        public HashMap<Node<V>, Integer> sizeMap;

        // 初始化
        // 形参需要传入样本集合,从而生成每一个小样本集合
        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            // 遍历初始化,只有一个样本的集合
            for (V cur : values) {
                // 数据类型是V,对应的变量名是cur
                Node<V> node = new Node<>(cur);
                nodes.put(cur, node);
                // 指针指向自己
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点,请你往上到不能再往上,把代表节点返回
        // KeyPoint private 修饰符
        //  被private关键字修饰的内容,不可以被外界所访问,只能在本类中有效
        //  因为main方法也是和UnionFind类在同一个类中的,所以可以调用
        private Node<V> findFather(Node<V> cur) {
            // 使用栈记录沿途的样本元素
            Stack<Node<V>> path = new Stack<>();
            // cur的父不是自己,说明没有到顶
            while (cur != parents.get(cur)) {
                // 沿途节点入栈
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur == parents.get(cur)
            while (!path.isEmpty()) {
                // 沿途节点扁平化,此时cur已经是代表节点了
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        // a,b是样本集合values中元素
        public boolean isSameSet(V a, V b) {
            // 比较a,b所在集合的代表节点即可
            // 需要通过nodes.get()找到相应的node节点
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        // 将两个样本a,e所在集合的全体变成一个集合
        public void union(V a, V b) {
            // 代表节点
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            // 若代表节点不同,则不是同一个集合,则是需要合并的
            // 比较的是内存地址,使用的是==,不是equals()方法
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                // 大,小集合头部重定向
                // 确定aSetSize和bSetSize谁大谁小
                Node<V> big = (aSetSize >= bSetSize) ? aHead : bHead;
                Node<V> small = (big == aHead) ? bHead : aHead;
                // 小集合代表节点指向大集合代表节点
                parents.put(small, big);
                // 更新大集合的size
                sizeMap.put(big, aSetSize + bSetSize);
                // 移除小集合
                sizeMap.remove(small);
            }
        }

        // 获取集合个数
        public int sets() {
            return sizeMap.size();
        }
    }

    public static void main(String[] args) {
        // list有1,2,3,4,5
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        UnionFind<Integer> unionFind = new UnionFind<>(list);

        // {1},{2},{3},{4},{5}
        System.out.println(unionFind.isSameSet(3, 4)); // false
        // {1},{2},{3,4},{5}
        unionFind.union(3, 4);
        System.out.println(unionFind.isSameSet(3, 4)); // true
        // {2},{1,3,4},{5}
        unionFind.union(1, 3);
        System.out.println(unionFind.isSameSet(1, 3)); // true
        System.out.println(unionFind.sets()); // 3
    }
}
