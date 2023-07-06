package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-21 21:10
 * @Version 1.0
 */
public class _03_477_total_hamming_distance {
    public int totalHammingDistance1(int[] nums) {
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; i++) {
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }

    public int hammingDistance(int x, int y) {
        int diff = x ^ y;
        int res = 0;
        while (diff != 0) {
            diff = diff & (diff - 1);
            res++;
        }
        return res;
    }

    public int totalHammingDistance(int[] nums) {
        int[] cnt = new int[32];

        int n = nums.length;
        for (int num : nums) {
            int index = 0;
            while (index < 31 && num > 0) {
                cnt[index] += (num & 1);
                num >>= 1;
                index++;
            }
        }

        int res = 0;
        for (int count : cnt) {
            res += count * (n - count);
        }
        return res;
    }
}
