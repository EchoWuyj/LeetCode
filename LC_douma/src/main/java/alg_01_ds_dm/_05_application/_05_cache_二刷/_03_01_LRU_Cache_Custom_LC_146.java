package alg_01_ds_dm._05_application._05_cache_二刷;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wuyj
 * @DateTime 2023-05-30 14:16
 * @Version 1.0
 */
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
