package alg_03_high_frequency._01_codetop.top_100;

/**
 * @Author Wuyj
 * @DateTime 2024-01-16 18:13
 * @Version 1.0
 */
public class _61_43_multiply_debug {
    public static String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) return "0";
        String res = "0";
        // KeyPoint m 和 n 不用减一，for 循环中已经减一了
        int m = num1.length();
        int n = num2.length();

        for (int i = n - 1; i >= 0; i--) {
            StringBuilder cur = new StringBuilder();
            for (int zero = n - 1; zero > i; zero--) cur.append("0");

            int x = num2.charAt(i) - '0';
            int carry = 0;

            for (int j = m - 1; j >= 0; j--) {
                int y = num1.charAt(j) - '0';
                int sum = x * y + carry;
                // System.out.println(sum);
                cur.append(sum % 10);
                carry = sum / 10;
            }
            if (carry != 0) cur.append(carry);
            res = add(res, cur.reverse().toString());
        }
        return res;
    }

    public static String add(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int m = num1.length() - 1;
        int n = num2.length() - 1;
        int carry = 0;
        while (m >= 0 || n >= 0) {
            int x = (m >= 0) ? num1.charAt(m) - '0' : 0;
            int y = (n >= 0) ? num2.charAt(n) - '0' : 0;
            int sum = x + y + carry;
            res.append(sum % 10);
            carry = sum / 10;
            m--;
            n--;
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        // KeyPoint debug 技巧
        // 1.先缩小范围，add 方法测试通过，后续不用分析，需要多测试几个用例
        // System.out.println(add("1", "2"));

        // 2.bug 在 multiply 主方法中，再去进一步排除
        // 优先进行 codeview，其次是 print，最后是 debug，提高效率

        // 3. 根据输出结果的特征，判断可能出现的 bug
        // 如：1*2=2，但是测试用例输出结果非常大，则说明 x*y+carry 有问题，再去进一步缩小排查范围
        System.out.println(multiply("3", "5"));
    }
}
