package alg_02_train_dm._13_day_综合应用一_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-08-01 11:24
 * @Version 1.0
 */
public class _02_167_TwoSum2 {

    // KeyPoint 方法二 双指针 => 对撞指针
    // 使用双指针 => 降低时间复杂度和空间复杂度
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        // 对撞指针
        // => 若 left 和 right 相等，nums[left] 和 nums[right] 是同一个元素，不符合题目要求
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                // 数组索引从 1 开始，故需要加 1
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}
