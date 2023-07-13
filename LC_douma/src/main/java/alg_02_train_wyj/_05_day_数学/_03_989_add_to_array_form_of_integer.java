package alg_02_train_wyj._05_day_数学;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 13:43
 * @Version 1.0
 */
public class _03_989_add_to_array_form_of_integer {

    public static List<Integer> addToArrayForm(int[] num, int k) {
        int l1 = num.length - 1;
        String str = String.valueOf(k);
        int n = str.length();
        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums2[i] = (int) str.charAt(i) - '0';
        }
        System.out.println(Arrays.toString(nums2));
        int l2 = nums2.length - 1;
        int carry = 0;
        List<Integer> res = new ArrayList<>();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? num[l1] : 0;
            int y = l2 >= 0 ? nums2[l2] : 0;
            int sum = x + y + carry;
            res.add(sum % 10);
            carry = sum / 10;
            l1--;
            l2--;
        }
        if (carry != 0) res.add(carry);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 0, 0};
        int k = 34;
        addToArrayForm(arr, k);
    }

    public static List<Integer> addToArrayForm2(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        int l1 = num.length - 1;
        int carry = 0;
        while (l1 >= 0 || k != 0) {
            int x = l1 >= 0 ? num[l1] : 0;
            int y = k != 0 ? k % 10 : 0;
            int sum = x + y + carry;
            res.add(sum % 10);
            carry = sum / 10;
            l1--;
            k /= 10;
        }

        if (carry != 0) res.add(carry);
        Collections.reverse(res);
        return res;
    }
}
