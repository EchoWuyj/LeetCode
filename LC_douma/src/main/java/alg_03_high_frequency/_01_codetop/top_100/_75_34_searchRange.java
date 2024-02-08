package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 21:43
 * @Version 1.0
 */
public class _75_34_searchRange {

    // 在排序数组中查找元素的第一个和最后一个位置
    // 二分查找
    public int[] searchRange(int[] nums, int target) {
        // 特判
        if (nums == null || nums.length == 0) {
            // 没有找到，则返回 {-1,-1}
            return new int[]{-1, -1};
        }
        int firstIndex = getFirstIndex(nums, target);
        int lastIndex = getLastIndex(nums, target);
        return new int[]{firstIndex, lastIndex};
    }

    private int getFirstIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 以 target 为分解
            if (target > nums[mid]) {
                // 左侧一半不要，left 更新新位置
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 最后跳出 while 循环，left 和 right 是同一个位置
        if (nums[left] == target) return left;
        return -1;
    }

    private int getLastIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 加 1 不要丢了
            int mid = left + (right - left + 1) / 2;
            // 以 target 为分解
            if (target < nums[mid]) {
                // 右侧一半不要，right 更新新位置
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }
}
