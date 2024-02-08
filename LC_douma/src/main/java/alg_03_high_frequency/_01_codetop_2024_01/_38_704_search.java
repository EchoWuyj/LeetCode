package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 22:05
 * @Version 1.0
 */
public class _38_704_search {
    // 二分查找
    public int search(int[] nums, int target) {
        // 特判
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        // 查找结果在区间内的 while 循环形式
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 通过一个 if else 语句进行判断
            if (target == nums[mid]) {
                return mid;
            } else if (target < nums[mid]) {
                // 通过修改 mid 赋值给 right 和 left
                // 左侧 left / right = 右侧 mid
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
