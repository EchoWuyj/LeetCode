package alg_02_体系班_zcy.class12;

import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2022-09-25 16:11
 * @Version 1.0
 */
public class Code01_IsCBT {

    // 判断一个树是否是完全二叉树
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 思路:基于二叉树按层遍历:从左往右遍历(队列实现)
    // 不是完全二叉树的两种情况:
    // 1)某个节点Y,有右孩子,没有左孩子 -> 返回false
    // 2)第一次遇到左右孩子不双全(层次遍历遇到的第一个叶节点),接下来遍历的节点都是叶子节点
    public static boolean isCBT1(Node head) {
        if (head == null) {
            // 空树算作完全二叉树
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();

        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;

        // KeyPoint while循环反复使用的变量定义在外面,记住这种写法
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            // 记录左右孩子,后续做判断
            l = head.left;
            r = head.right;
            //  每次遍历到一个节点都去判断是否违反(1)or(2)
            //  1)如果遇到了不双全的节点之后(leaf为true)
            //    只要后面节点中,左or右孩子中有一个不是null,则当前节点不是叶节点
            //  2)有右孩子,没有左孩子
            //  满足(1)or(2)则说明不是完全二叉树
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }
            // 加入左右节点进入队列中
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            // 遍历中对每个节点都要判断开关是否要开启
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    // 方法二(递归实现)
    public static boolean isCBT2(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).isCBT;
    }

    // 对每一棵子树,是否是满二叉树,是否是完全二叉树,高度
    public static class Info {
        public boolean isFull;
        public boolean isCBT;
        public int height;

        public Info(boolean full, boolean cbt, int h) {
            isFull = full;
            isCBT = cbt;
            height = h;
        }
    }

    public static Info process(Node X) {
        if (X == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(X.left);
        Info rightInfo = process(X.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isFull = leftInfo.isFull
                &&
                rightInfo.isFull
                && leftInfo.height == rightInfo.height;

        boolean isCBT = false;
        if (isFull) {
            isCBT = true;
        } else { // 以x为头整棵树,不满
            if (leftInfo.isCBT && rightInfo.isCBT) {

                if (leftInfo.isCBT
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        &&
                        rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                if (leftInfo.isFull
                        && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info(isFull, isCBT, height);
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
