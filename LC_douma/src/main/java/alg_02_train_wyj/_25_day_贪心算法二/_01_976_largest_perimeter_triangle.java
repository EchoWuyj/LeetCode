package alg_02_train_wyj._25_day_贪心算法二;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-04-13 23:07
 * @Version 1.0
 */
public class _01_976_largest_perimeter_triangle {
    public int largestPerimeter(int[] nums) {
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (nums[i - 2] + nums[i - 1] > nums[i]) {
                return nums[i - 2] + nums[i - 1] + nums[i];
            }
        }
        return 0;
    }
}
