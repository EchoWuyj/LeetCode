package alg_02_train_wyj._05_day_数学;

/**
 * @Author Wuyj
 * @DateTime 2023-04-20 16:21
 * @Version 1.0
 */
public class _06_67_add_binary {

    public String addBinary(String a, String b) {
        int l1 = a.length() - 1;
        int l2 = b.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while (l1 >= 0 || l2 >= 0) {
            int x = l1 >= 0 ? a.charAt(l1) - '0' : 0;
            int y = l2 >= 0 ? b.charAt(l2) - '0' : 0;
            int sum = carry + x + y;
            res.append(sum % 2);
            carry = sum / 2;
            l1--;
            l2--;
        }
        if (carry != 0) res.append(carry);
        return res.reverse().toString();
    }
}
