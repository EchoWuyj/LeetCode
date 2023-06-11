package alg_02_train_dm._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 20:55
 * @Version 1.0
 */
public class _03_477_total_hamming_distance {
    /*
        477. 汉明距离总和
        两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
        给你一个整数数组 nums，请你计算并返回 nums 中任意两个数之间 汉明距离的总和 。

        示例 1：
        输入：nums = [4,14,2]
        输出：6
        解释：
        在二进制表示中，4 表示为 0100 ，14 表示为 1110 ，2表示为 0010 。（这样表示是为了体现后四位之间关系）
        HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6

        示例 2
        输入：nums = [4,14,4]
        输出：4

        提示：
        1 <= nums.length <= 10^4
        0 <= nums[i] <= 10^9
        给定输入的对应答案符合 32-bit 整数范围

     */

    // KeyPoint 方法一 暴力求解
    // 时间复杂度：大于 O(n^2) => 超出时间限制
    public int totalHammingDistance1(int[] nums) {
        int res = 0;
        // O(n^2)
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += hammingDistance(nums[i], nums[j]); // O(k)
            }
        }
        return res;
    }

    public int hammingDistance(int x, int y) {
        // 使用异或计算 x 和 y 的不同位，结果中位为 1 ，说明这位不同
        int diff = x ^ y;
        // 计算 diff 中位 1 个数
        int res = 0;
        while (diff != 0) { // O(k)
            diff = diff & (diff - 1);
            res++;
        }

        return res;
    }

    // KeyPoint 方法二
    // 不同位的汉明距离是相互独立的 => 分别计算每一位的汉明距离即可
    // 考虑数组中每个数二进制的第 i 位，假设一共有 t 个 1 和 n - t 个 0
    // 那么显然在第 i 位的汉明距离的总和为 t * (n - t)，再将每一位汉明距离累加即可
    //  4  0100
    //  14 1110
    //  2  0010
    //       ↑ [2 个 1] * [1 个 0] = 2
    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        // 存储所有元素对应位的 1 的个数
        int[] cnt = new int[32];
        // 累加所有元素每一位的 1
        for (int num : nums) { // O(n)
            // 遍历数组元素指针
            int i = 0;
            // 累加当前元素每一位 1
            while (num > 0) { // O(k)
                // 检查每个位是否为 1，并累加
                cnt[i] += num & 1;
                num >>= 1;
                i++;
            }
        }

        int res = 0;
        for (int k : cnt) { // O(n)
            // 增强 for 循环，k 已经是数组中元素，不需要使用索引 num[index]
            res += k * (n - k);
        }
        return res;
    }
}
