package alg_02_train_wyj._06_day_位运算;

/**
 * @Author Wuyj
 * @DateTime 2023-04-22 21:42
 * @Version 1.0
 */
public class _09_260_single_number_iii {
    public int[] singleNumber(int[] nums) {
        int bitMask = 0;
        for (int num : nums) {
            bitMask ^= num;
        }

        int diff = bitMask & (-bitMask);

        int[] res = new int[2];
        for (int num : nums) {
            if ((diff & num) != 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
