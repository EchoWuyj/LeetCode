package _04_binary_search;

/**
 * @Author Wuyj
 * @DateTime 2022-08-27 14:32
 * @Version 1.0
 */
public class Offer_57_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 定义头指针 left
        int left = 0;
        // 定义尾指针 right
        int right = nums.length - 1;

        // 移动 left 和 right ，直到 left 在 right 右侧或者相遇为止
        while (left < right) {
            // 计算此时两个指针指向的元素值之和
            int sum = nums[left] + nums[right];

            // 如果这两个元素值之和小于目标值 target
            if (sum < target) {
                // 由于数组是递增排序的数组
                // 所以需要去找更大的数，那 left 向右移动
                left++;

                // 如果这两个元素值之和大于目标值 target
            } else if (sum > target) {
                right--;
            } else {

                // 说明找到一对符合要求的数,返回即可
                return new int[]{nums[left], nums[right]};
            }
        }

        return new int[0];
    }
}
