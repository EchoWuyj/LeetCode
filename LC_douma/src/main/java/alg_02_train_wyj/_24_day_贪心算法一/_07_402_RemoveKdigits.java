package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 16:18
 * @Version 1.0
 */
public class _07_402_RemoveKdigits {
    public String removeKdigits(String num, int k) {
        StringBuilder sb = new StringBuilder(num);
        for (int count = 0; count < k; count++) {
            boolean hasDelete = false;
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i - 1) > sb.charAt(i)) {
                    sb.deleteCharAt(i - 1);
                    hasDelete = true;
                    break;
                }
            }
            if (!hasDelete) sb.deleteCharAt(sb.length() - 1);
        }

        int n = sb.length();
        while (n != 0) {
            char c = sb.charAt(0);
            if (c > '0') break;
            sb.deleteCharAt(0);
            n = sb.length();
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {

    }
}
