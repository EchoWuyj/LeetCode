package alg_02_train_dm._13_day_综合应用一;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 20:34
 * @Version 1.0
 */
public class _02_167_TwoSum {

    /*
        167. 两数之和 II - 输入有序数组

        给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列，
        请你从数组中找出满足相加之和等于目标数 target 的两个数。
        如果设这两个数分别是 numbers[index1] 和 numbers[index2]，则 1 <= index1 < index2 <= numbers.length

        以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2
        你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。

        你所设计的解决方案必须只使用常量级的额外空间。

        示例 1：
        输入：numbers = [2,7,11,15], target = 9
        输出：[1,2]
        解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。

        示例 2：
        输入：numbers = [2,3,4], target = 6
        输出：[1,3]
        解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。

        提示：
        2 <= numbers.length <= 3 * 104
        -1000 <= numbers[i] <= 1000
        numbers 按 非递减顺序 排列
        -1000 <= target <= 1000
        仅存在一个有效答案

     */

    // KeyPoint 方法一 二分查找
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int[] twoSum1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];

        for (int i = 0; i < nums.length; i++) {
            // 二分查找 => O(logn) => 固定一个元素 x，在剩余的 [i + 1, nums.length-1] 区间上，二分查找 target - x
            int x = nums[i];
            int index = binarySearch(nums, i + 1, nums.length - 1, target - x);
            if (index != -1) {
                return new int[]{i + 1, index + 1};
            }
        }
        return new int[0];
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        // 二分查找 => left 和 right 可以取等
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return -1;
    }

    // KeyPoint 方法二 双指针 => 对撞指针
    // 使用双指针 => 降低时间复杂度和空间复杂度
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[0];
        int left = 0;
        int right = nums.length - 1;
        // 双指针 => left 和 right 可以不取等
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        // 返回一个长度为 0 的数组，也是可以返回 null
        return new int[0];
    }
}
