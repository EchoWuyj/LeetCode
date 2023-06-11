package alg_01_ds_dm._03_high_level._01_heap;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 14:23
 * @Version 1.0
 */
public class HeapifyPerformanceTest {
    private static Random random = new Random();

    public static void main(String[] args) {
        int cnt = 10000000;
        double time1 = testHeapify(cnt, false);
        System.out.println("使用 sift up 花费的时间：" + time1); // 使用 sift up 花费的时间：1.1878169

        double time2 = testHeapify(cnt, true);
        System.out.println("使用 sift down 花费的时间：" + time2); // 使用 sift down 花费的时间：0.3751725 => 堆化效率更好
    }

    private static double testHeapify(int cnt, boolean isSiftDown) {
        Integer[] arr = new Integer[cnt];
        for (int i = 0; i < cnt; i++) {
            arr[i] = random.nextInt();
        }

        long startTime = System.nanoTime();
        if (isSiftDown) {
            // 1 使用 sift down 进行堆化
            MaxHeap<Integer> heap = new MaxHeap<>(arr);
        } else {
            // 2 使用 sift up 进行堆化
            MaxHeap<Integer> heap = new MaxHeap<>();
            for (int i = 0; i < cnt; i++) {
                heap.add(arr[i]);
            }
        }
        return (System.nanoTime() - startTime) / 1000_000_000.0;
    }
}
