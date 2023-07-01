package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-07-01 16:34
 * @Version 1.0
 */
public class _02_34_Find_first_and_last_position_of_element_in_sorted_array2 {

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

    // KeyPoint 重点掌握
    // KeyPoint 方法二 思路二：在循环体中排除一定不存在目标元素的区间
    // 注意事项
    // 1.不等号方向 > 或 <
    // 2.从左往右，还是 从右往左 的逼近方向
    // 3.以及求解问题：第一个 还是 最后一个
    //  => 这三者是相互绑定，不要搞混了
    // 本题：求'第一个'等于 target 元素
    // 1. >
    // 2.从右往左
    // 3.第一个
    private int searchFirstTargetIndex(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                // target <= nums[mid]，right 从右向左向 mid 逼近，直到就剩最后一个元素
                // 即为第一个等于目标元素下标，跳出 while 循环，进行后续比较
                right = mid;
            }
        }
        // 第一个 '大于等于' target 的 nums[left]
        // 再附加 '等于'判断，
        // => 第一个等于 target 的 nums[left]

        // 最后一个元素，若是等于 target，则一定是第一个等于 target
        if (nums[left] == target) return left;
        // 最后一个元素，若是不于 target，区间没有 target，返回 -1 即可
        return -1;
    }

    // 本题：求'最后一个'等于 target 元素
    // 1. <
    // 2.从左往右
    // 3.最后一个
    // 4.额外补充：计算 mid 得 +1
    private int searchLastTargetIndex(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // 计算中间值 mid 需要靠右，否则会有死循环
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
