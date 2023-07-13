package alg_02_train_wyj._05_day_数学;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 15:33
 * @Version 1.0
 */
public class _04_66_plus_one {

    public int[] plusOne1(int[] digits) {
        int l1 = digits.length - 1;
        int carry = 0;
        List<Integer> list = new ArrayList<>();
        int k = 1;
        while (l1 >= 0 || k != 0) {
            int x = l1 >= 0 ? digits[l1] : 0;
            int y = k != 0 ? k % 10 : 0;
            int sum = x + y + carry;
            list.add(sum % 10);
            carry = sum / 10;
            l1--;
            k /= 10;
        }
        if (carry != 0) list.add(carry);
        int size = list.size();
        int[] res = new int[size];
        for (int i = size - 1; i >= 0; i--) {
            res[size - 1 - i] = list.get(i);
        }
        return res;
    }

    public int[] plusOne2(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }
}
