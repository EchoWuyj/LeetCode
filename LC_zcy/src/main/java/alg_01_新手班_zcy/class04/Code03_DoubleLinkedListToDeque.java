package alg_01_新手班_zcy.class04;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author Wuyj
 * @DateTime 2022-09-02 9:35
 * @Version 1.0
 */
public class Code03_DoubleLinkedListToDeque {

    // 双端队列只能使用双向链表来实现O(1)从头或尾加减元素
    //   单链表支持O(1)从头部加减元素,从尾部加元素
    //   但是不支持O(1)从尾部减元素,因为需要遍历到尾的前一个元素

    // 定义双向链表的节点
    public static class Node<V> {
        public V value;
        public Node<V> last;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
            last = null;
            next = null;
        }
    }

    // 自定义实现双端队列(两个指针)
    public static class MyDeque<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyDeque() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // 从头部加
        public void pushHead(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                // 双向链表节点有两个指针,所以都是需要调整的
                // 通过头插法实现
                // cur.last没有设置为null
                cur.next = head;
                head.last = cur;
                // 头指针前移动
                head = cur;
            }
            size++;
        }

        // 从尾部加
        public void pushTail(V value) {
            Node<V> cur = new Node<>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                // 通过尾插法实现
                tail.next = cur;
                cur.last = tail;
                // cur.next 没有设置为null
                // 尾指针后移
                tail = cur;
            }
            size++;
        }

        // 从头部出
        public V pollHead() {
            V ans = null;
            // head==null直接return ans方法结束
            if (head == null) {
                return ans;
            }

            size--;
            ans = head.value;

            // 只有一个元素,再从头部出去直接没有元素了
            // 所以head和tail需要设置为null
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                // 头指针后移
                head = head.next;
                // head.last=null保证Jvm不可达
                head.last = null;
            }
            return ans;
        }

        // 从尾部出
        public V pollTail() {
            V ans = null;
            if (head == null) {
                return ans;
            }
            size--;
            ans = tail.value;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                // 尾指针前移
                tail = tail.last;
                // 将前一个元素的next指针设置为null
                tail.next = null;
            }
            return ans;
        }

        // 获取头部值
        public V peekHead() {
            V ans = null;
            if (head != null) {
                ans = head.value;
            }
            return ans;
        }

        // 获取尾部值
        public V peekTail() {
            V ans = null;
            // if (head != null) 同样可以
            if (tail != null) {
                ans = tail.value;
            }
            return ans;
        }
    }

    // testDeque
    public static void testDeque() {
        MyDeque<Integer> myDeque = new MyDeque<>();
        Deque<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myDeque.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myDeque.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                if (Math.random() < 0.5) {
                    myDeque.pushHead(num);
                    test.addFirst(num);
                } else {
                    myDeque.pushTail(num);
                    test.addLast(num);
                }
            } else if (decide < 0.66) {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.pollHead();
                        num2 = test.pollFirst();
                    } else {
                        num1 = myDeque.pollTail();
                        num2 = test.pollLast();
                    }
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myDeque.isEmpty()) {
                    int num1 = 0;
                    int num2 = 0;
                    if (Math.random() < 0.5) {
                        num1 = myDeque.peekHead();
                        num2 = test.peekFirst();
                    } else {
                        num1 = myDeque.peekTail();
                        num2 = test.peekLast();
                    }
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myDeque.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myDeque.isEmpty()) {
            int num1 = myDeque.pollHead();
            int num2 = test.pollFirst();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        testDeque();
    }
}
