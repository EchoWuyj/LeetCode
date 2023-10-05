package alg_02_train_dm._01_day_数组技巧_二刷._02_技巧二_双指针;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 16:26
 * @Version 1.0
 */

//详细注释 01_283_MoveZeroes
public class _05_283_move_zeroes_推荐 {

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

        //      fast
        //       ↓
        // 6 0 0 3 8
        //   ↑
        //  slow

        // slow 指向最后一个非零元素的下个元素
        int slow = 0;
        // fast 遍历数组每个元素
        int fast = 0;

        //  KeyPoint for 循环代码
//        for (int fast = 0; fast < nums.length; fast++)

        // fast 越界，即遍历一遍数组，跳出 while 循环
        int n = nums.length;
        while (fast < n) {
            // [fast] != 0 和 [slow] 交换
            if (nums[fast] != 0) {
                // 若 fast == slow，则两指针指向同一元素
                // 没有必要进行交换，从而减少交换的次数
                if (slow != fast) {
                    // 交换 fast 和 slow 指向的元素
                    // 交换需要访问数组两次，可以优化
                    int tmp = nums[fast];
                    nums[fast] = nums[slow];
                    nums[slow] = tmp;
                }
                // 交换之后，slow 前移，fast 同时也前移
                slow++;
                fast++;
            } else {
                fast++;
            }
        }
    }

    // KeyPoint 方法二 快慢指针
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] != 0) {
                // 使用 if 判断，减少交换的次数
                // KeyPoint 注意
                // 实际上，不加上 if 判断，性能还更好
                // => 说明'赋值语句'比'比较语句'执行快
                if (slow != fast) {
                    // 使用直接赋值代替交换
                    nums[slow] = nums[fast];
                }
                slow++;
                fast++;
            } else {
                fast++;
            }
        }

        // slow 往后元素，再将其全部置 0
        while (slow < n) nums[slow++] = 0;
    }
}
