package alg_01_ds_wyj._01_line._05_algo._03_sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-11 11:18
 * @Version 1.0
 */
public class _10_BucketSorter {

    public void sort(int[] data) {
        if (data == null || data.length <= 1) return;
        int maxValue = data[0];
        for (int num : data) {
            maxValue = Math.max(num, maxValue);
        }
        int bucketNum = maxValue / 10 + 1;
        ArrayList<Integer>[] buckets = new ArrayList[bucketNum];

        int n = data.length;
        for (int i = 0; i < n; i++) {
            int index = data[i] / 10;
            if (buckets[index] == null) {
                buckets[index] = new ArrayList<>();
            }
            buckets[index].add(data[i]);
        }

        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucket = buckets[i];
            if (bucket != null) {
                IntegerArrayQuickSorter.sort(bucket);
            }
        }

        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucket = buckets[i];
            if (bucket != null) {
                int size = bucket.size();
                for (int j = 0; j < size; j++) {
                    data[index++] = bucket.get(j);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new _10_BucketSorter().sort(data);
        System.out.println(Arrays.toString(data));
        // [1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 8, 12, 22, 23, 33, 56]
    }
}
