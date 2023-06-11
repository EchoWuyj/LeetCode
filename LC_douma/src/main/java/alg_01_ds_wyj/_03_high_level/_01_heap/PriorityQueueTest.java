package alg_01_ds_wyj._03_high_level._01_heap;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 21:10
 * @Version 1.0
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        test1();
        System.out.println("============");
        test2();
    }

    private static void test2() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });

        pq.add(13);
        pq.add(10);
        pq.add(56);
        pq.add(69);
        pq.add(40);
        pq.add(90);

        System.out.println(pq.remove()); // 10
        System.out.println(pq.remove()); // 13
        System.out.println(pq);
    }

    private static void test1() {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });

        pq.add(13);
        pq.add(10);
        pq.add(56);
        pq.add(69);
        pq.add(40);
        pq.add(90);

        System.out.println(pq.remove()); // 10
        System.out.println(pq.remove()); // 13
        System.out.println(pq);
    }
}
