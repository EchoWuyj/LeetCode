package alg_02_train_wyj._07_day_排序算法;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-11 18:17
 * @Version 1.0
 */
public class _18_164_maximum_gap1 {

    public int maximumGap1(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        Arrays.sort(nums);
        int n = nums.length;
        int maxGap = -1;
        for (int i = 1; i < n; i++) {
            maxGap = Math.max(nums[i] - nums[i - 1], maxGap);
        }
        return maxGap;
    }

    // 基数排序
    public static int maximumGap2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        // System.out.println(Arrays.toString(nums));

        for (int exp = 1; max / exp > 0; exp *= 10) {
            System.out.println(exp);
            countSort(nums, exp);
        }
//        System.out.println("111");
//        System.out.println(Arrays.toString(nums));

        int n = nums.length;
        int maxGap = -1;
        for (int i = 1; i < n; i++) {
            maxGap = Math.max(nums[i] - nums[i - 1], maxGap);
        }
        return maxGap;
    }

    public static void countSort(int[] nums, int exp) {
        int[] count = new int[10];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num = (nums[i] / exp) % 10;
            count[num]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int num = (nums[i] / exp) % 10;
            int index = count[num] - 1;
            output[index] = nums[i];
            count[num]--;
        }

        for (int i = 0; i < n; i++) {
            nums[i] = output[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {100, 3, 2, 1};
        System.out.println(maximumGap2(arr));
    }
}
