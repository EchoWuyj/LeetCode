package alg_02_train_dm._12_day_滑动窗口_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:09
 * @Version 1.0
 */
public class _05_485_max_consecutive_ones {
    /*
       485. 最大连续 1 的个数
       给定一个二进制数组， 计算其中最大连续 1 的个数。

       示例：
       输入：[1,1,0,1,1,1]
       输出：3
       解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.

       提示：
       输入的数组只包含 0 和 1 。
       输入数组的长度是正整数，且不超过 10,000。

    */

    // KeyPoint 方法一 直接模拟求解，线性遍历一遍即可
    public int findMaxConsecutiveOnes1(int[] nums) {
        int res = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                // 记录连续 1 的个数
                count++;
            } else {
                // 重置前，记录最大连续 1 的个数
                res = Math.max(res, count);
                // 重置 count
                count = 0;
            }
        }

        // 输入
        // [1,1,0,1,1,1]
        // 输出
        // 2
        // 预期结果
        // 3

        // KeyPoint if else 分支涉及 Math.max 或者 Math.min  比较
        // if 分支中，涉及最大值比较：res = Math.max(res, count)，有可能不会执行
        // 故不能简单返回 res，最后返回结果前，需要再套一层 Math.max
        // 因为 nums 最后一个元素可能是 1，从而没有走 Math.max 分支，导致结果错误
        return Math.max(res, count);
    }

    // KeyPoint 方法二 滑动窗口
    // 适用场景： 1 多 0 少 => 主要执行操作：right++
    public int findMaxConsecutiveOnes2(int[] nums) {
        int res = 0;
        int left = 0, right = 0;
        int n = nums.length;
        while (right < n) {
            // 不连续点
            if (nums[right] == 0) {
                // 此时 right 在 0 位置，故可以使用 right - left，且不用 + 1
                res = Math.max(res, right - left);
                left = right + 1;
            }
            // nums[right] == 1，right 直接前移
            right++;
        }
        // 若 nums 最后一个元素为 1，则没有执行 if 循环，少执行一次 Math.max
        // 故最后返回前，需要再套一层 Math.max
        return Math.max(res, right - left);
    }

    // KeyPoint 方法三 => 如果 1 少的话，可以这样做
    // 适用场景： 1 少 0 多 => 主要执行操作：else 语句
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int left = 0, right = 0;
        int n = nums.length;
        while (right < n) {
            if (nums[right] == 1) {
                // 1.若 nums 最后一个元素为 1，需要计算 Math.max
                // 2.若 nums 最后一个元素为 0，本身就不需要计算 Math.max
                //   故最后可以直接返回 res
                res = Math.max(res, right - left + 1);
            } else {
                left = right + 1;
            }
            right++;
        }
        return res;
    }
}
