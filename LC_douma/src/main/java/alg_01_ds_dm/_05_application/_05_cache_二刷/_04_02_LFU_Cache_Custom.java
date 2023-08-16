package alg_01_ds_dm._05_application._05_cache_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:05
 * @Version 1.0
 */

public class _04_02_LFU_Cache_Custom {

    // KeyPoint 优化
    // 自己手写一个双向链表，不是使用 Java 内置的 LinkedHashSet
    // 没有使用 K,V

    // 1.定义 node 节点
    // 将 val，count 信息都定义成 Node 属性
    // 从而将 cache 和 keyToCount 这两个 Map 省略
    class Node {
        int key, val, count;
        Node next, prev;

        // KeyPoint 定义了有参构造，若想使用无参构造，需要手动实现
        public Node(int key, int val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
            // prev 和 next 没有设置，则 prev 和 next 都为 null
        }

        // 补充：手动实现无参构造
        public Node() {
        }
    }

    // 2.定义双向链表
    // 基于 Node 节点基础上，又定义 DoubleLinkedList
    // 将操作双向链表的方法都封装到一起
    class DoubleLinkedList {
        // 使用 head 和  tail 节点，统一的删除和新增操作，方便后续代码可以复用
        Node head;
        Node tail;

        public DoubleLinkedList() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        // 1.删除指定节点
        // KeyPoint 注意事项
        // 删除节点，最好是将 node 节点进行返回
        // 但是可以不使用，以免需要使用，结果没有返回值
        private Node deleteNode(Node node) {

            // 1.确定 prevNode 和 nextNode 节点
            Node preNode = node.prev;
            Node nextNode = node.next;

            // 2.调整 prevNode 和 nextNode 指针
            preNode.next = nextNode;
            nextNode.prev = preNode;

            node.prev = null;
            node.next = null;

            return node;

            // 简化代码 => 不推荐
            // node.prev.next = node.next;
            // node.next.prev = node.prev;
        }

        // 2.若 node 被使用，则将 node 放到表头
        // => 若将 node 拼接到表头，则表尾：最久没有使用过的 node
        // => 若将 node 拼接到表尾，则表头：最久没有使用过的 node
        private void addNewNodeToHead(Node newNode) {

            // KeyPoint 链表代码操作
            // 指针 = 节点
            // => newNode.next = head.next;
            // => newNode 的 next 指针 = head 后面一个节点，即为头节点

            // 1.newNode 指针
            // => 单纯移动 newNode 的 next 和 prev 指针，不涉及原链表断链操作
            newNode.next = head.next;
            newNode.prev = head;

            // 2.head 指针
            // 注意事项 => 03_01_LRU_Cache_Custom
            head.next.prev = newNode;
            head.next = newNode;
        }

        // 3.删除尾节点 => 删除 tail 的前一个节点
        private Node deleteLastNodeFromTail() {
            if (isEmpty()) return null;
            Node lastNode = tail.prev;
            return deleteNode(lastNode);
        }

        // 4.判空
        private boolean isEmpty() {

            // 链表判空
            // 1.head.next == tail => 检查链表一端
            // 2.tail.prev == head => 检查链表一端
            // 3.head.next == tail && tail.prev == head => 检查链表两端

            // 比较使用 '=='，赋值使用 '='
            return head.next == tail && tail.prev == head;
        }
    }

    // 每个 key 对应的 Node
    Map<Integer, Node> keyToNode;

    // 每个 count 对应的 KeySet
    // 每个 KeySet 都使用自定义的 DoubleLinkedList 存储，按照最近使用的顺序组织
    Map<Integer, DoubleLinkedList> countToKeySet;

    int capacity;
    int minCount;

    public _04_02_LFU_Cache_Custom(int capacity) {
        keyToNode = new HashMap<>();
        countToKeySet = new HashMap<>();
        this.capacity = capacity;
        minCount = 0;
    }

    public int get(int key) {

        // 注意：capacity 可能为 0，只是健壮性判断，不影响代码的正确性
        // if (capacity == 0) return -1;

        // 访问 key 后，需要维护和更新映射
        // 1.维护 count
        // 2.更新 countToKeySet
        // 3.更新 minCount
        // 4.将 key 添加到 count+1 对应的 KeySet 集合中

        Node node = keyToNode.get(key);
        if (node == null) return -1;

        // 1.维护 count
        int count = node.count;
        node.count = count + 1;

        // 2.更新 countToKeySet
        countToKeySet.get(count).deleteNode(node);

        // 3.更新 minCount
        // 若当前 count 等于 minCount，并且当前 count 没有的 key，那么 minCount++
        // KeyPoint 注意事项
        // 因为涉及 deleteNode，才有可能 countToKeySet.get(count).isEmpty()
        if (count == minCount
                && countToKeySet.get(count).isEmpty()) {
            minCount++;
        }

        // 4.将 key 添加到 count+1 对应的 KeySet 集合中
        addKeyToKeySet(node, count + 1);
        return node.val;
    }

    // 函数功能：将 key 添加到 count+1 对应的 KeySet 集合中
    private void addKeyToKeySet(Node node, int count) {
        if (!countToKeySet.containsKey(count)) {
            countToKeySet.put(count, new DoubleLinkedList());
        }

        // node 被使用，将 node 放到表头
        // count 作为 key 发生变化，对应的 KeySet 也不同，故 add node 可以认为是 add newNode

        // KeyPoint 注意事项
        // => 若将 node 拼接到表头，则表尾：最久没有使用过的 node
        // => 若将 node 拼接到表尾，则表头：最久没有使用过的 node
        countToKeySet.get(count).addNewNodeToHead(node);
    }

    public void put(int key, int value) {

        // 注意：capacity 可能为 0，只是健壮性判断，不影响代码的正确性
        // if (capacity == 0) return;

        // 1.若 keyToNode 存在 key
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            // 更新 key 对应的 value 值
            node.val = value;
            keyToNode.put(key, node);
            // 调用 get 方法，维护数据结构
            get(key);
        } else {
            // 2.若 keyToNode 不存在 key，存在新增的 key 的情况
            // 2.1 若容量达到上限
            // 先清除使用频率最小，且最久未使用的 key
            if (keyToNode.size() == capacity) {
                // 使用频率最小，且最久未使用的 key
                Node removeNode = countToKeySet.get(minCount).deleteLastNodeFromTail();
                // 删除 removeNode.key，对应的 node 一起被删除，不用维护 count
                keyToNode.remove(removeNode.key);
            }
            // 2.2 容量没有达到上限
            // 新增一个缓存中不存在的 key
            Node newNode = new Node(key, value, 1);
            // 维护 keyToNode
            keyToNode.put(key, newNode);
            // 维护 minCount
            minCount = 1;
            // 将 key 添加到 minCount 对应的 KeySet 集合中
            addKeyToKeySet(newNode, 1);
        }
    }
}