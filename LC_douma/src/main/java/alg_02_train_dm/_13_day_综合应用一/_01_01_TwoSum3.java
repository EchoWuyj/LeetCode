package alg_02_train_dm._13_day_综合应用一;

import java.util.Arrays;

/**
 * @Author Wuyj
 * @DateTime 2023-07-31 13:04
 * @Version 1.0
 */
public class _01_01_TwoSum3 {

    // KeyPoint 方法三 双指针
    // 时间复杂度：O(nlogn)
    // 空间复杂度：O(1)
    public int[] twoSum3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return null;
        // KeyPoint 存在 bug
        // 不能对原数组进行排序，因为排序会改变原数组中元素的相对位置(索引)，这样返回索引就不对了
        Arrays.sort(nums); // O(nlogn)
        int n = nums.length;
        // 对撞指针，从数组两头，开始遍历
        int i = 0;
        int j = n - 1;
        while (i < j) { // O(n/2)
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{i, j};
            } else if (sum < target) {
                i++;
            } else { // sum > target
                j--;
            }
        }
        return null;
    }
}
