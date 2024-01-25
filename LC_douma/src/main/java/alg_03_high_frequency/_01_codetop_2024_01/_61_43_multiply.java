package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 16:37
 * @Version 1.0
 */
public class _61_43_multiply {
    public String multiply(String num1, String num2) {
        // 特判
        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length();
        int n = num2.length();
        String res = "";

        for (int i = n - 1; i >= 0; i--) {
            StringBuilder tmp = new StringBuilder();
            // 末尾补"0"，不要忘记
            for (int zero = n - 1; zero > i; zero--) {
                tmp.append("0");
            }
            int x = num2.charAt(i) - '0';
            int carry = 0;
            for (int j = m - 1; j >= 0; j--) {
                int y = num1.charAt(j) - '0';
                int product = x * y + carry;
                // KeyPoint 运算针对的都是 product，不是 carry
                tmp.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) tmp.append(carry);
            // 需要反转 tmp.reverse()
            // 将累加值 addString 赋值给 res
            res = addString(res, tmp.reverse().toString());
        }
        return res;
    }

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
