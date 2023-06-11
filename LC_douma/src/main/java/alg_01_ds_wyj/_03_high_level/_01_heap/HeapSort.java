package alg_01_ds_wyj._03_high_level._01_heap;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 21:03
 * @Version 1.0
 */
public class HeapSort {
    public void sort(Integer[] data) {
        MaxHeap<Integer> maxHeap = new MaxHeap(data);
        Integer[] tmp = new Integer[data.length];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            tmp[i] = maxHeap.removeMax();
            i++;
        }

        for (int j = 0; j < data.length; j++) {
            data[j] = tmp[j];
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{15, 17, 19, 13, 22, 16, 28, 30, 42, 66};
        System.out.println(Arrays.toString(data)); // [15, 17, 19, 13, 22, 16, 28, 30, 42, 66]
        new HeapSort().sort(data);
        System.out.println(Arrays.toString(data)); // [66, 42, 30, 28, 22, 19, 17, 16, 15, 13]
    }
}
