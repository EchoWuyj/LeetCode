package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 21:22
 * @Version 1.0
 */
public class _09_344_reverse_string {
    public void reverseString(char[] chars) {
        int left = 0, right = chars.length - 1;
        while (left < right) {
            char temp = chars[right];
            chars[right] = chars[left];
            chars[left] = temp;
            left++;
            right--;
        }
    }
}
