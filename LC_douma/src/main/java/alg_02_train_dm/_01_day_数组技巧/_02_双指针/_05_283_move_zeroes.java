package alg_02_train_dm._01_day_数组技巧._02_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 16:26
 * @Version 1.0
 */
public class _05_283_move_zeroes {

    /*
        283. 移动零
        给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，
        同时保持非零元素的相对顺序。

        请注意，必须在不复制数组的情况下原地对数组进行操作。
        尽量减少操作次数

        输入: nums = [0,1,0,3,12]
        输出: [1,3,12,0,0]

        输入: nums = [0]
        输出: [0]
     */

    // KeyPoint 方法一 快慢指针
    public void moveZeroes1(int[] nums) {
        if (nums.length == 0) return;

        // 指向最后一个非零元素的下个元素
        int slow = 0;
        // 遍历每个元素
        int fast = 0;

        // for 循环代码
        // for(int fast =0;fast<nums.length;fast++)

        // fast 越界，即遍历一遍数组，跳出 while 循环
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                // fast == slow 指针指向同一元素，则没有必要进行交换，从而减少交换的次数
                if (slow != fast) {
                    // 交换 fast 和 slow 指向的元素
                    int tmp = nums[fast];
                    nums[fast] = nums[slow];
                    nums[slow] = tmp;
                }
                // 交换之后，slow 前移
                slow++;
            }
            // fast 每次都是需要往前走一步
            fast++;
        }
    }

    // KeyPoint 方法二 快慢指针
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) return;
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                // 不加上 if 判断，性能还更好 => 说明'赋值语句'比'比较语句'执行快
                if (slow != fast) { // 减少交换的次数
                    // 使用直接赋值代替交换
                    nums[slow] = nums[fast];
                }
                slow++;
            }
            fast++;
        }
        // slow 往后元素，再将其全部置 0
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
