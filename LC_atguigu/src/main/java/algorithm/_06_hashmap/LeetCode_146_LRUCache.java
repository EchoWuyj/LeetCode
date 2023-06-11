package algorithm._06_hashmap;

import java.util.HashMap;

/**
 * @Author Wuyj
 * @DateTime 2022-03-18 19:30
 * @Version 1.0
 */

//自定义实现HashMap+双向链表的缓存机制
public class LeetCode_146_LRUCache {
    //定义双向链表的节点类(内部类)
    class Node {
        int key;
        int value;
        Node next;
        Node prev; //指向前一个节点的指针

        public Node() {
        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //定义哈希表
    private HashMap<Integer, Node> hashMap = new HashMap<>();
    //定义属性
    private int capacity;//本身容量大小
    private int size;//当前已经保存数据量的大小

    //定义头尾指针
    private Node head, tail;

    //初始化构造方法
    public LeetCode_146_LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;

        //用哑节点定义哨兵,方便统一处理
        head = new Node();
        tail = new Node();

        //一开始什么都没有,只有这两个哨兵节点连在一起,没有具体的数据
        head.next = tail;
        tail.prev = head;
    }

    //get方法
    public int get(int key) {
        //从哈希表中查找key,如果不存在的话就返回-1
        Node node = hashMap.get(key);
        if (node == null) {
            return -1;
        }

        //如果存在,将当前节点移到链表末尾
        moveToTail(node);

        return node.value;
    }

    //put方法
    public void put(int key, int value) {
        //同样先在哈希表中查找key
        Node node = hashMap.get(key);

        //如果key存在,修改value,并移动到末尾
        if ((node != null)) {
            node.value = value;
            moveToTail(node);
        }
        //如果不存在,需要创建新的节点,插入到末尾
        else {
            Node newNode = new Node(key, value);
            hashMap.put(key, newNode); //保存进哈希表
            addToTail(newNode); //添加到双向链表的末尾
            size++; //当前size增大

            //追加一个元素,涉及到如果超出了容量限制,删除链表头节点
            if (size > capacity) {
                Node head = removeHead();
                //删除头节点之外,还需要将其从Hash表中删除
                //真正意义上保存的缓存是在Hash表中的
                hashMap.remove(head.key);
                size--;
            }
        }
    }

    //1.移动节点到链表末尾
    private void moveToTail(Node node) {
        //1.在当前的链表中将该节点删除
        removeNode(node);
        //2.再去在队尾使用addToTail添加到末尾
        addToTail(node);
    }

    //2.在链表末尾增加一个节点
    private void addToTail(Node node) {
        //KeyPoint 这里指针调整需要考虑先后次序问题
        node.next = tail;
        //以原先的末尾节点作为前一个节点
        node.prev = tail.prev;
        //当tail的前一个节点没有做保存,该节点是通过tail.prev获取的
        //若先修改tail.prev=node,则无法找到前节点了
        tail.prev.next = node;
        tail.prev = node;
    }

    //3.通用方法,删除链表中的某一个节点
    private void removeNode(Node node) {
        //KeyPoint 跳过当前node,故双向指针都是需要改变的
        //前一个节点的next指向node.next
        node.prev.next = node.next;
        //后一个节点的prev指向node.prev
        node.next.prev = node.prev;
    }

    //3.删除头节点
    private Node removeHead() {
        Node readHead = head.next;
        removeNode(readHead);
        return readHead;
    }

    public static void main(String[] args) {
        LeetCode_146_LRUCache lRUCache = new LeetCode_146_LRUCache(2);
        lRUCache.put(1, 1); //缓存是 {1=1}
        lRUCache.put(2, 2); //缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));   //返回 1
        lRUCache.put(3, 3); //该操作会使得关键字 2 作废,缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    //返回 -1 (未找到)
        lRUCache.put(4, 4); //该操作会使得关键字 1 作废,缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));     //返回 -1 (未找到)
        System.out.println(lRUCache.get(3));     //返回 3
        System.out.println(lRUCache.get(4));     //返回 4
    }
}
