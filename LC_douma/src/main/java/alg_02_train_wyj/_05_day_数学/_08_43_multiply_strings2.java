package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-07-12 19:26
 * @Version 1.0
 */
public class _08_43_multiply_strings2 {

    public String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        int l1 = num1.length() - 1;
        int l2 = num2.length() - 1;

        String res = "0";
        for (int i = l2; i >= 0; i--) {
            StringBuilder curRes = new StringBuilder();
            for (int k = l2; k > i; k--) curRes.append("0");
            int y = num2.charAt(i) - '0';
            int carry = 0;
            for (int j = l1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + carry;
                curRes.append(product % 10);
                carry = product / 10;
            }
            if (carry != 0) curRes.append(carry);
            String cur = curRes.reverse().toString();
            res = add(res, cur);
        }
        return res;
    }

    public String add(String str1, String str2) {
        int l1 = str1.length() - 1;
        int l2 = str2.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? str1.charAt(l1) - '0' : 0;
            int y = l2 >= 0 ? str2.charAt(l2) - '0' : 0;
            int sum = x + y + carry;
            res.append(sum % 10);
            carry = sum / 10;
            l1--;
            l2--;
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }
}
