package alg_02_体系班_zcy.class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Author Wuyj
 * @DateTime 2022-09-10 22:59
 * @Version 1.0
 */
public class Code03_DoubleEndsQueueToStackAndQueue {

    // 双向链表的节点类型
    public static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        // 将T等价替换成int来理解即可
        public Node(T data) {
            value = data;
        }
    }

    // 通过双向链表实现双端队列
    // 在创建的类中已经定义好了泛型,所以调用方法时闯入的参数
    // 只能是T value,而不能是其他数据类型
    public static class DoubleEndsQueue<T> {
        // 定义属性
        // 红色代码代表类的属性
        public Node<T> head;
        public Node<T> tail;

        // 从头加入元素
        public void addFromHead(T value) {
            // 加入节点都是需要加入泛型T的
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                // 将新节点挂在头上
                cur.next = head;
                head.last = cur;
                // cur.last没有设置,自然为null
                head = cur;
            }
        }

        // 从尾加元素
        public void addFromBottom(T value) {
            Node<T> cur = new Node<T>(value);
            // head是最开始的节点,自然是以head为标准来判空
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.last = tail;
                tail.next = cur;
                tail = cur;
            }
        }

        //--------------------------------------------

        // 从头出(返回值不为void)
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            // 将当前节点取出来
            Node<T> cur = head;

            // 只有一个元素(区别于head==null)
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                // 是先移动节点,再调整指针
                head = head.next;
                cur.next = null;
                head.last = null;
            }
            return cur.value;
        }

        // 从尾出
        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            // 将当前节点取出来
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                // 先移动节点,再去调整指针
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.value;
        }

        // 判空
        public boolean isEmpty() {
            return head == null;
        }
    }

    // -----------------------------------------------

    // 双端队列实现栈,时间复杂度O(1)
    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // 双端队列实现队列,时间复杂度O(1)
    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        // 队列交poll,栈叫pop
        public T poll() {
            return queue.popFromBottom();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // for test
    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    // for test
    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }
}
