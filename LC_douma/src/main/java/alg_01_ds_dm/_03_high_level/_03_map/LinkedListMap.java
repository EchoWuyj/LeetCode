package alg_01_ds_dm._03_high_level._03_map;

import java.util.NoSuchElementException;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 16:15
 * @Version 1.0
 */
public class LinkedListMap<K, V> implements Map<K, V> {
    // 定义链表节点
    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " -> " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node();
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K key, V value) { // O(n)
        // 1. 找到 key 对应的 value 所在的节点
        Node curr = getNode(key);

        // 2. 如果 curr 为空的话，说明 Map 中还没有这个键值对
        if (curr == null) {
            // 直接将键值对插入头节点即可
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            // 键值对已经存在，那么更新 value
            curr.value = value;
        }
    }

    private Node getNode(K key) { // O(n)
        Node curr = dummyHead.next;
        while (curr != null) {
            if (key.equals(curr.key)) {
                break;
            }
            curr = curr.next;
        }
        return curr;
    }

    @Override
    public V remove(K key) { // O(n)
        // 1. 找到需要删除的节点的前一个节点
        Node prev = dummyHead;
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.key.equals(key)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }

        // 2. 如果需要删除的节点不为空，则删除节点，并返回删除节点的值
        if (curr != null) {
            prev.next = curr.next;
            curr.next = null;
            size--;
            return curr.value;
        }
        // 遍历中没有删除的节点，则直接返回 null
        return null;
    }

    @Override
    public void set(K key, V newValue) { // O(n)
        // 1. 找到 key 对应的 value 所在的节点
        Node curr = getNode(key);

        if (curr != null) curr.value = newValue;
        else throw new NoSuchElementException("没有对应的 key ：" + key);
    }

    @Override
    public V get(K key) { // O(n)
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean containsKey(K key) { // O(n)
        Node node = getNode(key);
        // 如果 node 不为 null ，则表示存在，否则表述不存在
        return node != null;
    }
}
