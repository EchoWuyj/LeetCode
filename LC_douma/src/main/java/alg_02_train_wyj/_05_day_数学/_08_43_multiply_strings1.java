package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 19:18
 * @Version 1.0
 */
public class _08_43_multiply_strings1 {
    public static String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[n + m];

        for (int i = n - 1; i >= 0; i--) {
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int sum = res[i + j + 1] + x * y;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < (m + n); i++) {
            if (i == 0 && res[0] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
