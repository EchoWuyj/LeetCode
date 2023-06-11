package alg_01_ds_dm._03_high_level._01_heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 14:53
 * @Version 1.0
 */
public class PriorityQueueTest {
    public static void main(String[] args) {

        // Java 内置优先队列，默认使用小顶堆实现的
        //  1 在刷力扣题中，使用 Java 内置的优先队列，更加灵活
        //  2 通过构造参数定义，来实现小顶堆和大顶堆

        /*
        在 Java 中，PriorityQueue 是一个基于优先级的队列，实现了 Queue 接口和 Iterable 接口。

        常用的 PriorityQueue 方法如下：

        add(E e): 将元素 e 插入队列中，并且根据元素的自然排序或指定的 Comparator 进行排序。
        offer(E e): 同 add 方法，将元素 e 插入队列中。

        remove(Object o): 从队列中删除指定的元素，如果队列中不包含该元素，则返回 false。
        poll(): 返回队列中优先级最高的元素，并将其从队列中删除，如果队列为空，则返回 null。

        peek(): 返回队列中优先级最高的元素，但不将其从队列中删除，如果队列为空，则返回 null。

        PriorityQueue(): 创建一个空的 PriorityQueue。
        size(): 返回队列中的元素数量。
        isEmpty(): 判断队列是否为空，为空返回 true，否则返回 false。
        toArray(): 返回一个包含队列中所有元素的数组，按照优先级排序。
        clear(): 从队列中删除所有元素。
        comparator(): 返回用于元素排序的 Comparator，如果 PriorityQueue 使用的是自然排序，则返回 null。

        spliterator(): 返回一个 Spliterator，用于并行遍历队列中的元素。
        stream(): 返回一个 Stream，用于顺序遍历队列中的元素。
        iterator(): 返回一个迭代器，用于顺序遍历队列中的元素。
         */
        test();
        System.out.println("===============");
        test1();
    }

    private static void test1() {
        // 优先级队列需要传入泛型，前面是 <Integer>，后面是 <>，否则 Lambda 表达式报错
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            // 大顶堆
            return o2 - o1;
        });

        pq.add(13);
        pq.add(10);
        pq.add(56);
        pq.add(69);
        pq.add(40);
        pq.add(90);

        System.out.println(pq.remove()); // 90
        System.out.println(pq.remove()); // 69

        System.out.println(pq); // [56, 40, 13, 10]
    }

    private static void test() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 默认是小顶堆，从小到大排序(升序排列) o1 - o2
                return o1 - o2;
            }
        });

        pq.add(13);
        pq.add(10);
        pq.add(56);
        pq.add(69);
        pq.add(40);
        pq.add(90);

        System.out.println(pq.remove()); // 10
        System.out.println(pq.remove()); // 13

        System.out.println(pq); // [40, 69, 56, 90]
    }
}
