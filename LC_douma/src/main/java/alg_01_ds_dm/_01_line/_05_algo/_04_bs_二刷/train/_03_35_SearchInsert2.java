package alg_01_ds_dm._01_line._05_algo._04_bs_二刷.train;

/**
 * @Author Wuyj
 * @DateTime 2023-07-01 17:20
 * @Version 1.0
 */
public class _03_35_SearchInsert2 {

    // KeyPoint 思路二：在循环体内排除没有目标值的区间 => 重点掌握
    //  => 找第一个'大于等于' target 的元素的下标
    public int searchInsert(int[] nums, int target) {
        if (nums == null) return -1;
        // 插入为第一个位置
        if (nums.length == 0) return 0;

        // 不能统一处理：if (nums == null || nums.length == 0) return -1;
        // 不过本题测试用例，没有涉及到 nums.length == 0

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        //  0 1 2 3
        // [1,3,5,6]
        // 7
        // 输出 3
        // 预期 4

        // 通过测试用例，测出来的 bug
        if (target > nums[nums.length - 1]) return nums.length;
        return left;
    }

    // 优化
    public int searchInsert1(int[] nums, int target) {
        if (nums == null) return -1;
        if (nums.length == 0) return 0;

        int left = 0;

        // 修改了 right 索引，变成了 nums.length，比原来 nums.length - 1 要多一位
        // if (target > nums[nums.length - 1]) return nums.length; 将该种断情况进行合并了
        int right = nums.length;

        // 当 left = right = nums.length，已经退出 while 循环了,
        // 不执 while 里面代码，不会访问 nums[mid]，即不存在数组越界
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                // 严格排除一半区间(左侧)
                left = mid + 1;
            } else {
                // target <= nums[mid]
                // 找'第一个' => 从右往左找
                right = mid;
            }
        }
        // 返回 left 就是第一个大于等于 target 的索引位置
        return left;
    }
}
