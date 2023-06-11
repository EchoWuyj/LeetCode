package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 21:42
 * @Version 1.0
 */
public class _09_260_single_number_iii {
    public int[] singleNumber(int[] nums) {
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;
        int diff = bitmask & (-bitmask);
        int[] ans = new int[2];
        for (int num : nums) {
            if ((diff & num) != 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        return ans;
    }
}
