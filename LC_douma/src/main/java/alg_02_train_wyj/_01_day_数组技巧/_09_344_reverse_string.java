package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 21:22
 * @Version 1.0
 */
public class _09_344_reverse_string {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
            left++;
            right--;
        }
    }
}
