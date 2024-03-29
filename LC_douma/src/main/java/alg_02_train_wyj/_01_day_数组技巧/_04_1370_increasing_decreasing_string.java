package alg_02_train_wyj._01_day_数组技巧;

/**
 * @Author Wuyj
 * @DateTime 2023-04-16 15:30
 * @Version 1.0
 */
public class _04_1370_increasing_decreasing_string {
    public String sortString(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    sb.append((char) (i + 'a'));
                    count[i]--;
                }
            }

            for (int i = 25; i >= 0; i--) {
                if (count[i] > 0) {
                    sb.append((char) (i + 'a'));
                    count[i]--;
                }
            }
        }
        return sb.toString();
    }
}
