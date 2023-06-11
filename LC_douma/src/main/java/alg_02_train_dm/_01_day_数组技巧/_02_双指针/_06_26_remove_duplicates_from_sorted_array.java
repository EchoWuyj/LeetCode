package alg_02_train_dm._01_day_数组技巧._02_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 17:56
 * @Version 1.0
 */
public class _06_26_remove_duplicates_from_sorted_array {

    /*
        26. 删除有序数组中的重复项
        给你一个 升序排列 的数组 nums ，请你 原地 删除重复出现的元素，
        使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。
        不要使用额外的空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

        提示
        1 <= nums.length <= 3 * 10^4
        -10^4 <= nums[i] <= 10^4
        nums 已按 升序 排列

        KeyPoint  10^4 => O(n^2) √
                 3 * 10^4 => O(n^2) ×

        输入：nums = [1,1,2]
        输出：2, nums = [1,2]
        解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。
        不需要考虑数组中超出新长度后面的元素。

        输入：nums = [0,0,1,1,1,2,2,3,3,4]
        输出：5, nums = [0,1,2,3,4]
        解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。
        不需要考虑数组中超出新长度后面的元素。

     */

    // 注意：本题和移动零非常类似的
    // 1 移动零是将非零移动到数组前面
    // 2 本题将非重复元素移动到数组前面
    // KeyPoint 快慢指针
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 指向已经处理的区域(没有重复元素)最后一个位置
        int slow = 0;
        // 指向当前正在处理的元素
        // fast 指针从 1 开始，是前后两个元素之间才能判断是否重复，而不是自己和自己比较
        int fast = 1;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 返回新数组长度，数组从 0 开始，故 slow + 1
        return slow + 1;
    }
}
