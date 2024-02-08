package alg_03_high_frequency._01_codetop_2024_01;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 18:40
 * @Version 1.0
 */
public class _03_146_LRUCache {

}

// LRU 缓存
// 双向链表 + HashMap
class LRUCache {

    // 双向链表节点
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    // 哨兵节点
    Node head;
    Node tail;
    // 数值 和 节点 的映射
    Map<Integer, Node> cache;
    int capacity;

    // 初始化过程
    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        // 指针串联
        head.next = tail;
        tail.prev = head;
        cache = new HashMap<>();
        this.capacity = capacity;
    }

    // 双向链表操作(4个操作：3 删 (del) 1 add)
    // 复合方法
    public void delNodeToHead(Node node) {
        delNode(node);
        addNodeToHead(node);
    }

    // 原子方法
    public void delNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        node.next = null;
        node.prev = null;
    }

    // 原子方法
    public void addNodeToHead(Node node) {
        // node 操作
        // 先调整 node 的 next 指针，再调整 node 的 prev 指针
        node.next = head.next;
        node.prev = head;
        // head 和 head 下个节点的操作
        // 注意：必须得是先 prev，再是 next
        head.next.prev = node;
        head.next = node;
    }

    // 复合方法
    public Node delNodeFromTail() {
        Node lastNode = tail.prev;
        delNode(lastNode);
        // 返回删除节点
        return lastNode;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // 更新节点在双向链表中的位置
        delNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            // cache 已满
            if (cache.size() == capacity) {
                // 获取最后一个节点
                Node lastNode = delNodeFromTail();
                // 删除对应 key
                // cache 中 KV 中的 K 和 Node 中 key 保持一致
                cache.remove(lastNode.key);
            }
            // cache 没有满，创建新的节点
            Node newNode = new Node();
            newNode.value = value;
            newNode.key = key;
            // 维护 cache
            // put 操作 key 对应是 newNode
            cache.put(key, newNode);
            // 维护链表：新增节点加入双向链表开头位置
            addNodeToHead(newNode);
        } else {
            node.value = value;
            // 更新节点在双向链表中的位置
            delNodeToHead(node);
        }
    }
}
