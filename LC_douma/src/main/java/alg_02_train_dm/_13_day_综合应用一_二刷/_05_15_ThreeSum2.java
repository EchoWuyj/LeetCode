package alg_02_train_dm._13_day_综合应用一_二刷;

import java.util.*;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 15:47
 * @Version 1.0
 */
public class _05_15_ThreeSum2 {

    // KeyPoint 方法二  三数之和 => 两数之和 => 对撞指针 => 勉强通过
    // 时间复杂度 O(n^2)
    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null || nums.length < 3) return new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        // 有序数组，查找元素，对撞指针
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        // O(n^2)
        for (int i = 0; i < n; i++) {
            // nums[i] + target = 0 => target = -nums[i]
            int target = -nums[i];
            // 问题转化：在 i+1 ... n-1 中查找相加等于 -nums[i] 的两个数
            int left = i + 1;
            int right = n - 1;
            // 若跳出 while 循环，都没有找到，则执行下轮 for 循环
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    set.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // KeyPoint 找到一个结果后，left 和 right 指针需要继续移动，是否有别的解
                    // 注意：left 和 right 同时移动
                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        // set 转 List，时间复杂度 O(n)
        return new ArrayList<>(set);
    }
}
