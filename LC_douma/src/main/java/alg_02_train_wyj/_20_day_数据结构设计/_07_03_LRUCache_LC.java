package alg_02_train_wyj._20_day_数据结构设计;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-08-06 19:26
 * @Version 1.0
 */
public class _07_03_LRUCache_LC {

}

class LRU {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    Node tail;
    Node head;
    Map<Integer, Node> cache;
    int capacity;

    public LRU(int capacity) {
        tail = new Node();
        head = new Node();
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
        node.prev = null;
        node.next = null;
    }

    public void addNodeToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public Node delTailNode() {
        Node tailNode = tail.prev;
        delNode(tailNode);
        return tailNode;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) return -1;
        delNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null){
            if (cache.size() == capacity){
                Node delNode = delTailNode();
                cache.remove(delNode.key);
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


