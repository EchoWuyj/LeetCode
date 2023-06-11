package alg_02_train_dm._13_day_综合应用一;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-05-25 13:19
 * @Version 1.0
 */
public class _06_18_FourSum {

    /*
        18. 四数之和
        给定一个包含 n 个整数的数组 nums 和一个目标值 target，
        判断 nums 中是否存在四个元素 a，b，c和 d，使得 a + b + c + d 的值与 target 相等？
        0 <= a, b, c, d < n，a、b、c 和 d 互不相同
        找出所有满足条件且不重复的四元组。 注意：答案中不可以包含重复的四元组。

        示例 1：
        给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
        满足要求的四元组集合为：
            [[-1,  0, 0, 1],
             [-2, -1, 1, 2],
             [-2,  0, 0, 2]]

        提示：
        1 <= nums.length <= 200
        -10^9 <= nums[i] <= 10^9
        -10^9 <= target <= 10^9
    */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length < 4)
            return new ArrayList<>();
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        int n = nums.length;

        // O(n^3)
        // 固定两个元素，在剩余数组中找两个数，两数之和为 target
        // i 最多为 n-4，保证后面有 3 个数，能稍微提高一点性能
        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // KeyPoint 使用 long 防止溢出
                // -10^9 <= nums[i] <= 10^9，相加确实可能越界，一般是测试用例，报错提供的信息

                // 输入
                // [1000|000|000,1000000000,1000000000,1000000000]
                // -294967296
                // 输出
                // [[1000000000,1000000000,1000000000,1000000000]]

                // 解释：
                // 4 个 1000000000 相加的结果，已经超过了 int 最大值 214|748|3647 范围
                // 而数据相加的溢出的结果正好是 -294967296，所以该测试用例能通过，但其实是不对的

                // 牢记：最大值和最小值
                // Integer.MAX_VALUE = 214|748|3647
                // Integer.MIN_VALUE = -214|748|3648

                long partSum = nums[i] + nums[j];
                int left = j + 1;
                int right = n - 1;

                while (left < right) {
                    // bug fix： 使用 long 防止溢出
                    long sum = partSum + nums[left] + nums[right];
                    if (sum > target) {
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[++left]) ;
                        while (left < right && nums[right] == nums[--right]) ;
                    }
                }
            }
        }

        return res;
    }
}
