package alg_02_体系班_wyj.class19;

/**
 * @Author Wuyj
 * @DateTime 2023-03-01 20:30
 * @Version 1.0
 */
public class Code02_ConvertToLetterString {
    public static int number(String str) {
        if (str.length() == 0 || str == null) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] str, int index) {
        if (index == str.length) {
            return 1;
        }

        if (str[index] == '0') {
            return 0;
        }

        int ways = process(str, index + 1);
        if ((index + 1) < str.length && ((str[index] - '0') * 10 + str[index + 1] - '0') < 27) {
            ways += process(str, index + 2);
        }

        return ways;
    }

    public static int dp(String str) {
        if (str.length() == 0 || str == null) {
            return 0;
        }
        char[] array = str.toCharArray();
        int n = str.length();
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int index = n - 1; index >= 0; index--) {
            if (array[index] != '0') {
                int ways = dp[index + 1];
                if ((index + 1) < array.length && ((array[index] - '0') * 10 + array[index + 1] - '0') < 27) {
                    ways += dp[index + 2];
                }
                dp[index] = ways;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(number("7210231231232031203123"));
        System.out.println(dp("7210231231232031203123"));
    }
}
