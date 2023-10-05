package alg_01_ds_wyj._05_application._05_cache;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 23:52
 * @Version 1.0
 */
public class LRUCache {

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
        return lastNode;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        delNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() == capacity) {
                Node lastNode = delNodeFromTail();
                cache.remove(lastNode.key);
            }
            Node newNode = new Node();
            newNode.value = value;
            newNode.key = key;
            cache.put(key, newNode);
            addNodeToHead(newNode);
        } else {
            node.value = value;
            delNodeToHead(node);
        }
    }
}
