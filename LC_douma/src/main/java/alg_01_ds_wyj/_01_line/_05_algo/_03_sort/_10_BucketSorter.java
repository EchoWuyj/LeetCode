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
            maxValue = Math.max(maxValue, num);
        }

        int bucketNum = maxValue / 10 + 1;
        ArrayList<Integer>[] buckets = new ArrayList[bucketNum];

        for (int i = 0; i < data.length; i++) {
            int index = data[i] / 10;
            if (buckets[index] == null) {
                buckets[index] = new ArrayList<>();
            }
            buckets[index].add(data[i]);
        }

        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucketData = buckets[i];
            if (bucketData != null) {
                _09_IntegerArrayQuickSorter.sort(bucketData);
            }
        }

        int index = 0;
        for (int i = 0; i < bucketNum; i++) {
            ArrayList<Integer> bucketData = buckets[i];
            if (bucketData != null) {
                for (int num = 0; num < bucketData.size(); num++) {
                    data[index++] = bucketData.get(num);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 5, 1, 23, 22, 33, 56, 12, 5, 3, 5, 6, 8, 2, 3, 4};
        new _10_BucketSorter().sort(data);
        System.out.println(Arrays.toString(data)); // [1, 2, 2, 3, 3, 4, 5, 5, 5, 6, 8, 12, 22, 23, 33, 56]
    }
}
