package alg_02_train_dm._11_day_优先队列;

/**
 * @Author Wuyj
 * @DateTime 2023-05-23 15:32
 * @Version 1.0
 */
public class _08_239_sliding_window_maximum1 {

     /*
        239 滑动窗口最大值
        给你一个整数数组 nums，有一个大小为 k 的滑动窗口，从数组的最左侧移动 到 数组的最右侧
        你只可以看到在滑动窗口内的 k 个数字，滑动窗口每次只向右移动一位，返回滑动窗口中的最大值

        输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        输出：[3,3,5,5,6,7]

        滑动窗口的位置                  最大值
        ----------------------------------
        [1  3  -1] -3  5  3  6  7       3
         1 [3  -1  -3] 5  3  6  7       3
         1  3 [-1  -3  5] 3  6  7       5
         1  3  -1 [-3  5  3] 6  7       5
         1  3  -1  -3 [5  3  6] 7       6
         1  3  -1  -3  5 [3  6  7]      7

        提示：
        1 <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
        1 <= k <= nums.length

     */

    // KeyPoint 方法一 暴力解法
    // 遍历每个窗口，在每个窗口中找到最大值
    // 1 <= k <= nums.length
    // 1 <= nums.length <= 10^5
    // 时间复杂度 O((n-k)*k) => 相当于是 O(n^2) => 超时
    public int[] maxSlidingWindow1(int[] nums, int k) {
        int n = nums.length;
        // res 存储每个窗口最大值，窗口的个数 n - k + 1
        // => 设最后一个窗口，start 索引为 x(未知)，窗口长度为 k
        //    (n-1)-x +1 = k => x = n-k
        // => 从 0 ~ n-k，故一共有 n-k+1 个窗口
        int[] res = new int[n - k + 1];
        // 遍历每个窗口
        for (int i = 0; i < n - k + 1; i++) { // O(n-k)
            int maxNum = Integer.MIN_VALUE;
            // 遍历每个窗口中 k 个元素
            for (int j = i; j < i + k; j++) { // O(k)
                maxNum = Math.max(maxNum, nums[j]);
            }
            // KeyPoint 性能分析：
            // 1.每个窗口内，存在重复计算，性能瓶颈
            // 2.获取最大值，最快的方法，就是堆(大顶堆)，堆顶就是最大值
            res[i] = maxNum;
        }
        return res;
    }
}
