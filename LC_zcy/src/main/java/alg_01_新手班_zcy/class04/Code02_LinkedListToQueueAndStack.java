package alg_01_新手班_zcy.class04;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-02 9:35
 * @Version 1.0
 */
public class Code02_LinkedListToQueueAndStack {

    // 定义节点,泛型为V
    public static class Node<V> {
        private V value;
        public Node<V> next;

        // 只是定义了有参构造,故创建对象只能通过有参构造创建对象
        // 构造函数中,不需要定义泛型
        public Node(V v) {
            value = v;
            next = null;
        }
    }

    //-------------------------------------------------------------------

    // 自定义实现队列
    // KeyPoint 自定义类中需要传入V,类似于HashMap
    public static class MyQueue<V> {

        // 定义首尾指针
        private Node<V> head;
        private Node<V> tail;

        private int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        // 判空
        public boolean isEmpty() {
            return size == 0;
        }

        // 大小
        public int size() {
            return size;
        }

        // 进队:队列接受某个值
        // KeyPoint 每次新加入的元素都是从尾巴加入
        public void offer(V value) {
            Node<V> cur = new Node<V>(value);
            // 队列为空,头尾指向第一元素
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                // 队列不为空
                // 最后一个节点的next指针
                // 使用tail表示最后一个节点
                tail.next = cur;
                // 不断更新最后一个节点
                tail = cur;
            }
            size++;
        }

        // 出队:弹出(先进先出)
        public V poll() {
            // 使用 V ans将原来头节点的值保存
            // 使用数据值的数据类型V,不需要使用Node节点类型
            // 返回不是节点数据类型
            V ans = null;
            if (head != null) {
                ans = head.value;
                // head指针后移
                // 不用管之前头节点,因为程序只有head和tail这两个入口
                // 原来的头节点是不可达的,等着被JVM进行释放
                head = head.next;
                size--;
            }

            if (head == null) {
                // head移动到null位置,此时队列为空
                // 同时需要保证tail指向head位置,将原来tail指向的最后一个节点释放掉
                tail = null;
            }
            return ans;
        }

        // 获取队头元素的值(不将其移除队列)
        public V peek() {
            // 使用LinkedList或者Queue实现的队列,其中的元素
            // 只能使用基本数据类型的包装类,所以这里可以null
            V ans = null;
            if (head != null) {
                // 指针不需要后移
                ans = head.value;
            }
            // 为空直接返回ans=null
            return ans;
        }
    }

    //-------------------------------------------------------------------

    // 自定义实现栈
    public static class MyStack<V> {

        // 使用单个head指针即可实现
        private Node<V> head;
        private int size;

        public MyStack() {
            // 一个=是赋值,两个==是判断
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // 压栈操作
        public void push(V value) {
            // 新加入的节点
            Node<V> cur = new Node<V>(value);
            if (head == null) {
                // cur为第一个节点
                head = cur;
            } else {
                // 新加入节点的next指针指向栈顶
                cur.next = head;
                // 移动栈顶指针指向最新的元素
                // head指针时刻需要保持在栈顶
                head = cur;
            }

            size++;
        }

        // 出栈操作
        public V pop() {
            // 本质类似于:int a = 0;
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        public V peek() {
            return head != null ? head.value : null;
        }
    }

    // testQueue
    public static void testQueue() {
        MyQueue<Integer> myQueue = new MyQueue<>();
        Queue<Integer> test = new LinkedList<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myQueue.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myQueue.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myQueue.offer(num);
                test.offer(num);
            } else if (decide < 0.66) {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.poll();
                    int num2 = test.poll();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myQueue.isEmpty()) {
                    int num1 = myQueue.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myQueue.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myQueue.isEmpty()) {
            int num1 = myQueue.poll();
            int num2 = test.poll();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void testStack() {
        MyStack<Integer> myStack = new MyStack<>();
        Stack<Integer> test = new Stack<>();
        int testTime = 5000000;
        int maxValue = 200000000;
        System.out.println("测试开始！");
        for (int i = 0; i < testTime; i++) {
            if (myStack.isEmpty() != test.isEmpty()) {
                System.out.println("Oops!");
            }
            if (myStack.size() != test.size()) {
                System.out.println("Oops!");
            }
            double decide = Math.random();
            if (decide < 0.33) {
                int num = (int) (Math.random() * maxValue);
                myStack.push(num);
                test.push(num);
            } else if (decide < 0.66) {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.pop();
                    int num2 = test.pop();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            } else {
                if (!myStack.isEmpty()) {
                    int num1 = myStack.peek();
                    int num2 = test.peek();
                    if (num1 != num2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        if (myStack.size() != test.size()) {
            System.out.println("Oops!");
        }
        while (!myStack.isEmpty()) {
            int num1 = myStack.pop();
            int num2 = test.pop();
            if (num1 != num2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束！");
    }

    public static void main(String[] args) {
        // 测试节点类型
        Node<String> node = new Node<String>("1");
        System.out.println(node.value);
        System.out.println("-------------");

        //
        testQueue();
        System.out.println("-------------");
        testStack();
    }
}