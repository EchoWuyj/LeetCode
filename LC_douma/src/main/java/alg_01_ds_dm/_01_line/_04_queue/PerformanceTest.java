package alg_01_ds_dm._01_line._04_queue;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-10 14:29
 * @Version 1.0
 */
public class PerformanceTest {
    private static Random random = new Random();

    private static double testQueue(Queue<Integer> queue, int cnt) {
        long startTime = System.nanoTime();

        for (int i = 0; i < cnt; i++) {
            queue.enqueue(random.nextInt());
        }
        for (int i = 0; i < cnt; i++) {
            queue.dequeue();
        }

        return (System.nanoTime() - startTime) / 1000_000_000.0;
    }

    public static void main(String[] args) {
        int cnt = 1000000;

        Queue<Integer> queue = new ArrayQueue<>();
        double time1 = testQueue(queue, cnt);
        System.out.println("ArrayQueue 花费的时间：" + time1); // ArrayQueue 花费的时间：410.2996808

        queue = new LoopQueue<>();
        double time2 = testQueue(queue, cnt);
        System.out.println("LoopQueue 花费的时间：" + time2); // LoopQueue 花费的时间：0.0609155

        // KeyPoint 循环队列比动态数组实现队列性能要好

        System.out.println("===================================");

        queue = new LinkedListQueue2<>();
        double time3 = testQueue(queue, cnt);
        System.out.println("LinkedListQueue2 花费的时间：" + time3); // LinkedListQueue2 花费的时间：0.0461031

        // KeyPoint
        //  1 链表不需要手动维护扩容的，但是循环数组（LoopQueue）（静态数组）是需要维护 resize 的
        //  2 随着数据量增加，循环队列要比优化后链表性能好，因为数据量很大之后，链表是要不断创建 node
        //    创建 node 的过程是非常耗费时间的过程
    }
}
