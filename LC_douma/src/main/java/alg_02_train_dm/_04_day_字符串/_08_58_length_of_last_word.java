package alg_02_train_dm._04_day_字符串;

/**
 * @Author Wuyj
 * @DateTime 2023-08-16 12:11
 * @Version 1.0
 */
public class _08_58_length_of_last_word {

    // 从右往左遍历
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') end--;
        if (end < 0) return 0;

        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') start--;

        return end - start;
    }

    public int lengthOfLastWord1(String s) {
        int ans = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            if (s.charAt(start) == ' ') {
                start++;
                end++;
            } else {
                while (end < s.length() && s.charAt(end) != ' ') end++;
                ans = end - start;
                while (end < s.length() && s.charAt(end) == ' ') end++;
                if (end < s.length() && s.charAt(end) != ' ') {
                    start = end;
                }
            }
        }
        return ans;
    }
}
