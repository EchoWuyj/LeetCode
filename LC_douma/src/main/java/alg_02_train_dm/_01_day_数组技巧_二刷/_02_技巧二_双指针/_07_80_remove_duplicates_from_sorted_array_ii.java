package alg_02_train_dm._01_day_数组技巧_二刷._02_技巧二_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 18:12
 * @Version 1.0
 */
public class _07_80_remove_duplicates_from_sorted_array_ii {
    /*
        80. 删除有序数组中的重复项 II
        给你一个有序数组 nums ，请你 原地 删除重复出现的元素，
        使得出现次数超过两次的元素只出现两次，返回删除后数组的新长度。
        不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

        输入：nums = [1,1,1,2,2,3]
        输出：5, nums = [1,1,2,2,3]
        解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3
         不需要考虑数组中超出新长度后面的元素。

        输入：nums = [0,0,1,1,1,1,2,3,3]
        输出：7, nums = [0,0,1,1,2,3,3]
        解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3
        不需要考虑数组中超出新长度后面的元素。

        提示
        1 <= nums.length <= 3 * 104
        -104 <= nums[i] <= 104
        nums 已按升序排列
     */

    // KeyPoint 方法一 快慢指针 => slow 定义方式一
    public int removeDuplicates(int[] nums) {
        // KeyPoint 防御式编程
        if (nums != null && nums.length <= 2) return nums.length;

        // slow 和 fast 定义，跳过 0 和 1 位置
        // => 即使这两个位置元素相同，也是满足相同元素最多出现两次的要求

        // slow 已经处理区域最后一个位置的下一个位置
        // 区别：前一题 slow 定义
        int slow = 2;
        int fast = 2;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != nums[slow - 2]) {
                // KeyPoint 移动和赋值
                // 先赋值后移动，还是先移动后赋值 => 关键在 slow 指针的定义
                // 若 slow 已经处理区域最后一个位置的下一个位置
                // => 说明 slow 已经在合适的位置
                // => 先将 [fast] 赋值 给 [slow]，再去移动 slow
                nums[slow] = nums[fast];
                slow++;
                fast++;
            } else {
                // nums[fast] == nums[slow - 2]
                // 不是想要的结果，故 fast 直接前移一步
                // slow ~ fast 区域 => 已处理区域，但是不符合题目要求
                fast++;
            }
        }
        // slow 已经处理区域最后一个位置的下一个位置，直接返回 slow 即可，不需要再 + 1
        return slow;
    }

    // KeyPoint 方法二 快慢指针 => slow 定义方式二
    public int removeDuplicates1(int[] nums) {
        if (nums != null && nums.length <= 2) return nums.length;
        // slow 已经处理区域的最后一个位置
        int slow = 1;
        int fast = 2;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != nums[slow - 1]) {
                // slow 已经处理区域的最后一个位置，只有先移动后面一个位置，才能去赋值
                slow++;
                nums[slow] = nums[fast];
                fast++;
            } else {
                fast++;
            }
        }
        return slow + 1;
    }
}
