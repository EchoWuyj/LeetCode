package alg_02_train_wyj._10_day_栈和队列;

/**
 * @Author Wuyj
 * @DateTime 2023-11-20 14:51
 * @Version 1.0
 */
public class _14_151_test {
    public String reverseWords(String s) {
        if (s == null) return s;
        String str1 = trim(s);
        String str2 = reverse(str1.toCharArray(), 0, str1.length() - 1);
        return reverseChar(str2);
    }

    public String trim(String str) {
        int left = 0, right = str.length() - 1;
        while (left <= right && str.charAt(left) == ' ') left++;
        while (left <= right && str.charAt(right) == ' ') right--;
        StringBuilder builder = new StringBuilder();
        while (left <= right) {
            if (str.charAt(left) != ' ') {
                builder.append(str.charAt(left));
            } else {
                if (builder.charAt(builder.length() - 1) != ' ') {
                    builder.append(" ");
                }
            }
            left++;
        }
        return builder.toString();
    }

    public String reverse(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
        return new String(chars);
    }

    public String reverseChar(String str) {
        char[] chars = str.toCharArray();
        int left = 0;
        int n = str.length();
        while (left < n) {
            if (chars[left] != ' ') {
                int right = left;
                while (right < n-1 && chars[right + 1] != ' ') right++;
                reverse(chars, left, right);
                left = right + 1;
            } else {
                left++;
            }
        }
        return new String(chars);
    }
}
