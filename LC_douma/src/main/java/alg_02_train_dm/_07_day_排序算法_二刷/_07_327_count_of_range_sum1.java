package alg_02_train_dm._07_day_排序算法_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 11:44
 * @Version 1.0
 */

// KeyPoint 归并排序应用
public class _07_327_count_of_range_sum1 {

    /*
        327. 区间和的个数
        给你一个整数数组 nums 以及两个整数 lower 和 upper 。
        求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
        区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。

        示例 1：
        输入：nums = [-2,5,-1], lower = -2, upper = 2
        输出：3

        KeyPoint 解释
        num   -2 5 -1
        index  0 1  2

        i = 0，子数组 [-2] sum = -2  => 属于 [-2,2] √  => [0,0]
                     [-2,5] sum = 3 => 不属于 [-2,2] ×
                     [-2,5,-1] sum = 2 => 属于 [-2,2] √ => [0,2]

        i = 1，子数组 [5] sum = 5 => 不属于 [-2,2] ×
                      [5,-1] sum = 4 => 不属于 [-2,2] ×

        i = 2，子数组 [-1] sum = -1 属于 [-2,2] √  => [2,2]

        故：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2

        提示：
        1 <= nums.length <= 10^5
        -2^31 <= nums[i] <= 2^31 - 1
        -10^5 <= lower <= upper <= 10^5
        题目数据保证答案是一个 32 位 的整数

     */

    // KeyPoint 方法一 暴力解 => 超时
    // 时间复杂度 O(n^3)
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int count = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) { // O(n)
            for (int j = i; j < n; j++) {  // O(n)

                // KeyPoint 对数据范围敏感
                // 根据题目给的数据范围：-2^31 <= nums[i] <= 2^31 - 1
                // => 累加和 sum 可能溢出，所以需要使用 long 类型
                // => long 范围：[-9223372036854775808,9223372036854775807]

                // 测试用例
                // [-2147483647,0,-2147483647,2147483647]
                // -564
                // 3864

                long sum = 0;
                // 牢记这种形式，计算时间复杂度是算作 O(n)
                // 计算区间和，存在大量重复计算，可以优化
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum <= upper && sum >= lower) count++;
            }
        }
        return count;

        // KeyPoint 代码经验
        // 若测试用例中，数据 [-2147483647,0,-2147483647,2147483647] 出错
        // 多半是数据范围不够，将 int 修改成 long，再进行测试
    }

    // KeyPoint 方法二 前缀和 => 超时
    // 时间复杂度 O(n^2)
    public int countRangeSum3(int[] nums, int lower, int upper) {
        int n = nums.length;
        // KeyPoint 前缀和 long 类型
        // 注意：前缀和也是需要 long 类型的，因为本身就涉及累和操作，使用 int 可能导致数据溢出
        long[] prefixSum = new long[n + 1];
        prefixSum[0] = 0;

        // 构建前缀和
        for (int i = 1; i < n + 1; i++) { // O(n)
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
        int count = 0;
        for (int i = 0; i < n + 1; i++) { // O(n^2)
            for (int j = i + 1; j < n + 1; j++) {
                // 前缀和相减 => 局部元素累和
                // p[j] = p[i+1] = p[i] + n[i]
                // p[i]
                // p[j] - p[i] = n[i]
                // 必须是 long，要不然会溢出
                long sum = prefixSum[j] - prefixSum[i];
                if (sum <= upper && sum >= lower) count++;
            }
        }
        return count;
    }
}
