package alg_02_体系班_zcy.class13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Wuyj
 * @DateTime 2022-09-26 15:49
 * @Version 1.0
 */
public class Code03_lowestAncestor {

    // 给定一颗二叉树,和二叉树的两个节点,计算出这两个节点的最低公公祖先
    // 注意:一个节点也可以是它自己的祖先

    // 说明:
    // 1)所有节点的值都是唯一的
    // 2)o1,o2为不同节点且均存在于给定的二叉树中

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 方法一(使用容器解决)
    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key是子节点,value是父节点
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);

        // 填充Map的方法
        fillParentMap(head, parentMap);

        HashSet<Node> o1Set = new HashSet<>();
        // 第一个节点o1
        Node cur = o1;
        // 因为一个节点也可以是它自己的祖先,所以需要将o1加入集合中
        o1Set.add(cur);

        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }

        // 另外一个节点o2
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    // 先序遍历,递归实现填充Map
    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        // 先左再右
        if (head.left != null) {
            // 设置子父节点的映射
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    // 方法二()
    public static Node lowestAncestor2(Node head, Node a, Node b) {
        return process(head, a, b).ans;
    }

    public static class Info {
        public boolean findA;
        public boolean findB;
        public Node ans;

        // KeyPoint 递归过程,X可能是任意节点,所以需要可能性都列清楚

        // 目标:给定要找的节点a和b,作为全局变量给你的,以X为头的整颗树上,a和b最初汇聚在那里?
        // 1)汇聚点与当前root节点无关
        //   a.左边有汇聚点
        //   b.右边有汇聚点
        //   c.a,b不全(a或b中有一个节点不在当前树中)
        // 2)汇聚点与当前root节点有关
        //   a.左边有a,b其中之一,右边有另一个
        //   b.root本身就是a,b其中之一,子树中有另一个
        //  => Info:有无a,有无b,有无a,b汇聚的公共祖先

        public Info(boolean findA, boolean findB, Node ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }

    public static Info process(Node x, Node a, Node b) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);

        boolean findA = (x == a) || leftInfo.findA || rightInfo.findA;
        boolean findB = (x == b) || leftInfo.findB || rightInfo.findB;

        Node ans = null;
        if (leftInfo.ans != null) {
            // 可能性1
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            // 可能性2
            ans = rightInfo.ans;
        } else {
            // 可能性3
            if (findA && findB) {
                ans = x;
            }
        }
        return new Info(findA, findB, ans);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
