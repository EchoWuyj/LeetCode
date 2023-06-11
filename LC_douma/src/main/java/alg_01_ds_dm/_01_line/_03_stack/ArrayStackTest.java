package alg_01_ds_dm._01_line._03_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author Wuyj
 * @DateTime 2023-03-09 21:40
 * @Version 1.0
 */
public class ArrayStackTest {
    public static void main(String[] args) {

        test1();
        System.out.println("==========");
        test2();
        System.out.println("==========");
        test3();
        System.out.println("==========");

        // 1 java 内置的 Stack
        java.util.Stack<Integer> stack = new java.util.Stack<>();
//      java 中有内置的 util 中 Stack，Stack 底层是基于数组 Object[] 来实现的，以后可以直接使用
//      synchronized 修饰， 保证 Stack 是并发安全的，多线程在调用该类时，是线程安全的，但是性能方面可能差一点

        // 2 双端队列作为 Stack，推荐使用
        Deque<Integer> stack1 = new ArrayDeque<>();
    }

    private static void test1() {
        // 静态数组 => 数组大小固定
        Stack<Integer> stack = new ArrayStack<>(5);
        stack.push(10);
        System.out.println(stack); // Stack: [10] top
        stack.push(20);
        System.out.println(stack); // Stack: [10,20] top
        stack.push(30);
        System.out.println(stack); // Stack: [10,20,30] top

        stack.pop();
        System.out.println(stack); // Stack: [10,20] top
        stack.pop();
        System.out.println(stack); // Stack: [10] top
    }

    private static void test2() {
        // 动态数组 => 动态扩容
        // Raw use of parameterized class 需要加上 <>
        Stack<Integer> stack = new DynamicArrayStack<>(5);
        stack.push(10);
        System.out.println(stack); // Stack: [10] top
        stack.push(20);
        System.out.println(stack); // Stack: [10,20] top
        stack.push(30);
        System.out.println(stack); // Stack: [10,20,30] top

        stack.pop();
        System.out.println(stack); // Stack: [10,20] top
        stack.pop();
        System.out.println(stack); // Stack: [10] top
    }

    private static void test3() {
        // 链表
        Stack<Integer> stack = new LinkedListStack<>();
        stack.push(10);
        System.out.println(stack); // Stack: [10] top
        stack.push(20);
        System.out.println(stack); // Stack: [10,20] top
        stack.push(30);
        System.out.println(stack); // Stack: [10,20,30] top

        stack.pop();
        System.out.println(stack); // Stack: [10,20] top
        stack.pop();
        System.out.println(stack); // Stack: [10] top
    }
}
