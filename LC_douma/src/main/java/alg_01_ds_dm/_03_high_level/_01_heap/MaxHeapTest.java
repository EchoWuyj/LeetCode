package alg_01_ds_dm._03_high_level._01_heap;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 13:38
 * @Version 1.0
 */
public class MaxHeapTest {
    public static void main(String[] args) {
        int n = 10;
        Random random = new Random();

        MaxHeap<Integer> heap = new MaxHeap<>();

        // 1. 往堆中添加 10000 个随机整数
        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt(100));
        }

        // 2. 依次从堆中取出 10000 个整数，并依次放入到数组中
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            // 每次都是取最大值，相当于是降序排列
            arr[i] = heap.removeMax();
        }

        // 3. 判断先拿出来的元素是最大的，如果不是的话，则说明不符合堆的性质
        for (int i = 1; i < n; i++) {
            // 大顶堆 => 降序排列，若前一个 i-1 比 i 要小，则抛出异常
            if (arr[i - 1] < arr[i]) {
                throw new RuntimeException("Error");
            }
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("==========");

        System.out.println("Test_BitMap MaxHeap Succ");
    }
}
