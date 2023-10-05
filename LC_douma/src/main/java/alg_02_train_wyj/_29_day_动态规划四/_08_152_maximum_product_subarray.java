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
            res = Math.max(res, maxP[i]);
        }
        return res;
    }

    public int maxProduct2(int[] nums) {
        int n = nums.length;

        int curMaxP = nums[0];
        int curMinP = nums[0];
        int res = curMaxP;

        for (int i = 1; i < n; i++) {
            int preMaxP = curMaxP, preMinP = curMinP;
            curMaxP = Math.max(curMaxP * nums[i], Math.max(nums[i], nums[i] * preMinP));
            curMinP = Math.min(preMinP * nums[i], Math.min(nums[i], nums[i] * preMaxP));
            res = Math.max(curMaxP, res);
        }
        return res;
    }
}
