package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 21:10
 * @Version 1.0
 */
public class _03_477_total_hamming_distance {
    public int totalHammingDistance1(int[] nums) {
        int res = 0;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }

    public int hammingDistance(int x, int y) {
        int diff = x ^ y;
        int res = 0;
        while (diff != 0) {
            diff = (diff) & (diff - 1);
            res++;
        }
        return res;
    }

    public int totalHammingDistance(int[] nums) {
        int n = nums.length;
        int[] count = new int[32];
        for (int num : nums) {
            int i = 0;
            while (num > 0) {
                count[i] += num & 1;
                num >>= 1;
                i++;
            }
        }

        int res = 0;
        for (int x : count) {
            res += x * (n - x);
        }
        return res;
    }
}
