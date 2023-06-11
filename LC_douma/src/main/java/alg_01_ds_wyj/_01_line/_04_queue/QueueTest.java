package alg_01_ds_wyj._01_line._04_queue;

import alg_01_ds_dm._01_line._04_queue.ArrayQueue;
import alg_01_ds_dm._01_line._04_queue.LinkedListQueue;
import alg_01_ds_dm._01_line._04_queue.LinkedListQueue2;
import alg_01_ds_dm._01_line._04_queue.Queue;
import alg_01_ds_wyj._01_line._04_queue.LoopQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-11 18:26
 * @Version 1.0
 */
public class QueueTest {
    public static void main(String[] args) {
        test1();
        System.out.println("==================================");
        test2();
        System.out.println("==================================");
        test3();
        System.out.println("==================================");
        test4();
    }

    private static void test4() {
        LinkedListQueue2<Integer> queue3 = new LinkedListQueue2<>();
        queue3.enqueue(10);
        System.out.println(queue3); // Queue：队首 [10 -> null]

        queue3.enqueue(20);
        System.out.println(queue3); // Queue：队首 [10 -> 20 -> null]

        queue3.enqueue(30);
        System.out.println(queue3); // Queue：队首 [10 -> 20 -> 30 -> null]

        queue3.dequeue();
        System.out.println(queue3); // Queue：队首 [20 -> 30 -> null]

        queue3.dequeue();
        System.out.println(queue3); // Queue：队首 [30 -> null]
    }

    private static void test3() {
        LoopQueue<Integer> queue = new LoopQueue<>();
        queue.enqueue(10);
        System.out.println(queue); // Queue：size = 1, capacity = 9  队首 [10]

        queue.enqueue(20);
        System.out.println(queue); // Queue：size = 2, capacity = 9  队首 [10,20]

        queue.enqueue(30);
        System.out.println(queue); // Queue：size = 3, capacity = 9  队首 [10,20,30]

        queue.dequeue();
        System.out.println(queue); // Queue：size = 2, capacity = 4  队首 [20,30]

        queue.dequeue();
        System.out.println(queue); // Queue：size = 1, capacity = 2  队首 [30]
    }

    private static void test2() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        System.out.println(queue); // Queue：队首 [10]

        queue.enqueue(20);
        System.out.println(queue); // Queue：队首 [10, 20]

        queue.enqueue(30);
        System.out.println(queue); // Queue：队首 [10, 20, 30]

        queue.dequeue();
        System.out.println(queue); // Queue：队首 [20, 30]

        queue.dequeue();
        System.out.println(queue); // Queue：队首 [30]
    }

    private static void test1() {
        Queue<Integer> queue = new ArrayQueue<>();
        queue.enqueue(10);
        System.out.println(queue); // Queue：队首 [10]

        queue.enqueue(20);
        System.out.println(queue); // Queue：队首 [10, 20]

        queue.enqueue(30);
        System.out.println(queue); // Queue：队首 [10, 20, 30]

        queue.dequeue();
        System.out.println(queue); // Queue：队首 [20, 30]

        queue.dequeue();
        System.out.println(queue); // Queue：队首 [30]
    }
}
