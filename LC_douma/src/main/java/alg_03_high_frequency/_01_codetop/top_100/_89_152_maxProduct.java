package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-17 18:24
 * @Version 1.0
 */
public class _89_152_maxProduct {
    public int maxProduct(int[] nums) {

        // 乘积最大子数组
        // 给你一个整数数组 nums，请你找出数组中乘积最大的非空连续子数组(该子数组中至少包含一个数字)
        // 并返回该子数组所对应的乘积。

        int n = nums.length;
        // maxP[i] 表示以索引为 i 的元素结尾的'最大'子数组乘积
        int[] maxProduct = new int[n];
        int[] minProduct = new int[n];

        minProduct[0] = nums[0];
        maxProduct[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < n; i++) {
            maxProduct[i] = Math.max(maxProduct[i - 1] * nums[i],
                    Math.max(nums[i], minProduct[i - 1] * nums[i]));
            minProduct[i] = Math.min(minProduct[i - 1] * nums[i],
                    Math.min(nums[i], maxProduct[i - 1] * nums[i]));
            res = Math.max(res, maxProduct[i]);
        }

        return res;
    }
}
