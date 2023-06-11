package alg_01_ds_dm._03_high_level._04_skiplist;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 22:05
 * @Version 1.0
 */
public class SkipList<E extends Comparable<E>> {

    // 表示跳表的最大高度，包括原始链表这一层(16为经验值)
    private static final int MAX_LEVEL = 16;

    // 跳表的节点，每个节点记录了当前节点数据和所在层的下一个节点
    private class Node<E extends Comparable<E>> {
        E data;

        // 表示当前节点的所有层的下一节点，因为有多个故使用数组存储
        // nextNodes[0] 表示当前节点在第一层的下一个节点 => 原始链表层
        // nextNodes[1] 表示当前节点在第二层的下一个节点
        // nextNodes[15] 表示当前节点在第十六层的下一个节点 => 从上层切换到下层，就是数组下标减去 1
        Node<E>[] nextNodes;

        // KeyPoint Node 节点的属性 maxIndexLevel，每个 Node 的 maxIndexLevel 都是不一样的
        // 记录当前节点维护的'索引节点'的最大高度，索引层数是不包含原始链表的那一层的
        // PPT 上 15 节点维护的 3 层索引
        int maxIndexLevel = 0;

        Node(E data) {
            this.data = data;
            // MAX_LEVEL 是固定值，最大为 16
            nextNodes = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append("{ data: ");
            builder.append(data);
            builder.append("; levels: ");
            builder.append(maxIndexLevel);
            builder.append(" }");
            return builder.toString();
        }
    }

    // KeyPoint SkipList 跳表的属性 levelCount，标识跳表的高度
    // 跳表最大高度是 16，但是实际上高度不一定达到 16
    // 表示当前跳表'实际'最大的高度(包含了原始链表层，所以默认初始值是 1)
    private int levelCount;

    // 虚拟头头节点
    private Node<E> dummyHead;

    // 产生随机数
    private Random random = new Random();

    // 空参构造方法
    public SkipList() {
        // 跳表的高度是包含原始链表层的，所以默认初始值为 1
        this.levelCount = 1;
        // KeyPoint 这里 node 没有加上 <E>，故可以直接将 -1 传入
        this.dummyHead = new Node(-1);
    }

    // 判断是否存在
    public boolean contains(E e) {
        // get(e) != null 整体成立，则返回 true
        return get(e) != null;
    }

    // KeyPoint 1 查询操作
    public Node<E> get(E e) {
        Node<E> curr = dummyHead;
        // 从最高一层往下，一层一层的寻找查找元素所在的区间，直到找到 e 的前一个节点
        for (int i = levelCount - 1; i >= 0; i--) {
            // 每层都尽量向前找，若 curr.nextNodes[i].data.compareTo(e) >= 0
            // 去下一层再找，直到最原始的链表
            // curr 不为 null，才去进行比较，当前值 < e，cur 后移
            while (curr.nextNodes[i] != null && curr.nextNodes[i].data.compareTo(e) < 0) {
                curr = curr.nextNodes[i];
            }
        }

        // for 循环结束之后，必然是到原始链表层，levelCount = 0
        // 同时从 while 循环出来应该是 curr.nextNodes[i].data.compareTo(e) >= 0 再去进一步判读是否相等
        // KeyPoint 方法调用之前必须判断是否为 null，养成这样好习惯
        if (curr.nextNodes[0] != null
                && curr.nextNodes[0].data.compareTo(e) == 0) {
            return curr.nextNodes[0];
        }

        // 没有找到
        return null;
    }

    // KeyPoint 随机函数 => 计算插入一个节点时，需要维护多少层索引(动态维护索引节点)
    // 1 通过随机数，从概率上来保证跳表的 索引大小和数据大小平衡性，不至于性能过度退化
    // 2 随机 level 次，如果是奇数层数 +1，防止伪随机
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; i++) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }

        // 经过上面 for 循环，导致跳表实际上每个节点索引层数都是不一样的,索引层数都是通过随机函数随机出来的
        return level;
    }

    // KeyPoint 2 插入操作
    public void add(E e) {

        // KeyPoint
        //  代码实现上不是严格按照每 2 个节点，抽取一索引节点，若是这样插入性能是很差的
        //  为了使得插入性能也很快，对 level 取值进行随机取值，动态维护索引节点 => 保证跳表的平衡性

        // 原始链表为 null，此时不需要维护索引
        int level = dummyHead.nextNodes[0] == null ? 1 : randomLevel();

        // 先将 level 层的前一个节点都初始化为虚拟头节点
        Node<E>[] prevNodes = new Node[level];
        for (int i = 0; i < level; i++) {
            // 从 dummyHead 往后进行查找
            prevNodes[i] = dummyHead;
        }

        // 1 找到节点要插入的位置的前一个节点 prev
        Node<E> prev = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (prev.nextNodes[i] != null && prev.nextNodes[i].data.compareTo(e) < 0) {
                prev = prev.nextNodes[i];
            }

            // 维护每一层的前一个节点，使用 if 判断防止索引越界
            // 若 level = 2，则对应索引 0,1
            if (i < level) prevNodes[i] = prev;
        }

        // 2 在每一层的正确的位置插入新节点
        Node<E> newNode = new Node<>(e);
        // 更新 newNode 的 maxIndexLevel 高度
        newNode.maxIndexLevel = level;
        for (int i = 0; i < level; i++) {
            // 每一层 newNode 指向 pre 的后一个节点
            newNode.nextNodes[i] = prevNodes[i].nextNodes[i];
            // 每层前一个节点指向 newNode
            prevNodes[i].nextNodes[i] = newNode;
        }

        // 更新链表的高度，level 是通过随机值产生的，可能比 levelCount 要大，故需要进行 if 判断
        if (levelCount < level) levelCount = level;
    }

    // KeyPoint 3 删除操作
    public void remove(E e) {
        // 1. 找到需要删除节点的前一个节点，以及删除节点对应的索引节点的前一个索引节点
        // KeyPoint 因为不知道删除的元素 e 在跳表的那一层，故需要记录跳表所有层数的前一个节点
        Node<E>[] prevNodes = new Node[levelCount];
        Node<E> prev = dummyHead;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (prev.nextNodes[i] != null
                    && prev.nextNodes[i].data.compareTo(e) < 0) {
                prev = prev.nextNodes[i];
            }
            // 记录每层要删除节点的前一个节点
            prevNodes[i] = prev;
        }

        // 2. 对每一层进行删除节点
        // 在删除之前，需要先判断待删除节点 e 是否存在，存在的话才执行删除
        if (prev.nextNodes[0] != null
                && prev.nextNodes[0].data.compareTo(e) == 0) {
            // 在存在的前提下，对每一层进行删除节点
            for (int i = levelCount - 1; i >= 0; i--) {
                Node<E> delNode = prevNodes[i].nextNodes[i];
                // 每层不一定有 delNode，同时 delNode 得是真正要删除的元素 e
                if (delNode != null && delNode.data.compareTo(e) == 0) {
                    prevNodes[i].nextNodes[i] = delNode.nextNodes[i];
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
