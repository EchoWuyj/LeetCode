package alg_01_ds_wyj._03_high_level._01_heap;

import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 20:11
 * @Version 1.0
 */
public class MaxHeapTest {
    public static void main(String[] args) {
        int n = 10;
        Random random = new Random();
        MaxHeap<Integer> heap = new MaxHeap<>();

        for (int i = 0; i < n; i++) {
            heap.add(random.nextInt(100));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = heap.removeMax();
        }

        for (int i = 1; i < n; i++) {
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
