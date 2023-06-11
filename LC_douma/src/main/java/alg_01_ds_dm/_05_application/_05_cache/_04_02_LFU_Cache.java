package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:05
 * @Version 1.0
 */

// KeyPoint 优化：自己手写一个双向链表，不是使用 Java 内置的 LinkedHashSet
public class _04_02_LFU_Cache {

    // 1.自定义 node 节点
    // 将 val，count 信息都定义成属性
    class Node {
        int key, val, count;
        Node next, prev;

        // 手动实现无参构造
        public Node() {
        }

        // 定义了有参构造，若想使用无参构造，需要手动实现
        public Node(int key, int val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    // 2.定义双向链表
    class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        // 1.删除指定节点
        // 最好是将 node 节点进行返回，但是可以不使用
        private Node removeNode(Node node) {

//            // 1 确定 prevNode 和 nextNode 节点
//            Node preNode = node.prev;
//            Node nextNode = node.next;
//
//            // 2 调整 prevNode 和 nextNode 指针
//            preNode.next = nextNode;
//            nextNode.prev = preNode;

            // 删除节点操作 => 前 4 行代码整合成 2 行代码
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.prev = null;
            node.next = null;
            return node;
        }

        // 2.对应 node 被使用，则将 node 放到表头
        // 2.1 若将 node 拼接到表头，则表尾，则是最久没有使用过的 node
        // 2.2 若将 node 拼接到表尾，则表头，则是最久没有使用过的 node
        private void addNodeToHead(Node node) {

            // 指针 -> 节点
            // 1. = 前面 -> 指针
            // 2. = 后面 -> 节点

            // 指针调整是双向 => ←，→

            // 1. node 指针
            node.next = head.next;
            node.prev = head;

            // 2. head 指针
            head.next.prev = node;
            head.next = node;

            // KeyPoint 错误写法
            // head.next 原链表第一个节点，经过 head.next = node 之后
            // head.next.prev 不再是：原链表第一个节点 prev 指针
            // 后续代码 head.next.prev，依赖前面代码 head.next
//        head.next = node;
//        head.next.prev = node;

        }

        // 3.移除 tail 的前一个节点(删除尾节点)
        private Node removeTailNode() {
            if (isEmpty()) return null;
            // 因为使用 head 和  tail 节点，统一的删除和新增操作，所以可以直接调用，删除节点的方法
            return removeNode(tail.prev);
        }

        // 4.判空
        private boolean isEmpty() {
            // 比较使用 ==
            return head.next == tail && tail.prev == head;

            // 链表判空
            // 1.head.next == tail => 检查链表一端
            // 2.tail.prev == head => 检查链表一端
            // 3.head.next == tail && tail.prev == head => 检查链表两端
        }
    }

    // 每个 key 对应的 Node 的映射
    private Map<Integer, Node> keyToNode;

    // 每个 count 对应的所有的 keys (按照最近使用的顺序组织)
    private Map<Integer, DoubleLinkedList> countToKeys;

    private int capacity;

    // 记录整个缓存中所有 key 中使用最小的次数
    private int minUsedCount;

    public _04_02_LFU_Cache(int capacity) {
        keyToNode = new HashMap<>();
        countToKeys = new HashMap<>();
        this.capacity = capacity;
        minUsedCount = 0;
    }

    public int get(int key) {
        // 注意：capacity 可能为 0，只是健壮性判断，不影响代码的正确性
//        if (capacity == 0) return -1;

        Node node = keyToNode.get(key);
        if (node == null) return -1;

        // 维护这个 key 对应的 count
        int count = node.count;
        // 1.从这个 key 目前对应的 count 的集合中删除掉这个 key
        countToKeys.get(count).removeNode(node);
        node.count = count + 1;

        // 2.更新最小使用的次数
        // 如果当前的 count 等于最小次数，并且当前的 count 没有的 key，那么将最小次数加 1
        if (count == minUsedCount
                && countToKeys.get(count).isEmpty()) {
            minUsedCount++;
        }

        // 3. 将 node 记录到 count + 1 中的集合中
        putUsedCount(node, count + 1);

        return node.val;
    }

    private void putUsedCount(Node node, int count) {
        if (!countToKeys.containsKey(count)) {
            countToKeys.put(count, new DoubleLinkedList());
        }
        // 将 node 添加到表头
        countToKeys.get(count).addNodeToHead(node);
    }

    public void put(int key, int value) {
        // 注意：capacity 可能为 0，只是健壮性判断，不影响代码的正确性
//        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            // 更新 key 对应的 value 值
            node.val = value;
            keyToNode.put(key, node);
            // 更新 key 对应的 count 值
            get(key);
            return;
        }

        // Map 中 key 已经达到 capacity
        if (keyToNode.size() == capacity) {
            // 删除最久未使用的 key
            Node removeNode = countToKeys.get(minUsedCount).removeTailNode();
            keyToNode.remove(removeNode.key);
        }

        // 新增一个缓存中不存在的 key
        Node newNode = new Node(key, value, 1);
        keyToNode.put(key, newNode);

        // 将 key 记录到 minUsedCount 中的集合中
        minUsedCount = 1;
        putUsedCount(newNode, 1);
    }
}