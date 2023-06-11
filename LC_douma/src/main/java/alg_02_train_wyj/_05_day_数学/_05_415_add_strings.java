package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 16:21
 * @Version 1.0
 */
public class _05_415_add_strings {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int carry = 0;
        while (l1 >= 0 || l2 >= 0) {
            // -'0' => 字符转数字
            int x = l1 < 0 ? 0 : num1.charAt(l1) - '0';
            int y = l2 < 0 ? 0 : num2.charAt(l2) - '0';
            int sum = x + y + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            l1--;
            l2--;
        }

        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
