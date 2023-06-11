package alg_02_体系班_wyj.class06;

import java.util.PriorityQueue;

/**
 * @Author Wuyj
 * @DateTime 2022-09-20 15:43
 * @Version 1.0
 */
public class sortedArrDistanceLessK {
    public static void sortArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>();

        int index = 0;
        for (; index <= Math.min(arr.length - 1, k); index++) {
            heap.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length - 1; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        int[] array = {3, 4, 1, 2, 5};
        sortArrDistanceLessK(array, 2);
        printArray(array);
    }
}
