package alg_02_train_dm._05_day_数学_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 21:53
 * @Version 1.0
 */
public class _05_415_add_strings {

    /*
        415. 字符串相加
        给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
        你不能使用任何內建的用于处理大整数的库(比如 BigInteger)，也不能直接将输入的字符串转换为整数形式。

        示例 1：
        输入：num1 = "11", num2 = "123"
        输出："134"

        示例 2：
        输入：num1 = "456", num2 = "77"
        输出："533"

        示例 3：
        输入：num1 = "0", num2 = "0"
        输出："0"

     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        // KeyPoint 注意：减 1，不要遗漏了
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
        // StringBuilder 中有 reverse API
        return sb.reverse().toString();
    }
}
