package alg_01_ds_wyj._05_application._05_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-27 23:52
 * @Version 1.0
 */
public class LRUCache {

    class Node {
        int key;
        int value;
        Node next;
        Node prev;
    }

    Node head;
    Node tail;
    Map<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public void delNodeToHead(Node node) {
        delNode(node);
        addNodeToHead(node);
    }

    public void delNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        nextNode.prev = prevNode;
        prevNode.next = nextNode;
        node.next = null;
        node.prev = null;
    }

    public void addNodeToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.next = node;
        head.next = node;
    }

    public Node delTailNode() {
        Node tailNode = tail.prev;
        delNode(tailNode);
        return tailNode;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        delNodeToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            if (map.size() == capacity) {
                Node tailNode = delTailNode();
                map.remove(tailNode.key);
            }
            Node newNode = new Node();
            newNode.key = key;
            newNode.value =value;
            addNodeToHead(newNode);
            map.put(key, newNode);
        } else {
            node.value = value;
            delNodeToHead(node);
        }
    }
}
