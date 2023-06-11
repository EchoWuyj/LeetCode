package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-19 21:55
 * @Version 1.0
 */
public class _06_67_add_binary {
    /*
        67. 二进制求和
        给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。

        示例 1：
        输入:a = "11", b = "1"
        输出："100"

        示例 2：
        输入：a = "1010", b = "1011"
        输出："10101"

        提示：
        1 <= a.length, b.length <= 104
        a 和 b 仅由字符 '0' 或 '1' 组成
        字符串如果不是 "0" ，就不含前导零

     */
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int l1 = a.length() - 1;
        int l2 = b.length() - 1;
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 < 0 ? 0 : a.charAt(l1) - '0';
            int y = l2 < 0 ? 0 : b.charAt(l2) - '0';

            int sum = x + y + carry;
            // 将 10 进制替换成 2进制
            res.append(sum % 2);
            carry = sum / 2;

            l1--;
            l2--;
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }
}
