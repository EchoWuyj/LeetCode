package alg_01_ds_wyj._01_line._05_algo._03_sort.train;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-05-12 19:47
 * @Version 1.0
 */
public class _04_164_MaximumGap {

    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        Arrays.sort(nums);
        int maxGap = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
        }
        return maxGap;
    }

    public int maximumGap2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        // RadixSort
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(nums, exp);
        }

        // 最大间距
        int maxGap = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            maxGap = Math.max(maxGap, nums[i + 1] - nums[i]);
        }
        return maxGap;
    }

    public void countSort(int[] data, int exp) {
        int[] count = new int[10];
        int n = data.length;
        for (int i = 0; i < n; i++) {
            int digit = (data[i] / exp) % 10;
            count[digit]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int digit = (data[i] / exp) % 10;
            int k = count[digit] - 1;
            res[k] = data[i];
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            data[i] = res[i];
        }
    }

    public static int maximumGap3(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(num, min);
            max = Math.max(num, max);
        }


        if (max == min) return 0;

        int n = nums.length;
        int gap = (int) Math.ceil(((double) (max - min)) / (n - 1));
        // System.out.println(gap);

        Bucket[] buckets = new Bucket[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new Bucket();
        }

        for (int num : nums) {
            int bucketId = (num - min) / gap;
            buckets[bucketId].hasData = true;
            buckets[bucketId].min = Math.min(buckets[bucketId].min, num);
            buckets[bucketId].max = Math.max(buckets[bucketId].max, num);
        }

        int maxGap = 0;
        int preBucketMax = min;
        for (Bucket bucket : buckets) {
            if (!bucket.hasData) continue;
            maxGap = Math.max(maxGap, bucket.min - preBucketMax);
            preBucketMax = bucket.max;
        }
        return maxGap;
    }

    public static class Bucket {
        private boolean hasData = false;
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        int[] test = {3, 6, 9, 1};
        System.out.println(maximumGap3(test));
    }
}
