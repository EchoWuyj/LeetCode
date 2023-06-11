package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 14:18
 * @Version 1.0
 */
public class _04_02_LFU_Cache_LC {
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
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.prev = head;
        }

        private Node removeNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            node.next = null;
            node.prev = null;

            return node;
        }

        private void addNodeToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private Node removeTailNode() {
            if (isEmpty()) return null;
            return removeNode(tail.prev);
        }

        public boolean isEmpty() {
            return head.next == tail && tail.prev == head;
        }
    }

    private Map<Integer, Node> keyToNode;
    private Map<Integer, DoubleLinkedList> countToKeys;
    private int capacity;
    private int minUsedCount;

    public _04_02_LFU_Cache_LC(int capacity) {
        keyToNode = new HashMap<>();
        countToKeys = new HashMap<>();
        this.capacity = capacity;
        this.minUsedCount = 0;
    }

    public int get(int key) {
        if (capacity == 0) return -1;
        Node node = keyToNode.get(key);
        if (node == null) return -1;

        int count = node.count;
        countToKeys.get(count).removeNode(node);
        node.count += 1;

        if (count == minUsedCount
                && countToKeys.get(count).isEmpty()) {
            minUsedCount++;
        }

        putUsedCount(node, count + 1);
        return node.val;
    }

    private void putUsedCount(Node node, int count) {
        if (!countToKeys.containsKey(count)) {
            countToKeys.put(count, new DoubleLinkedList());
        }
        countToKeys.get(count).addNodeToHead(node);
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
            Node removeNode = countToKeys.get(minUsedCount).removeTailNode();
            keyToNode.remove(removeNode.key);
        }

        Node newNode = new Node(key, value, 1);
        keyToNode.put(key, newNode);
        minUsedCount = 1;
        putUsedCount(newNode, 1);
    }
}
