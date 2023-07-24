package alg_02_train_wyj._11_day_优先队列;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 16:14
 * @Version 1.0
 */
public class _04_973_k_closest_points_to_origin2 {

    public int[][] kClosest3(int[][] points, int k) {
        int n = points.length;
        int left = 0, right = n - 1;
        int target = n - k;
        while (true) {
            int pivotIndex = partition(points, left, right);
            if (target == pivotIndex) {
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return Arrays.copyOfRange(points, n - k, n);
    }

    public int partition(int[][] nums, int low, int high) {
        if (high > low) {
            int pivotIndex = new Random().nextInt(high - low + 1) + low;
            swap(nums, pivotIndex, high);
        }
        int pivot = nums[high][0] * nums[high][0]
                + nums[high][1] * nums[high][1];
        int less = 0, great = 0;
        for (; great < high; great++) {
            int distance = nums[great][0] * nums[great][0]
                    + nums[great][1] * nums[great][1];
            if (distance > pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, high);
        return less;
    }

    public void swap(int[][] nums, int i, int j) {
        int[] tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
