package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:29
 * @Version 1.0
 */

// 本题使用 HashMap + 队列 => 实现 LRUCache 效果不好
// 原因：当修改的元素在队列中间，则需要先出队和再进队，时间复杂度高
// 想要：支持任意节点，高效移动，移动到表头或者表尾 => 双向链表
public class _03_01_LRU_Cache<K, V> implements Cache<K, V> {

    // class 不能丢了
    class Node {
        // KeyPoint 小 tips
        // 提交力扣，需要将对应的泛型 K，V，替换成题目要求的数据类型，比如 int
        // 同时返回值，有的也是需要改变的，将 return null，替换成 return -1
        K key;
        V value;
        // 使用双向指针，灵活调整
        Node next;
        Node prev;
    }

    // 通过 HashMap 结构，高效地获取中间节点
    // 1.使用 map 结构，通过 key 快速获取该节点 node，从方便操作 node => O(1)
    // 2.没有 map 支持，想要找到 node 节点则是需要进行遍历才能找到 node 节点 => O(n)
    private Map<K, Node> cache;
    private int capacity;

    // head 和 tail 不存储值，只是作为哨兵(虚拟)节点
    // => 主要为统一链表中表头元素和表尾元素的操作，不需特殊对待表头和表尾，从而简化代码逻辑

    // 1.维护最近使用的键值对 => 链表是从头开始，所以头在左侧
    private Node head;
    // 2.维护最久使用的键值对 => 先有头再有尾，所以头在右侧
    private Node tail;

    public _03_01_LRU_Cache(int capacity) {
        head = new Node();
        tail = new Node();

        // 初始化过程中维护 head 和 tail 的串联指针关系，初始化时使用
        head.next = tail;
        tail.prev = head;

        // 区别于：链表判空
        // 1.head.next == tail => 检查链表一端
        // 2.tail.prev == head => 检查链表一端
        // 3.head.next == tail && tail.prev == head => 检查链表两端

        cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }

    @Override
    public V get(K key) { // O(1)
        Node node = cache.get(key);
        if (node == null) return null;
        // 将查询到的 node 移动到表头，表头位置是最近才访问的节点
        // => 将复用的代码抽取，可能一开始意识不到，后面写着写着，发现代码重复，可以优化代码
        moveNodeToHead(node);
        // node != null，将 value 返回
        return node.value;
    }

    @Override
    public void put(K key, V value) { // O(1)
        Node node = cache.get(key);
        // node 不存在，需要 put 操作
        if (node == null) {
            // 1. 判断缓存容量大小，淘汰最久未使用的节点
            if (cache.size() == capacity) {
                // 删除节点需要返回节点，通过 node 中的 key 删除 cache 的 key
                Node delNode = removeTailNode();
                // Map 删除 delNode.key，维护映射关系
                cache.remove(delNode.key);
            }

            // 2. 创建新节点，使用 newNode 以便区分
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            // 3. 维护链表和缓存
            cache.put(key, newNode);

            // 最近使用的数据 (新建或更新 node) 放到表头
            addNodeToHead(newNode);
        } else {
            //  node 存在，更新 value
            node.value = value;
            // 最近使用的数据 (新建或更新 node) 放到表头
            moveNodeToHead(node);
        }
    }

    // KeyPoint 双向链表基本操作
    // 1.将 Node 移动到 Head
    private void moveNodeToHead(Node node) { // O(1)
        // 1. 从双向链表中删除节点 node
        removeNode(node);
        // 2. 将节点 node 添加到表头(将 node 添加到 head.next)
        addNodeToHead(node);
    }

    // 2. 从双向链表中删除节点 node
    private void removeNode(Node node) { // O(1)

        // 1 确定 prevNode 和 nextNode 节点
        Node preNode = node.prev;
        Node nextNode = node.next;

        // 2 调整 prevNode 和 nextNode 指针
        preNode.next = nextNode;
        nextNode.prev = preNode;

        // 3 清除 node 前后指针
        // 指针设置为空，使用单个 '='，判断是否相等使用 '=='
        node.prev = null;
        node.next = null;
    }

    // 3.将 node 添加到 head (将 node 添加到 head.next)
    private void addNodeToHead(Node node) { // O(1)

        // 注意：节点指针调整都是来去成对出现的，调整时注意指针先后顺序，避免断链
        // 1 node 指针
        node.next = head.next;
        node.prev = head;

        // 2 head 指针

        // KeyPoint 错误写法
        // head.next 原链表第一个节点，经过 head.next = node 之后
        // head.next.prev 不再是：原链表第一个节点 prev 指针

        // 后续代码 head.next.prev，依赖前面代码 head.next
//        head.next = node;
//        head.next.prev = node;

        head.next.prev = node;
        head.next = node;
    }

    // 4.移除 tail 的前一个节点(删除尾节点)
    private Node removeTailNode() { // O(1)
        Node delNode = tail.prev;
        // 删除双向链表中的一个节点，通用操作，代码复用
        removeNode(delNode);
        return delNode;
    }

    public static void main(String[] args) {
        _03_01_LRU_Cache<Integer, Integer> cache = new _03_01_LRU_Cache<>(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(3)); // 3
        cache.put(2, 5);
        cache.put(5, 6);
        System.out.println(cache.get(4)); // null
    }
}
