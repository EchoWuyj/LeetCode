package alg_01_ds_dm._01_line._05_algo._04_bs.train;

/**
 * @Author Wuyj
 * @DateTime 2023-04-03 16:09
 * @Version 1.0
 */

public class _01_704_BinarySearch {
    
    /*
        704. 二分查找 => 3 种实现
        给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，
        写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

        示例 1:
        输入: nums = [-1,0,3,5,9,12], target = 9
        输出: 4
        解释: 9 出现在 nums 中并且下标为 4

        提示：
        你可以假设 nums 中的所有元素是不重复的。
        n 将在 [1, 10000]之间。
        nums 的每个元素都将在 [-9999, 9999]之间。
     */

    // KeyPoint 思路 1：不断的在循环体中查找目标元素
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid])
                return mid;
            else if (target < nums[mid])
                right = mid - 1;
            else
                left = mid + 1;
        }
        // 循环结束后：left > right，数组区间里面的数据已经在
        //  while 循环体中已经处理完了，没有任何的数据需要后处理
        return -1;
    }

    // KeyPoint 思路 2：在循环体中排除一定不存在目标元素的区间 => 重点掌握
    //          既然是排除一定不存在目标元素的区间，则 if 判断条件为严格 > 或者 <
    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // left == right 循环结束，此时只有一个元素，不在循环体内处理，而在 while 循环外处理
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 这种思路下 if 判断只有两种情况
            if (target > nums[mid])
                // target > nums[mid] => target 必然不会在 mid 以左，包括 mid 本身，
                // 故 [left,mid] 全部排除，即小的那一侧舍弃，移动 left 指针
                left = mid + 1;
            else
                // target <= nums[mid] => target 必然不会在 mid + 1 以右，但不包括 mid 本身
                // 因为 target 有可能等于 nums[mid]，故 [mid+1,right] 全部排除
                // KeyPoint 记忆：target <= nums[mid]，含有等号则只能取 mid
                right = mid; // 从右往左移
        }
        // while 循环结束，将所有肯定不存在目标元素的区间全部排除，最后只剩一个元素，则处理该元素
        if (nums[left] == target) return left;
        return -1;
    }

    // KeyPoint 思路 2 变形 => 重点掌握
    //          只是修改 if 比较表达式的不等号方向，原来 > 改成 <
    public int search3(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            // KeyPoint 计算中间值 mid 需要靠右，否则会有死循环
            int mid = left + (right - left + 1) / 2;
            if (target < nums[mid]) // 严格小于 =>  right = mid 严格减 1
                right = mid - 1;
            else
                // target >= nums[mid] 非严格大于 => left = mid
                left = mid; // 从左往右移
        }
        if (nums[left] == target) return left;
        return -1;
    }

    // KeyPoint 思路 3：保证 while 循环结束，有两个元素在循环体外进行处理
    public int search4(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        // 循环结束后：left + 1 == right，在 while 循环外处理这两个元素，依次进行判断
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // 若要调整不等号 >，同时调整 left 和 right 移动策略
            if (target > nums[mid])
                // 不管是 >= 或者 <，都是统一移动到 mid，从而保证最后有两个元素在 while 之外进行处理
                left = mid;
            else
                right = mid;
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

    // KeyPoint 思路 3 变形：只是修改 if 比较表达式的不等号方向，原来 > 改成 <
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid])
                right = mid;
            else
                left = mid;
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
