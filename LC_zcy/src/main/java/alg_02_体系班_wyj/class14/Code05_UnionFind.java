package alg_02_体系班_wyj.class14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-10-01 10:57
 * @Version 1.0
 */
public class Code05_UnionFind {

    public static class Node<V> {
        public V value;

        public Node(V value) {
            this.value = value;
        }
    }

    public static class UnionFind<V> {
        public HashMap<V, Node<V>> nodes;
        public HashMap<Node<V>, Node<V>> parents;
        public HashMap<Node<V>, Integer> sizeMap;

        // 初始化
        public UnionFind(List<V> values) {
            nodes = new HashMap<>();
            parents = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : values) {
                Node<V> node = new Node<>(value);
                nodes.put(value, node);
                parents.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        // 给你一个节点,请你往上到不能再往上,把代表节点返回
        public Node<V> findFather(Node<V> cur) {
            Stack<Node<V>> path = new Stack<>();
            while (cur != parents.get(cur)) {
                path.push(cur);
                cur = parents.get(cur);
            }
            // cur == parents.get(cur)
            while (!path.isEmpty()) {
                parents.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(nodes.get(a)) == findFather(nodes.get(b));
        }

        // 将两个样本a,e所在集合的全体变成一个集合
        public void union(V a, V b) {
            // 代表节点
            Node<V> aHead = findFather(nodes.get(a));
            Node<V> bHead = findFather(nodes.get(b));
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                Node<V> big = (aSetSize > bSetSize) ? aHead : bHead;
                Node<V> small = (big == aHead) ? bHead : aHead;
                parents.put(small, big);
                sizeMap.put(big, aSetSize + bSetSize);
                sizeMap.remove(small);
            }
        }

        public int sets() {
            return sizeMap.size();
        }
    }

    // for test
    public static void main(String[] args) {
        // list有1,2,3,4,5
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        UnionFind<Integer> unionFind = new UnionFind<>(list);

        System.out.println(unionFind.isSameSet(3, 4));
        unionFind.union(3, 4);
        System.out.println(unionFind.isSameSet(3, 4));
        unionFind.union(1, 3);
        System.out.println(unionFind.isSameSet(1, 3));
        System.out.println(unionFind.sets());
    }
}
