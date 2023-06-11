package alg_02_train_wyj._24_day_贪心算法一;

/**
 * @Author Wuyj
 * @DateTime 2023-04-12 16:18
 * @Version 1.0
 */
public class _07_402_RemoveKdigits {
    public String removeKdigits(String num, int k) {
        StringBuilder builder = new StringBuilder(num);
        for (int count = 0; count < k; count++) {
            boolean hasDeleted = false;
            for (int i = 1; i < builder.length(); i++) {
                if (builder.charAt(i) < builder.charAt(i - 1)) {
                    builder.deleteCharAt(i - 1);
                    hasDeleted = true;
                    break;
                }
            }
            if (!hasDeleted) builder.deleteCharAt(builder.length() - 1);
        }

        int len = builder.length();
        while (len != 0) {
            if (builder.charAt(0) > '0') break;
            builder.deleteCharAt(0);
            len = builder.length();
        }

       return builder.length() == 0 ? "0" : builder.toString();
    }

    public static void main(String[] args) {

    }
}
