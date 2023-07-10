package alg_02_train_wyj._07_day_排序算法;

/**
 * @Author Wuyj
 * @DateTime 2023-05-14 20:20
 * @Version 1.0
 */
public class _05_Offer_51_ReversePairs1 {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > nums[j]) count++;
            }
        }
        return count;
    }
}
