package alg_03_leetcode_top_wyj.class_09;

/**
 * @Author Wuyj
 * @DateTime 2023-02-25 20:33
 * @Version 1.0
 */
public class Problem_0066_PlusOne {
    public static int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] arr = new int[digits.length + 1];
        arr[0] = 1;
        return arr;
    }
}
