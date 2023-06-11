package alg_01_ds_wyj._01_line._03_stack;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 14:14
 * @Version 1.0
 */
public class ArrayStackTest {
    public static void main(String[] args) {
        test1();
        System.out.println("==========");
        test2();
        System.out.println("==========");
        test3();
    }

    private static void test3() {
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

    private static void test2() {
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

    private static void test1() {
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
}
