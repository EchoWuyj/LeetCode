package alg_02_train_dm._13_day_综合应用一;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-05-24 21:50
 * @Version 1.0
 */
public class _05_15_ThreeSum {

    /*
        15. 三数之和
        给你一个整数数组 nums，判断是否存在三元组 [nums[i], nums[j], nums[k]]
        满足 i != j、i != k 且 j != k，同时还满足 nums[i] + nums[j] + nums[k] == 0
        请你返回所有和为 0 且不重复的三元组。注意：答案中不可以包含重复的三元组。

        示例 1：
        输入：nums = [-1,0,1,2,-1,-4]
        输出：[[-1,-1,2],[-1,0,1]]
        解释：
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0  => [-1,0,1]
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0  => [0,1,-1]，其中 [-1,0,1] 和 [0,1,-1] 是重复的三元组
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 => [-1,2,1]
        不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
        注意，输出的顺序和三元组的顺序并不重要。

        提示：
        3 <= nums.length <= 3000
        -10^5 <= nums[i] <= 10^5

     */

    // KeyPoint 方法一 暴力
    // 时间复杂度 O(n^3)
    public List<List<Integer>> threeSum1(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();

        Set<List<Integer>> res = new HashSet<>();

        // 本题返回数组元素值，不是元素索引，故可以排序
        Arrays.sort(nums); // O(nlogn)

        // O(n^3)
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        // 元素顺序和元素值相等，set 进行去重
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        // 将 set 转成 ArrayList
        return new ArrayList<>(res);
    }

    // KeyPoint 方法二  三数之和 => 两数之和 => 对撞指针
    // 时间复杂度 O(n^2)
    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();

        Set<List<Integer>> res = new HashSet<>();

        // 有序数组，查找元素，对撞指针
        Arrays.sort(nums); // O(nlogn)

        // O(n^2)
        for (int i = 0; i < nums.length; i++) {
            // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            // 若跳出 while 循环，都没有找到，则执行下轮 for 循环
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 找到一个结果后，left 和 right 指针需要继续移动，是否有别的解
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>(res); // O(n)
    }

    // KeyPoint 方法三  去掉 set 转 ArrayList，提高性能
    public List<List<Integer>> threeSum3(int[] nums) {
        if (nums == null || nums.length < 3)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // O(nlogn)

        // O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            // KeyPoint 这种方式去重前提条件：数组是有序的，相等的元素相邻
            // 当前 [i] 和 [i-1] 相同，则跳过，原因如下：
            // i-1 在前，在 i-1 这轮循环中，已经所有可能结果 result 获取
            // i 在后，在 i 轮轮循环中，所有可能结果 result' 只是 result 的子集，必然存在重复三元组
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // KeyPoint 手动去重 => 这种方式去重前提条件：数组是有序的，相等的元素相邻
                    // 跳过，left 和 right 指针，跳过相邻且相等的元素，从而实现去重
                    while (left < right && nums[left] == nums[++left]) ;
                    while (left < right && nums[right] == nums[--right]) ;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }
}
