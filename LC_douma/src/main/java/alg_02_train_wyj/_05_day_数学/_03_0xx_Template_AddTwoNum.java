package alg_02_train_wyj._05_day_数学;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-07-12 12:37
 * @Version 1.0
 */
public class _03_0xx_Template_AddTwoNum {

    public static List<Integer> addTwoNum(int[] nums1, int[] nums2) {
        int l1 = nums1.length - 1;
        int l2 = nums2.length - 1;
        int carry = 0;
        List<Integer> res = new ArrayList<>();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? nums1[l1] : 0;
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
        int[] arr1 = {1, 3, 4};
        int[] arr2 = {2, 1};
        System.out.println(addTwoNum(arr1, arr2)); // [1, 5, 5]

        int[] arr3 = {1, 3};
        int[] arr4 = {2, 1, 3};
        System.out.println(addTwoNum(arr3, arr4)); // [2, 2, 6]

        int[] arr5 = {1, 3};
        int[] arr6 = {2, 1};
        System.out.println(addTwoNum(arr5, arr6)); // [3, 4]
    }
}
