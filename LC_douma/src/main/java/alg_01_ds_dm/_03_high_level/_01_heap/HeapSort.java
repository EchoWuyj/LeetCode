package alg_01_ds_dm._03_high_level._01_heap;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 14:32
 * @Version 1.0
 */
public class HeapSort {
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(n)
    public void sort(Integer[] data) {
        // 1. 建堆，堆化 O(n)
        MaxHeap<Integer> maxHeap = new MaxHeap<>(data);

        // 2. 排序 => 不断从堆里面删除最大值，将最大值放到数组里面，直到堆为空
        Integer[] tmp = new Integer[data.length];
        // int i = 0; 降序
        int i = data.length - 1; // 升序
        // O(nlogn)
        while (!maxHeap.isEmpty()) { // O(n)
            tmp[i] = maxHeap.removeMax(); // O(logn)
            i--; // 降序需要 i++;
        }

        // 3. 拷贝 => 从临时数组 tmp 拷贝到原数组 data 中
        for (int j = 0; j < data.length; j++) {
            data[j] = tmp[j];
        }
    }

    public static void main(String[] args) {
        Integer[] data = new Integer[]{15, 17, 19, 13, 22, 16, 28, 30, 42, 66};
        System.out.println(Arrays.toString(data)); // [15, 17, 19, 13, 22, 16, 28, 30, 42, 66]
        new HeapSort().sort(data);
        System.out.println(Arrays.toString(data)); // [13, 15, 16, 17, 19, 22, 28, 30, 42, 66]
    }
}
