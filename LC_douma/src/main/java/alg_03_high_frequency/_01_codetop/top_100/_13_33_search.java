package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-12 23:29
 * @Version 1.0
 */
public class _13_33_search {

    // 搜索旋转排序数组
    // 二分查找
    public int search(int[] nums, int target) {
        // 特判
        if (nums == null || nums.length == 0) return -1;

        // 定义左右边界
        int left = 0;
        int right = nums.length - 1;

        // 二分查找，结果值在区间内
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) return mid;
            // 判断依据：[left] 和 [mid] 进行比较，划分左边有序还是右边有序
            // 左边有序
            if (nums[left] <= nums[mid]) {
                // 判断 target 区间
                // target 和 nums[mid] 比较不再考虑相等情况
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右边有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        // 没有找到，返回-1
        return -1;
    }
}
