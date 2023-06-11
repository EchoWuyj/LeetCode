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
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) return digits;
        }

        int[] res = new int[length + 1];
        res[0] = 1;
        return res;
    }

    public int[] plusOne2(int[] digits) {
        List<Integer> res = new ArrayList<>();
        int k = 1;
        int carry = 0;
        int l1 = digits.length - 1;
        while (l1 >= 0 || k != 0) {
            int x = l1 < 0 ? 0 : digits[l1];
            int y = k == 0 ? 0 : k % 10;
            int sum = x + y + carry;
            res.add(sum % 10);
            carry = sum / 10;
            l1--;
            k /= 10;
        }

        if (carry != 0) {
            res.add(carry);
        }
        int index = 0;
        int[] arr = new int[res.size()];
        for (int i = res.size() - 1; i >= 0; i--) {
            arr[index++] = res.get(i);
        }
        return arr;
    }
}
