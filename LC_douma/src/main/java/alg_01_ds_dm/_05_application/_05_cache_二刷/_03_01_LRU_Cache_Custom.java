package alg_01_ds_dm._05_application._05_cache_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-26 17:29
 * @Version 1.0
 */

public class _03_01_LRU_Cache_Custom<K, V> implements Cache<K, V> {

    // KeyPoint 注意事项
    // 本题若延续 FIFOCache，使用 HashMap + 队列 => 实现 LRUCache 效果不好
    // 原因：当修改的元素在队列中间，则需要先出队和再进队，时间复杂度高
    // 想要支持任意节点，高效移动，移动到表头或者表尾 => 双向链表
    // => 自定义双向链表 + Map(高效地获取中间节点)

    // 定义类，其中 class 不能丢了
    class Node {
        // KeyPoint 注意事项
        // 提交力扣，需要将对应的泛型 K，V，替换成题目要求的数据类型，比如 int
        // 同时返回值，有的也是需要改变的，将 return null，替换成 return -1
        K key;
        V value;
        // 使用双向指针，灵活调整
        Node next;
        Node prev;
    }

    // KeyPoint 通过 HashMap 结构，高效地获取中间节点
    // Map 映射关系
    // key => node.key
    // value => node 节点
    // 1.使用 map 结构，通过 key 快速获取该节点 node，从方便操作 node => O(1)
    // 2.没有 map 支持，想要找到 node 节点则是需要进行遍历才能找到 node 节点 => O(n)
    Map<K, Node> cache;
    int capacity;

    // KeyPoint 哨兵节点
    // => head 和 tail 不存储值，只是作为哨兵(虚拟)节点
    // => 主要为统一链表中表头元素和表尾元素的操作，不需特殊对待表头和表尾，从而简化代码逻辑

    // 1.维护最近才使用的键值对
    // => 链表是从头开始，所以头在左侧
    Node head;

    // 2.维护最久未使用的键值对
    // => 先有头再有尾，所以头在右侧
    Node tail;

    //   最近才使用   最久未使用
    //        ↓          ↓
    //      head        tail
    //        ↓          ↓
    //       k3 ⇆ k2 ⇆ k1
    //        ↓    ↓     ↓
    //       v3    v2    v1

    public _03_01_LRU_Cache_Custom(int capacity) {
        // 创建 head 和 tail，再去设置 head 和 tail
        head = new Node();
        tail = new Node();

        // KeyPoint 初始化过程中：维护 head 和 tail 的串联指针关系
        // 1.将头节点和尾节点连接起来，形成一个闭合的双向链表
        // 2.这样双向链表便可以在头尾两端高效地插入和删除元素

        // => Node ⇆ Node
        //     ↑       ↑
        //    head    tail

        // KeyPoint 赋值操作，使用的 '='，而不是判断 '=='
        head.next = tail;
        tail.prev = head;

        // KeyPoint 区别于：链表判空
        // 1.head.next == tail => 检查链表一端
        // 2.tail.prev == head => 检查链表一端
        // 3.head.next == tail && tail.prev == head => 检查链表两端

        cache = new HashMap<>(capacity);
        this.capacity = capacity;
    }



    // KeyPoint 双向链表基本操作 => 需要掌握
    // KeyPoint 1.将原链表中 node 移动到 Head
    //            ⇆ 先删除 node，再将 node 添加到 head
    private void deleteNodeToHead(Node node) { // O(1)
        // 1. 从双向链表中删除节点 node
        deleteNode(node);
        // 2. 将节点 node 添加到表头 => 将 node 添加到 head.next
        addNewNodeToHead(node);
    }

    // KeyPoint 2. 从双向链表中任意位置，删除节点 delNode
    private void deleteNode(Node delNode) { // O(1)

        // KeyPoint 示意图
        // preNode ⇆ Node ⇆ nextNode

        // 1 确定 prevNode 和 nextNode 节点
        Node preNode = delNode.prev;
        Node nextNode = delNode.next;

        // 2 调整 prevNode 和 nextNode 指针
        preNode.next = nextNode;
        nextNode.prev = preNode;

        // 3 清除 delNode 前后指针
        // KeyPoint 指针设置为空，使用单个 '='，判断是否相等使用 '=='
        delNode.prev = null;
        delNode.next = null;
    }

    // KeyPoint 3.将 newNode 添加到表头 head => 将 newNode 添加到 head.next
    private void addNewNodeToHead(Node newNode) { // O(1)

        // KeyPoint 注意事项
        // 1.节点指针调整双向 => ←，→
        // 2.调整时注意指针先后顺序，避免链表断链

        // KeyPoint 示意图

        //    newNode
        //      ↓
        // head ⇆ Node ⇆ ...
        //

        // 1.newNode 指针
        // => 单纯移动 newNode 的 next 和 prev 指针，不涉及原链表断链操作
        newNode.next = head.next;
        newNode.prev = head;

        // 2.head 指针
        head.next.prev = newNode;
        head.next = newNode;

        // KeyPoint 注意：调整链表先后次序
        // head.next = newNode;
        // head.next.prev = newNode;

        // head.next.prev，依赖前面代码 head.next，head.next 原链表第一个节点
        // 但是经过 head.next = newNode 之后，链表第一个节点已经发生变化了，已经是 newNode
        // 故 head.next.prev 已经不再是原链表第一个节点 prev 指针

    }

    // KeyPoint 4.删除尾节点 => 删除 tail 的前一个节点
    private Node deleteLastNodeFromTail() { // O(1)
        Node lastNode = tail.prev;
        // 删除双向链表中的一个节点，通用操作，代码复用
        deleteNode(lastNode);
        return lastNode;
    }

    @Override
    public V get(K key) { // O(1)
        Node node = cache.get(key);
        // cache 中没有，返回 null
        if (node == null) return null;

        // 将查询到的 node 移动到表头，表头位置是最近才访问的节点
        // KeyPoint 优化
        // 将复用的代码抽取，可能一开始意识不到，后面写着写着，发现代码重复，可以优化代码
        deleteNodeToHead(node);
        // 返回值类型为 V，node != null，将 value 返回
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
                Node delNode = deleteLastNodeFromTail();
                // Map 删除 delNode.key，维护映射关系
                cache.remove(delNode.key);
            }

            // 2. 创建新节点，使用 newNode 以便区分
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;

            // 3. 维护链表和缓存
            cache.put(key, newNode);

            // 最近使用的数据(新建或更新 node)放到表头
            addNewNodeToHead(newNode);
        } else {
            // node 存在，更新 value
            node.value = value;
            // 最近使用的数据(新建或更新 node)放到表头
            deleteNodeToHead(node);
        }
    }

    public static void main(String[] args) {
        _03_01_LRU_Cache_Custom<Integer, Integer> cache = new _03_01_LRU_Cache_Custom<>(3);
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
