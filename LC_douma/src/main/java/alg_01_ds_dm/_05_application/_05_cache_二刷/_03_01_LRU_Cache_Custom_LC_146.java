package alg_01_ds_dm._05_application._05_cache_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 14:16
 * @Version 1.0
 */

// 手写 LRU
// HashMap + 双向链表
// HashMap：通过 key-value 高效获取中间节点
// 双向链表：动态调整访问后的节点
public class _03_01_LRU_Cache_Custom_LC_146 {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    int capacity;
    Map<Integer, Node> cache;
    Node head;
    Node tail;

    public _03_01_LRU_Cache_Custom_LC_146(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        cache = new HashMap<>();
        this.capacity = capacity;
    }

    private void deleteNodeToHead(Node node) {
        deleteNode(node);
        addNewNodeToHead(node);
    }

    private void deleteNode(Node delNode) {
        Node prevNode = delNode.prev;
        Node nextNode = delNode.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        delNode.next = null;
        delNode.prev = null;
    }

    private void addNewNodeToHead(Node node) {
        // node
        node.next = head.next;
        node.prev = head;
        // head
        head.next.prev = node;
        head.next = node;
    }

    private Node deleteLastNodeFromTail() {
        Node lastNode = tail.prev;
        deleteNode(lastNode);
        return lastNode;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        // node != null
        deleteNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() == capacity) {
                Node delNode = deleteLastNodeFromTail();
                // 获取最后一个节点 key，将其删除
                cache.remove(delNode.key);
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            cache.put(key, newNode);
            addNewNodeToHead(newNode);
        } else {
            node.value = value;
            deleteNodeToHead(node);
        }
    }
}
