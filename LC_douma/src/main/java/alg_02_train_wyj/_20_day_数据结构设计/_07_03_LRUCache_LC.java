package alg_02_train_wyj._20_day_数据结构设计;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-06 19:26
 * @Version 1.0
 */
public class _07_03_LRUCache_LC {

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

    public _07_03_LRUCache_LC(int capacity) {
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

    private void addNewNodeToHead(Node newNode) {
        newNode.next = head.next;
        newNode.prev = head;
        head.next.prev = newNode;
        head.next = newNode;
    }

    private void deleteNode(Node delNode) {
        Node prevNode = delNode.prev;
        Node nextNode = delNode.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        delNode.next = null;
        delNode.prev = null;
    }

    private Node deleteLastNodeFromTail() {
        Node lastNode = tail.prev;
        deleteNode(lastNode);
        return lastNode;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        deleteNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            if (cache.size() == capacity) {
                Node delNode = deleteLastNodeFromTail();
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
