package alg_02_train_wyj._29_day_动态规划四;

/**
 * @Author Wuyj
 * @DateTime 2023-06-15 19:43
 * @Version 1.0
 */
public class _08_152_maximum_product_subarray {

    public int maxProduct1(int[] nums) {
        int n = nums.length;

        int[] maxP = new int[n];
        int[] minP = new int[n];

        maxP[0] = nums[0];
        minP[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < n; i++) {
            maxP[i] = Math.max(maxP[i - 1] * nums[i], Math.max(nums[i], minP[i - 1] * nums[i]));
            minP[i] = Math.min(minP[i - 1] * nums[i], Math.min(nums[i], maxP[i - 1] * nums[i]));
            res = Math.max(maxP[i], res);
        }
        return res;
    }

    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int maxP = nums[0];
        int minP = nums[0];
        int res = nums[0];

        for (int i = 1; i < n; i++) {
            int preMax = maxP, preMin = minP;
            maxP = Math.max(preMax * nums[i], Math.max(nums[i], preMin * nums[i]));
            minP = Math.min(preMin * nums[i], Math.min(nums[i], preMax * nums[i]));
            res = Math.max(maxP, res);
        }
        return res;
    }
}
