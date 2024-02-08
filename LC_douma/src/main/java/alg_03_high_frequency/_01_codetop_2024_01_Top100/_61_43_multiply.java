package alg_03_high_frequency._01_codetop_2024_01_Top100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:37
 * @Version 1.0
 */
public class _61_43_multiply {

    // 字符串相乘
    // 直接模拟
    public String multiply(String num1, String num2) {
        // 特判
        // 字符串判断相等[引用数据类型]，使用 equals 方法，而不是使用 ==
        if (num1.equals("0") || num2.equals("0")) return "0";

        // m 和 n 都是在越界位置
        int m = num1.length(); // 被乘数
        int n = num2.length(); // 乘数
        String res = "";

        // 如：
        //   2 4 9 => 被乘数
        // * 5 6 4 => 乘数
        //--------------------
        //     计算结果

        // 乘数
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder tmp = new StringBuilder();
            // 被乘数
            // 末尾补"0"，不要忘记，结束条件 zero>i，必须严格大于
            for (int zero = n - 1; zero > i; zero--) {
                tmp.append("0");
            }
            // 减去 '0' 不要忘记
            int x = num2.charAt(i) - '0';
            int carry = 0;
            for (int j = m - 1; j >= 0; j--) {
                // 减去 '0' 不要忘记
                int y = num1.charAt(j) - '0';
                int product = x * y + carry;
                // KeyPoint 注意：运算针对的都是 product，不是 carry
                tmp.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) tmp.append(carry);
            // 需要反转 tmp.reverse()，将累加值 addString 赋值给 res
            res = addString(res, tmp.reverse().toString());
        }
        return res;
    }

    // 字符串形式两数相加
    public String addString(String num1, String num2) {
        int m = num1.length() - 1;
        int n = num2.length() - 1;

        StringBuilder builder = new StringBuilder();
        int carry = 0;

        // 不是 && 的关系，只要一个没有越界，while 循环就可以执行
        while (m >= 0 || n >= 0) {
            int x = (m >= 0) ? num1.charAt(m) - '0' : 0;
            int y = (n >= 0) ? num2.charAt(n) - '0' : 0;
            int sum = x + y + carry;
            // KeyPoint 运算针对的都是 sum，尤其是 carry，不是 carry = carry / 10;
            builder.append(sum % 10);
            carry = sum / 10;
            m--;
            n--;
        }

        if (carry != 0) builder.append(carry);
        // 需要反转 builder.reverse()
        return builder.reverse().toString();
    }
}
