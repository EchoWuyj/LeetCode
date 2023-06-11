package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 18:53
 * @Version 1.0
 */

public class _02_34_Find_first_and_last_position_of_element_in_sorted_array {
    
    /*
        34. 在排序数组中查找元素的第一个和最后一个位置
        给你一个按照 非递减顺序排列 的整数数组 nums，和一个目标值 target。
        请你找出给定目标值在数组中的开始位置和结束位置。
        如果数组中不存在目标值 target，返回 [-1, -1]。
        你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

        示例 1：
        输入：nums = [5,7,7,8,8,10], target = 8
        输出：[3,4]

        示例 2：
        输入：nums = [5,7,7,8,8,10], target = 6
        输出：[-1,-1]
        
        提示：
        0 <= nums.length <= 10^5
        -10^9 <= nums[i] <= 10^9
        nums 是一个非递减数组
        -10^9 <= target <= 10^9

     */

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[]{-1, -1};

        // KeyPoint 写代码技巧：
        // 1.以后写代码先将大体的流程以代码框架的方式表达出来，关键梳理清楚处理逻辑
        // 2.至于代码中的实现细节，先通过空的方法表示，后续再去实现
        int firstTargetIndex = searchFirstTargetIndex(nums, target);
        if (firstTargetIndex == -1) {
            return new int[]{-1, -1};
        }
        int lastTargetIndex = searchLastTargetIndex(nums, target);
        return new int[]{firstTargetIndex, lastTargetIndex};
    }

    // KeyPoint 方法一 思路一：不断的在循环体中查找目标元素
    // 1.1 '第一个'
    private int searchFirstTargetIndex1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                // 标准写法 nums[mid - 1] != target，这里 < 也是可以的
                if (mid == 0 || nums[mid - 1] < target) return mid;
                else right = mid - 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // 1.2 '最后一个'
    private int searchLastTargetIndex1(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                if (mid == nums.length - 1 || nums[mid + 1] > target) return mid;
                else left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    // KeyPoint 重点掌握
    // KeyPoint 方法二 思路二：在循环体中排除一定不存在目标元素的区间
    // 2.1 '第一个'
    private int searchFirstTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 不等号方向，最后 right 或者 left 移动的方向，以及求解问题是第一个还是最后一个
            // => 这三者是相互绑定，不要搞混了
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                // target <= nums[mid]，right 从右向左向 mid 逼近，直到就剩最后一个元素
                // 即为第一个等于目标元素下标，跳出 while 循环，进行后续比较
                right = mid;
            }
        }
        // 最后一个元素，若是等于 target，则一定是第一个等于 target
        if (nums[left] == target) return left;
        // 最后一个元素，若是不于 target，区间没有 target，返回 -1 即可
        return -1;
    }

    // 2.2 '最后一个'
    private int searchLastTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // KeyPoint 计算中间值 mid 需要靠右，否则会有死循环
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                // target >= nums[mid]，left 从左往右向 mid 位置向右逼近，直到就剩最后一个元素
                // 即为最后一个等于目标元素下标，跳出 while 循环，进行后续比较
                left = mid;
            }
        }
        if (nums[left] == target) return left;
        return -1;
    }
}
