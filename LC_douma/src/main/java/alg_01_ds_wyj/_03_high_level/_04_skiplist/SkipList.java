package alg_01_ds_wyj._03_high_level._04_skiplist;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-20 16:15
 * @Version 1.0
 */
public class SkipList<E extends Comparable<E>> {

    private static final int MAX_LEVEL = 16;

    private class Node<E extends Comparable<E>> {
        E data;
        Node<E>[] nextNodes;
        int maxIndexLevel = 0;

        public Node() {
            this.data = null;
            nextNodes = new Node[MAX_LEVEL];
        }

        public Node(E data) {
            this.data = data;
            nextNodes = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{ data: ");
            sb.append(data);
            sb.append("; levels: ");
            sb.append(maxIndexLevel);
            sb.append(" }");
            return sb.toString();
        }
    }

    private int levelCount;
    private Node<E> dummyHead;
    private Random random = new Random();

    public SkipList() {
        this.levelCount = 1;
        this.dummyHead = new Node<E>();
    }

    public boolean contains(E e) {
        return get(e) != null;
    }

    //  KeyPoint 查询操作
    public Node<E> get(E e) {
        Node<E> cur = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (cur.nextNodes[i] != null && cur.nextNodes[i].data.compareTo(e) < 0) {
                cur = cur.nextNodes[i];
            }
        }

        if (cur.nextNodes[0] != null && cur.nextNodes[0].data.compareTo(e) == 0) {
            return cur.nextNodes[0];
        }
        return null;
    }

    // 随机层数
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    // KeyPoint 插入操作
    public void add(E e) {

        int level = dummyHead.nextNodes[0] == null ? 1 : randomLevel();

        Node<E>[] preNodes = new Node[level];
        for (int i = 0; i < level; i++) {
            preNodes[i] = dummyHead;
        }

        Node<E> pre = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (pre.nextNodes[i] != null && pre.nextNodes[i].data.compareTo(e) < 0) {
                pre = pre.nextNodes[i];
            }

            if (i < level) preNodes[i] = pre;
        }

        Node<E> newNode = new Node<>(e);
        newNode.maxIndexLevel = level;
        for (int i = 0; i < level; i++) {
            newNode.nextNodes[i] = preNodes[i].nextNodes[i];
            preNodes[i].nextNodes[i] = newNode;
        }

        if (levelCount < level) levelCount = level;
    }

    // KeyPoint 删除节点
    public void remove(E e) {
        Node<E>[] preNodes = new Node[levelCount];
        Node<E> pre = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (pre.nextNodes[i] != null && pre.nextNodes[i].data.compareTo(e) < 0) {
                pre = pre.nextNodes[i];
            }
            preNodes[i] = pre;
        }

        if (pre.nextNodes[0] != null && pre.nextNodes[0].data.compareTo(e) == 0) {
            for (int i = levelCount - 1; i > 0; i--) {
                Node<E> delNode = preNodes[i].nextNodes[i];
                if (delNode != null && delNode.data.compareTo(e) == 0) {
                    preNodes[i].nextNodes[i] = delNode.nextNodes[i];
                    delNode.nextNodes[i] = null;
                }
            }
        }
    }

    // KeyPoint 4 显示
    public void display() {
        Node<E> cur = dummyHead;
        while (cur.nextNodes[0] != null) {
            // KeyPoint 打印输出，特别注意 print 和 println
            if (cur.nextNodes[0].nextNodes[0] != null) {
                System.out.print(cur.nextNodes[0].data + ",");
            } else {
                System.out.print(cur.nextNodes[0].data);
            }
            cur = cur.nextNodes[0];
        }
        System.out.println();
    }
}
