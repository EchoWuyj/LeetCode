package alg_02_train_dm._11_day_优先队列_二刷;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author Wuyj
 * @DateTime 2023-07-22 15:39
 * @Version 1.0
 */
public class _04_973_k_closest_points_to_origin3 {

    // keyPoint 方法三 快速排序分区优化
    // 1.前 k 个最大元素 -> 升序(快排) -> 拿到后面 k 个元素即可
    //   index 0 1 2 3 4 5
    //   value 1 2 3 4 5 6，若 k = 4，n - k = 6 - 4 = 2
    //             ↑
    //            n-k
    // 2.前 k 个最小元素 -> 降序(快排) -> 拿到后面 k 个元素即可
    // => 通过调整快排的'升序'和'降序'，从而保持主代码逻辑不变化
    public int[][] kClosest3(int[][] points, int k) {
        int n = points.length;
        // 只是改变快排逻辑，而 target 不发生变化
        int target = n - k;
        int left = 0, right = n - 1;
        while (true) {
            int pivotIndex = partition(points, left, right);
            if (pivotIndex == target) {
                break;
            } else if (pivotIndex < target) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }
        return Arrays.copyOfRange(points, n - k, n);
    }

    private int partition(int[][] nums, int low, int high) {
        if (high > low) {
            int pivotIndex = new Random().nextInt(high - low + 1) + low;
            swap(nums, pivotIndex, high);
        }
        // KeyPoint 根据不同题目，修改比较逻辑和排序
        // 1.欧几里德距离
        // 2.降序排列
        int pivot = nums[high][0] * nums[high][0]
                + nums[high][1] * nums[high][1];
        int less = low, great = low;
        for (; great < high; great++) {
            int distance = nums[great][0] * nums[great][0]
                    + nums[great][1] * nums[great][1];
            // 降序排列 => 修改比较符号即可，将 < 修改成 > 即可
            if (distance > pivot) {
                swap(nums, less, great);
                less++;
            }
        }
        swap(nums, less, high);
        return less;
    }

    // swap 方法 => 根据传入数组 int[][] nums 变化，调整相应代码，将 int 替换成 int[]
    private void swap(int[][] nums, int i, int j) {
        int[] tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
