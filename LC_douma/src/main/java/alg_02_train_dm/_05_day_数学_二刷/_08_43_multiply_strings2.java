package alg_02_train_dm._05_day_数学_二刷;

/**
 * @Author Wuyj
 * @DateTime 2023-07-12 15:21
 * @Version 1.0
 */
public class _08_43_multiply_strings2 {

    // KeyPoint 方法二 简化字符串操作
    // 乘法规律求解，将中间结果直接存在 res 数组中，减少中间字符串操作
    public String multiply(String num1, String num2) {

        // KeyPoint 规律一 num1 * num2 确定 res 长度 => 记住

        // num1 长度为 m，num2 长度为 n => 确定 res 长度
        // 1. num1 最小值 10^(m-1)，num2 最小值 10^(n-1)
        //    => num1 * num2 = 10^(m+n-2) => 长度 m+n-1
        // 2. num1 最大值 10^m -1，num2 最大值 10^n -1
        //    => num1 * num2 = (10^m -1) * (10^n -1) < 10^(m+n) => 长度 <= m+n
        // 结论： m+n-1 <= res 长度 <= m+n

        // KeyPoint 规律二 结果索引位置 => 记住

        //       0 1 2   索引
        //       2 4 9   i = 2 => 数字 9
        //     * 5 6 4   j = 1 => 数字 6
        // ----------------
        //       9 9 6
        //   1 4 9 4 0
        // ----------------
        //   1 5 9 3 6
        // 1 2 4 5 0 0
        // ----------------
        // 0 1 2 3 4 5  索引
        // 1 4 0 4 3 6
        //       ↑ ↑
        //   个位数：i + j + 1  = 1 + 2 + 1 = 4
        //   进位数：i + j = 1 + 2 = 3

        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m + n];

        // KeyPoint 注意：乘数和被乘数，变量之间对应关系 => 内外 for 循环不要搞混淆了
        // num1 => m => 被乘数 => x
        // num2 => n =>  乘数  => y

        //  0 1 2 3 索引
        //  1 2 4 9  i => 被乘数
        //    5 6 4  j => 乘数
        // --------------------

        // 处理 乘数 的每一位
        for (int i = n - 1; i >= 0; i--) {
            int y = num2.charAt(i) - '0';
            // 处理 被乘数 的每一位
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                // res[i+j+1] 是 i+j+1 位置之前的数值，同样需要累加到 x*y 中
                int sum = res[i + j + 1] + x * y;
                // 存储个位数
                // KeyPoint 运算符是 =，之前的 res[i+j+1]，计算 sum 中已经考虑过了
                res[i + j + 1] = sum % 10;
                // 累加进位值
                // KeyPoint 运算符是 +=，不是简单赋值 =，累加之前的 res[i + j]
                res[i + j] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            // m+n-1 <= res 长度 <= m+n
            // 只有 0 位才能是最高位，其值有可能为 0，故需要判断下
            // 而 1 位，其值必然不为 0，否则 res 长度小于 m+n-1，不满足规律
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
