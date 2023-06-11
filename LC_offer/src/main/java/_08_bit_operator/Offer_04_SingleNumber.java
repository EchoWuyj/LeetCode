package _08_bit_operator;

/**
 * @Author Wuyj
 * @DateTime 2022-09-17 12:47
 * @Version 1.0
 */
public class Offer_04_SingleNumber {
    public int singleNumber(int[] nums) {
        int[] temp = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                temp[i] += (num >> i) & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (temp[i] % 3 != 0) {
                ans |= 1 << i;
            }
        }

        return ans;
    }
}
