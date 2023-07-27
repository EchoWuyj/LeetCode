package alg_02_train_dm._12_day_滑动窗口;

/**
 * @Author Wuyj
 * @DateTime 2023-05-05 20:11
 * @Version 1.0
 */
public class _07_1004_max_consecutive_ones_iii {
     /* 
        1004. 最大连续 1 的个数 III
        给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。

        返回仅包含 1 的最长(连续)子数组的长度。

        示例 1：
        输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
        输出：6
        解释：
        [1,1,1,0,0,1,1,1,1,1,1]
        粗体数字从 0 翻转到 1，最长的子数组长度为 6。

        示例 2：
        输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
        输出：10
        解释：
        [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
        粗体数字从 0 翻转到 1，最长的子数组长度为 10。

        提示：
        1 <= A.length <= 20000
        0 <= K <= A.length
        A[i] 为 0 或 1

     */

    // KeyPoint 方法一 记录当前窗口中 0 的个数
    // 本质和 487 题是一样的
    public int longestOnes1(int[] nums, int k) {
        int res = 0;
        int left = 0, right = 0;
        int count = 0;
        int n = nums.length;
        while (right < n) {
            if (nums[right] == 0) {
                // 记录当前窗口中 0 的个数
                count++;
                // 窗口中有 k+1 个 0，触发计算 res
                if (count == k + 1) {
                    // right 在 0 位置，直接使用 right - left 即可
                    res = Math.max(res, right - left);
                }
            }

            // left 右移，保证窗口中只有 k 个 0
            while (count > k) {
                // 只是 nums[left] == 0，才将 count 减 1
                if (nums[left] == 0) count--;
                // 否则只是移动 left
                left++;
            }

            right++;
        }
        // res 是在 nums[right] == 0 更新，故返回 res 前，还得套一层 Math.max
        return Math.max(res, right - left);
    }

    // KeyPoint 方法二 记录当前窗口中 1 的个数
    public int longestOnes(int[] nums, int k) {
        int res = 0;
        int left = 0, right = 0;
        int n = nums.length;
        // 记录当前窗口中 1 的个数
        int oneCnt = 0;
        while (right < n) {
            if (nums[right] == 1) oneCnt++;
            // 本题限制 k 个 0 => 窗口长度 - 1 个数 = 0 个数
            // 1.若 0 个数 > k，缩短窗口长度
            // 2.若 0 个数 = k，内层 while 结束循环，同时计算 res
            while ((right - left + 1) - oneCnt > k) {
                // 缩小 oneCnt，直到跳出 while 循环
                if (nums[left] == 1) oneCnt--;
                left++;
            }
            res = Math.max(res, right - left + 1);
            // 右移
            right++;
        }
        return res;
    }
}
