package alg_02_train_dm._01_day_数组技巧_二刷._02_技巧二_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 20:15
 * @Version 1.0
 */
public class _08_27_remove_element {

    /*
        27. 移除元素
        给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，
        并返回移除后数组的新长度。元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
        不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

        输入：nums = [3,2,2,3], val = 3
        输出：2, nums = [2,2]
        解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
        你不需要考虑数组中超出新长度后面的元素。
        例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。

        输入：nums = [0,1,2,2,3,0,4,2], val = 2
        输出：5, nums = [0,1,4,0,3]
        解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
        注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。

        提示
        0 <= nums.length <= 100
        0 <= nums[i] <= 50
        0 <= val <= 100
     */

    // 注意：本题和移动零非常类似的
    // 1.移动零：将非零移动到数组前面
    // 2.本题将'不等于 val 元素'移动到数组前面
    public int removeElement1(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        // slow 已经处理区域最后一个位置的下一个位置
        int slow = 0;
        int fast = 0;
        int n = nums.length;
        while (fast < n) {
            // nums[fast] != val 想要保留的元素，通过赋值操作，保存下来
            if (nums[fast] != val) {
                // slow 和 fast 指向同一元素，赋值没有意义，直接跳过
                if (fast != slow) {
                    nums[slow] = nums[fast];
                }
                slow++;
                fast++;
            } else {
                // nums[fast] == val，跳过该元素，即不想要的元素，fast ++，
                fast++;
            }
        }
        return slow;
    }

    // KeyPoint 进阶：如果要删除的元素特别少，怎么提高性能呢?
    // 1.考虑到要删除的元素特别少，且位于数组前面位置
    //   => 若使用快指针方式解决，则数组中除第一个元素外，后面位置的元素需要依次向前赋值，比较费时
    //   => 故不使用快慢指针，因为快慢指针都是从左往右依次遍历的
    // 2.使用对撞指针 left 和 right
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length - 1;
        // right > left => 表示所有元素都已经处理完了
        while (left <= right) {
            // 操作逻辑
            // 1.遇到要删除的元素，使用数组最后一个元素来替换该元素，right 前移
            // 2.替换后的数字有可能也是要删除的元素，故没有移动 left 指针，只是移动 right 指针
            //   下轮循环，接着判断 nums[left]
            if (nums[left] == val) {
                nums[left] = nums[right];
                right--;
            } else {
                // 不需要删除的元素，直接移动指针跳过
                // 避免数组大量前移赋值操作，从而提高性能
                left++;
            }
        }
        return right + 1;
    }
}
