package alg_02_train_dm._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 17:16
 * @Version 1.0
 */
public class _08_43_multiply_strings1 {

    /*
        43. 字符串相乘
        给定两个以 字符串形式 表示的 非负整数 num1 和 num2，返回 num1 和 num2 的乘积
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
    // 时间复杂度 O(m*n)
    // => 时间复杂度最优，两个数相乘必须遍历'被乘数'和'乘数'的每一位
    // => 但因做了许多字符串相加操作，导致性能比较低下
    public String multiply1(String num1, String num2) {
        // 特判：数字 0
        if (num1.equals("0") || num2.equals("0")) return "0";

        String res = "0";

        int m = num1.length(); // 被乘数
        int n = num2.length(); // 乘数

        // 如：
        //   2 4 9 => 被乘数
        // * 5 6 4 => 乘数
        //--------------------

        // KeyPoint 搞清楚内外层 for 循环，不要混淆了
        // 外层 for 循环，处理 n-1 => 乘数
        // 内层 for 循环，处理 m-1 => 被乘数

        // 1.处理 乘数 的每一位
        for (int i = n - 1; i >= 0; i--) {
            //   2 4 9 => 被乘数
            // *     4 => 乘数
            // 存放：当前乘数 * 被乘数 => 4 * 249
            StringBuilder currAns = new StringBuilder();

            // 根据 i 的位置，补相应 0 的个数
            // 如：i 在 6 位置，则：249 * 6 结果，后面补 1 个 0
            // 如：i 在 4 位置，则：249 * 4 结果，不用补 0 个 0
            for (int zero = n - 1; zero > i; zero--) currAns.append("0");

            // 乘数的当前位值
            int y = num2.charAt(i) - '0';
            // 记录相乘进位
            int carry = 0;
            // 2.处理 被乘数 的每一位
            // 将'被乘数当前每一位'和'乘数当前每一位'进行相乘
            for (int j = m - 1; j >= 0; j--) {
                // KeyPoint 循环遍量 j
                // for 循环中，charAt 一定是变量 j，而不是固定值 m - 1，树立'循环变化'的意识
                // 被乘数的当前位值
                int x = num1.charAt(j) - '0';
                // 乘积结果加上进位
                int product = x * y + carry;
                currAns.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) currAns.append(carry);

            // 将 被乘数每一位 和 乘数 相乘的结果累加 => 249 * 4 => currAns
            // 注意：累加前先反转下 currAns
            // res 初始为 "0"
            res = addStrings(res, currAns.reverse().toString());
        }
        return res;
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
}
