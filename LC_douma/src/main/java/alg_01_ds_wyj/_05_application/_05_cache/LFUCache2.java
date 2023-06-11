package alg_01_ds_wyj._05_application._05_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-03-29 14:00
 * @Version 1.0
 */
public class LFUCache2 {
    class Node {
        int key, val, count;
        Node next, prev;

        public Node() {
        }

        public Node(int key, int val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    class DoubleLinkedList {
        Node head;
        Node tail;

        public DoubleLinkedList() {
            this.head = new Node();
            this.tail = new Node();
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        public Node remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            return node;
        }

        public boolean isEmpty() {
            return this.head.next == this.tail;
        }

        public void add(Node node) {
            node.prev = tail.prev;
            tail.prev.next = node;
            tail.prev = node;
            node.next = tail;
        }

        public Node popFirst() {
            if (isEmpty()) return null;
            return remove(this.head.next);
        }
    }

    private Map<Integer, Node> keyToNode;
    private Map<Integer, DoubleLinkedList> keys;
    private int capacity;
    private int minUsedCount;

    public LFUCache2(int capacity) {
        keyToNode = new HashMap<>();
        keys = new HashMap<>();
        this.capacity = capacity;
        minUsedCount = 0;
    }

    public int get(int key) {
        if (capacity == 0) return -1;
        Node node = keyToNode.get(key);
        if (node == null) return -1;
        int count = node.count;
        keys.get(count).remove(node);
        node.count = count + 1;

        if (count == minUsedCount
                && keys.get(count).isEmpty()) {
            minUsedCount++;
        }
        putUsedCount(node, count + 1);
        return node.val;
    }

    private void putUsedCount(Node node, int count) {
        if (!keys.containsKey(count)) {
            keys.put(count, new DoubleLinkedList());
        }
        keys.get(count).add(node);
    }

    public void put(int key, int value) {
        if (capacity == 0) return;
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            keyToNode.put(key, node);
            get(key);
            return;
        }

        if (keyToNode.size() == capacity) {
            Node removeNode = keys.get(minUsedCount).popFirst();
            keyToNode.remove(removeNode.key);
        }

        Node node = new Node(key, value, 1);
        keyToNode.put(key, node);
        minUsedCount = 1;
        putUsedCount(node, minUsedCount);
    }


}
