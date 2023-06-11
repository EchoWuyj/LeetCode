package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 19:18
 * @Version 1.0
 */
public class _08_43_multiply_strings {
    public static String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int len1 = num1.length();
        int len2 = num2.length();
        String res = "";
        for (int i = len2 - 1; i >= 0; i--) {
            int x = num2.charAt(i) - '0';
            StringBuilder curNum = new StringBuilder();
            for (int zero = len2 - 1; zero > i; zero--) curNum.append("0");
            int carry = 0;
            for (int j = len1 - 1; j >= 0; j--) {
                int y = num1.charAt(j) - '0';
                int mul = x * y + carry;
                curNum.append(mul % 10);
                carry = mul / 10;
            }
            if (carry != 0) curNum.append(carry);
            res = addTwoStr(curNum.reverse().toString(), res);
        }
        return res;
    }

    public static String addTwoStr(String num1, String num2) {
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 < 0 ? 0 : num1.charAt(l1) - '0';
            int y = l2 < 0 ? 0 : num2.charAt(l2) - '0';
            int sum = x + y + carry;
            res.append(sum % 10);
            carry = sum / 10;
            l1--;
            l2--;
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String str1 = "123";
        String str2 = "456";
        System.out.println(multiply1(str1, str2));
    }

    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n];
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
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            sb.append(res[i]);
        }
        return sb.toString();
    }
}
