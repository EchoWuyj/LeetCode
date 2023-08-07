package alg_01_ds_dm._05_application._05_cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 14:18
 * @Version 1.0
 */
public class _04_02_LFU_Cache_Custom_LC_460 {

    class Node {
        int key, val, count;
        Node next, prev;

        public Node(int key, int val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }

        public Node() {

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

        private Node deleteNode(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            node.next = null;
            node.prev = null;

            return node;
        }

        private void addNewNodeToHead(Node newNode) {
            newNode.next = head.next;
            newNode.prev = head;

            head.next.prev = newNode;
            head.next = newNode;
        }

        private Node deleteLastNodeFromTail() {
            if (isEmpty()) return null;
            Node lastNode = tail.prev;
            return deleteNode(lastNode);
        }

        private boolean isEmpty() {
            return head.next == tail && tail.prev == head;
        }
    }

    Map<Integer, Node> keyToNode;
    Map<Integer, DoubleLinkedList> countToKeySet;
    int capacity;
    int minCount;

    _04_02_LFU_Cache_Custom_LC_460(int capacity) {
        keyToNode = new HashMap<>();
        countToKeySet = new HashMap<>();
        this.capacity = capacity;
        minCount = 0;
    }

    public int get(int key) {
        Node node = keyToNode.get(key);
        if (node == null) return -1;

        int count = node.count;
        node.count = count + 1;
        countToKeySet.get(count).deleteNode(node);

        if (minCount == count &&
                countToKeySet.get(count).isEmpty()) {
            minCount++;
        }

        addKeyToKeySet(node, count + 1);
        return node.val;
    }

    private void addKeyToKeySet(Node node, int count) {
        if (!countToKeySet.containsKey(count)) {
            countToKeySet.put(count, new DoubleLinkedList());
        }
        countToKeySet.get(count).addNewNodeToHead(node);
    }

    public void put(int key, int value) {
        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            keyToNode.put(key, node);
            get(key);
        } else {
            if (capacity == keyToNode.size()) {
                Node removeNode = countToKeySet.get(minCount).deleteLastNodeFromTail();
                keyToNode.remove(removeNode.key);
            }
            Node newNode = new Node(key, value, 1);
            keyToNode.put(key, newNode);
            minCount = 1;
            addKeyToKeySet(newNode, 1);
        }
    }
}
