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

// 完整代码
class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    Node head;
    Node tail;
    Map<Integer, Node> cache;
    int capacity;

    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        cache = new HashMap<>();
        this.capacity = capacity;
    }

    // 双向链表操作
    public void delNodeToHead(Node node) {
        delNode(node);
        addNodeToHead(node);
    }

    public void delNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        node.next = null;
        node.prev = null;
    }

    public void addNodeToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public Node delNodeFromTail() {
        Node lastNode = tail.prev;
        delNode(lastNode);
        // 删除节点返回
        return lastNode;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // 更新双向链表位置
        delNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() == capacity) {
                // 获取最后一个节点
                Node lastNode = delNodeFromTail();
                // 删除对应key
                cache.remove(lastNode.key);
            }
            // 创建新的节点
            Node newNode = new Node();
            newNode.value = value;
            newNode.key = key;
            // 维护 cache ，put 对应是 newNode
            cache.put(key, newNode);
            // 维护链表
            addNodeToHead(newNode);
        } else {
            node.value = value;
            // 更新双向链表位置
            delNodeToHead(node);
        }
    }
}
