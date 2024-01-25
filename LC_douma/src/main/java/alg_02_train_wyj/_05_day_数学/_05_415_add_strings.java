package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 16:21
 * @Version 1.0
 */
public class _05_415_add_strings {
    public String addStrings(String num1, String num2) {
        int m = num1.length()-1;
        int n = num2.length()-1;
        int carry = 0;
        StringBuilder builder = new StringBuilder();
        while (m >= 0 || n >= 0) {
            int x = m >= 0 ? num1.charAt(m) - '0' : 0;
            int y = n >= 0 ? num2.charAt(n) - '0' : 0;
            int sum = x + y + carry;
            builder.append(sum % 10);
            carry = sum / 10;
            m--;
            n--;
        }
        if (carry != 0) builder.append(carry);
        return builder.reverse().toString();
    }
}
