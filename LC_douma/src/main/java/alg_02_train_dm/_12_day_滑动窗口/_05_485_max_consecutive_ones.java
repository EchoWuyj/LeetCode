package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:09
 * @Version 1.0
 */
public class _05_485_max_consecutive_ones {
    /*
       leetcode 485. 最大连续 1 的个数
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
        int ans = 0;
        int ones = 0;
        for (int num : nums) {
            if (num == 1) {
                // 记录连续 1 的个数
                ones++;
            } else {
                // 重置前，记录最大连续 1 的个数
                ans = Math.max(ans, ones);
                // 重置 ones
                ones = 0;
            }
        }
        // if 分支中，涉及最大值的比较，不能简单返回 ans，最后返回结果前，需要再套一层 Math.max
        // 因为 nums 最后一个元素可能是 1，从而没有走 Math.max 分支，导致结果错误
        return Math.max(ans, ones);
    }

    // KeyPoint 方法二 滑动窗口 => 如果 0 少的话，可以这样做
    public int findMaxConsecutiveOnes2(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                // 此时 right 在 0 位置，故可以使用 right - left，且不用 + 1
                ans = Math.max(ans, right - left);
                left = right + 1;
            }
            // nums[right] == 1，right 直接前移
            right++;
        }
        // nums 最后一个元素为 1，没有执行 if 循环，少一次 Math.max
        // 故最后返回前，需要再套一层 Math.max
        return Math.max(ans, right - left);
    }

    // KeyPoint 方法三 => 如果 1 少的话，可以这样做
    public int findMaxConsecutiveOnes(int[] nums) {
        int ans = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 1) {
                // 若 nums 最后一个元素为 1，需要计算 Math.max
                // 若 nums 最后一个元素为 0，也不需要计算 Math.max，故最后可以直接返回 ans
                ans = Math.max(ans, right - left + 1);
            } else {
                left = right + 1;
            }
            right++;
        }
        return ans;
    }
}
