package alg_02_train_dm._00_day_准备_二刷;

import java.util.Scanner;

/**
 * @Author Wuyj
 * @DateTime 2023-08-09 21:58
 * @Version 1.0
 */

public class _01_HJ12_字符串反转 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 从控制台输入
        String input = scanner.nextLine();
        String res = "";
        int n = input.length();
        for (int i = n - 1; i >= 0; i--) {
            res += input.charAt(i);
        }
        // 输出到控制台
        System.out.println(res);

        // 示例1
        // 输入：abcd
        // 输出：dcba
    }
}
