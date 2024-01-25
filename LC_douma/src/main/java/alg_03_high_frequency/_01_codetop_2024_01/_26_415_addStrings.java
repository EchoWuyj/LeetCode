package alg_03_high_frequency._01_codetop_2024_01;

/**
 * @Author Wuyj
 * @DateTime 2024-01-13 15:18
 * @Version 1.0
 */
public class _26_415_addStrings {
    public String addStrings(String num1, String num2) {
        // 从末尾开始
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();

        // 只要一个没有越界，则执行 while 循环，可以取等 0
        while (l1 >= 0 || l2 >= 0) {
            int x = (l1 >= 0) ? num1.charAt(l1) - '0' : 0;
            int y = (l2 >= 0) ? num2.charAt(l2) - '0' : 0;
            int sum = x + y + carry;
            // 取余追加到 res 中
            res.append(sum % 10);
            // 整除赋值给 carry
            carry = sum / 10;
            // 移动指针，不要忘记了！
            l1--;
            l2--;
        }
        if (carry != 0) res.append(carry);
        // 反转
        res.reverse();
        return res.toString();
    }
}
