package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 17:16
 * @Version 1.0
 */
public class _08_43_multiply_strings {

    /*
        43. 字符串相乘
        给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积
        它们的乘积也表示为字符串形式。
        注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

        提示：
        1 <= num1.length, num2.length <= 200
        num1 和 num2 只能由数字组成。
        num1 和 num2 都不包含任何前导零，除了数字0本身。

        示例 1:
        输入: num1 = "2", num2 = "3"
        输出: "6"

        示例 2:
        输入: num1 = "123", num2 = "456"
        输出: "56088"

     */

    // KeyPoint 方法一 直接模拟 => 通过代码形式模拟乘法运算过程
    // 时间复杂度 O(m*n) => 时间复杂度最优，两个数相乘必须遍历'被乘数'和'乘数'的每一位，
    //                  => 但因做了许多字符串相加操作，导致性能比较低下
    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";

        String ans = "0";
        int m = num1.length();
        int n = num2.length();

        //   2 4 9 => 被乘数指
        // * 5 6 4 => 乘数指
        //--------------------
        // 处理乘数的每一位
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder currAns = new StringBuilder();

            // 对当前位的后面补 0
            for (int zero = n - 1; zero > i; zero--) currAns.append("0");
            // 乘数的当前位值
            int y = num2.charAt(i) - '0';
            // 记录相乘进位
            int carry = 0;

            // 将'被乘数当前每一位'和'乘数当前每一位'进行相乘
            // 处理被乘数的每一位
            for (int j = m - 1; j >= 0; j--) {

                // 被乘数的当前位值
                // KeyPoint for 循环中，charAt 一定是变量 j，而不是固定值 m - 1，树立'循环变化'的意识
                int x = num1.charAt(j) - '0';

                int product = x * y + carry;
                currAns.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) currAns.append(carry);

            // 将被乘数每一位和乘数相乘的结果累加
            // currAns 累加前先反转下
            ans = addStrings(ans, currAns.reverse().toString());
        }
        return ans;
    }

    // 两个字符串相加
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int i1 = num1.length() - 1, i2 = num2.length() - 1;
        int carry = 0;
        while (i1 >= 0 || i2 >= 0) {
            int x = i1 >= 0 ? num1.charAt(i1) - '0' : 0;
            int y = i2 >= 0 ? num2.charAt(i2) - '0' : 0;

            int sum = x + y + carry;
            res.append(sum % 10);
            carry = sum / 10;

            i1--;
            i2--;
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }

    // KeyPoint 方法二 乘法规律求解，将中间结果直接存在 res 数组中，减少中间字符串操作
    public String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length(), n = num2.length();

        // 规律一：num1 * num2 确定 res 长度
        // 1. num1 最小值 10^(m-1)，num2 最小值 10^(n-1)
        //    => num1 * num2 = 10^(m+n-2) => 长度 m+n-1
        // 2. num1 最大值 10^m -1，num2 最大值 10^n -1
        //    => num1 * num2 = (10^m -1) * (10^n -1) < 10^(m+n) => 长度 <= m+n
        // 结论：m+n-1 <= res 长度 <= m+n
        int[] res = new int[m + n];

        // 规律二：结果索引位置
        //      0 1 2   索引
        //      2 4 9   i = 2
        //    * 5 6 4   j = 1
        //----------------
        //      9 9 6
        //  1 4 9 4 0
        //----------------
        //  1 5 9 3 6
        //1 2 4 5 0 0
        //----------------
        //0 1 2 3 4 5  索引
        //1 4 0 4 3 6
        //      ↑ ↑
        //  个位数：i + j + 1  = 1 + 2 + 1 = 4
        //  进位数：i + j = 1 + 2 = 3

        // 处理乘数的每一位
        // KeyPoint 乘数和被乘数，变量之间对应关系
        // num1 m 乘数 x
        // num2 n 被乘数 y
        for (int i = n - 1; i >= 0; i--) {
            int y = num2.charAt(i) - '0';
            // 处理被乘数的每一位
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int sum = res[i + j + 1] + x * y;
                // 存储个位数
                res[i + j + 1] = sum % 10;
                // 累加进位值
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            // 最高位可能是 0，故需要判断下
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
